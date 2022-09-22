package academy.learnprogramming.queues

class ArrayQueue(capacity: Int) {
    private var queue: Array<Employee?>
    private var front = 0
    private var back = 0

    init {
        queue = arrayOfNulls(capacity)
    }

    fun add(employee: Employee?) {
        if (back == queue.size) {
            val newArray = arrayOfNulls<Employee>(2 * queue.size)
            System.arraycopy(queue, 0, newArray, 0, queue.size)
            queue = newArray
        }
        queue[back] = employee
        back++
    }

    fun remove(): Employee? {
        if (size() == 0) {
            throw NoSuchElementException()
        }
        val employee = queue[front]
        queue[front] = null
        front++
        if (size() == 0) {
            front = 0
            back = 0
        }
        return employee
    }

    fun peek(): Employee? {
        if (size() == 0) {
            throw NoSuchElementException()
        }
        return queue[front]
    }

    fun size(): Int {
        return back - front
    }

    fun printQueue() {
        for (i in front until back) {
            println(queue[i])
        }
    }
}