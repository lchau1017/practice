package patternsForCodingInterviews.fastAndSlowPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743560700_15Unit
object HappyNumber {
    @JvmStatic
    fun main(args: Array<String>) {
        println(find(23))
        println(find(12))
    }

    /*
    Time: O(log N)
    Space: O(1)
     */
    fun find(num: Int): Boolean {
        var slow = num
        var fast = num
        do {
            slow = findSquareSum(slow) //Move one step
            fast = findSquareSum(findSquareSum(fast)) // Move 2 steps
        } while (slow != fast) //Found the cycle
        return slow == 1 // see if the cycle is stuck on the number '1'
    }

    private fun findSquareSum(num: Int): Int {
        var num = num
        var sum = 0
        var digit: Int
        while (num > 0) {
            digit = num % 10
            sum += digit * digit
            num /= 10
        }
        return sum
    }
}