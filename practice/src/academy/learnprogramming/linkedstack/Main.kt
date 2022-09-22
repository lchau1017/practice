package academy.learnprogramming.linkedstack

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val janeJones = Employee("Jane", "Jones", 123)
        val johnDoe = Employee("John", "Doe", 4567)
        val marySmith = Employee("Mary", "Smith", 22)
        val mikeWilson = Employee("Mike", "Wilson", 3245)
        val billEnd = Employee("Bill", "End", 78)
        val stack = LinkedStack()
        stack.push(janeJones)
        stack.push(johnDoe)
        stack.push(marySmith)
        stack.push(mikeWilson)
        stack.push(billEnd)

        //stack.printStack();

        //System.out.println(stack.peek());
        //stack.printStack();
        println("Popped: " + stack.pop())
        println(stack.peek())
    }
}