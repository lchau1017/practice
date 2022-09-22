package algorithms.dynamicProgramming.commonPatternsInDP

import java.util.*

//https://leetcode.com/explore/learn/card/dynamic-programming/632/common-patterns-in-dp-problems/4109/
class MinimumDifficultyOfAJobSchedule {
    //Let's use one state variable i, where i is the index of the first job that will be done on the current day.
    //Let's use another state variable \text{day}day, where \text{day}day indicates what day it currently is.
    private var n = 0
    private var d = 0
    private lateinit var memo: Array<IntArray>
    private lateinit var jobDifficulty: IntArray
    private lateinit var hardestJobRemaining: IntArray

    //i is the index of the first job that will be done on the current day
    //Let's use another state variable \text{day}day, where \text{day}day
    // indicates what day it currently is
    private fun dp(i: Int, day: Int): Int {
        // Base case, it's the last day so we need to finish all the jobs
        if (day == d) {
            //We can precompute an array \text{hardestJobRemaining}hardestJobRemaining
            // where \text{hardestJobRemaining[i]}hardestJobRemaining[i] represents
            // the difficulty of the hardest job on or after day \text{i}i, so that this base case
            // is handled in constant time
            return hardestJobRemaining[i]
        }
        if (memo[i][day] == -1) {
            var best = Int.MAX_VALUE
            var hardest = 0
            // Iterate through the options and choose the best
            //On each day, we try all the options - do only one job, then two jobs, etc
            println("i :$i")
            for (j in i until n - (d - day)) {
                println("j : $j")
                //Define the difficulty of the day
                hardest = Math.max(hardest, jobDifficulty[j]) //Keep track of the hardest job done today
                // Recurrence relation
                //We add hardest to the next state which is the next day, starting with the next job
                //Whatever our choice, find the difficulty of the job shedule, it is the current day's difficulty
                // + the next state which would be the next day starting with the job index.
                best = Math.min(best, hardest + dp(j + 1, day + 1))
            }
            memo[i][day] = best
        }
        return memo[i][day]
    }

    fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
        n = jobDifficulty.size
        // If we cannot schedule at least one job per day,
        // it is impossible to create a schedule
        if (n < d) {
            return -1
        }
        hardestJobRemaining = IntArray(n)
        var hardestJob = 0
        for (i in n - 1 downTo 0) {
            hardestJob = Math.max(hardestJob, jobDifficulty[i])
            hardestJobRemaining[i] = hardestJob
        }
        // Initialize memo array with value of -1.
        memo = Array(n) { IntArray(d + 1) }
        for (i in 0 until n) {
            Arrays.fill(memo[i], -1)
        }
        this.d = d
        this.jobDifficulty = jobDifficulty
        return dp(0, 1) //dp(0, 1), since we start on the first day with no jobs done yet.
    }

    companion object {
        /*
    https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
    You have to finish at least one task every day. The difficulty of a job schedule is the sum of
    difficulties of each
    day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
     */
        @JvmStatic
        fun main(args: Array<String>) {
            val jobDifficulty = intArrayOf(6, 5, 4, 3, 2, 1)
            val jobDifficulty1 = intArrayOf(6, 5, 10, 3, 2, 1)
            val _3days = 3
            val d = 2
            val obj = MinimumDifficultyOfAJobSchedule()
            println(obj.minDifficulty(jobDifficulty1, _3days))
        }
    }
}