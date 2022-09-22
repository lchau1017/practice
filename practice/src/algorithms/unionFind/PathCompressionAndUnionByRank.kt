package algorithms.unionFind

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3843/
object PathCompressionAndUnionByRank {
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
        private val root: IntArray

        // Use a rank array to record the height of each vertex, i.e., the "rank" of each vertex.
        private val rank: IntArray

        init {
            root = IntArray(size)
            rank = IntArray(size)
            for (i in 0 until size) {
                root[i] = i
                rank[i] = 1 // The initial "rank" of each vertex is 1, because each of them is
                // a standalone vertex with no connection to other vertices.
            }
        }

        // The find function here is the same as that in the disjoint set with path compression.
        // Optimized with Path compression
        fun find(x: Int): Int {
            return if (x == root[x]) {
                x
            } else find(root[x]).also { root[x] = it }
        }

        // The union function with union by rank
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

        fun connected(x: Int, y: Int): Boolean {
            return find(x) == find(y)
        }
    }
}