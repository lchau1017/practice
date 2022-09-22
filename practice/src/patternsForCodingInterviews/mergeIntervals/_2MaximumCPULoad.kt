package patternsForCodingInterviews.mergeIntervals

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743662085_26Unit
object _2MaximumCPULoad {
    @JvmStatic
    fun main(args: Array<String>) {
        var input: List<Job> = ArrayList(
            Arrays.asList(
                Job(1, 4, 3),
                Job(2, 5, 4), Job(7, 9, 6)
            )
        )
        println(
            "Maximum CPU load at any time: " +
                    findMaxCPULoad(input)
        )
        input = ArrayList(
            Arrays.asList(
                Job(6, 7, 10), Job(2, 4, 11),
                Job(8, 12, 15)
            )
        )
        println(
            "Maximum CPU load at any time: " +
                    findMaxCPULoad(input)
        )
        input = ArrayList(
            Arrays.asList(
                Job(1, 4, 2), Job(2, 4, 1),
                Job(3, 6, 5)
            )
        )
        println(
            "Maximum CPU load at any time: " +
                    findMaxCPULoad(input)
        )
    }

    /*
    Time: O(N * log N) caused by sorting algo and O(log N) caused by the heap -> 'poll()' method
    Space: O(N) caused by sorting and heap
     */
    fun findMaxCPULoad(jobs: List<Job>): Int {
        //Sort the jobs by the start
        jobs.sortedWith { a: Job, b: Job -> a.start - b.start }
        var maxCPULoad = 0
        var currentCPULoad = 0
        val minHeap = PriorityQueue(
            jobs.size
        ) { a: Job, b: Job -> a.end - b.end }
        for (job in jobs) {
            //Remove all the job that have ended
            while (!minHeap.isEmpty() && job.start > minHeap.peek().end) {
                currentCPULoad -= minHeap.poll().cpuLoad
            }
            minHeap.add(job)
            currentCPULoad += job.cpuLoad
            maxCPULoad = Math.max(maxCPULoad, currentCPULoad)
        }
        return maxCPULoad
    }

    class Job(var start: Int, var end: Int, var cpuLoad: Int)
}