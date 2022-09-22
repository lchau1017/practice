package patternsForCodingInterviews.treeDFS

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743930963_55Unit
object AllPathsForASum {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(12)
        root.left = TreeNode(7)
        root.right = TreeNode(1)
        root.left!!.left = TreeNode(4)
        root.right!!.left = TreeNode(10)
        root.right!!.right = TreeNode(5)
        val sum = 23
        val result = findPaths(root, sum)
        println("Tree paths with sum $sum: $result")
    }

    /*
    Time complexity: O(N²), N total number in the tree.
    We traverse each node once.
    We might have to store its path: O(N)
    Space complexity: O(N)
     */
    fun findPaths(root: TreeNode?, sum: Int): List<List<Int>> {
        val allPaths: MutableList<List<Int>> = ArrayList()
        val currentPath: MutableList<Int> = ArrayList()
        findPathsRecursive(root, sum, currentPath, allPaths)
        return allPaths
    }

    private fun findPathsRecursive(
        currentNode: TreeNode?, sum: Int,
        currentPath: MutableList<Int>,
        allPaths: MutableList<List<Int>>
    ) {
        if (currentNode == null) return
        //Add the current node to the path
        currentPath.add(currentNode.`val`)
        if (currentNode.`val` == sum && currentNode.left == null && currentNode.right == null) {
            allPaths.add(ArrayList(currentPath))
        } else {
            //Left side
            findPathsRecursive(
                currentNode.left,
                sum - currentNode.`val`,
                currentPath,
                allPaths
            )
            //Right side
            findPathsRecursive(
                currentNode.right,
                sum - currentNode.`val`,
                currentPath,
                allPaths
            )
        }
        //Remove to backtrack
        currentPath.removeAt(currentPath.size - 1)
    }
}