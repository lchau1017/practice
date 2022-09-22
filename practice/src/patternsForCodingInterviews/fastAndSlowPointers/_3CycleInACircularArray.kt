package patternsForCodingInterviews.fastAndSlowPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743595805_19Unit
object _3CycleInACircularArray {
    @JvmStatic
    fun main(args: Array<String>) {
        println(loopExists(intArrayOf(1, 2, -1, 2, 2)))
        /*
        System.out.println(loopExists(new int[] { 2, 2, -1, 2 }));
        System.out.println(loopExists(new int[] { 2, 1, -1, -2 }));
         */
    }

    /*
    Time: O(N²) try to find cycle for each element
    Space: O(N)
     */
    fun loopExists(arr: IntArray): Boolean {
        for (i in arr.indices) {
            val isForward = arr[i] >= 0 //If we move forward or not
            var slow = i
            var fast = i
            //if slow or fast becomes '-1' this means we can no find cycle for this number
            do {
                slow = findNextIndex(arr, isForward, slow) //Move one step for slow pointer
                fast = findNextIndex(arr, isForward, fast) // move one step for fast pointer
                if (fast != -1) {
                    fast = findNextIndex(arr, isForward, fast) // move another step for fast ptr
                }
            } while (slow != -1 && fast != -1 && slow != fast)
            if (slow != -1 && slow == fast) {
                return true
            }
        }
        return false
    }

    private fun findNextIndex(arr: IntArray, isForward: Boolean, currentIndex: Int): Int {
        val direction = arr[currentIndex] >= 0
        if (isForward != direction) {
            return -1 //change in direction, return -1;
        }
        var nextIndex = (currentIndex + arr[currentIndex]) % arr.size
        if (nextIndex < 0) {
            nextIndex += arr.size //Wrap around for negative numbers
        }
        //One element cycle, return -1
        if (nextIndex == currentIndex) {
            nextIndex = -1
        }
        return nextIndex
    }
}