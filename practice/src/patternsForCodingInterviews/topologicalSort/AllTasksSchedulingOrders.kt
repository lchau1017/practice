package patternsForCodingInterviews.topologicalSort

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744554328_124Unit
object AllTasksSchedulingOrders {
    @JvmStatic
    fun main(args: Array<String>) {
        /*
        printOrders(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println();

         */
        printOrders(4, arrayOf(intArrayOf(3, 2), intArrayOf(3, 0), intArrayOf(2, 0), intArrayOf(2, 1)))
        println()

        /*
        printOrders(6,
                new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                        new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println();

         */
    }

    /*
    Time: O(V! * E) where V is the total number of tasks and E is the
    total prerequisites.
    We need E part because in each recursive call at max we remove and
    add back all the edges
     */
    fun printOrders(tasks: Int, prerequisites: Array<IntArray>) {
        val sortedOrder: MutableList<Int> = ArrayList()
        if (tasks <= 0) return
        //A initialize the graph
        val inDegree: MutableMap<Int, Int> = HashMap()
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        for (i in 0 until tasks) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }
        //B build the graph
        for (i in prerequisites.indices) {
            val parent = prerequisites[i][0]
            val child = prerequisites[i][1]
            graph[parent]!!.add(child)
            inDegree[child] = inDegree[child]!! + 1
        }
        //C find all sources (O in-degree)
        val sources: Queue<Int> = LinkedList()
        for ((key, value) in inDegree) {
            if (value == 0) {
                sources.add(key)
            }
        }
        printAllTopologicalSorts(graph, inDegree, sources, sortedOrder)
    }

    private fun printAllTopologicalSorts(
        graph: Map<Int, MutableList<Int>>,
        inDegree: MutableMap<Int, Int>,
        sources: Queue<Int>,
        sortedOrder: MutableList<Int>
    ) {
        if (!sources.isEmpty()) {
            for (vertex in sources) {
                sortedOrder.add(vertex)
                val sourcesForNextCall = cloneQueue(sources)
                println(sourcesForNextCall.toString())
                // only remove the current source, all other sources should
                // remain in the queue for the next call
                sourcesForNextCall.remove(vertex)
                //get the node's children to decrement their in-degrees
                val children: List<Int> = graph[vertex]!!
                for (child in children) {
                    inDegree[child] = inDegree[child]!! - 1
                    if (inDegree[child] == 0) {
                        // Save the new source for the next call
                        sourcesForNextCall.add(child)
                    }
                }
                //Recursive call to print other orderings from the remaining (and new)
                // sources
                printAllTopologicalSorts(
                    graph,
                    inDegree,
                    sourcesForNextCall,
                    sortedOrder
                )
                //Backtrack, remove the vertex from the sorted order and put all of its
                //children back to consider the next source instead of the current vertex
                sortedOrder.remove(vertex)
                for (child in children) {
                    inDegree[child] = inDegree[child]!! + 1
                }
            }
        }
        // if sortedOrder doesn't contain all tasks, either we've a cyclic dependency
        // between tasks, or we have not processed all the tasks in this recursive call
        if (sortedOrder.size == inDegree.size) {
            println(sortedOrder)
        }
    }

    //Make a deep copy of the Queue
    private fun cloneQueue(queue: Queue<Int>): Queue<Int> {
        val clone: Queue<Int> = LinkedList()
        for (num in queue) {
            clone.add(num)
        }
        return clone
    }
}