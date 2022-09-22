package patternsForCodingInterviews.twoPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743506829_10Unit
object _2ComparingStringsContainingBackspaces {
    @JvmStatic
    fun main(args: Array<String>) {
        println(compare("xy#z", "xzz#"))
        println(compare("xy#z", "xyz#"))
        println(compare("xp#", "xyz##"))
        println(compare("xywrrmp", "xywrrmu#p"))
    }

    fun compare(str1: String, str2: String): Boolean {
        //Use  two pointer Approach to compare the strings
        var index1 = str1.length - 1
        var index2 = str2.length - 1
        while (index1 >= 0 || index2 >= 0) {
            val i1 = getNextValidCharIndex(str1, index1)
            val i2 = getNextValidCharIndex(str2, index2)
            if (i1 < 0 && i2 < 0) { //Reach the end of both strings
                return true
            }
            if (i1 < 0 || i2 < 0) { // reached the end of one of the strings
                return false
            }
            if (str1[i1] != str1[i2]) {
                return false
            }
            index1 = i1 - 1
            index2 = i2 - 1
        }
        return true
    }

    private fun getNextValidCharIndex(str: String, index: Int): Int {
        var index = index
        var backspaceCount = 0
        while (index >= 0) {
            if (str[index] == '#') { //Found backspace character
                backspaceCount++
            } else if (backspaceCount > 0) { //A non-backspace character
                backspaceCount--
            } else {
                break
            }
            index--
        }
        return index
    }
}