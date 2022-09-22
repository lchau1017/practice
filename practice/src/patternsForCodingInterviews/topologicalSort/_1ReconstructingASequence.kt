package patternsForCodingInterviews.topologicalSort

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744569656_126Unit
object _1ReconstructingASequence {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = canConstruct(intArrayOf(1, 2, 3, 4), arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4)))
        println("Can we uniquely construct the sequence: $result")
        result = canConstruct(intArrayOf(1, 2, 3, 4), arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(2, 4)))
        println("Can we uniquely construct the sequence: $result")
        result = canConstruct(intArrayOf(3, 1, 4, 2, 5), arrayOf(intArrayOf(3, 1, 5), intArrayOf(1, 4, 2, 5)))
        println("Can we uniquely construct the sequence: $result")
    }

    fun canConstruct(originalSeq: IntArray, sequences: Array<IntArray>): Boolean {
        val sortedOrder: MutableList<Int> = ArrayList()
        if (originalSeq.size <= 0) return false
        // a. Initialize the graph
        val inDegree = HashMap<Int, Int>() // count of incoming edges for every vertex
        val graph = HashMap<Int, MutableList<Int>>() // adjacency list graph
        for (seq in sequences) {
            for (i in seq.indices) {
                inDegree.putIfAbsent(seq[i], 0)
                graph.putIfAbsent(seq[i], ArrayList())
            }
        }
        // b. Build the graph
        for (seq in sequences) {
            for (i in 1 until seq.size) {
                val parent = seq[i - 1]
                val child = seq[i]
                graph[parent]!!.add(child)
                inDegree[child] = inDegree[child]!! + 1
            }
        }
        // if we don't have ordering rules for all the numbers we'll not able to uniquely
        // construct the sequence
        if (inDegree.size != originalSeq.size) return false
        // c. Find all sources i.e., all vertices with 0 in-degrees
        val sources: Queue<Int> = LinkedList()
        for ((key, value) in inDegree) {
            if (value == 0) sources.add(key)
        }
        // d. For each source, add it to the sortedOrder and subtract one from all of its
        // children's in-degrees if a child's in-degree becomes zero, add it to sources queue
        while (!sources.isEmpty()) {
            if (sources.size > 1) return false // more than one sources mean, there is more than one way to
            // reconstruct the sequence
            if (originalSeq[sortedOrder.size] != sources.peek()) return false // the next source (or number) is different from original sequence
            val vertex = sources.poll()
            sortedOrder.add(vertex)
            // get the node's children to decrement their in-degrees
            val children: List<Int> = graph[vertex]!!
            for (child in children) {
                inDegree[child] = inDegree[child]!! - 1
                if (inDegree[child] == 0) sources.add(child)
            }
        }
        // if sortedOrder's size is not equal to original sequence's size, there is no
        // unique way to construct
        return sortedOrder.size == originalSeq.size
    }
}