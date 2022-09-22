package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743880609_49Unit
object LevelOrderSuccessor {
    /*
    Time: O(N)
    Space: O(N)
     */
    fun findSuccessor(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()) {
            val currentNode = queue.poll()
            if (currentNode!!.left != null) {
                queue.add(currentNode.left)
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right)
            }
            if (currentNode.`val` == key) {
                break
            }
        }
        return queue.peek()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.left!!.left = TreeNode(4)
        root.left!!.right = TreeNode(5)
        var result = findSuccessor(root, 3)
        if (result != null) println(result.`val`.toString() + " ")
        root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        result = findSuccessor(root, 9)
        if (result != null) println(result.`val`.toString() + " ")
        result = findSuccessor(root, 12)
        if (result != null) println(result.`val`.toString() + " ")
    }
}