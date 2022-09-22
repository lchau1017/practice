package algorithms.dynamicProgramming.commonPatternsInDP

import java.util.*

//https://leetcode.com/explore/learn/card/dynamic-programming/632/common-patterns-in-dp-problems/4112/
class WordBreak {
    private var s: String? = null
    private var wordDict: List<String>? = null
    private lateinit var memo: IntArray
    private fun dp(i: Int): Boolean {
        if (i < 0) return true
        if (memo[i] == -1) {
            for (word in wordDict!!) {
                if (i >= word.length - 1 && dp(i - word.length)) {
                    if (s!!.substring(i - word.length + 1, i + 1) == word) {
                        memo[i] = 1
                        break
                    }
                }
            }
        }
        if (memo[i] == -1) {
            memo[i] = 0
        }
        return memo[i] == 1
    }

    fun wordBreakTopDown(s: String, wordDict: List<String>?): Boolean {
        this.s = s
        this.wordDict = wordDict
        memo = IntArray(s.length)
        Arrays.fill(memo, -1)
        return dp(s.length - 1)
    }

    fun wordBreakBottomUp(s: String, wordDict: List<String>): Boolean {
        val dp = BooleanArray(s.length)
        for (i in 0 until s.length) {
            for (word in wordDict) {
                // Make sure to stay in bounds while checking criteria
                if (i >= word.length - 1 && (i == word.length - 1 || dp[i - word.length])) {
                    if (s.substring(i - word.length + 1, i + 1) == word) {
                        dp[i] = true
                        break
                    }
                }
            }
        }
        return dp[s.length - 1]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            /*
        //https://leetcode.com/problems/word-break/
        Input: s = "applepenapple", wordDict = ["apple","pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        Note that you are allowed to reuse a dictionary word.
         */
            val s = "applepenapple"
            val s1 = "leetcode"
            val s2 = "catsandog"
            val wordDict2: List<String> = ArrayList(java.util.List.of("cats", "dog", "and"))
            val wordDict1: List<String> = ArrayList(java.util.List.of("leet", "code"))
            val wordDict: List<String> = ArrayList(Arrays.asList("apple", "pen"))
            val obj = WordBreak()
            //System.out.println(obj.wordBreakBottomUp(s, wordDict));
            println(obj.wordBreakTopDown(s, wordDict))
            println(
                """
    BFS -> 
    ${wordBreakBFS(s2, wordDict2)}
    """.trimIndent()
            )
        }

        //Approach 3: Using BFS
        /*
    Time: O(n3)
    Space: O(n) -> Queue of at most n size is needed
     */
        fun wordBreakBFS(s: String, wordDict: List<String>?): Boolean {
            val wordDictSet: Set<String> = HashSet(wordDict)
            val queue: Queue<Int> = LinkedList()
            val visited = BooleanArray(s.length)
            queue.add(0)
            while (!queue.isEmpty()) {
                val start = queue.remove()
                if (visited[start]) {
                    continue
                }
                for (end in start + 1..s.length) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end)
                        if (end == s.length) {
                            return true
                        }
                    }
                }
                visited[start] = true
            }
            return false
        }
    }
}