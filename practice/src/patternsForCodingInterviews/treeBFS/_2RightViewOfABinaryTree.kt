package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743902403_52Unit
object _2RightViewOfABinaryTree {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        root.left!!.left!!.left = TreeNode(3)
        val result = traverse(root)
        for (node in result) {
            print(node!!.`val`.toString() + " ")
        }
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    fun traverse(root: TreeNode?): List<TreeNode?> {
        val result: MutableList<TreeNode?> = ArrayList()
        if (root == null) return result
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()) {
            val levelSize = queue.size
            for (i in 0 until levelSize) {
                val currentNode = queue.poll()
                //If this is the last node from that level, add it to the result
                if (i == levelSize - 1) {
                    result.add(currentNode)
                }
                if (currentNode!!.left != null) {
                    queue.add(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)
                }
            }
        }
        return result
    }
}