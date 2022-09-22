package algorithms.unionFind

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3879/
object UnionByRank {
    /*
    Most efficient than Quick Union
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val uf = UnionFind(10)
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2)
        uf.union(2, 5)
        uf.union(5, 6)
        uf.union(6, 7)
        uf.union(3, 8)
        uf.union(8, 9)
        println(uf.connected(1, 5)) // true
        println(uf.connected(5, 7)) // true
        println(uf.connected(4, 9)) // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4)
        println(uf.connected(4, 9)) // true
    }

    internal class UnionFind(size: Int) {
        //Space: O(N)
        private val root: IntArray
        private val rank: IntArray

        //Time: O(N)
        init {
            root = IntArray(size)
            rank = IntArray(size)
            for (i in 0 until size) {
                root[i] = i
                rank[i] = 1
            }
        }

        //Time: O(log N)
        fun find(x: Int): Int {
            var x = x
            while (x != root[x]) {
                x = root[x]
            }
            return x
        }

        //Time: O(log N)
        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY
                } else {
                    root[rootY] = rootX
                    rank[rootX] += 1
                }
            }
        }

        //Time: O(logN)
        fun connected(x: Int, y: Int): Boolean {
            return find(x) == find(y)
        }
    }
}