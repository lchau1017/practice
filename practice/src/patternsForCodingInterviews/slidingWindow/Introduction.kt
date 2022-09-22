package patternsForCodingInterviews.slidingWindow

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1627871350324_0Unit
object Introduction {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 3, 2, 6, -1, 4, 1, 8, 2)
        val k = 5
        println(Arrays.toString(findAverage(k, array)))
        println(Arrays.toString(findAverageSlidingWindow(k, array)))
    }

    //SLIDING WINDOW
    fun findAverageSlidingWindow(k: Int, arr: IntArray): DoubleArray {
        val result = DoubleArray(arr.size - k + 1)
        var windowSum = 0.0
        var windowStart = 0
        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd].toDouble() //add the next element
            //Slide the window, we do not need to slide if we have not hit the required window
            //size of 'k'
            if (windowEnd >= k - 1) {
                result[windowStart] = windowSum / k //Calculate the average
                windowSum -= arr[windowStart].toDouble() //Substract element going out
                windowStart++ // slide the window ahead
            }
        }
        return result
    }

    /*
    Brut Force
    Time complexity: O(N * k) where  N is the number of elements in the input array.
     */
    fun findAverage(k: Int, arr: IntArray): DoubleArray {
        val result = DoubleArray(arr.size - k + 1)
        for (i in 0..arr.size - k) {
            var sum = 0.0
            for (j in i until i + k) {
                sum += arr[j].toDouble()
            }
            result[i] = sum / k //Calculate the average
        }
        return result
    }
}