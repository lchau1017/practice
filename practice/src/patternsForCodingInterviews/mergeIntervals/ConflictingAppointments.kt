package patternsForCodingInterviews.mergeIntervals

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743642980_24Unit
object ConflictingAppointments {
    @JvmStatic
    fun main(args: Array<String>) {
        val intervals = arrayOf(
            Interval(1, 4), Interval(2, 5),
            Interval(7, 9)
        )
        var result = canAttendAllAppointments(intervals)
        println("Can attend all appointments: $result")
        val intervals1 = arrayOf(
            Interval(6, 7), Interval(2, 4),
            Interval(8, 12)
        )
        result = canAttendAllAppointments(intervals1)
        println("Can attend all appointments: $result")
        val intervals2 = arrayOf(
            Interval(4, 5), Interval(2, 3),
            Interval(3, 6)
        )
        result = canAttendAllAppointments(intervals2)
        println("Can attend all appointments: $result")
        val nums = arrayOf(
            Interval(4, 5),
            Interval(2, 3),
            Interval(3, 6),
            Interval(5, 7),
            Interval(7, 8)
        )
        for (ints in allConflictingAppointments(nums)) {
            println("\n")
            for (i in ints) {
                print(
                    """[ ${i.start} and ${i.end} conflicts 
"""
                )
            }
        }
    }

    /*
    Time: O(N * log * N) where N number of appointments, caused by sort
    Space: O(N) needed for sorting algo. For Java, Arrays.sort() uses Timesort which
    need O(N) space.
     */
    fun canAttendAllAppointments(intervals: Array<Interval>): Boolean {
        //Sort appointment by start time
        Arrays.sort(intervals) { a: Interval, b: Interval -> a.start - b.start }
        //Find overlapping appointment
        for (i in 1 until intervals.size) {
            // please note the comparison above, it is "<" and not "<="
            // while merging we needed "<=" comparison, as we will be merging the two
            // intervals having condition "intervals[i].start == intervals[i - 1].end" but
            // such intervals don't represent conflicting appointments as one starts right
            // after the other
            if (intervals[i].start < intervals[i - 1].end) {
                return false
            }
        }
        return true
    }

    //Problem 1: Problem 1: Given a list of appointments, find all the conflicting appointments.
    fun allConflictingAppointments(intervals: Array<Interval>?): List<List<Interval>> {
        val res: MutableList<List<Interval>> = ArrayList()
        if (intervals == null || intervals.size == 0) {
            return res
        }
        Arrays.sort(intervals) { a: Interval, b: Interval -> a.start - b.start }
        var longestAppointment: Interval? = null
        for (i in 1 until intervals.size) {
            if (intervals[i].start < intervals[i - 1].end || longestAppointment != null && intervals[i].start < longestAppointment.end) { //Conflict
                var list: MutableList<Interval> = ArrayList()
                if (longestAppointment != null && intervals[i].start < longestAppointment.end) {
                    list = ArrayList()
                    list.add(longestAppointment)
                    list.add(Interval(intervals[i].start, intervals[i].end))
                    if (longestAppointment.end < intervals[i].end) {
                        longestAppointment = intervals[i]
                    }
                } else {
                    list = ArrayList()
                    val interval1 = Interval(intervals[i].start, intervals[i].end)
                    val interval2 = Interval(intervals[i - 1].start, intervals[i - 1].end)
                    list.add(interval1)
                    list.add(interval2)
                    longestAppointment = if (intervals[i].end >= intervals[i - 1].end) {
                        intervals[i]
                    } else {
                        intervals[i - 1]
                    }
                }
                res.add(list)
            }
        }
        return res
    }
}