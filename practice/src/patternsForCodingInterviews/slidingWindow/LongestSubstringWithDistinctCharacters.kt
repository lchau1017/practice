package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541027921_3Unit
object LongestSubstringWithDistinctCharacters {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Length of the longest substring: "
                    + findLength("aabccbb")
        )
        println(
            "Length of the longest substring: "
                    + findLength("abbbb")
        )
        println(
            "Length of the longest substring: "
                    + findLength("abccde")
        )
    }

    fun findLength(str: String): Int {
        var windowStart = 0
        var maxLength = 0
        val charIndexMap: MutableMap<Char, Int> = HashMap()
        //Try to extend the range [windowStart, windowEnd]
        for (windowEnd in 0 until str.length) {
            val rightChar = str[windowEnd]
            // if the map already contains the 'rightChar', shrink the window from the
            // beginning so that we have only one occurrence of 'rightChar'
            if (charIndexMap.containsKey(rightChar)) {
                // this is tricky; in the current window, we will not have any 'rightChar' after
                // its previous index and if 'windowStart' is already ahead of the last index of
                // 'rightChar', we'll keep 'windowStart'
                windowStart = Math.max( //Reduce the window
                    windowStart, charIndexMap[rightChar]!! + 1
                )
            }
            //Insert the 'rightChar' into the map
            charIndexMap[rightChar] = windowEnd
            //remember the maximum length so far
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }
}