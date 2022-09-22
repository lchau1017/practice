package algorithms.unionFind

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3910/
class GraphValidTree {
    /*
    Time: O(N . & (N))
     */
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        //Graph must contains n - 1 edges
        if (edges.size != n - 1) return false
        val unionFind = UnionFind(n)
        // Add each edge. Check if a merge happened, because if it
        // didn't, there must be a cycle.
        for (edge in edges) {
            val A = edge[0]
            val B = edge[1]
            if (!unionFind.union(A, B)) {
                return false
            }
        }
        //No cycle
        return true
    }

    //Union Find approach
    //Optimizations path compression and union by size
    internal class UnionFind(n: Int) {
        private val parent: IntArray
        private val size: IntArray

        init {
            parent = IntArray(n)
            size = IntArray(n)
            for (node in 0 until n) {
                parent[node] = node
                size[node] = 1
            }
        }

        fun find(A: Int): Int {
            //Step 1: find the root
            return if (A == parent[A]) {
                A
            } else find(parent[A]).also { parent[A] = it }
        }

        // The union method, with optimization union by size. It returns True if a
        // merge happened, False if otherwise.
        fun union(A: Int, B: Int): Boolean {
            // Find the roots for A and B.
            val rootA = find(A)
            val rootB = find(B)
            // Check if A and B are already in the same set.
            if (rootA == rootB) {
                return false
            }
            // We want to ensure the larger set remains the root.
            if (size[rootA] < size[rootB]) {
                // Make rootB the overall root.
                parent[rootA] = rootB
                // The size of the set rooted at B is the sum of the 2.
                size[rootB] += size[rootA]
            } else {
                // Make rootA the overall root.
                parent[rootB] = rootA
                // The size of the set rooted at A is the sum of the 2.
                size[rootA] += size[rootB]
            }
            return true
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val edges = arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(0, 3), intArrayOf(1, 4))
            val edges1 =
                arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(1, 3), intArrayOf(1, 4))
            val obj = GraphValidTree()
            println(obj.validTree(5, edges))
            println(obj.validTree(6, edges1))
        }
    }
}