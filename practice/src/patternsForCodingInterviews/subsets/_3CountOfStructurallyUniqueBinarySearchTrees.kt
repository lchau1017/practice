package patternsForCodingInterviews.subsets

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744103124_75Unit
class _3CountOfStructurallyUniqueBinarySearchTrees {
    //Memoization
    var map: MutableMap<Int, Int> = HashMap()

    /*
    Time: O(n²)
    Space: O(n)
     */
    fun countTrees(n: Int): Int {
        if (map.containsKey(n)) {
            return map[n]!!
        }
        if (n <= 1) return 1
        var count = 0
        for (i in 1..n) {
            // making 'i' root of the tree
            val countOfLeftSubtrees = countTrees(i - 1)
            val countOfRightSubtrees = countTrees(n - i)
            count += countOfLeftSubtrees * countOfRightSubtrees
        }
        map[n] = count
        return count
    }

    internal class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ct = _3CountOfStructurallyUniqueBinarySearchTrees()
            val count = ct.countTrees(3)
            print("Total trees: \n$count")
        }
    }
}