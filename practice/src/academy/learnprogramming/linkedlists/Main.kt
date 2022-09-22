package academy.learnprogramming.linkedlists

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val janeJones = Employee("Jane", "Jones", 123)
        val johnDoe = Employee("John", "Doe", 4567)
        val marySmith = Employee("Mary", "Smith", 22)
        val mikeWilson = Employee("Mike", "Wilson", 3245)
        val list = EmployeeLinkedList()
        println(list.isEmpty)
        list.addToFront(janeJones)
        list.addToFront(johnDoe)
        list.addToFront(marySmith)
        list.addToFront(mikeWilson)
        println(list.size)
        list.printList()
        list.removeFromFront()
        println(list.size)
        list.printList()
    }
}