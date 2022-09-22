package patternsForCodingInterviews.treeBFS

import java.util.*

object MaximumDepthOfABinaryTree {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        println("Tree Maximum Depth: " + findMaxDepth(root))
        root.left!!.left = TreeNode(9)
        root.right!!.left!!.left = TreeNode(11)
        println("Tree Maximum Depth: " + findMaxDepth(root))
    }

    /*
    Find the MAXIMUM Depth
    Time: O(N)
    Space O(N)
     */
    fun findMaxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        var maximumDepth = 0
        val queue: Queue<TreeNode?> = LinkedList()
        queue.add(root)
        while (!queue.isEmpty()) {
            val levelSize = queue.size
            maximumDepth++
            for (i in 0 until levelSize) {
                val currNode = queue.poll()
                if (currNode!!.left != null) {
                    queue.add(currNode.left)
                }
                if (currNode.right != null) {
                    queue.add(currNode.right)
                }
            }
        }
        return maximumDepth
    }
}