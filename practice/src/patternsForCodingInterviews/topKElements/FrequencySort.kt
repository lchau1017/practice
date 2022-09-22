package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744311147_98Unit
object FrequencySort {
    @JvmStatic
    fun main(args: Array<String>) {
        var result = sortCharacterByFrequency("Programming")
        println(
            "Here is the given string after sorting characters by frequency: "
                    + result
        )
        result = sortCharacterByFrequency("abcbab")
        println(
            "Here is the given string after sorting characters by frequency: "
                    + result
        )
    }

    /*
    Time: O(D * logD), D is the distinct characters
    worst case when all characters are unique -> O(N * logN) where N total number of characters
    Space: O(N)
     */
    fun sortCharacterByFrequency(str: String): String {
        // find the frequency of each character
        val characterFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in str.toCharArray()) {
            characterFrequencyMap[chr] = characterFrequencyMap.getOrDefault(chr, 0) + 1
        }
        val maxHeap =
            PriorityQueue { (_, value): Map.Entry<Char, Int>, (_, value1): Map.Entry<Char, Int> -> value1 - value }
        // add all characters to the max heap
        maxHeap.addAll(characterFrequencyMap.entries)
        // build a string, appending the most occurring characters first
        val sortedString = StringBuilder(str.length)
        while (!maxHeap.isEmpty()) {
            val (key, value) = maxHeap.poll()
            //Loop because we can have multiple frequency for this entry, we want to keep all the letters in the output
            for (i in 0 until value) {
                sortedString.append(key)
            }
        }
        return sortedString.toString()
    }
}