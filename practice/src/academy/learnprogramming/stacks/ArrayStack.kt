package academy.learnprogramming.stacks

import java.util.*

class ArrayStack(capacity: Int) {
    private var stack: Array<Employee?>
    private var top = 0

    init {
        stack = arrayOfNulls(capacity)
    }

    fun push(employee: Employee?) {
        if (top == stack.size) {
            // need to resize the backing array
            val newArray = arrayOfNulls<Employee>(2 * stack.size)
            System.arraycopy(stack, 0, newArray, 0, stack.size)
            stack = newArray
        }
        stack[top++] = employee
    }

    fun pop(): Employee? {
        if (isEmpty) {
            throw EmptyStackException()
        }
        val employee = stack[--top]
        stack[top] = null
        return employee
    }

    fun peek(): Employee? {
        if (isEmpty) {
            throw EmptyStackException()
        }
        return stack[top - 1]
    }

    fun size(): Int {
        return top
    }

    val isEmpty: Boolean
        get() = top == 0

    fun printStack() {
        for (i in top - 1 downTo 0) {
            println(stack[i])
        }
    }
}