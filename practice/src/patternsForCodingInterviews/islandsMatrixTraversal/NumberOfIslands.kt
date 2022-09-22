package patternsForCodingInterviews.islandsMatrixTraversal

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1654828199398_262Unit
object NumberOfIslands {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            countIslandsWithVisitedMatrix(
                arrayOf(
                    intArrayOf(0, 1, 1, 1, 0),
                    intArrayOf(0, 0, 0, 1, 1),
                    intArrayOf(0, 1, 1, 1, 0),
                    intArrayOf(0, 1, 1, 0, 0),
                    intArrayOf(0, 0, 0, 0, 0)
                )
            )
        )
        println(
            countIslandsWithVisitedMatrix(
                arrayOf(
                    intArrayOf(1, 1, 1, 0, 0),
                    intArrayOf(0, 1, 0, 0, 1),
                    intArrayOf(0, 0, 1, 1, 0),
                    intArrayOf(0, 0, 1, 0, 0),
                    intArrayOf(0, 0, 1, 0, 0)
                )
            )
        )
    }

    //Approach BFS with visited matrix
    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    fun countIslandsWithVisitedMatrix(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        val visited = Array(rows) { BooleanArray(cols) }
        var totalIsland = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                // if the cell has not been visited before and is a land
                if (!visited[i][j] && matrix[i][j] == 1) {
                    // we have found an island
                    totalIsland++
                    visitIslandBFS(matrix, visited, i, j)
                }
            }
        }
        return totalIsland
    }

    private fun visitIslandBFS(matrix: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int) {
        val neighbors: Queue<IntArray> = LinkedList()
        neighbors.add(intArrayOf(x, y))
        while (!neighbors.isEmpty()) {
            val row = neighbors.peek()[0]
            val col = neighbors.peek()[1]
            neighbors.poll()
            if (isNotAValidCell(matrix, row, col)) //If condition is true, not valid
                continue  // continue, if it is not a valid cell
            if (matrix[row][col] == 0 || visited[row][col]) continue  // continue if the cell is water or visited
            visited[row][col] = true // mark the visited array

            // insert all neighboring cells to the queue for BFS
            neighbors.add(intArrayOf(row + 1, col)) // lower cell
            neighbors.add(intArrayOf(row - 1, col)) // upper cell
            neighbors.add(intArrayOf(row, col + 1)) // right cell
            neighbors.add(intArrayOf(row, col - 1)) // left cell
        }
    }

    //Approach BFS:
    /*
    Time: O(M*N)
    Space: O(min(M, N) worst case when matrix is completely filled with land cells.
    Size of the Q can grow up to min(M, N)
     */
    fun countIslandsBFS(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        var totalIslands = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (matrix[i][j] == 1) { // only if the cell is a land
                    // we have found an island
                    totalIslands++
                    visitIslandBFS(matrix, i, j)
                }
            }
        }
        return totalIslands
    }

    private fun visitIslandBFS(matrix: Array<IntArray>, x: Int, y: Int) {
        val neighbors: Queue<IntArray> = LinkedList()
        neighbors.add(intArrayOf(x, y))
        while (!neighbors.isEmpty()) {
            val row = neighbors.peek()[0]
            val col = neighbors.peek()[1]
            neighbors.poll()
            if (isNotAValidCell(matrix, row, col)) continue  // continue, if it is not a valid cell
            if (matrix[row][col] == 0) continue  // continue if it is a water cell
            matrix[row][col] = 0 // mark the cell visited by making it a water cell

            // insert all neighboring cells to the queue for BFS
            neighbors.add(intArrayOf(row + 1, col)) // lower cell
            neighbors.add(intArrayOf(row - 1, col)) // upper cell
            neighbors.add(intArrayOf(row, col + 1)) // right cell
            neighbors.add(intArrayOf(row, col - 1)) // left cell
        }
    }

    //Approach DFS:
    /*
    Time: O(M*N) where M number of rows and N number of columns.
     */
    fun countIslandsDFS(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        var totalIslands = 0
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (matrix[i][j] == 1) { //If cell is a land
                    totalIslands++
                    visitIslandsDFS(matrix, i, j)
                }
            }
        }
        return totalIslands
    }

    private fun visitIslandsDFS(matrix: Array<IntArray>, x: Int, y: Int) {
        if (isNotAValidCell(matrix, x, y)) { //No valid cell
            return
        }
        if (matrix[x][y] == 0) { //If water cell
            return
        }
        matrix[x][y] = 0 //mark the cell as visited b y making it a water cell
        //Recursively visit all neighbors
        visitIslandsDFS(matrix, x + 1, y) // lower cell
        visitIslandsDFS(matrix, x - 1, y) // upper cell
        visitIslandsDFS(matrix, x, y + 1) // right cell
        visitIslandsDFS(matrix, x, y - 1) // left cell
    }

    private fun isNotAValidCell(matrix: Array<IntArray>, row: Int, col: Int): Boolean {
        return row < 0 || row >= matrix.size || col < 0 || col >= matrix[0].size
    }
}