package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744374063_105Unit
object _2SchedulingTasks {
    @JvmStatic
    fun main(args: Array<String>) {
        var tasks = charArrayOf('a', 'a', 'a', 'b', 'c', 'c')
        println(
            "Minimum intervals needed to execute all tasks: " +
                    scheduleTasks(tasks, 2)
        )
        tasks = charArrayOf('a', 'b', 'a')
        println(
            "Minimum intervals needed to execute all tasks: " +
                    scheduleTasks(tasks, 3)
        )
    }

    /*
    Time: O(N * logN), for each iteration, we remove a tasks from the heap which take O(log N)
    Space: O(N)
     */
    fun scheduleTasks(tasks: CharArray, k: Int): Int {
        var intervalCount = 0
        val taskFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in tasks) {
            taskFrequencyMap[chr] = taskFrequencyMap.getOrDefault(chr, 0) + 1
        }
        //Execute the highest frequency task
        val maxHeap =
            PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_, value): Map.Entry<Char, Int>, (_, value1): Map.Entry<Char, Int> -> value1 - value }
        //add all tasks to the maxHeap
        maxHeap.addAll(taskFrequencyMap.entries)
        while (!maxHeap.isEmpty()) {
            val waitList: MutableList<MutableMap.MutableEntry<Char, Int>> = ArrayList()
            var n = k + 1 // try to execute as many as 'k+1' tasks from the max-heap
            while (n > 0 && !maxHeap.isEmpty()) {
                //Try to execute as many tasks as k + 1
                intervalCount++
                val currentEntry = maxHeap.poll()
                if (currentEntry.value > 1) {
                    //Decrease frequency
                    currentEntry.setValue(currentEntry.value - 1)
                    //Put in in a waiting list
                    waitList.add(currentEntry)
                }
                n--
            }
            maxHeap.addAll(waitList) // put all the waiting list back on the heap
            if (!maxHeap.isEmpty()) {
                intervalCount += n // we'll be having 'n' idle intervals for the next iteration
            }
        }
        return intervalCount
    }
}