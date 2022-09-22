package patternsForCodingInterviews.treeDFS

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743923908_54Unit
object BinaryTreePathSum {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        //System.out.println("Tree has path: " + hasPath(root, 23));
        println("Tree has path: " + hasPath(root, 16))
    }

    //Recursive
    /*
    Time complexity: O(N), where N is the total number of nodes in the tree.
    Space complexity: O(N), space used to store the recursion stack.
     */
    fun hasPath(root: TreeNode?, sum: Int): Boolean {
        if (root == null) return false
        return if (root.`val` == sum && root.left == null && root.right == null) true else hasPath(
            root.left,
            sum - root.`val`
        )
                || hasPath(root.right, sum - root.`val`)
    }
}