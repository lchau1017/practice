package academy.learnprogramming.arrays

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = IntArray(7)
        intArray[0] = 20
        intArray[1] = 35
        intArray[2] = -15
        intArray[3] = 7
        intArray[4] = 55
        intArray[5] = 1
        intArray[6] = -22
        var index = -1
        for (i in intArray.indices) {
            if (intArray[i] == 7) {
                index = i
                break
            }
        }
        println("index = $index")
    }
}