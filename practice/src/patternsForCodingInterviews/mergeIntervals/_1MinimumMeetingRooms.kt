package patternsForCodingInterviews.mergeIntervals

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743654901_25Unit
object _1MinimumMeetingRooms {
    @JvmStatic
    fun main(args: Array<String>) {
        var input: List<Meeting> = listOf(Meeting(1, 4),Meeting(2, 5),Meeting(7, 9))

        var result = findMinimumMeetingRooms(input)
        println("Minimum meeting rooms required: $result")
        input = listOf(Meeting(6, 7),Meeting(2, 4),Meeting(8, 12))
        result = findMinimumMeetingRooms(input)
        println("Minimum meeting rooms required: $result")

        input = listOf(Meeting(1,4),Meeting(2, 3),Meeting(3,6))

        result = findMinimumMeetingRooms(input)
        println("Minimum meeting rooms required: $result")

        input = listOf(Meeting(4,5),Meeting(2, 3),Meeting(2,4),Meeting(3,5))
        result = findMinimumMeetingRooms(input)
        println("Minimum meeting rooms required: $result")
    }

    /*
    Time: O(N * log N), due to the sorting and O(log N) for priorityQueue with 'poll()' method
    Space: O(N) used by sorting and heap
     */
    fun findMinimumMeetingRooms(meetings: List<Meeting>?): Int {
        if (meetings == null || meetings.size == 0) {
            return 0
        }
        //Sort the meeting by start time
        meetings.sortedWith{ a: Meeting, b: Meeting -> a.start - b.start } //Mergesort
        var minRooms = 0
        //Priority to the smaller end time
        val minHeap = PriorityQueue(
            meetings.size
        ) { a: Meeting, b: Meeting -> a.end - b.end }
        for (meeting in meetings) {
            ///Remove all meetings that have ended
            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end) {
                minHeap.poll()
            }
            //add the current meeting into the minHeap
            minHeap.add(meeting)
            //All active meeting are in the heapMap
            minRooms = Math.max(minRooms, minHeap.size)
        }
        return minRooms
    }

    class Meeting(var start: Int, var end: Int)
}