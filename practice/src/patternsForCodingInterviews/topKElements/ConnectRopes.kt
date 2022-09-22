package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744296940_96Unit
object ConnectRopes {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = 0
        result = minimumCostToConnectRopes(intArrayOf(1, 3, 11, 5))
        println("Minimum cost to connect ropes: $result")
        /*
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 3, 4, 5, 6 });
        System.out.println("Minimum cost to connect ropes: " + result);
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5, 2 });
        System.out.println("Minimum cost to connect ropes: " + result);

         */
    }

    /*
    Time: O(N * logN)
    Space: O(N)
     */
    fun minimumCostToConnectRopes(ropeLengths: IntArray): Int {
        val minHeap = PriorityQueue { n1: Int, n2: Int -> n1 - n2 }
        //Add all ropes to the min heap
        for (i in ropeLengths.indices) {
            minHeap.add(ropeLengths[i])
        }
        // go through the values of the heap, in each step take top (lowest) rope lengths
        // from the min heap connect them and push the result back to the min heap.
        // keep doing this until the heap is left with only one rope
        var result = 0
        var temp = 0
        while (minHeap.size > 1) {
            temp = minHeap.poll() + minHeap.poll()
            result += temp
            minHeap.add(temp)
        }
        return result
    }
}