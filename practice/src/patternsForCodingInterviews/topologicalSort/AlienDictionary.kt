package patternsForCodingInterviews.topologicalSort

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744560693_125Unit
object AlienDictionary {
    /*
    Video example:
    https://www.youtube.com/watch?v=L5n9AA7cHDY&ab_channel=codeTree
    until 3 min 03, easy to understand
     */
    @JvmStatic
    fun main(args: Array<String>) {
        var result = findOrder(arrayOf("ba", "bc", "ac", "cab"))
        println("Character order: $result")
        result = findOrder(arrayOf("cab", "aaa", "aab"))
        println("Character order: $result")
        result = findOrder(arrayOf("ywx", "wz", "xww", "xz", "zyy", "zwz"))
        println("Character order: $result")
    }

    /*
    Time: O(V + E)
    Space: O(V + E)
     */
    fun findOrder(words: Array<String>?): String {
        if (words == null || words.size == 0) {
            return ""
        }
        //A. Initialize the graph
        val inDegreee: MutableMap<Char, Int> = HashMap()
        val graph: MutableMap<Char, MutableList<Char>> = HashMap()
        for (word in words) {
            for (character in word.toCharArray()) {
                inDegreee[character] = 0
                graph[character] = ArrayList()
            }
        }
        //B Build the graph
        for (i in 0 until words.size - 1) {
            //Find ordering of characters from adjacent words
            val w1 = words[i]
            val w2 = words[i + 1]
            for (j in 0 until Math.min(w1.length, w2.length)) {
                val parent = w1[j]
                val child = w2[j]
                if (parent != child) { //If the 2 characters are different
                    graph[parent]!!.add(child)
                    inDegreee[child] = inDegreee[child]!! + 1
                    //Only the first different character between the two words
                    //will help us find the order
                    break
                }
            }
        }
        //C Find all sources with O in-Degree
        val sources: Queue<Char> = LinkedList()
        for ((key, value) in inDegreee) {
            if (value == 0) {
                sources.add(key)
            }
        }
        // d. For each source, add it to the
        // sortedOrder and subtract one from all of its
        // children's in-degrees if a child's
        // in-degree becomes zero, add it to sources queue
        val sortedOrder = StringBuilder()
        while (!sources.isEmpty()) {
            val vertex = sources.poll()
            sortedOrder.append(vertex)
            //get the node's children to decrement their in-degree
            val children: List<Char> = graph[vertex]!!
            for (child in children) {
                inDegreee[child] = inDegreee[child]!! - 1
                if (inDegreee[child] == 0) {
                    sources.add(child)
                }
            }
        }
        // if sortedOrder doesn't contain all characters, there is a cyclic dependency
        // between characters, therefore, we  will not be able to find the correct ordering
        // of the characters
        return if (sortedOrder.length != inDegreee.size) {
            ""
        } else sortedOrder.toString()
    }
}