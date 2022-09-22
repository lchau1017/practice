package patternsForCodingInterviews.twoHeaps

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744008443_64Unit
object MaximizeCapital {
    @JvmStatic
    fun main(args: Array<String>) {
        val result: Int
        /*
        result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 },
                new int[] { 1, 2, 3 }, 2, 1);
        System.out.println("Maximum capital: " + result);

         */result = findMaximumCapital(intArrayOf(10, 1, 3, 2), intArrayOf(1, 2, 3, 5), 3, 1)
        println("Maximum capital: $result")
    }

    /*
    Time: O(N log N + K log N) Where N total number of projects and K is the number of projects
    we are selecting.
    Space: O(N) we store all projects to the heap
     */
    fun findMaximumCapital(
        capital: IntArray,
        profits: IntArray,
        numberOfProjects: Int,
        initialCapital: Int
    ): Int {
        val n = profits.size
        val minCapitalHeap = PriorityQueue(
            n
        ) { i1: Int, i2: Int -> capital[i1] - capital[i2] }
        val maxProfitHeap = PriorityQueue(
            n
        ) { i1: Int, i2: Int -> profits[i2] - profits[i1] }

        //Insert all project capitals to a min-heap
        for (i in 0 until n) {
            minCapitalHeap.offer(i)
        }
        //let's try to find a total of 'numberOfProjects' best projects
        var availableCapital = initialCapital
        for (i in 0 until numberOfProjects) {
            //Find all projects that can be selected within the available capital and insert them in a max-heap
            while (!minCapitalHeap.isEmpty()
                && capital[minCapitalHeap.peek()] <= availableCapital
            ) {
                maxProfitHeap.add(minCapitalHeap.poll())
            }
            // terminate if we are not able to find any project that can be completed within
            // the available capital
            if (maxProfitHeap.isEmpty()) {
                break
            }
            //Select the project with the max profit
            availableCapital += profits[maxProfitHeap.poll()]
        }
        return availableCapital
    }
}