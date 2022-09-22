package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541068682_8Unit
object _3SmallestWindowContainingSubstring {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findSubstring("aabdec", "abc"))
        println(findSubstring("aabdec", "abac"))
        println(findSubstring("abdbca", "abc"))
        println(findSubstring("adcad", "abc"))
    }

    /*
    Time: O(N + M)
    Space: O(M)
     */
    fun findSubstring(str: String, pattern: String): String {
        var windowStart = 0
        var matched = 0
        var minLength = str.length + 1
        var subStrStart = 0
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in pattern.toCharArray()) charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in 0 until str.length) {
            val rightChar = str[windowEnd]
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap[rightChar] = charFrequencyMap[rightChar]!! - 1
                if (charFrequencyMap[rightChar]!! >= 0) // count every matching of a character
                    matched++
            }
            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length) {
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1
                    subStrStart = windowStart
                }
                val leftChar = str[windowStart++]
                if (charFrequencyMap.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll
                    // decrement the matched count only when a useful occurrence of a matched
                    // character is going out of the window
                    if (charFrequencyMap[leftChar] == 0) matched--
                    charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! + 1
                }
            }
        }
        return if (minLength > str.length) "" else str.substring(subStrStart, subStrStart + minLength)
    }
}