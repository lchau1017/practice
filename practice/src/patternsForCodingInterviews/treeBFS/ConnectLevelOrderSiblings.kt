package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743890075_50Unit
object ConnectLevelOrderSiblings {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        connect(root)
        println("Level order traversal using 'next' pointer: ")
        root.printLevelOrder()
    }

    fun connect(root: TreeNode?) {
        if (root == null) return
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()) {
            var previous: TreeNode? = null
            val levelSize = queue.size
            //connect all nodes for this level
            for (i in 0 until levelSize) {
                val currentNode = queue.poll()
                if (previous != null) {
                    previous.next = currentNode
                }
                previous = currentNode
                if (currentNode!!.left != null) {
                    queue.add(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)
                }
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
            //Note
            /*
            int s;
            int t;
            int i = s = t = 1;
            System.out.println("i : " + i + ", t : " + t + ", s : " + s);
            Output: i : 1, t : 1, s : 1
             */
        }

        //Level order traversal using 'next' pointer
        fun printLevelOrder() {
            var nextLevelRoot: TreeNode? = this
            while (nextLevelRoot != null) {
                var current = nextLevelRoot
                nextLevelRoot = null
                while (current != null) {
                    print(current.`val`.toString() + " ")
                    if (nextLevelRoot == null) {
                        if (current.left != null) {
                            nextLevelRoot = current.left
                        } else if (current.right != null) {
                            nextLevelRoot = current.right
                        }
                    }
                    current = current.next
                }
                println()
            }
        }
    }
}