package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744359516_103Unit
object RearrangeString {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Rearranged string: " +
                    rearrangeString("aappp")
        )
        println(
            "Rearranged string: " +
                    rearrangeString("Programming")
        )
        println(
            "Rearranged string: " +
                    rearrangeString("aapa")
        )
    }

    /*
    Time: O(N * logN)
    Space: O(N)
     */
    fun rearrangeString(str: String): String {
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in str.toCharArray()) {
            charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1
        }
        val maxHeap =
            PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_, value): Map.Entry<Char, Int>, (_, value1): Map.Entry<Char, Int> -> value1 - value }
        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entries)
        var previousEntry: MutableMap.MutableEntry<Char, Int>? = null
        val resultString = StringBuilder()
        while (!maxHeap.isEmpty()) {
            val currentEntry = maxHeap.poll()
            // add the previous entry back in the heap if its frequency is greater than zero
            if (previousEntry != null && previousEntry.value > 0) {
                maxHeap.offer(previousEntry)
            }
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.key)
            currentEntry.setValue(currentEntry.value - 1)
            previousEntry = currentEntry
        }
        // if we were successful in appending all the characters to the result string,
        // return it
        return if (resultString.length == str.length) resultString.toString() else ""
    }
}