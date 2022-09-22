package academy.learnprogramming.doublylinkedlists

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val janeJones = Employee("Jane", "Jones", 123)
        val johnDoe = Employee("John", "Doe", 4567)
        val marySmith = Employee("Mary", "Smith", 22)
        val mikeWilson = Employee("Mike", "Wilson", 3245)
        val list = EmployeeDoublyLinkedList()
        list.addToFront(janeJones)
        list.addToFront(johnDoe)
        list.addToFront(marySmith)
        list.addToFront(mikeWilson)
        list.printList()
        println(list.size)
        val billEnd = Employee("Bill", "End", 78)
        list.addToEnd(billEnd)
        list.printList()
        println(list.size)
        list.removeFromFront()
        list.printList()
        println(list.size)
        list.removeFromEnd()
        list.printList()
        println(list.size)
    }
}