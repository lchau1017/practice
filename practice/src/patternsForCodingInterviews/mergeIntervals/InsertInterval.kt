package patternsForCodingInterviews.mergeIntervals

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743628532_22Unit
object InsertInterval {
    @JvmStatic
    fun main(args: Array<String>) {
        val input: MutableList<Interval> = ArrayList()
        input.add(Interval(1, 3))
        input.add(Interval(5, 7))
        input.add(Interval(8, 12))
        for (interval in insert(input, Interval(4, 6))) print("[" + interval.start + "," + interval.end + "] ")
        println()

        /*
        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        for (Interval interval : insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        for (Interval interval : insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

         */
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    fun insert(
        intervals: List<Interval>?,
        newInterval: Interval
    ): List<Interval> {
        if (intervals == null || intervals.isEmpty()) return Arrays.asList(newInterval)
        val mergedIntervals: MutableList<Interval> = ArrayList()
        var i = 0
        //Skip (and add to output) all intervals that come before the
        // 'newInterval'
        while (i < intervals.size
            && intervals[i].end < newInterval.start
        ) {
            mergedIntervals.add(intervals[i++])
        }
        //Merge all intervals that overlap with 'newInterval'
        while (i < intervals.size
            && intervals[i].start <= newInterval.end
        ) {
            newInterval.start = Math.min(
                intervals[i].start,
                newInterval.start
            )
            newInterval.end = Math.max(
                intervals[i].end,
                newInterval.end
            )
            i++
        }
        //Insert the newInternal
        mergedIntervals.add(newInterval)
        //Add all the remaining intervals to the output
        while (i < intervals.size) {
            mergedIntervals.add(intervals[i++])
        }
        return mergedIntervals
    }
}