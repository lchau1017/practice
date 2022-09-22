package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541063154_7Unit
object _2StringAnagrams {
    @JvmStatic
    fun main(args: Array<String>) {
        println(findStringAnagrams("ppqp", "pq"))
        println(findStringAnagrams("abbcabc", "abc"))
        println(findStringAnagrams("abab", "ab"))
    }

    fun findStringAnagrams(str: String, pattern: String): List<Int> {
        var windowStart = 0
        var matched = 0
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in pattern.toCharArray()) {
            charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1
        }
        val resultIndices: MutableList<Int> = ArrayList()
        for (windowEnd in 0 until str.length) {
            val rightChar = str[windowEnd]
            //decrement the frequency of the matched character
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap[rightChar] = charFrequencyMap[rightChar]!! - 1
                if (charFrequencyMap[rightChar] == 0) {
                    matched++
                }
            }
            if (matched == charFrequencyMap.size) { //We have found an anagram
                resultIndices.add(windowStart)
            }
            if (windowEnd >= pattern.length - 1) { //Shrink the window
                //Decide what to do with the element after we shrank the window
                val leftChar = str[windowStart]
                windowStart++ //Increment windows start to shrink the window
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap[leftChar] == 0) {
                        matched-- // before putting the character back, decrement the matched count
                    }
                    charFrequencyMap[rightChar] = charFrequencyMap[leftChar]!! + 1
                }
            }
        }
        return resultIndices
    }
}