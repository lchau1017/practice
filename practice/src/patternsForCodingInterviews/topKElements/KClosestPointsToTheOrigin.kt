package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744290707_95Unit
object KClosestPointsToTheOrigin {
    @JvmStatic
    fun main(args: Array<String>) {
        val points = arrayOf(Point(1, 3), Point(3, 4), Point(2, -1))
        val result = findClosestPoints(points, 2)
        print("Here are the k points closest the origin: ")
        for (p in result) print("[" + p.x + " , " + p.y + "] ")
    }

    /*
    Time: (N * logK)
    Space: O(K), we need to store 'K' points in the heap
     */
    fun findClosestPoints(points: Array<Point>, k: Int): List<Point> {
        val maxHeap = PriorityQueue { p1: Point, p2: Point -> p2.distFromOrigin() - p1.distFromOrigin() }
        // put first 'k' points in the max heap
        for (i in 0 until k) {
            maxHeap.add(points[i]) //Only k points in the heap.
        }
        // go through the remaining points of the input array, if a point is closer to the
        // origin than the top point of the max-heap, remove the top point from heap and add
        // the point from the input array
        for (i in k until points.size) {
            if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) { //True is closer to the origin
                maxHeap.poll()
                maxHeap.add(points[i])
            }
        }
        // the heap has 'k' points closest to the origin, return them in a list
        return ArrayList(maxHeap)
    }

    class Point(var x: Int, var y: Int) {
        fun distFromOrigin(): Int {
            return x * x + y * y
        }
    }
}