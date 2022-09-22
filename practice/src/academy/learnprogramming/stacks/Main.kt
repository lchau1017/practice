package academy.learnprogramming.stacks

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val stack = ArrayStack(10)
        stack.push(Employee("Jane", "Jones", 123))
        stack.push(Employee("John", "Doe", 4567))
        stack.push(Employee("Mary", "Smith", 22))
        stack.push(Employee("Mike", "Wilson", 3245))
        stack.push(Employee("Bill", "End", 78))

        //stack.printStack();
        println(stack.peek())
        //stack.printStack();
        println("Popped: " + stack.pop())
        println(stack.peek())
    }
}