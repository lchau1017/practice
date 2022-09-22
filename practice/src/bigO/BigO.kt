package bigO

//https://www.baeldung.com/java-algorithm-complexity
object BigO {
    @JvmStatic
    fun main(args: Array<String>) {

        /*
        Better to the worst Big O annotation.
        Big O -> must understand the rates at which things can grow
         */

        //O(1) -> Constant time
        val n = 100
        println("Your input is: $n")
        println("\n")
        /** */

        //Logarithmic Time Algorithms
        //running time grows in proportion to the logarithm of the input
        // log to the base 2 (Example: BinarySearch)
        //O(log n)
        run {
            var i = 1
            while (i < n) {
                println("O(log n) : $i")
                i = i * 2
            }
        }
        println("\n")
        /** */

        //Linear Time Algorithms
        // O(N)
        for (i in 0 until n) {
            println("O(N) : $i")
        }
        println("\n")
        /** */

        //O(n log n)
        //The running time grows in proportion to n log n of the input
        for (i in 1..n) {
            var j = 1
            while (j < n) {
                println("O(n log n) : $j")
                j = j * 2
            }
        }
        println("\n")
        /** */

        //O(n²) -> quadratic (faster than n3)
        //O(n3) -> cubic (faster than n4 and so on....)
        //O(n4) -> quartic
        //Quadratic example:
        for (i in 1..n) {
            for (j in 1..n) {
                println("O(n²) : $j")
            }
        }
        println("\n")
        /** */

        //Exponential Time Algorithms
        //O(k n) algo will het k times bigger with every additional input
        //O(3n) algo triple with every additional input
        var i = 1
        while (i <= Math.pow(2.0, n.toDouble())) {
            println("O(k n): $i")
            i++
        }
        println("\n")
        /** */

        //Factorial Time Algorithms (worst algorithm)
        //O(n!)
        /*
        for (int i = 1; i <= factorial(n); i++){
            System.out.println("Hey - I'm busy looking at: " + i);
        }
         */
    }
}