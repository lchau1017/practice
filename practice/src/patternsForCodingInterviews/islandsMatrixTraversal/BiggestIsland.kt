package patternsForCodingInterviews.islandsMatrixTraversal

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d53be009288Unit
object BiggestIsland {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            maxAreaOfIsland(
                arrayOf(
                    intArrayOf(1, 1, 1, 0, 0),
                    intArrayOf(0, 1, 0, 0, 1),
                    intArrayOf(0, 0, 1, 1, 0),
                    intArrayOf(0, 1, 1, 0, 0),
                    intArrayOf(0, 0, 1, 0, 0)
                )
            )
        )
    }

    //Approach DFS
    /*
    Time: O(M * N)
    Space: DFS recursion stack can go M*N whre M numbers of rows and N is the number of columns
     */
    fun maxAreaOfIsland(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        var biggestIslandArea = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (matrix[i][j] == 1) {
                    biggestIslandArea = Math.max(
                        biggestIslandArea,
                        visitIslandDFS(matrix, i, j)
                    )
                }
            }
        }
        return biggestIslandArea
    }

    private fun visitIslandDFS(matrix: Array<IntArray>, row: Int, col: Int): Int {
        if (row < 0 || row >= matrix.size || col < 0 || col >= matrix[0].size) {
            return 0 // no valid cell
        }
        if (matrix[row][col] == 0) {
            return 0 // is a water cell
        }
        matrix[row][col] = 0 // mark the cell
        var area = 1 //Counting current cell
        area += visitIslandDFS(matrix, row + 1, col) // lower cell
        area += visitIslandDFS(matrix, row - 1, col) // upper cell
        area += visitIslandDFS(matrix, row, col + 1) // right cell
        area += visitIslandDFS(matrix, row, col - 1) // left cell
        return area
    }
}