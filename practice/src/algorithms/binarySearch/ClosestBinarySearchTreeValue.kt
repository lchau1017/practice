package algorithms.binarySearch

//https://leetcode.com/explore/learn/card/binary-search/136/template-analysis/1028/
object ClosestBinarySearchTreeValue {
    @JvmStatic
    fun main(args: Array<String>) {
        val target = 3.714286
        val four = TreeNode(4)
        four.left = TreeNode(2)
        four.right = TreeNode(5)
        four.left!!.left = TreeNode(1)
        four.left!!.right = TreeNode(3)
        println(closestValue(four, 2.0))
    }

    //Binary Search
    fun closestValue(root: TreeNode?, target: Double): Int {
        var root = root
        var `val`: Int
        var closest = root!!.`val`
        while (root != null) {
            `val` = root.`val`
            closest = if (Math.abs(`val` - target) < Math.abs(closest - target)) `val` else closest
            root = if (target < root.`val`) root.left else root.right
        }
        return closest
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}