package patternsForCodingInterviews.topKElements

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744377975_106Unit
class _3FrequencyStack {
    var sequenceNumber = 0
    var maxHeap = PriorityQueue(ElementComparator())
    var frequencyMap: MutableMap<Int, Int> = HashMap()

    /*
    Time: O(logN)
    Space: O(N)
     */
    fun push(num: Int) {
        frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
        maxHeap.offer(Element(num, frequencyMap[num]!!, sequenceNumber++))
    }

    /*
    Time: O(logN)
     */
    fun pop(): Int {
        val num = maxHeap.poll().number
        //Decrement the frequency or remove if this is the last number
        if (frequencyMap[num]!! > 1) {
            frequencyMap[num] = frequencyMap[num]!! - 1
        } else {
            frequencyMap.remove(num)
        }
        return num
    }

    internal class ElementComparator : Comparator<Element> {
        override fun compare(e1: Element, e2: Element): Int {
            return if (e1.frequency != e2.frequency) {
                e2.frequency - e1.frequency
            } else e2.sequenceNumber - e1.sequenceNumber
            // if both elements have same frequency, return the one that was pushed later
            // If there is a tie, return the number which was pushed later.
        }
    }

    class Element(var number: Int, var frequency: Int, var sequenceNumber: Int)
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val frequencyStack = _3FrequencyStack()
            frequencyStack.push(1)
            frequencyStack.push(2)
            frequencyStack.push(3)
            frequencyStack.push(2)
            frequencyStack.push(1)
            frequencyStack.push(2)
            frequencyStack.push(5)
            println(frequencyStack.pop())
            println(frequencyStack.pop())
            println(frequencyStack.pop())
            println(frequencyStack.pop())
        }
    }
}