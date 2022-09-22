package patternsForCodingInterviews.monotonicStack

import java.util.*

object MonotonicStack {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr: List<Int> = listOf(2,3,1,4,1)
        println(arr)
        val res = monotonicStack(arr)
        println(Arrays.toString(res))
    }

    //Order of List<Integer> arr -> 2, 3, 1, 4, 1
    //Initialize monotonic Stack
    /*
    Monotonic stack is a stack that from top to bottom, elements are monotonically increasing.
    From right to left, we can check whether the top of the stack is bigger than the one in array.
    If it is not bigger than the one in array, it cannot be the solution, since the one in array is farther than it
     */
    fun monotonicStack(arr: List<Int>): IntArray {
        //initalize a monotonic stack
        val s = Stack<Int>() //Stack is monotonically increasing
        val ans = IntArray(arr.size)
        //traverse from upperbound tp lowerbound
        for (i in arr.indices.reversed()) {
            while (!s.isEmpty() && s.peek() <= arr[i]) {
                s.pop()
            }
            if (s.isEmpty()) {
                ans[i] = 0
            } else {
                ans[i] = s.peek()
            }
            s.push(arr[i])
        }
        return ans
    }
}