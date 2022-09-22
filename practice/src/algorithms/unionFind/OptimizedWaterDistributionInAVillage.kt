package algorithms.unionFind

import java.util.*
import java.util.AbstractMap.SimpleEntry

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3916/
class OptimizedWaterDistributionInAVillage {
    //Greedy algorithm, best edges to be added with the lower cost
    //used by the Heap
    //Approach 2: Kruskal's Algorithm with Union-Find
    // Kruskal's is an algo to construct a minimum spanning tree
    fun minCostToSupplyWater(n: Int, wells: IntArray, pipes: Array<IntArray>): Int {
        //min heap to maintain the order of edges to be visited
        val edgesHeap = PriorityQueue(n) { (key): Map.Entry<Int, Int>, (key1): Map.Entry<Int, Int> -> key - key1 }
        //Representation of the graph in adjacency list
        val graph: MutableList<MutableList<Map.Entry<Int, Int>>> = ArrayList(n + 1)
        for (i in 0 until n + 1) {
            graph.add(ArrayList())
        }
        //add a virtual vertex indexed with 0
        // then add an edge to each of the house weighted by the cost
        for (i in wells.indices) {
            val virtualEdge = Pair.of(wells[i], i + 1)
            graph[0].add(virtualEdge)
            //Initialize the heap with the edges from the virtual vertex
            edgesHeap.add(virtualEdge)
        }
        // add the bidirectional edges to the graph
        for (i in pipes.indices) {
            val house1 = pipes[i][0]
            val house2 = pipes[i][1]
            val cost = pipes[i][2]
            //graph is undirected (bidirectional) we need to add two entries
            graph[house1].add(Pair.of(cost, house2))
            graph[house2].add(Pair.of(cost, house1))
        }
        //Kick off the exploration from the virtual vertex 0
        //We need a set to maintain all the vertices that we have
        // added to the final minimum spanning tree.
        val mstSet: MutableSet<Int> = HashSet()
        mstSet.add(0)
        var totalCost = 0
        while (mstSet.size < n + 1) {
            val (cost, nextHouse) = edgesHeap.poll()
            if (mstSet.contains(nextHouse)) {
                continue
            }
            //adding the new vertex into the set
            mstSet.add(nextHouse)
            totalCost += cost
            //Expanding the candidates of edge to choose from in the next round
            for (neighborEdge in graph[nextHouse]) {
                if (!mstSet.contains(neighborEdge.value)) {
                    edgesHeap.add(neighborEdge)
                }
            }
        }
        return totalCost
    }

    internal object Pair {
        fun <T, U> of(first: T, second: U): Map.Entry<T, U> {
            return SimpleEntry(first, second)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 3
            val wells = intArrayOf(1, 2, 2)
            val pipes = arrayOf(intArrayOf(1, 2, 1), intArrayOf(2, 3, 1))
            val obj = OptimizedWaterDistributionInAVillage()
            //Output : 3
            println(obj.minCostToSupplyWater(n, wells, pipes))
        }
    }
}