package algorithms.unionFind

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3878/
object QuickFind {
    /*
    Implementation with Quick Find: in this case,
    the time complexity of the find function will be O(1).
    However, the union function
    will take more time with the time complexity of O(N).
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
        private val root: IntArray

        //Time: O(N)
        //Space: O(N) to store the array of size N
        init {
            root = IntArray(size)
            for (i in 0 until size) {
                root[i] = i
            }
        }

        //O(1)
        fun connected(x: Int, y: Int): Boolean {
            return find(x) == find(y)
        }

        //O(1)
        fun find(x: Int): Int {
            return this.root[x]
        }

        //O(N)
        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX != rootY) {
                for (i in root.indices) {
                    if (root[i] == rootY) {
                        root[i] = rootX
                    }
                }
            }
        }
    }
}