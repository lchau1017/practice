package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743844952_44Unit
object BinaryTreeLevelOrderTraversal {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        val result = traverse(root)
        println(result)
    }

    /*
    Time: O(N), we traverse each node once.
    Space: O(N) -> caused by the Queue
     */
    fun traverse(root: TreeNode?): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        if (root == null) return result
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()) {
            val levelSize = queue.size
            val currentLevel: MutableList<Int> = ArrayList(levelSize)
            for (i in 0 until levelSize) {
                val currentNode = queue.poll()
                //Add the node to the current level
                currentLevel.add(currentNode!!.`val`)
                //Insert children
                if (currentNode.left != null) {
                    queue.offer(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right)
                }
            }
            result.add(currentLevel)
        }
        return result
    }
}