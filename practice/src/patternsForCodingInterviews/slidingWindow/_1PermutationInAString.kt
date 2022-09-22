package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541055153_6Unit
object _1PermutationInAString {
    @JvmStatic
    fun main(args: Array<String>) {
        /*
        System.out.println("Permutation exist: "
                + findPermutation("oidbcaf", "abc"));



        System.out.println(findPermutation("odicf", "dc"));
        System.out.println(findPermutation("ocidf", "dc"));


        System.out.println("Permutation exist: "
                + findPermutation("bcdxabcdy", "bcdyabcdx"));

         */
        //System.out.println(findPermutation("aaacb", "abc"));
        println(findPermutation("acaab", "abc"))
    }

    /*
    Time: O(N + M) N: number of characters in the input and pattern respectively.
    Space: O(M) hashMap
     */
    fun findPermutation(str: String, pattern: String): Boolean {
        var windowStart = 0
        var matched = 0
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in pattern.toCharArray()) charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1

        // our goal is to match all the characters from the 'charFrequencyMap' with the
        // current window try to extend the range [windowStart, windowEnd]
        for (windowEnd in 0 until str.length) {
            val rightChar = str[windowEnd]
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched characte
                charFrequencyMap[rightChar] = charFrequencyMap[rightChar]!! - 1
                if (charFrequencyMap[rightChar] == 0) { // character is completely matched
                    matched++
                }
            }
            if (matched == charFrequencyMap.size) {
                return true
            }
            //Shrink window by one character
            if (windowEnd >= pattern.length - 1) {
                val leftChar = str[windowStart++]
                //Check if permutation
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap[leftChar] == 0) {
                        matched-- // before putting the char back, decrement the matched count
                    }
                    // put the character back for matching
                    charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! + 1
                }
            }
        }
        return false
    }
}