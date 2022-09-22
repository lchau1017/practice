package algorithms.backtracking.mustKnow

//https://leetcode.com/problems/palindrome-partitioning/
class Palindrome {
    //Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/
    /*
    Time: O(N * 2n), O(N) time to generate substring and determine if it is a palindrome or not.
    Space: O(N)
     */
    fun partition(s: String): List<List<String>> {
        val output: MutableList<List<String>> = ArrayList()
        backtrack(output, ArrayList(), s, 0)
        return output
    }

    private fun backtrack(
        output: MutableList<List<String>>,
        currList: MutableList<String>,
        s: String,
        start: Int
    ) {
        if (start == s.length) {
            //Find a solution
            output.add(ArrayList(currList))
        } else {
            //Try with these candidates
            for (i in start until s.length) {
                if (isPalindrome(s, start, i)) {
                    currList.add(s.substring(start, i + 1))
                    //Next candidate
                    backtrack(output, currList, s, i + 1)
                    //Backtrack
                    currList.removeAt(currList.size - 1)
                }
            }
        }
    }

    private fun isPalindrome(s: String, low: Int, high: Int): Boolean {
        var low = low
        var high = high
        while (low < high) {
            if (s[low++] != s[high--]) return false
        }
        return true
    }

    companion object {
        /*
    Given a string s, partition s such that every substring of the partition is a palindrome.
    Return all possible palindrome partitioning of s.
    A palindrome string is a string that reads the same backward as forward.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
     */
        @JvmStatic
        fun main(args: Array<String>) {
            val s = "aab"
            val obj = Palindrome()
            val res = obj.partition(s)
            for (l in res) {
                println(l)
            }
        }
    }
}