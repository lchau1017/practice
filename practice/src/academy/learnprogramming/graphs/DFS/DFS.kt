package academy.learnprogramming.graphs.DFS

import java.util.*

//https://www.geeksforgeeks.org/iterative-depth-first-traversal/
object DFS {
    @JvmStatic
    fun main(args: Array<String>) {
        val g = Graph(5)
        g.addEdge(1, 0)
        g.addEdge(0, 2)
        g.addEdge(2, 1)
        g.addEdge(0, 3)
        g.addEdge(1, 4)
        g.DFS(0)
    }
}

internal class Graph( //Number of vertices;
    var V: Int
) {
    var adj // adjacency lists
            : Array<LinkedList<Int>> = arrayOf()

    init {
        for (i in adj.indices) {
            adj[i] = LinkedList()
        }
    }

    //Print all not yet visited vertices reachable from s
    /*
    Time complexity: O(V + E): where V is the number of vertices and E is the number of edges in the graph.
    Space complexity: O(V) Since an extra visited array is needed
     */
    fun DFS(s: Int) {
        var s = s
        val visited: MutableList<Boolean> = ArrayList()
        for (i in 0 until V) {
            visited[i] = false
        }
        //Create Stack for DFS
        val stack = Stack<Int>()
        //Push the current source node
        stack.push(s)
        while (!stack.isEmpty()) {
            //Pop vertex from stack
            s = stack.pop()
            //Stack may contain same vertex, check if already visited
            if (!visited[s]) {
                println(s)
                visited[s] = true
            }
            //Get all adjacent vertices, if adjacent has not been visited,
            //then push it to the stack
            val itr: Iterator<Int> = adj[s].iterator()
            while (itr.hasNext()) {
                val v = itr.next()
                if (!visited[v]) {
                    stack.push(v)
                }
            }
        }
    }

    //Add an edge to graph
    fun addEdge(v: Int, w: Int) {
        adj[v].add(w)
    }
}