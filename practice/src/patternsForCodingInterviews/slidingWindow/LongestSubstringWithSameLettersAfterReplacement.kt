package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541037657_4Unit
object LongestSubstringWithSameLettersAfterReplacement {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findLength("aabccbb", 2))
        //System.out.println(findLength("abbcb", 1));
        //System.out.println(findLength("abccde", 1));
    }

    fun findLength(str: String, k: Int): Int {
        var windowStart = 0
        var maxLength = 0
        var maxRepeatLetterCount = 0
        val letterFrequencyMap: MutableMap<Char, Int> = HashMap()
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in 0 until str.length) {
            val rightChar = str[windowEnd]
            letterFrequencyMap[rightChar] = letterFrequencyMap.getOrDefault(rightChar, 0) + 1
            // we don't need to place the maxRepeatLetterCount under the below 'if', see the
            // explanation in the 'Solution' section above.
            maxRepeatLetterCount = Math.max(
                maxRepeatLetterCount,
                letterFrequencyMap[rightChar]!!
            )
            // current window size is from windowStart to windowEnd, overall we have a letter
            // which is repeating 'maxRepeatLetterCount' times, this means we can have a window
            // which has one letter repeating 'maxRepeatLetterCount' times and the remaining
            // letters we should replace. If the remaining letters are more than 'k', it is the
            // time to shrink the window as we are not allowed to replace more than 'k' letters
            if (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
                val leftChar = str[windowStart]
                letterFrequencyMap[leftChar] = letterFrequencyMap[leftChar]!! - 1
                windowStart++
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }
}