package patternsForCodingInterviews.treeBFS

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743859019_46Unit
object ZigzagTraversal {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(9)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        root.right!!.left!!.left = TreeNode(20)
        root.right!!.left!!.right = TreeNode(17)
        val result = traverse(root)
        println(result)
    }

    /*
    Time: O(N)
    Space: O(N
     */
    fun traverse(root: TreeNode?): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        if (root == null) return result
        val queue: Queue<TreeNode?> = LinkedList()
        queue.offer(root)
        var leftToRight = true
        while (!queue.isEmpty()) {
            val levelSize = queue.size
            val currentLevel: MutableList<Int> = LinkedList()
            // LinkedList<Integer> currentLevel = new LinkedList<>();
            for (i in 0 until levelSize) {
                val currentNode = queue.poll()
                //Add the node to the current Level
                if (leftToRight) {
                    currentLevel.add(currentNode!!.`val`)
                    // currentLevel.addLast(current.val);
                } else {
                    currentLevel.add(0, currentNode!!.`val`)
                    // currentLevel.addFirst(current.val);
                }
                //Insert children
                if (currentNode.left != null) {
                    queue.offer(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right)
                }
            }
            result.add(currentLevel)
            leftToRight = !leftToRight
        }
        return result
    }
}