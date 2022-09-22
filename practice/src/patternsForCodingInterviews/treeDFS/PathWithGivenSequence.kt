package patternsForCodingInterviews.treeDFS

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743947562_57Unit
object PathWithGivenSequence {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(1)
        root.left = TreeNode(0)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(1)
        root.right!!.left = TreeNode(6)
        root.right!!.right = TreeNode(5)
        println(
            "Tree has path sequence: " +
                    findPath(root, intArrayOf(1, 1, 6))
        )
    }

    /*
    Time complexity: O(N) where N is the number of nodes in the tree.
    Space complexity: O(N) to store the recursion stack.
     */
    fun findPath(root: TreeNode?, sequence: IntArray): Boolean {
        return if (root == null) sequence.size == 0 else findPathRecursive(root, sequence, 0)
    }

    private fun findPathRecursive(currentNode: TreeNode?, sequence: IntArray, sequenceIndex: Int): Boolean {
        if (currentNode == null) return false
        if (sequenceIndex >= sequence.size || currentNode.`val` != sequence[sequenceIndex]) {
            return false
        }
        return if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.size - 1) {
            true
        } else findPathRecursive(
            currentNode.left,
            sequence,
            sequenceIndex + 1
        )
                || findPathRecursive(currentNode.right, sequence, sequenceIndex + 1)
    }
}