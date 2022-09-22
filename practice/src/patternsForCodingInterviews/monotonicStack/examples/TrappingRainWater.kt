package patternsForCodingInterviews.monotonicStack.examples

import java.util.*

//https://leetcode.com/problems/trapping-rain-water/
object TrappingRainWater {
    /**
     * For Two pointers solution : [topInterviewQuestion.amazon.arraysAndStrings.TrappingRainWater]
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val heights = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
        val heights1 = intArrayOf(2, 1, 0, 1, 3, 2, 1, 2)
        //Output: 6
        println(trap(heights1))
    }

    //Approach 3: Monotonic stack
    fun trap(height: IntArray): Int {
        var ans = 0
        var current = 0
        val st = Stack<Int>()
        while (current < height.size) {
            while (!st.empty() && height[current] > height[st.peek()]) {
                val top = st.peek()
                st.pop()
                if (st.empty()) break
                val distance = current - st.peek() - 1
                val bounded_height = Math.min(height[current], height[st.peek()]) - height[top]
                ans += distance * bounded_height
            }
            st.push(current++)
        }
        return ans
    }
}