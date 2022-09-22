package algorithms.backtracking

import java.util.AbstractMap.SimpleEntry

//https://leetcode.com/explore/learn/card/recursion-ii/472/backtracking/2794/
class RobotRoomCleaner {
    //Approach 1: Spiral Backtracking
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    var directions = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))
    var visited: MutableSet<Map.Entry<Int, Int>> = HashSet()
    var robot: Robot? = null
    fun goBack() {
        robot!!.turnRight()
        robot!!.turnRight()
        robot!!.move()
        robot!!.turnRight()
        robot!!.turnRight()
    }

    /*
    Time: O(N - M) where N is the number of cell and M number of obstacles
    At each visit, we will check 4 directions around the cell.
    Therefore, the total number of operations would be 4 (N - M).
    Space: O(N - M)
    We employed a hashtable
    to keep track of whether a non-obstacle cell is visited or not.
     */
    fun cleanRoom(robot: Robot?) {
        this.robot = robot
        backTrack(0, 0, 0)
    }

    private fun backTrack(row: Int, col: Int, d: Int) {
        visited.add(Pair.of(row, col))
        robot!!.clean()
        //Explore 4 directions : up, right, down, and left
        // (the order is important since the idea is always to turn right) :
        for (i in 0..3) {
            val newD = (d + i) % 4
            val newRow = row + directions[newD][0]
            val newCol = col + directions[newD][1]
            if (!visited.contains(Pair.of(newRow, newCol)) && robot!!.move()) {
                backTrack(newRow, newCol, newD)
                goBack()
            }
            //Turn the robot following chosen direction : clockwise
            robot!!.turnRight()
        }
    }

    internal object Pair {
        fun <T, U> of(first: T, second: U): Map.Entry<T, U> {
            return SimpleEntry(first, second)
        }
    }

    interface Robot {
        //return tru  if the cell in front  is open and robot moves
        //into the cell
        //Return false if the cell in front is blocked and robot stays in the
        //current cell
        fun move(): Boolean

        //Robot stay in the same cell after calling turnLeft/turnRight
        //Each turn will be 90 degres
        fun turnLeft()
        fun turnRight()

        //Clean the current cell
        fun clean()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val room = arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 0, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 0, 1, 1),
                intArrayOf(1, 0, 1, 1, 1, 1, 1, 1),
                intArrayOf(0, 0, 0, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1)
            )
            val obj = RobotRoomCleaner()
        }
    }
}