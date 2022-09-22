package patternsForCodingInterviews.multiThreaded

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62bcedcfddd2aUnit
object InvertBinaryTree {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(10)
        root.left = TreeNode(4)
        root.left!!.left = TreeNode(1)
        root.right = TreeNode(15)
        root.right!!.left = TreeNode(14)
        root.right!!.right = TreeNode(19)
        root.right!!.right!!.right = TreeNode(20)
        invertTree(root)
        println(root.right!!.`val` == 4)
        println(root.left!!.`val` == 15)
        println(root.left!!.right!!.`val` == 14)
        println(root.left!!.left!!.`val` == 19)
        println(root.left!!.left!!.left!!.`val` == 20)
    }

    /*
    MULTITHREADED

     */
    fun invertTree(root: TreeNode?): TreeNode? {
        val numThreads = Runtime.getRuntime().availableProcessors()
        return invertTreeMultiThreaded(root, numThreads)
    }

    private fun invertTreeMultiThreaded(root: TreeNode?, numThreads: Int): TreeNode? {
        if (root == null) return null
        // invert the current node
        val temp = root.left
        root.left = root.right
        root.right = temp
        if (numThreads > 0) {
            // spawn a separate thread to invert the left sub-tree
            val t1 = Thread { invertTreeMultiThreaded(root.left, numThreads / 2) }
            t1.start()
            // invert the right sub-tree in the same thread
            invertTreeMultiThreaded(root.right, numThreads / 2)
            try {
                t1.join() // wait for the thread inverting the left subtree
            } catch (e: InterruptedException) {
                println(e)
            }
        } else {
            invertTreeMultiThreaded(root.left, 0)
            invertTreeMultiThreaded(root.right, 0)
        }
        return root
    }

    /*
    Time: O(N)
    Space O(H)
     */
    fun invertTreeNonConcurrency(root: TreeNode?): TreeNode? {
        if (root == null) return null
        //invert children of the current node
        val temp = root.left
        root.left = root.right
        root.right = temp
        //Invert left and right
        invertTree(root.left)
        invertTree(root.right)
        return root
    }
}