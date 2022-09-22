package patternsForCodingInterviews.multiThreaded

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62bcdae955936Unit
class SameTree {
    /*
    MULTITHREADED
     */
    //Declaring a variable volatile thus
    // guarantees the visibility for other threads of writes to that variable.
    @Volatile
    private var isSame //update the value concurrently
            = false

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        isSame = true
        val numThreads = Runtime.getRuntime().availableProcessors()
        return isSameTreeMultiThreaded(p, q, numThreads)
    }

    private fun isSameTreeMultiThreaded(p: TreeNode?, q: TreeNode?, numThreads: Int): Boolean {
        if (p == null && q == null) return true
        if (q == null || p == null) return false
        if (p.`val` != q.`val`) return false
        // if we can start more threads, we will spawn a new thread to check the
        // right subtree, otherwise we will do everything in the current thread
        if (numThreads > 0) {
            // spawn a separate thread for checking the right sub-tree
            val t1 = Thread { isSame = isSame and isSameTreeMultiThreaded(p.right, q.right, numThreads / 2) }
            t1.start()
            // check the left sub-tree in the current thread
            isSame = isSame and isSameTreeMultiThreaded(p.left, q.left, numThreads / 2)
            try {
                t1.join() //Wait for the thread checking the right subtree
            } catch (e: InterruptedException) {
                println(e)
            }
        } else { //do everything in the current thread
            isSame = isSame and (isSameTreeMultiThreaded(p.right, q.right, 0)
                    && isSameTreeMultiThreaded(p.left, q.left, 0))
        }
        return isSame
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val p = TreeNode(10)
            p.left = TreeNode(4)
            p.left!!.left = TreeNode(1)
            p.right = TreeNode(15)
            p.right!!.left = TreeNode(14)
            val q = TreeNode(10)
            q.left = TreeNode(4)
            q.left!!.left = TreeNode(1)
            q.right = TreeNode(15)
            q.right!!.left = TreeNode(14)
            val sameTree = SameTree()
            println(sameTree.isSameTree(p, q))
            q.right!!.right = TreeNode(20)
            println(sameTree.isSameTree(p, q))
            q.right!!.right = TreeNode(20)
            p.left!!.`val` = 9
            println(sameTree.isSameTree(p, q))
        }

        /*
    Non concurrency
    Time: O(min(M, N) where M and N are the number of nodes in the given trees respectively.
    Taking minimum M and N since as soon as we see difference, we do not check the remaining trees.
    Space: O(N) for the recusion stack
     */
        fun isSameTreeNonConcurrency(p: TreeNode?, q: TreeNode?): Boolean {
            // p and q are both null
            if (p == null && q == null) return true
            // one of p and q is null
            if (q == null || p == null) return false
            // one of p and q has different value
            return if (p.`val` != q.`val`) false else isSameTreeNonConcurrency(p.right, q.right) &&
                    isSameTreeNonConcurrency(p.left, q.left)

            // check left and right subtree recursively
        }
    }
}