package academy.learnprogramming.queues

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val janeJones = Employee("Jane", "Jones", 123)
        val johnDoe = Employee("John", "Doe", 4567)
        val marySmith = Employee("Mary", "Smith", 22)
        val mikeWilson = Employee("Mike", "Wilson", 3245)
        val billEnd = Employee("Bill", "End", 78)
        val queue = ArrayQueue(10)
        queue.add(janeJones)
        queue.add(johnDoe)
        queue.add(marySmith)
        queue.add(mikeWilson)
        queue.add(billEnd)
        //queue.printQueue();
        queue.remove()
        queue.remove()
        queue.printQueue()

        //System.out.println(queue.peek());
        queue.remove()
        queue.remove()
        queue.remove()
        //queue.remove();
        queue.add(mikeWilson)
        queue.printQueue()
    }
}