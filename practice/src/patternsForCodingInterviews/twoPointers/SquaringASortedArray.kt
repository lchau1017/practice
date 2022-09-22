package patternsForCodingInterviews.twoPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743435284_3Unit
object SquaringASortedArray {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = makeSquares(intArrayOf(-2, -1, 0, 2, 3))
        for (num in result) print("$num ")
        println()
        result = makeSquares(intArrayOf(-3, -1, 0, 1, 2))
        for (num in result) print("$num ")
        println()
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    fun makeSquares(arr: IntArray): IntArray {
        val n = arr.size
        val squares = IntArray(n)
        var highestSquareIdx = n - 1
        val left = 0
        var right = arr.size - 1
        while (left <= right) {
            val leftSquare = arr[left] * arr[left]
            val rightSquare = arr[right] * arr[right]
            if (leftSquare > rightSquare) {
                squares[highestSquareIdx--] = leftSquare
            } else {
                squares[highestSquareIdx--] = rightSquare
                right--
            }
        }
        return squares
    }
}