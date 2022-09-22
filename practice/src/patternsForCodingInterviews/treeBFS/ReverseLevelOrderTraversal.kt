package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743852073_45Unit
object ReverseLevelOrderTraversal {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        val result = traverse(root)
        println("Reverse level order traversal: $result")
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    fun traverse(root: TreeNode?): List<List<Int>> {
        val result: MutableList<List<Int>> = LinkedList()
        if (root == null) return result
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()) {
            val levelSize = queue.size
            val currentLevel: MutableList<Int> = ArrayList(levelSize)
            for (i in 0 until levelSize) {
                val currentNode = queue.poll()
                //add node to the current level
                currentLevel.add(currentNode!!.`val`)
                if (currentNode.left != null) {
                    queue.add(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)
                }
            }
            //append the current level at the beginning
            result.add(0, currentLevel)
        }
        return result
    }
}