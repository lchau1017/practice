package patternsForCodingInterviews.islandsMatrixTraversal

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d679f3bf35bUnit
object _2NumberOfDistinctIslands {
    @JvmStatic
    fun main(args: Array<String>) {
        /*
        System.out.println(findDistinctIslandsDFS(
                new int[][] {
                        { 1, 1, 0, 1, 1 },
                        { 1, 1, 0, 1, 1 },
                        { 0, 0, 0, 0, 0 },
                        { 0, 1, 1, 0, 1 },
                        { 0, 1, 1, 0, 1 }
                }));


         */
        println(
            findDistinctIslandsDFS(
                arrayOf(
                    intArrayOf(1, 1, 0, 1),
                    intArrayOf(0, 1, 1, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(1, 1, 0, 0),
                    intArrayOf(0, 1, 1, 0)
                )
            )
        )
    }

    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    fun findDistinctIslandsDFS(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        val visited = Array(rows) { BooleanArray(cols) }
        val islandSet: MutableSet<String> = HashSet()
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    val islandTraversal = StringBuilder()
                    traverseIslandDFS(matrix, visited, i, j, islandTraversal, "0") //origin
                    islandSet.add(islandTraversal.toString())
                }
            }
        }
        return islandSet.size
    }

    private fun traverseIslandDFS(
        matrix: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int,
        islandTraversal: StringBuilder, direction: String
    ) {
        if (x < 0 || x >= matrix.size || y < 0 || y >= matrix[0].size) { //check boundaries
            return
        }
        if (matrix[x][y] == 0 || visited[x][y]) { //Continue to move forward if true
            return
        }
        visited[x][y] = true
        islandTraversal.append(direction)
        // recursively visit all neighboring cells (horizontally & vertically)
        traverseIslandDFS(matrix, visited, x + 1, y, islandTraversal, "D") // down
        traverseIslandDFS(matrix, visited, x - 1, y, islandTraversal, "U") // up
        traverseIslandDFS(matrix, visited, x, y + 1, islandTraversal, "R") // right
        traverseIslandDFS(matrix, visited, x, y - 1, islandTraversal, "L") // left
        islandTraversal.append("B") // back
    }
}