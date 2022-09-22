package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541009794_1Unit
object LongestSubstringWithKDistinctCharacters {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Length of the longest substring: "
                    + findLength("araaci", 2)
        )
        println(
            "Length of the longest substring: "
                    + findLength("araaci", 1)
        )
        println(
            "Length of the longest substring: "
                    + findLength("cbbebi", 3)
        )
    }

    /*
    Given a string, find the length of the
    longest substring in it with no more than K distinct characters.
     */
    fun findLength(str: String, k: Int): Int {
        require((str == null || str.length == 0 || str.length<k))
        var windowStart = 0
        var maxLength = 0
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        // in the following loop we'll try to extend the range
        // [windowStart, windowEnd]
        for (windowEnd in 0 until str.length) {
            val rightChar = str[windowEnd]
            charFrequencyMap[rightChar] = charFrequencyMap.getOrDefault(rightChar, 0) + 1
            // shrink the sliding window, until we are left with 'k'
            // distinct characters in the frequency map
            while (charFrequencyMap.size > k) {
                val leftChar = str[windowStart]
                charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! - 1
                if (charFrequencyMap[leftChar] == 0) {
                    charFrequencyMap.remove(leftChar)
                }
                windowStart++ //Shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }
}