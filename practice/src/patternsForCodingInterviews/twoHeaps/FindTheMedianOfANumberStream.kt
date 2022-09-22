package patternsForCodingInterviews.twoHeaps

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743994867_62Unit
class FindTheMedianOfANumberStream {
    var maxHeap //Containing first half of numbers
            : PriorityQueue<Int>
    var minHeap //Containing the second half of numbers
            : PriorityQueue<Int>

    init {
        maxHeap = PriorityQueue { a: Int, b: Int -> b - a } //Higher number is the priority
        minHeap = PriorityQueue { a: Int, b: Int -> a - b } //Smaller nuumber is the priority
    }

    /*
    Time: Due to insertion in the heap, take O(log N)
    Space: O(N) storing all the numbers
     */
    fun insertNum(num: Int) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num)
        } else {
            minHeap.add(num)
        }
        //either both the heaps will have equal number of elements or max-heap wil have one more element than min-heap
        if (maxHeap.size > minHeap.size + 1) {
            minHeap.add(maxHeap.poll())
        } else if (maxHeap.size < minHeap.size) {
            maxHeap.add(minHeap.poll())
        }
    }

    /*
    Time: O(1)
     */
    fun findMedian(): Double {
        return if (maxHeap.size == minHeap.size) {
            //We have even number of elements, take the average of middle two elements
            maxHeap.peek() / 2.0 + minHeap.peek() / 2.0
        } else maxHeap.peek().toDouble()
        //Because max-heap will have one more element than the mid-heap
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val medianOfAStream = FindTheMedianOfANumberStream()
            medianOfAStream.insertNum(3)
            medianOfAStream.insertNum(1)
            println("The median is: " + medianOfAStream.findMedian())
            medianOfAStream.insertNum(5)
            println("The median is: " + medianOfAStream.findMedian())
            medianOfAStream.insertNum(4)
            println("The median is: " + medianOfAStream.findMedian())
        }
    }
}