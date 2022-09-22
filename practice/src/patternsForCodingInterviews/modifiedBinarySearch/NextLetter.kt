package patternsForCodingInterviews.modifiedBinarySearch

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744148867_79Unit
object NextLetter {
    @JvmStatic
    fun main(args: Array<String>) {
        println(searchNextLetter(charArrayOf('a', 'c', 'f', 'h'), 'f'))
        println(searchNextLetter(charArrayOf('a', 'c', 'f', 'h'), 'b'))
        println(searchNextLetter(charArrayOf('a', 'c', 'f', 'h'), 'm'))
        println(searchNextLetter(charArrayOf('a', 'c', 'f', 'h'), 'h'))
    }

    /*
    Time: O(logN)
    Space: O(1)
     */
    fun searchNextLetter(letters: CharArray, key: Char): Char {
        val n = letters.size
        var start = 0
        var end = n - 1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (key < letters[mid]) {
                end = mid - 1
            } else { //if (key >= letters[mid]) {
                start = mid + 1
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop,
        // 'start == end+1'
        return letters[start % n]
    }
}