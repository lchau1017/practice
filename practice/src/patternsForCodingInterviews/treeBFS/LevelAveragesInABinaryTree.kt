package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743864804_47Unit
object LevelAveragesInABinaryTree {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.left!!.right = TreeNode(2)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        val result = findLevelAverages(root)
        print("Level averages are: $result")
    }

    /*
    Time: O(N)
    Space: O(N)
    Follow up: Find the largest value on each level of a binary Tree
     */
    fun findLevelAverages(root: TreeNode?): List<Double> {
        val result: MutableList<Double> = ArrayList()
        if (root == null) return result
        //int maxValue = Integer.MIN_VALUE;
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()) {
            val levelSize = queue.size
            var levelSum = 0.0
            for (i in 0 until levelSize) {
                val currentNode = queue.poll()
                // maxValue = Math.max(maxValue, currentNode.val);
                levelSum += currentNode!!.`val`.toDouble()
                if (currentNode.left != null) {
                    queue.add(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)
                }
            }
            result.add(levelSum / levelSize)
        }
        //return maxValue
        return result
    }
}