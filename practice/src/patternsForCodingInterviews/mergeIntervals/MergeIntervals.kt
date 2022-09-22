package patternsForCodingInterviews.mergeIntervals

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743622133_21Unit
object MergeIntervals {
    @JvmStatic
    fun main(args: Array<String>) {
        var input: MutableList<Interval> = ArrayList()
        input.add(Interval(1, 4))
        input.add(Interval(2, 5))
        input.add(Interval(7, 9))
        print("Merged intervals: ")
        for (interval in merge(input)) print("[" + interval.start + "," + interval.end + "] ")
        println()
        input = ArrayList()
        input.add(Interval(6, 7))
        input.add(Interval(2, 4))
        input.add(Interval(5, 9))
        print("Merged intervals: ")
        /*

        for (Interval interval : merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

         */
    }

    /*
    Time: O(N * log N)
    Space: O(N) caused by Timsort algo
     */
    fun merge(intervals: List<Interval>): List<Interval> {
        if (intervals.size < 2) {
            return intervals
        }
        //Sort the intervals by start time
        //O(N * log N)
        //Timsort algorithms O(N)
        Collections.sort(intervals) { a: Interval, b: Interval -> a.start - b.start }
        val mergedIntervals: MutableList<Interval> = LinkedList()
        val intervalItr = intervals.iterator()
        var interval = intervalItr.next()
        var start = interval.start
        var end = interval.end
        while (intervalItr.hasNext()) {
            interval = intervalItr.next()
            if (interval.start <= end) { //Overlapping interval, adjust the 'end'
                end = Math.max(interval.end, end)
            } else { // non-overlapping interval, add the previous interval and reset
                mergedIntervals.add(Interval(start, end))
                start = interval.start
                end = interval.end
            }
        }
        //Add the latest interval
        mergedIntervals.add(Interval(start, end))
        return mergedIntervals
    }
}