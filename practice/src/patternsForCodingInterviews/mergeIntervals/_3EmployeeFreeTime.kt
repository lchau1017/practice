package patternsForCodingInterviews.mergeIntervals

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743666558_27Unit
object _3EmployeeFreeTime {
    @JvmStatic
    fun main(args: Array<String>) {
        val input: MutableList<List<Interval>> = ArrayList()
        input.add(
            ArrayList(
                Arrays.asList(
                    Interval(1, 3),
                    Interval(5, 6)
                )
            )
        )
        input.add(
            ArrayList(
                Arrays.asList(
                    Interval(2, 3),
                    Interval(6, 8)
                )
            )
        )
        val result = findEmployeeFreeTime(input)
        print("Free intervals: ")
        for (interval in result) print("[" + interval.start + ", " + interval.end + "] ")
        println()
        /*
        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
                new Interval(9, 12))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result = findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
                new Interval(7, 9))));
        result = findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");

 */
    }

    fun findEmployeeFreeTime(schedule: List<List<Interval>>): List<Interval> {
        val result: MutableList<Interval> = ArrayList()
        // PriorityQueue to store one interval from each employee
        val minHeap = PriorityQueue { a: EmployeeInterval, b: EmployeeInterval ->
            Integer.compare(
                a.interval.start,
                b.interval.start
            )
        }

        // insert the first interval of each employee to the queue
        for (i in schedule.indices) minHeap.offer(EmployeeInterval(schedule[i][0], i, 0))
        var previousInterval = minHeap.peek().interval
        while (!minHeap.isEmpty()) {
            val queueTop = minHeap.poll()
            // if previousInterval is not overlapping with the next interval, insert a free
            // interval
            if (previousInterval.end < queueTop.interval.start) {
                result.add(Interval(previousInterval.end, queueTop.interval.start))
                previousInterval = queueTop.interval
            } else { // overlapping intervals, update the previousInterval if needed
                if (previousInterval.end < queueTop.interval.end) previousInterval = queueTop.interval
            }
            // if there are more intervals available for the same employee, add their next
            // interval
            val employeeSchedule = schedule[queueTop.employeeIndex]
            if (employeeSchedule.size > queueTop.intervalIndex + 1) {
                minHeap.offer(
                    EmployeeInterval(
                        employeeSchedule[queueTop.intervalIndex + 1],
                        queueTop.employeeIndex,
                        queueTop.intervalIndex + 1
                    )
                )
            }
        }
        return result
    }

    class Interval(var start: Int, var end: Int)
    internal class EmployeeInterval(// interval representing employee's working hours
        var interval: Interval, // index of the list containing working hours of this employee
        var employeeIndex: Int, // index of the interval in the employee list
        var intervalIndex: Int
    )
}