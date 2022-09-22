package patternsForCodingInterviews.subsets

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744093989_73Unit
object _1EvaluateExpression {
    @JvmStatic
    fun main(args: Array<String>) {
        val result: List<Int>
        result = diffWaysToEvaluateExpression("1+2*3")
        println("Expression evaluations: $result")
        /*
        result = diffWaysToEvaluateExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);

         */
    }

    //memoization
    var map: MutableMap<String, List<Int>> = HashMap()

    /*
    Time: O(N * 2N)
    Space: O(2N)
     */
    fun diffWaysToEvaluateExpression(input: String): List<Int> {
        //Part of memoization
        if (map.containsKey(input)) {
            return map[input]!!
        }
        val result: MutableList<Int> = ArrayList()
        //base case: if the input string is a number, parse and add it to the output.
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(input.toInt())
            return result
        } else {
            for (i in 0 until input.length) {
                val chr = input[i]
                if (!Character.isDigit(chr)) {
                    //Break  the equation here into two parts and make recursively calls
                    val leftParts = diffWaysToEvaluateExpression(input.substring(0, i))
                    val rightParts = diffWaysToEvaluateExpression(input.substring(i + 1))
                    for (part1 in leftParts) {
                        for (part2 in rightParts) {
                            if (chr == '+') {
                                result.add(part1 + part2)
                            } else if (chr == '-') {
                                result.add(part1 - part2)
                            } else if (chr == '*') {
                                result.add(part1 * part2)
                            }
                        }
                    }
                }
            }
        }
        //Part of memoization
        map[input] = result
        return result
    }
}