package patternsForCodingInterviews.islandsMatrixTraversal

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d679f549f28Unit
object _1IslandPerimeter {
    @JvmStatic
    fun main(args: Array<String>) {
        /*
        System.out.println(findIslandPerimeter(
                new int[][] {
                        { 1, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));
        */
        println(
            findIslandPerimeter(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 1, 0, 0),
                    intArrayOf(0, 1, 0, 0),
                    intArrayOf(0, 1, 1, 0),
                    intArrayOf(0, 1, 0, 0)
                )
            )
        )
    }

    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    fun findIslandPerimeter(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        val visited = Array(rows) { BooleanArray(cols) }
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    return islandPerimeterDFS(matrix, visited, i, j)
                }
            }
        }
        return 0
    }

    private fun islandPerimeterDFS(matrix: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int): Int {
        if (x < 0 || x >= matrix.size || y < 0 || y >= matrix[0].size) {
            return 1 //return 1 since this a boundary cell initiated this DFS call
        }
        if (matrix[x][y] == 0) {
            return 1 // return 1 because of the shared side b/w a water and land cell
        }
        if (visited[x][y]) {
            return 0 // we have already taken care of this cell
        }
        visited[x][y] = true
        var edgeCount = 0
        // recursively visit all neighboring cells (horizontally & vertically)
        edgeCount += islandPerimeterDFS(matrix, visited, x + 1, y) // lower cell
        edgeCount += islandPerimeterDFS(matrix, visited, x - 1, y) // upper cell
        edgeCount += islandPerimeterDFS(matrix, visited, x, y + 1) // right cell
        edgeCount += islandPerimeterDFS(matrix, visited, x, y - 1) // left cell
        return edgeCount
    }
}