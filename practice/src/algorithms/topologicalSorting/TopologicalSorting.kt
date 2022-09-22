package algorithms.topologicalSorting

import java.util.*

//https://www.geeksforgeeks.org/topological-sorting/
//Video:
//https://www.youtube.com/watch?v=Q9PIxaNGnig
/*
Topological Sorting is mainly used for scheduling jobs from
the given dependencies among jobs.
 */
object TopologicalSorting {
    // Driver code
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a graph given in the above diagram
        val g = Graph(6)
        g.addEdge(5, 2)
        g.addEdge(5, 0)
        g.addEdge(4, 0)
        g.addEdge(4, 1)
        g.addEdge(2, 3)
        g.addEdge(3, 1)
        println(
            "Following is a Topological "
                    + "sort of the given graph"
        )
        // Function Call
        g.topologicalSort()
    }

    internal class Graph( // No. of vertices
        private val V: Int
    ) {
        // Adjacency List as ArrayList of ArrayList's
        private val adj: MutableList<MutableList<Int>>

        // Constructor
        init {
            adj = ArrayList(V)
            for (i in 0 until V) adj.add(ArrayList())
        }

        // Function to add an edge into the graph
        fun addEdge(v: Int, w: Int) {
            adj[v].add(w)
        }

        // A recursive function used by topologicalSort
        fun topologicalSortUtil(
            v: Int, visited: BooleanArray,
            stack: Stack<Int>
        ) {
            // Mark the current node as visited.
            visited[v] = true
            var i: Int

            // Recur for all the vertices adjacent
            // to thisvertex
            val it: Iterator<Int> = adj[v].iterator()
            while (it.hasNext()) {
                i = it.next()
                if (!visited[i]) topologicalSortUtil(i, visited, stack)
            }

            // Push current vertex to stack
            // which stores result
            //Because this node does not have non-visited neighbors
            stack.push(v)
        }

        // The function to do Topological Sort.
        // It uses recursive topologicalSortUtil()
        /*
    Time: O(V + E) (same as DFS)
    Space: O(V), the extra space is needed for the stack
     */
        fun topologicalSort() {
            val stack = Stack<Int>()

            // Mark all the vertices as not visited
            val visited = BooleanArray(V)
            for (i in 0 until V) visited[i] = false

            // Call the recursive helper
            // function to store
            // Topological Sort starting
            // from all vertices one by one
            for (i in 0 until V) if (visited[i] == false) topologicalSortUtil(i, visited, stack)

            // Print contents of stack
            while (stack.empty() == false) print(stack.pop().toString() + " ")
        }
    }
}