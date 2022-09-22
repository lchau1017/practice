package academy.learnprogramming.hashtable.chaining

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val janeJones = Employee("Jane", "Jones", 123)
        val johnDoe = Employee("John", "Doe", 4567)
        val marySmith = Employee("Mary", "Smith", 22)
        val mikeWilson = Employee("Mike", "Wilson", 3245)
        val ht = ChainedHashtable()
        ht.put("Jones", janeJones)
        ht.put("Doe", johnDoe)
        ht.put("Wilson", mikeWilson)
        ht.put("Smith", marySmith)
        ht.printHashtable()

//        System.out.println("Retrieve key Smith: " + ht.get("Smith"));
//
//        ht.remove("Doe");
//        ht.remove("Jones");
//        ht.printHashtable();
//
    }
}