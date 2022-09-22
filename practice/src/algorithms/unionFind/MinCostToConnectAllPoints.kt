package algorithms.unionFind

import java.util.*

class MinCostToConnectAllPoints {
    // Kruskal's Algorithm
    /*
    Use to construct a minimum spanning tree
     */
    fun minCostConnectPoints(points: Array<IntArray>?): Int {
        if (points == null || points.size == 0) {
            return 0
        }
        val size = points.size
        val pq = PriorityQueue { x: Edge, y: Edge -> x.cost - y.cost }
        val uf: UnionFind = UnionFind(size)
        for (i in 0 until size) {
            val coordinate1 = points[i]
            for (j in i + 1 until size) {
                val coordinate2 = points[j]
                // Calculate the distance between two coordinates.
                val cost = Math.abs(coordinate1[0] - coordinate2[0]) +
                        Math.abs(coordinate1[1] - coordinate2[1])
                val edge: Edge = Edge(i, j, cost)
                pq.add(edge)
            }
        }
        var result = 0
        var count = size - 1
        while (!pq.isEmpty() && count > 0) {
            val edge = pq.poll()
            if (!uf.connected(edge.point1, edge.point2)) {
                uf.union(edge.point1, edge.point2)
                result += edge.cost
                count--
            }
        }
        return result
    }

    internal inner class Edge(var point1: Int, var point2: Int, var cost: Int)
    internal inner class UnionFind(size: Int) {
        var root: IntArray
        var rank: IntArray

        init {
            root = IntArray(size)
            rank = IntArray(size)
            for (i in 0 until size) {
                root[i] = i
                rank[i] = 1
            }
        }

        fun find(x: Int): Int {
            return if (x == root[x]) {
                x
            } else find(root[x]).also { root[x] = it }
        }

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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points =
                arrayOf(intArrayOf(0, 0), intArrayOf(2, 2), intArrayOf(3, 10), intArrayOf(5, 2), intArrayOf(7, 0))
            val solution = MinCostToConnectAllPoints()
            print("Minimum Cost to Connect Points = ")
            println(solution.minCostConnectPoints(points))
        }
    }
}