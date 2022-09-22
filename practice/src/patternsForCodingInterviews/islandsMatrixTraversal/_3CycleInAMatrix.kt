package patternsForCodingInterviews.islandsMatrixTraversal

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d679f1e93e0Unit
object _3CycleInAMatrix {
    @JvmStatic
    fun main(args: Array<String>) {
        /*
        System.out.println(hasCycle(
                new char[][]{
                        {'a', 'a', 'a', 'a'},
                        {'b', 'a', 'c', 'a'},
                        {'b', 'a', 'c', 'a'},
                        {'b', 'a', 'a', 'a'}
                }));
         */
        /*
        System.out.println(hasCycle(
                new char[][]{
                        {'a', 'a', 'a', 'a'},
                        {'a', 'b', 'b', 'a'},
                        {'a', 'b', 'a', 'a'},
                        {'a', 'a', 'a', 'c'}
                }));

        / *
        System.out.println(hasCycle(
                new char[][]{
                        {'a', 'b', 'e', 'b'},
                        {'b', 'b', 'b', 'b'},
                        {'b', 'c', 'c', 'd'},
                        {'c', 'c', 'd', 'd'}
                }));

         */
        println(hasCycle(arrayOf(charArrayOf('a', 'a', 'a'), charArrayOf('a', 'b', 'a'), charArrayOf('a', 'a', 'a'))))
    }

    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    fun hasCycle(matrix: Array<CharArray>): Boolean {
        val rows = matrix.size
        val cols = matrix[0].size
        val visited = Array(rows) { BooleanArray(cols) }
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (!visited[i][j]) {
                    //-1 and -1 for prevX and prevX are simply used to initialize a value which is not contained in the 2D array
                    //Avoid infinite loop
                    if (containsCycleDFS(matrix, visited, matrix[i][j], i, j, -1, -1)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun containsCycleDFS(
        matrix: Array<CharArray>, visited: Array<BooleanArray>, startChar: Char,
        row: Int, col: Int, prevX: Int, prevY: Int
    ): Boolean {
        if (row < 0 || row >= matrix.size || col < 0 || col >= matrix[0].size) { //Check boundaries
            return false
        }
        if (matrix[row][col] != startChar) {
            return false
        }
        if (visited[row][col]) {
            return true // Found a cycle, we are visiting an already visited valid cell
        }
        visited[row][col] = true // mark the cell visited
        // recursively visit all neighboring cells (horizontally & vertically)
        if (row + 1 != prevX //This condition avoid to go backward (avoid infinite loop)
            && containsCycleDFS(matrix, visited, startChar, row + 1, col, row, col)
        ) // down
            return true
        if (row - 1 != prevX //Once we reached top down we look to go up now but it has already been visited with prevX (row) and prevY (col)
            && containsCycleDFS(matrix, visited, startChar, row - 1, col, row, col)
        ) // up
            return true
        if (col + 1 != prevY
            && containsCycleDFS(matrix, visited, startChar, row, col + 1, row, col)
        ) // right
            return true
        return if (col - 1 != prevY
            && containsCycleDFS(matrix, visited, startChar, row, col - 1, row, col)
        ) true else false
    }
}