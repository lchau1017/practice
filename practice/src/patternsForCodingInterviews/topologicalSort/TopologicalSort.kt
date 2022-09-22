package patternsForCodingInterviews.topologicalSort

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744530502_121Unit
object TopologicalSort {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = sort(4, arrayOf(intArrayOf(3, 2), intArrayOf(3, 0), intArrayOf(2, 0), intArrayOf(2, 1)))
        println(result)
        result =
            sort(5, arrayOf(intArrayOf(4, 2), intArrayOf(4, 3), intArrayOf(2, 0), intArrayOf(2, 1), intArrayOf(3, 1)))
        println(result)
        result = sort(
            7,
            arrayOf(
                intArrayOf(6, 4),
                intArrayOf(6, 2),
                intArrayOf(5, 3),
                intArrayOf(5, 4),
                intArrayOf(3, 0),
                intArrayOf(3, 1),
                intArrayOf(3, 2),
                intArrayOf(4, 1)
            )
        )
        println(result)
    }

    /*
    Time: O(V + E) v total of vertices, and E is the total number of edges in the graph
    Space: O(V + E), we store all the edges for each vertex in adjacency list
     */
    fun sort(vertices: Int, edges: Array<IntArray>): List<Int?> {
        val sortedOrder: MutableList<Int?> = ArrayList()
        if (vertices <= 0) {
            return sortedOrder
        }
        //A . Initialize the graph
        val inDegree: MutableMap<Int, Int> = HashMap() //Count of incoming edges for every vertex
        //Adjency list graph
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        for (i in 0 until vertices) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }
        //B. Build the graph
        for (i in edges.indices) {
            val parent = edges[i][0]
            val child = edges[i][1]
            graph[parent]!!.add(child) //Put the child into it's parent's list
            inDegree[child] = inDegree[child]!! + 1 //Increment child's inDegree
        }
        //C. Find all sources, all vertices with 0 in-degree
        val sources: Queue<Int> = LinkedList()
        for ((key, value) in inDegree) {
            if (value == 0) {
                sources.add(key)
            }
        }
        //D. For each source, add  it to the sortedOrder and subtract one from all of its children
        // in-degree if a child's in-degree becomes zero, add it to source queue
        while (!sources.isEmpty()) {
            val vertex = sources.poll()
            sortedOrder.add(vertex)
            //Get the node's children to decrement their in-degrees
            val children: List<Int> = graph[vertex]!!
            for (child in children) {
                inDegree[child] = inDegree[child]!! - 1
                if (inDegree[child] == 0) {
                    sources.add(child)
                }
            }
        }
        //Problem 1: Find if a given Directed Graph has a cycle in it or not.
        /*
        Solution: If we can’t determine the topological ordering of all the vertices of a directed graph,
        the graph has a cycle in it. This was also referred to in the above code:
         */return if (sortedOrder.size != vertices) {
            ArrayList()
        } else sortedOrder
    }
}