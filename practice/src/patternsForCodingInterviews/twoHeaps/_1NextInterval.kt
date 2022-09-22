package patternsForCodingInterviews.twoHeaps

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744015026_65Unit
object _1NextInterval {
    /*
    Explanation: (Same logic with example 2)
    Example 1:
      O       1       2
    [2, 3], [3, 4]; [5, 6]
    Output:
    For index 0, the next interval is 1
    For index 1, the next interval is 2
    For index 2, the next interval is -1 (no interval available
    [1, 2, -1]
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val intervals: Array<Interval>
        intervals = arrayOf(
            Interval(2, 3), Interval(3, 4),
            Interval(5, 6)
        )
        val result = findNextInterval(intervals)
        print("Next interval indices are: ")
        for (index in result) print("$index ")
        println()

        /*
        intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5),
                new Interval(4, 6) };
        result = findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");

         */
    }

    /*
    Time: O(N log N)
    Space: O(N)
     */
    fun findNextInterval(intervals: Array<Interval>): IntArray {
        val n = intervals.size
        //Heap for finding the maximum start
        val maxStartHeap = PriorityQueue(
            n
        ) { a: Int, b: Int -> intervals[b].start - intervals[a].start }
        //Heap for finding the minimum end
        val maxEndHeap = PriorityQueue(
            n
        ) { a: Int, b: Int -> intervals[b].end - intervals[a].end }
        val result = IntArray(n)
        for (i in 0 until n) {
            maxStartHeap.offer(i)
            maxEndHeap.offer(i)
        }
        //go through all the intervals to find each intervals's next interval
        for (i in 0 until n) {
            // let's find the next interval of the interval which has the highest 'end'
            //Take out the top (having highest end) interval from the maxEndHeap to find its next interval.
            // Let’s call this interval topEnd.
            val topEnd = maxEndHeap.poll()
            // If we can’t find the next interval, add ‘-1’ in the result array.
            result[topEnd] = -1 // defaults to -1
            //Find an interval in the maxStartHeap with the closest start greater than or
            // equal to the start of topEnd. Since maxStartHeap is sorted by ‘start’ of
            // intervals, it is easy to find the interval with the highest ‘start’.
            // Let’s call this interval topStart.
            if (intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) { //Interval
                var topStart = maxStartHeap.poll()
                // find the interval that has the closest 'start'
                while (!maxStartHeap.isEmpty()
                    && intervals[maxStartHeap.peek()].start >= intervals[topEnd].end
                ) {
                    topStart = maxStartHeap.poll()
                }
                // Add the index of topStart in the result array as the next interval of topEnd.
                result[topEnd] = topStart
                // put the interval back as it could be the next interval of other intervals
                //Put the topStart back in the maxStartHeap, as it could be the next interval of other intervals.
                maxStartHeap.add(topStart)
            }
        }
        return result
    }
}