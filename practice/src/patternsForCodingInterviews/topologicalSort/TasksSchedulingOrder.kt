package patternsForCodingInterviews.topologicalSort

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744542693_123Unit
object TasksSchedulingOrder {
    @JvmStatic
    fun main(args: Array<String>) {
        val result: List<Int>
        /*
        result = findOrder(3, new int[][] {
                new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println(result);
         */result = findOrder(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 0)))
        println(result)
        /*
        result = findOrder(6, new int[][] { new int[] { 2, 5 },
                new int[] { 0, 5 }, new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 },
                new int[] { 1, 3 } });
        System.out.println(result);

         */
    }

    fun findOrder(tasks: Int, prerequisites: Array<IntArray>): List<Int> {
        val sortedOrder: MutableList<Int> = ArrayList()
        if (tasks <= 0) return sortedOrder
        // a. Initialize the graph
        val inDegree = HashMap<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
        for (i in 0 until tasks) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }
        // b. Build the graph
        for (i in prerequisites.indices) {
            val parent = prerequisites[i][0]
            val child = prerequisites[i][1]
            graph[parent]!!.add(child) // put the child into it's parent's list
            inDegree[child] = inDegree[child]!! + 1 // increment child's inDegree
        }

        // c. Find all sources i.e., all vertices with 0 in-degrees
        val sources: Queue<Int> = LinkedList()
        for ((key, value) in inDegree) {
            if (value == 0) sources.add(key)
        }
        // d. For each source, add it to the sortedOrder and subtract one from all of its
        // children's in-degrees if a child's in-degree becomes zero, add it to sources queue
        while (!sources.isEmpty()) {
            val vertex = sources.poll()
            sortedOrder.add(vertex)
            // get the node's children to decrement their in-degrees
            val children: List<Int> = graph[vertex]!!
            for (child in children) {
                inDegree[child] = inDegree[child]!! - 1
                if (inDegree[child] == 0) sources.add(child)
            }
        }
        // if sortedOrder doesn't contain all tasks, there is a cyclic dependency between
        // tasks, therefore, we will not be able to schedule all tasks
        return if (sortedOrder.size != tasks) ArrayList() else sortedOrder
    }
}