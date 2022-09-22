package academy.learnprogramming.hashtable.challenges.one

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val nums = IntArray(10)
        val numsToAdd = intArrayOf(59382, 43, 6894, 500, 99, -58)
        for (i in numsToAdd.indices) {
            nums[hash(numsToAdd[i])] = numsToAdd[i]
        }
        for (i in nums.indices) {
            println(nums[i])
        }
    }

    fun hash(value: Int): Int {
        return Math.abs(value % 10)
    }
}