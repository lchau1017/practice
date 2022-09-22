package academy.learnprogramming.hashtable.arrayImplementation

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val janeJones = Employee("Jane", "Jones", 123)
        val johnDoe = Employee("John", "Doe", 4567)
        val marySmith = Employee("Mary", "Smith", 22)
        val mikeWilson = Employee("Mike", "Wilson", 3245)
        val billEnd = Employee("Bill", "End", 78)
        val ht = SimpleHashtable()
        ht.put("Jones", janeJones)
        ht.put("Doe", johnDoe)
        ht.put("Wilson", mikeWilson)
        ht.put("Smith", marySmith)

        //ht.printHashtable();
        println("Retrieve key Wilson: " + ht["Wilson"])
    }
}