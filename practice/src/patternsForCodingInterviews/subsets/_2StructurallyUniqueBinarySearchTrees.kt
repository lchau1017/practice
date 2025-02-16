package patternsForCodingInterviews.subsets

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744099244_74Unit
object _2StructurallyUniqueBinarySearchTrees {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = findUniqueTrees(2)
        print("Total trees: " + result.size)
    }

    fun findUniqueTrees(n: Int): List<TreeNode?> {
        return if (n <= 0) {
            ArrayList()
        } else findUniqueTreesRecursive(1, n)
    }

    private fun findUniqueTreesRecursive(start: Int, end: Int): List<TreeNode?> {
        val result: MutableList<TreeNode?> = ArrayList()
        // base condition, return 'null' for an empty sub-tree
        // consider n=1, in this case we will have start=end=1, this means we should have
        // only one tree we will have two recursive calls, findUniqueTreesRecursive(1, 0) &
        // (2, 1) both of these should return 'null' for the left and the right child
        if (start > end) {
            result.add(null)
            return result
        }
        for (i in start..end) {
            // making 'i' root of the tree
            val leftSubtrees = findUniqueTreesRecursive(start, i - 1)
            val rightSubtrees = findUniqueTreesRecursive(i + 1, end)
            for (leftTree in leftSubtrees) {
                for (rightTree in rightSubtrees) {
                    val root = TreeNode(i)
                    root.left = leftTree
                    root.right = rightTree
                    result.add(root)
                }
            }
        }
        return result
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}