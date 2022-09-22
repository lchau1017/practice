package patternsForCodingInterviews.topologicalSort

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744572959_127Unit
object _2MinimumHeightTrees {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findTrees(5, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(2, 4)))
        println("Roots of MHTs: $result")
        result = findTrees(4, arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(2, 3)))
        println("Roots of MHTs: $result")
        result = findTrees(4, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 3)))
        println("Roots of MHTs: $result")
    }

    fun findTrees(nodes: Int, edges: Array<IntArray>): List<Int> {
        val minHeightTrees: MutableList<Int> = ArrayList()
        if (nodes <= 0) return minHeightTrees
        // with only one node, since its in-degree will be 0, therefore, we need to handle
        // it separately
        if (nodes == 1) {
            minHeightTrees.add(0)
            return minHeightTrees
        }
        // a. Initialize the graph
        val inDegree = HashMap<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
        for (i in 0 until nodes) {
            inDegree[i] = 0
            graph[i] = ArrayList()
        }
        // b. Build the graph
        for (i in edges.indices) {
            val n1 = edges[i][0]
            val n2 = edges[i][1]
            // since this is an undirected graph, therefore, add a link for both the nodes
            graph[n1]!!.add(n2)
            graph[n2]!!.add(n1)
            // increment the in-degrees of both the nodes
            inDegree[n1] = inDegree[n1]!! + 1
            inDegree[n2] = inDegree[n2]!! + 1
        }
        // c. Find all leaves i.e., all nodes with only 1 in-degree
        val leaves: Queue<Int> = LinkedList()
        for ((key, value) in inDegree) {
            if (value == 1) leaves.add(key)
        }
        // d. Remove leaves level by level and subtract each leave's children's in-degrees.
        // Repeat this until we are left with 1 or 2 nodes, which will be our answer.
        // Any node that has already been a leaf cannot be the root of a minimum height tree,
        // because  its adjacent non-leaf node will always be a better candidate.
        var totalNodes = nodes
        //We will repeat this process until we are left with one or two nodes in the graph
        while (totalNodes > 2) {
            val leavesSize = leaves.size
            totalNodes -= leavesSize
            for (i in 0 until leavesSize) {
                val vertex = leaves.poll()
                val children: List<Int> = graph[vertex]!!
                for (child in children) {
                    inDegree[child] = inDegree[child]!! - 1
                    if (inDegree[child] == 1) // if the child has become a leaf
                        leaves.add(child)
                }
            }
        }
        minHeightTrees.addAll(leaves)
        return minHeightTrees
    }
}