package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744369697_104Unit
object _1RearrangeStringKDistanceApart {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Reorganized string: " +
                    reorganizeString("mmpp", 2)
        )
        println(
            "Reorganized string: " +
                    reorganizeString("Programming", 3)
        )
        println(
            "Reorganized string: " +
                    reorganizeString("aab", 2)
        )
        println(
            "Reorganized string: " +
                    reorganizeString("aappa", 3)
        )
    }

    /*
    Time: O(N * logN) where N is the number of characters in the input
    Space: O(N) in worst case, we need to store all the N characters in the HashMap
     */
    fun reorganizeString(str: String, k: Int): String {
        if (k <= 1) {
            return str
        }
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in str.toCharArray()) {
            charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1
        }
        val maxHeap =
            PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_, value): Map.Entry<Char, Int>, (_, value1): Map.Entry<Char, Int> -> value1 - value }
        //Add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entries)
        val queue: Queue<MutableMap.MutableEntry<Char, Int>> = LinkedList()
        val resultString = StringBuilder()
        while (!maxHeap.isEmpty()) {
            val currentEntry = maxHeap.poll()
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.key)
            currentEntry.setValue(currentEntry.value - 1)
            //can keep track of previous  character in a queue to insert them back in the heap after K iterations
            queue.offer(currentEntry)
            //After k iteration, we re-insert the character after k iterations
            if (queue.size == k) {
                val entry = queue.poll()
                if (entry.value > 0) {
                    maxHeap.add(entry)
                }
            }
        }
        // if we were successful in appending all the characters to the result string,
        // return it
        return if (resultString.length == str.length) resultString.toString() else ""
    }
}