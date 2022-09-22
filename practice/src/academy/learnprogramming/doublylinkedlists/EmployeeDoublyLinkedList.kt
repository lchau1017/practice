package academy.learnprogramming.doublylinkedlists

class EmployeeDoublyLinkedList {
    private var head: EmployeeNode? = null
    private var tail: EmployeeNode? = null
    var size = 0
        private set

    fun addToFront(employee: Employee) {
        val node = EmployeeNode(employee)
        if (head == null) {
            tail = node
        } else {
            head?.previous = node
            node.next = head
        }
        head = node
        size++
    }

    fun addToEnd(employee: Employee) {
        val node = EmployeeNode(employee)
        if (tail == null) {
            head = node
        } else {
            tail?.next = node
            node.previous = tail
        }
        tail = node
        size++
    }

    fun removeFromFront(): EmployeeNode? {
        if (isEmpty) {
            return null
        }
        val removedNode = head
        if (head?.next == null) {
            tail = null
        } else {
            head?.next?.previous = null
        }
        head = head?.next
        size--
        removedNode?.next = null
        return removedNode
    }

    fun removeFromEnd(): EmployeeNode? {
        if (isEmpty) {
            return null
        }
        val removedNode = tail
        if (tail?.previous == null) {
            head = null
        } else {
            tail?.previous?.next = null
        }
        tail = tail?.previous
        size--
        removedNode?.previous = null
        return removedNode
    }

    val isEmpty: Boolean
        get() = head == null

    fun printList() {
        var current = head
        print("HEAD -> ")
        while (current != null) {
            print(current)
            print(" <=> ")
            current = current.next
        }
        println("null")
    }
}