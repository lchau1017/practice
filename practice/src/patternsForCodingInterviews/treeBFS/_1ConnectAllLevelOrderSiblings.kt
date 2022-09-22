package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743897497_51Unit
object _1ConnectAllLevelOrderSiblings {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        connect(root)
        root.printTree()
    }

    fun connect(root: TreeNode?) {
        if (root == null) return
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        var currentNode: TreeNode? = null
        var previousNode: TreeNode? = null
        while (!queue.isEmpty()) {
            currentNode = queue.poll()
            if (previousNode != null) {
                previousNode.next = currentNode
            }
            previousNode = currentNode
            if (currentNode!!.left != null) {
                queue.offer(currentNode.left)
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right)
            }
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode?
        var right: TreeNode?
        var next: TreeNode? = null

        init {
            right = next
            left = right
        }

        fun printTree() {
            var current: TreeNode? = this
            while (current != null) {
                print(current.`val`.toString() + " ")
                current = current.next
            }
        }
    }
}