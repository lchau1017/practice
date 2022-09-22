package patternsForCodingInterviews.islandsMatrixTraversal

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d6145eebddeUnit
object NumberOfClosedIslands {
    @JvmStatic
    fun main(args: Array<String>) {
        //Closed island  -> means surrounded by the water
        println(
            countClosedIslands(
                arrayOf(
                    intArrayOf(1, 1, 0, 0, 0),
                    intArrayOf(0, 1, 0, 0, 0),
                    intArrayOf(0, 0, 1, 1, 0),
                    intArrayOf(0, 1, 1, 0, 0),
                    intArrayOf(0, 0, 0, 0, 0)
                )
            )
        )

        /*
        System.out.println(countClosedIslands(
                new int[][] {
                        { 0, 0, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 }
                }));

         */
    }

    //Approach DFS
    /*
    Time: O(N)
    Space: O(1)
     */
    fun countClosedIslands(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        var countClosedIslands = 0
        val visited = Array(rows) { BooleanArray(cols) }
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    if (isClosedIslandDFS(matrix, visited, i, j)) {
                        countClosedIslands++
                    }
                }
            }
        }
        return countClosedIslands
    }

    private fun isClosedIslandDFS(matrix: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int): Boolean {
        if (x < 0 || x >= matrix.size || y < 0 || y >= matrix[0].size) //Boundaries
            return false // returning false since the island is touching an edge
        if (matrix[x][y] == 0 || visited[x][y]) return true // returning true as the island is surrounded by water
        visited[x][y] = true
        var isClosed: Boolean
        // recursively visit all neighboring cells (horizontally & vertically)
        // a &= b; is equivalent to a = a & b;.
        isClosed = isClosedIslandDFS(matrix, visited, x + 1, y) // lower cell
        // =& if true all condition are true
        isClosed = isClosed and isClosedIslandDFS(matrix, visited, x - 1, y) // upper cell
        isClosed = isClosed and isClosedIslandDFS(matrix, visited, x, y + 1) // right cell
        isClosed = isClosed and isClosedIslandDFS(matrix, visited, x, y - 1) // left cell
        return isClosed
    }
}