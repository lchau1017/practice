package algorithms.unionFind

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3911/
class NumberOfConnectedComponentsInAnUndirectedGraph {
    //✔️ Solution 1c: Union-Find with Path Compression and Union by Size ~ 1ms
    //https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/discuss/516491/Java-Union-Find-DFS-BFS-Solutions-Complexity-Explain-Clean-code
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val parent = IntArray(n)
        val size = IntArray(n)
        for (i in 0 until n) {
            parent[i] = i
            size[i] = 1
        }
        var components = n
        for (e in edges) {
            val p1 = findParent(parent, e[0])
            val p2 = findParent(parent, e[1])
            //Edges are not connected
            if (p1 != p2) {
                if (size[p1] < size[p2]) { // Merge small size to large size
                    size[p2] += size[p1]
                    //Connection
                    parent[p1] = p2
                } else {
                    size[p1] += size[p2]
                    //Connection
                    parent[p2] = p1
                }
                components--
            }
        }
        return components
    }

    private fun findParent(parent: IntArray, i: Int): Int {
        return if (i == parent[i]) i else findParent(parent, parent[i]).also {
            parent[i] = it // Path compression
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val edges = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(3, 4))
            val edges1 = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4))
            val obj = NumberOfConnectedComponentsInAnUndirectedGraph()
            println(obj.countComponents(5, edges))
            //System.out.println(obj.countComponents(5, edges1));
        }
    }
}