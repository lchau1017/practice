package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743873257_48Unit
object MinimumDepthOfABinaryTree {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        println(findDepth(root))
        root.left!!.left = TreeNode(9)
        root.right!!.left!!.left = TreeNode(11)
        println(findDepth(root))
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    fun findDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val queue: Queue<TreeNode?> = LinkedList()
        queue.add(root)
        var minimumDepth = 0
        while (!queue.isEmpty()) {
            minimumDepth++
            val levelSize = queue.size
            for (i in 0 until levelSize) {
                val currentNode = queue.poll()
                //Check if this is a leaf node
                if (currentNode!!.left != null && currentNode.right != null) return minimumDepth
                if (currentNode.left != null) queue.add(currentNode.left)
                if (currentNode.right != null) queue.add(currentNode.right)
            }
        }
        return minimumDepth
    }
}