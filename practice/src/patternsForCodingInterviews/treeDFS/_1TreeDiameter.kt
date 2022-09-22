package patternsForCodingInterviews.treeDFS

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743963987_59Unit
object _1TreeDiameter {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.left!!.left = TreeNode(4)
        root.right!!.left = TreeNode(5)
        root.right!!.right = TreeNode(6)
        println("Tree Diameter: " + findDiameter(root))
        root.left!!.left = null
        root.right!!.left!!.left = TreeNode(7)
        root.right!!.left!!.right = TreeNode(8)
        root.right!!.right!!.left = TreeNode(9)
        root.right!!.left!!.right!!.left = TreeNode(10)
        root.right!!.right!!.left!!.left = TreeNode(11)
        println("Tree Diameter: " + findDiameter(root))
    }

    private var treeDiameter = 0

    /*
    Time complexity: O(N), traverse each node
    Space complexity: O(N) recursion stack
     */
    fun findDiameter(root: TreeNode?): Int {
        calculateHeight(root)
        return treeDiameter
    }

    private fun calculateHeight(currentNode: TreeNode?): Int {
        if (currentNode == null) return 0
        val leftTreeHeight = calculateHeight(currentNode.left)
        val rightTreeHeight = calculateHeight(currentNode.right)
        //If the current node does not have a left and right subtree, we can not have a path passing through it.
        if (leftTreeHeight != 0 && rightTreeHeight != 0) {
            //Diameter at the current node
            val diameter = leftTreeHeight + rightTreeHeight + 1
            //Update the global tree
            treeDiameter = Math.max(treeDiameter, diameter)
        }
        return Math.max(leftTreeHeight, rightTreeHeight) + 1
    }
}