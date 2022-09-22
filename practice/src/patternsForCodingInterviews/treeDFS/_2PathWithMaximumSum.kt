package patternsForCodingInterviews.treeDFS

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743967658_60Unit
object _2PathWithMaximumSum {
    @JvmStatic
    fun main(args: Array<String>) {
        var root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        println("Maximum Path Sum: " + findMaximumPathSum(root))
        root.left!!.left = TreeNode(1)
        root.left!!.right = TreeNode(3)
        root.right!!.left = TreeNode(5)
        root.right!!.right = TreeNode(6)
        root.right!!.left!!.left = TreeNode(7)
        root.right!!.left!!.right = TreeNode(8)
        root.right!!.right!!.left = TreeNode(9)
        println("Maximum Path Sum: " + findMaximumPathSum(root))
        root = TreeNode(-1)
        root.left = TreeNode(-3)
        println("Maximum Path Sum: " + findMaximumPathSum(root))
    }

    private var globalMaximumSum = 0
    fun findMaximumPathSum(root: TreeNode?): Int {
        globalMaximumSum = Int.MAX_VALUE
        findMaximumPathSumRecursive(root)
        return globalMaximumSum
    }

    private fun findMaximumPathSumRecursive(currentNode: TreeNode?): Int {
        if (currentNode == null) return 0
        var maxSumFromLeft = findMaximumPathSumRecursive(currentNode.left)
        var maxSumFromRight = findMaximumPathSumRecursive(currentNode.right)
        //Ignore paths with negative sums.
        maxSumFromLeft = Math.max(maxSumFromLeft, 0)
        maxSumFromRight = Math.max(maxSumFromRight, 0)
        //max path sum at the current node
        val localMaximumSum = maxSumFromLeft + maxSumFromRight + currentNode.`val`
        //Update the global maximum
        globalMaximumSum = Math.max(globalMaximumSum, localMaximumSum)
        return Math.max(maxSumFromLeft, maxSumFromRight) + currentNode.`val`
    }
}