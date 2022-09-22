package academy.learnprogramming.hashtable.bucketSort

import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(54, 46, 83, 66, 95, 92, 43)
        bucketSort(intArray)
        for (i in intArray.indices) {
            println(intArray[i])
        }
    }

    fun bucketSort(input: IntArray) {
        val buckets: Array<MutableList<Int>> = arrayOf()
        for (i in buckets.indices) {
            // using linked lists for the buckets
            //buckets[i] = new LinkedList<Integer>();

            // using arraylists as the buckets
            buckets[i] = ArrayList()
        }
        for (i in input.indices) {
            buckets[hash(input[i])].add(input[i])
        }
        for (bucket in buckets) {
            Collections.sort(bucket)
        }
        var j = 0
        for (i in buckets.indices) {
            for (value in buckets[i]) {
                input[j++] = value
            }
        }
    }

    private fun hash(value: Int): Int {
        return value / 10
    }
}