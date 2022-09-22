package patternsForCodingInterviews.topologicalSort

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744536717_122Unit
object TasksScheduling {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = isSchedulingPossible(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)))
        println("Tasks execution possible: $result")
        result = isSchedulingPossible(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 0)))
        println("Tasks execution possible: $result")
        result = isSchedulingPossible(
            6,
            arrayOf(
                intArrayOf(2, 5),
                intArrayOf(0, 5),
                intArrayOf(0, 4),
                intArrayOf(1, 4),
                intArrayOf(3, 2),
                intArrayOf(1, 3)
            )
        )
        println("Tasks execution possible: $result")
    }

    fun isSchedulingPossible(tasks: Int, prerequisites: Array<IntArray>): Boolean {
        val sortedOrder: MutableList<Int> = ArrayList()
        if (tasks <= 0) return false
        // a. Initialize the graph
        val inDegree = HashMap<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
        for (i in 0 until tasks) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }
        //B. Build the graph
        for (i in prerequisites.indices) {
            val parent = prerequisites[i][0]
            val child = prerequisites[i][1]
            graph[parent]!!.add(child) // put the child into it's parent's list
            inDegree[child] = inDegree[child]!! + 1 // increment child's inDegree
        }
        //C Find all sources (vertices with 0 in-degree
        val sources: Queue<Int> = LinkedList()
        for ((key, value) in inDegree) {
            if (value == 0) {
                sources.add(key)
            }
        }
        // d. For each source, add it to the sortedOrder and subtract one from all of its
        // children's in-degrees if a child's in-degree becomes zero, add it to sources queue
        while (!sources.isEmpty()) {
            val vertex = sources.poll()
            sortedOrder.add(vertex)
            val children: List<Int> = graph[vertex]!!
            for (child in graph[vertex]!!) {
                inDegree[child] = inDegree[child]!! - 1
                if (inDegree[child] == 0) {
                    sources.add(child)
                }
            }
        }
        // if sortedOrder doesn't contain all tasks, there is a cyclic dependency between
        // tasks, therefore, we will not be able to schedule all tasks
        return sortedOrder.size == tasks
    }
}