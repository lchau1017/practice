package patternsForCodingInterviews.subsets

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744074098_71Unit
object BalancedParentheses {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = generateValidParentheses(2)
        println("All combinations of balanced parentheses are: $result")
        result = generateValidParentheses(3)
        println("All combinations of balanced parentheses are: $result")
    }

    /*
    Recursive
     */
    fun generateValidParenthesesRecursive(num: Int): List<String> {
        val result: MutableList<String> = ArrayList()
        val parenthesesString = CharArray(2 * num)
        generateValidParenthesesRecursive(num, 0, 0, parenthesesString, 0, result)
        return result
    }

    private fun generateValidParenthesesRecursive(
        num: Int, openCount: Int,
        closeCount: Int, parenthesesString: CharArray, index: Int, result: MutableList<String>
    ) {
        // if we've reached the maximum number of open and close parentheses, add to result
        if (openCount == num && closeCount == num) {
            result.add(String(parenthesesString))
        } else {
            if (openCount < num) { // if we can add an open parentheses, add it
                parenthesesString[index] = '('
                generateValidParenthesesRecursive(
                    num, openCount + 1, closeCount,
                    parenthesesString, index + 1, result
                )
            }
            if (openCount > closeCount) { // if we can add a close parentheses, add it
                parenthesesString[index] = ')'
                generateValidParenthesesRecursive(
                    num, openCount, closeCount + 1,
                    parenthesesString, index + 1, result
                )
            }
        }
    }

    /*
    Iterative
    Time: O(N * 2N)
    Space: O(N * 2N)
     */
    fun generateValidParentheses(num: Int): List<String?> {
        val result: MutableList<String?> = ArrayList()
        val queue: Queue<ParenthesesString> = LinkedList()
        queue.add(ParenthesesString(StringBuilder(), 0, 0))
        while (!queue.isEmpty()) {
            val ps = queue.poll()
            // if we've reached the maximum number of open and close parentheses, add to result
            if (ps.openCount == num && ps.closeCount == num) {
                result.add(ps.str.toString())
            } else {
                var strBuilder: StringBuilder
                var parenthesesString: ParenthesesString
                if (ps.openCount < num) { // if we can add an open parentheses, add it
                    strBuilder = StringBuilder(ps.str).append("(")
                    parenthesesString = ParenthesesString(strBuilder, ps.openCount + 1, ps.closeCount)
                    queue.add(parenthesesString)
                }
                if (ps.openCount > ps.closeCount) { // if we can add a close parentheses, add it
                    strBuilder = StringBuilder(ps.str).append(")")
                    parenthesesString = ParenthesesString(strBuilder, ps.openCount, ps.closeCount + 1)
                    queue.add(parenthesesString)
                }
            }
        }
        return result
    }

    internal class ParenthesesString(
        var str: StringBuilder, //Open parentheses count
        var openCount: Int, //Close parentheses count
        var closeCount: Int
    )
}