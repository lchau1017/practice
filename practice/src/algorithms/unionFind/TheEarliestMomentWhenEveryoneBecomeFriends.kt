package algorithms.unionFind

import java.util.*

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3912/
class TheEarliestMomentWhenEveryoneBecomeFriends {
    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
        //Must sort the events in chronological order
        Arrays.sort(logs) { log1, log2 -> //Increasing order
            Integer.compare(log1[0], log2[0])
        }
        //Treat each individual as a separate group
        var groupCount = n
        val uf = UnionFind(n)
        for (log in logs) {
            val timestamp = log[0]
            val friendA = log[1]
            val friendB = log[2]
            //We merge the groups along the way
            if (uf.union(friendA, friendB)) { //If true, we merged successfully
                groupCount -= 1
            }
            //The moment when all individuals are connected to each other
            if (groupCount == 1) {
                return timestamp
            }
        }
        //There are still more than one groups left
        return -1
    }

    internal class UnionFind(size: Int) {
        private val group: IntArray
        private val rank: IntArray

        init {
            group = IntArray(size)
            rank = IntArray(size)
            for (i in 0 until size) {
                group[i] = i
                rank[i] = 1
            }
        }

        fun find(person: Int): Int {
            return if (person == group[person]) {
                person
            } else find(group[person]).also { group[person] = it }
        }

        /*
        merge the two groups that x and y belong
        return true if the groups are merged
         */
        fun union(a: Int, b: Int): Boolean {
            val groupA = find(a)
            val groupB = find(b)
            //Two people shared the same group
            if (groupA == groupB) {
                return false
            }
            // Otherwise, merge the two groups.
            val isMerged = true
            // Merge the lower-rank group into the higher-rank group.
            if (rank[groupA] > rank[groupB]) {
                group[groupB] = groupA
            } else if (rank[groupA] < rank[groupB]) {
                group[groupA] = groupB
            } else {
                group[groupB] = groupA
                rank[groupA] += 1
            }
            return isMerged
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val logs = arrayOf(
                intArrayOf(20190101, 0, 1),
                intArrayOf(20190104, 3, 4),
                intArrayOf(20190107, 2, 3),
                intArrayOf(20190211, 1, 5),
                intArrayOf(20190224, 2, 4),
                intArrayOf(20190301, 0, 3),
                intArrayOf(20190312, 1, 2),
                intArrayOf(20190322, 4, 5)
            )
            val logs1 = arrayOf(
                intArrayOf(0, 2, 0),
                intArrayOf(1, 0, 1),
                intArrayOf(3, 0, 3),
                intArrayOf(4, 1, 2),
                intArrayOf(7, 3, 1)
            )
            val obj = TheEarliestMomentWhenEveryoneBecomeFriends()
            //20190301
            println(obj.earliestAcq(logs, 6))
            //3
            println(obj.earliestAcq(logs1, 4))
        }
    }
}