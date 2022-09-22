package patternsForCodingInterviews.islandsMatrixTraversal

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d52d75d7964Unit
object FloodFill {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            Arrays.deepToString(
                floodFill(
                    arrayOf(
                        intArrayOf(0, 1, 1, 1, 0),
                        intArrayOf(0, 0, 0, 1, 1),
                        intArrayOf(0, 1, 1, 1, 0),
                        intArrayOf(0, 1, 1, 0, 0),
                        intArrayOf(0, 0, 0, 0, 0)
                    ), 1, 3, 2
                )
            )
        )
        println(
            Arrays.deepToString(
                floodFill(
                    arrayOf(
                        intArrayOf(0, 0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0, 0),
                        intArrayOf(0, 0, 1, 1, 0),
                        intArrayOf(0, 0, 1, 0, 0),
                        intArrayOf(0, 0, 1, 0, 0)
                    ), 3, 2, 5
                )
            )
        )
    }

    //Approach DFS
    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    fun floodFill(matrix: Array<IntArray>, x: Int, y: Int, newColor: Int): Array<IntArray> {
        if (matrix[x][y] != newColor) {
            fillDFS(matrix, x, y, matrix[x][y], newColor)
        }
        return matrix
    }

    private fun fillDFS(matrix: Array<IntArray>, x: Int, y: Int, oldColor: Int, newColor: Int) {
        if (x < 0 || x >= matrix.size || y < 0 || y >= matrix[0].size) {
            return
        }
        if (matrix[x][y] != oldColor) { //Base case, if we have a different color with the old one it is our boundary
            return  //It is not the required color
        }
        matrix[x][y] = newColor //set with the new color
        // recursively visit all neighboring cells (horizontally & vertically)
        fillDFS(matrix, x + 1, y, oldColor, newColor) // lower cell
        fillDFS(matrix, x - 1, y, oldColor, newColor) // upper cell
        fillDFS(matrix, x, y + 1, oldColor, newColor) // right cell
        fillDFS(matrix, x, y - 1, oldColor, newColor) // left cell
    }
}