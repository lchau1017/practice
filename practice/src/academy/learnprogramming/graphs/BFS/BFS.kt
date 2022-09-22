package academy.learnprogramming.graphs.BFS

import java.util.*

//https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
object BFS {
    /*
    Finding the shortest path between two nodes u and v,
    with path length measured by the total number of edges (an advantage over depth–first search).
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val g = Graph(4)
        g.addEdge(0, 1)
        g.addEdge(0, 2)
        g.addEdge(1, 2)
        g.addEdge(2, 0)
        g.addEdge(2, 3)
        g.addEdge(3, 3)
        g.BFS(2)
    }
}

internal class Graph( //Vertices
    private val V: Int
) {
    private val adj: Array<LinkedList<Int>> = arrayOf()

    init {
        for (i in 0 until V) {
            adj[i] = LinkedList<Int>()
        }
    }

    //Print BFS traversal from a given source
    /*
    Time complexity: O(V + E) where V is the number of nodes and E is the number of edges.
    Space complexity: O(V) used by visited array.
     */
    fun BFS(s: Int) {
        var s = s
        val visited = BooleanArray(V)
        //Create Queue for BFS
        val queue: Queue<Int> = LinkedList()
        //Mark the current node as visited
        visited[s] = true
        queue.add(s)
        while (!queue.isEmpty()) {
            s = queue.poll()
            println(s)
            //Get all adjacent vertices if not visited, then mark it visited and enqueue it.
            val itr: Iterator<Int> = adj[s].iterator()
            while (itr.hasNext()) {
                val n = itr.next()
                if (!visited[n]) {
                    visited[n] = true
                    queue.add(n)
                }
            }
        }
    }

    fun addEdge(v: Int, w: Int) {
        adj[v].add(w)
    }
}