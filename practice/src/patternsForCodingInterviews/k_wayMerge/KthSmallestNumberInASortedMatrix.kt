package patternsForCodingInterviews.k_wayMerge

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744424045_110Unit
object KthSmallestNumberInASortedMatrix {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = Int.MIN_VALUE
        var matrix = arrayOf(intArrayOf(2, 6, 8), intArrayOf(3, 7, 10), intArrayOf(5, 8, 11))
        result = findKthSmallest(matrix, 5)
        print("Kth smallest number is: $result \n\n")
        println("Binary Search : \n")
        matrix = arrayOf(intArrayOf(2, 6, 8), intArrayOf(3, 7, 10), intArrayOf(5, 8, 11))
        result = findKthSmallestBinarySearch(matrix, 5)
        println("Kth smallest number is: $result")
        matrix = arrayOf(intArrayOf(-5))
        result = findKthSmallestBinarySearch(matrix, 1)
        println("Kth smallest number is: $result")
        matrix = arrayOf(intArrayOf(2, 6, 8), intArrayOf(3, 7, 10), intArrayOf(5, 8, 11))
        result = findKthSmallestBinarySearch(matrix, 5)
        println("Kth smallest number is: $result")
        matrix = arrayOf(intArrayOf(1, 5, 9), intArrayOf(10, 11, 13), intArrayOf(12, 13, 15))
        result = findKthSmallestBinarySearch(matrix, 8)
        println("Kth smallest number is: $result")
    }

    fun findKthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val minHeap = PriorityQueue { n1: Node, n2: Node -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col] }
        // put the 1st element of each row in the min heap
        // we don't need to push more than 'k' elements in the heap
        var i = 0
        while (i < matrix.size && i < k) {
            minHeap.add(Node(i, 0))
            i++
        }
        // take the smallest (top) element form the min heap, if the running count is equal
        // to k return the number. if the row of the top element has more elements, add the
        // next element to the heap
        var numberCount = 0
        var result = 0
        while (!minHeap.isEmpty()) {
            val node = minHeap.poll()
            result = matrix[node.row][node.col]
            if (++numberCount == k) {
                break
            }
            node.col++
            if (matrix[0].size > node.col) {
                minHeap.add(node)
            }
        }
        return result
    }

    //Alternate approach using Binary Search
    /*
    Time: O(log(max - min)) each iteration take O(N) for counting
    Overall: O(N * log(max - min))
    Space complexity: O(1)
     */
    fun findKthSmallestBinarySearch(matrix: Array<IntArray>, k: Int): Int {
        val n = matrix.size
        var start = matrix[0][0]
        var end = matrix[n - 1][n - 1]
        while (start < end) {
            // the mid number is NOT necessarily an element in the matrix
            val mid = start + (end - start) / 2
            // first number is the smallest and the second number is the largest
            // Keep track of the smallest number greater than the middle
            val smallLargePair = intArrayOf(matrix[0][0], matrix[n - 1][n - 1])
            // count all the numbers smaller than or equal to middle in the matrix
            val count = countLessEqual(matrix, mid, smallLargePair)
            if (count == k) return smallLargePair[0]
            if (count < k) start = smallLargePair[1] // search higher
            else end = smallLargePair[0] // search lower
        }
        return start
    }

    private fun countLessEqual(matrix: Array<IntArray>, mid: Int, smallLargePair: IntArray): Int {
        var count = 0
        val n = matrix.size
        var row = n - 1
        var col = 0
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col])
                row--
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col])
                count += row + 1
                col++
            }
        }
        return count
    }

    internal class Node(var row: Int, var col: Int)
}