package patternsForCodingInterviews.treeDFS

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743939828_56Unit
object SumOfPathNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(1)
        root.left = TreeNode(0)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(1)
        root.right!!.left = TreeNode(6)
        root.right!!.right = TreeNode(5)
        println(
            "Total Sum of Path Numbers: " +
                    findSumOfPathNumbers(root)
        )
        println(findRootToLeafPathNumbersStr(root))
    }

    fun findSumOfPathNumbers(root: TreeNode?): Int {
        return findRootToLeafPathNumbers(root, 0)
    }

    /*
    Time complexity: O(N) where N is the total number of nodes in the tree.
    Space complexity: O(N) Space for recursion stack.
     */
    private fun findRootToLeafPathNumbers(current: TreeNode?, pathSum: Int): Int {
        var pathSum = pathSum
        if (current == null) return 0
        //See below for alternative solution by using StringBuilder
        pathSum = 10 * pathSum + current.`val`
        return if (current.left == null && current.right == null) pathSum else findRootToLeafPathNumbers(
            current.left,
            pathSum
        )
                + findRootToLeafPathNumbers(current.right, pathSum)
        //Traverse left and right
    }

    //Using StringBuilder
    private fun findRootToLeafPathNumbersStr(root: TreeNode): Int {
        val result = 0
        return helper(root, StringBuilder(), result)
    }

    private fun helper(
        node: TreeNode?,
        path: StringBuilder,
        result: Int
    ): Int {
        var result = result
        if (node == null) return 0
        //Add to the path
        path.append(node.`val`)
        //It is a leaf node
        if (node.left == null && node.right == null) {
            return path.toString().toInt()
        }
        result = helper(node.left, path, result)
        //Remove the latest String number format
        path.deleteCharAt(path.length - 1)
        result += helper(node.right, path, result)
        //Return total of that path including left and right paths
        return result
    }
}