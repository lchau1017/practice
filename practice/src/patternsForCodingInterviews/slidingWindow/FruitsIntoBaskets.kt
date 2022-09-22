package patternsForCodingInterviews.slidingWindow

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541018393_2Unit
object FruitsIntoBaskets {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Maximum number of fruits: " +
                    findLength(charArrayOf('A', 'B', 'C', 'A', 'C'))
        )
        println(
            "Maximum number of fruits: " +
                    findLength(charArrayOf('A', 'B', 'C', 'B', 'B', 'C'))
        )
    }

    /*
    Time: O(N) where N is the number of characters in the input array.
    Space: O(1), there can be a maximum of three types of fruits stored in the
    frequency map.
     */
    fun findLength(arr: CharArray): Int {
        var windowStart = 0
        var maxLength = 0
        val fruitFrequencyMap: MutableMap<Char, Int> = HashMap()
        //Try to extend the range [windowStart, windowsEnd]
        for (windowEnd in arr.indices) {
            fruitFrequencyMap[arr[windowEnd]] = fruitFrequencyMap.getOrDefault(
                arr[windowEnd], 0
            ) + 1
            //shrink the sliding window until we are left with '2' fruits in
            //the frequency
            while (fruitFrequencyMap.size > 2) {
                fruitFrequencyMap[arr[windowStart]] = fruitFrequencyMap[arr[windowStart]]!! - 1
                if (fruitFrequencyMap[arr[windowStart]] == 0) {
                    fruitFrequencyMap.remove(arr[windowStart])
                }
                windowStart++
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }
}