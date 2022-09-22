package academy.learnprogramming.linkedstack

import java.util.*

class LinkedStack {
    private val stack: LinkedList<Employee>

    init {
        stack = LinkedList()
    }

    fun push(employee: Employee) {
        stack.push(employee)
    }

    fun pop(): Employee {
        return stack.pop()
    }

    fun peek(): Employee {
        return stack.peek()
    }

    val isEmpty: Boolean
        get() = stack.isEmpty()

    fun printStack() {
        val iterator: ListIterator<Employee> = stack.listIterator()
        while (iterator.hasNext()) {
            println(iterator.next())
        }
    }
}