package patternsForCodingInterviews.monotonicStack.examples

import java.util.*

//https://leetcode.com/problems/buildings-with-an-ocean-view/
object BuildingWithAnOceanView {
    //The ocean is to the right of the buildings
    @JvmStatic
    fun main(args: Array<String>) {
        val heights = intArrayOf(4, 2, 3, 1)
        println(Arrays.toString(findBuildings(heights)))
    }

    //Approach 2: Monotonic stack
    /*
    Stack is not empty, which means there is at least one greater or equal height building.
    Thus the view is blocked.
     Time: O(N) -> iterate over the given array once.
     Space: O(N)
     */
    fun findBuildings(heights: IntArray): IntArray {
        val n = heights.size
        val list: MutableList<Int> = ArrayList()
        // Monotonically decreasing stack.
        val stack = Stack<Int>()
        for (current in n - 1 downTo 0) {
            // If the building to the right is smaller, we can pop it.
            //height of the building on top of the stack is less than the height of the current
            //building, we pop() from the stack.
            //If stack not empty, there is at least one greater or equal height building, Thus view is blocked.
            while (!stack.isEmpty() && heights[stack.peek()] < heights[current]) {
                stack.pop() //We keep the greater building in the stack
            }
            // If the stack is empty, it means there is no building to the right
            // that can block the view of the current building.
            if (stack.isEmpty()) {
                list.add(current)
            }
            // Push the current building in the stack.
            stack.push(current)
        }
        // Push elements from list to answer array in reverse order.
        val answer = IntArray(list.size)
        for (i in list.indices) {
            answer[i] = list[list.size - 1 - i]
        }
        return answer
    }
}