package academy.learnprogramming.linkedlists

class EmployeeLinkedList {
    private var head: EmployeeNode? = null
    var size = 0
        private set

    fun addToFront(employee: Employee) {
        val node = EmployeeNode(employee)
        node.next = head
        head = node
        size++
    }

    fun removeFromFront(): EmployeeNode? {
        if (isEmpty) {
            return null
        }
        val removedNode = head
        head = head?.next
        size--
        removedNode?.next = null
        return removedNode
    }

    val isEmpty: Boolean
        get() = head == null

    fun printList() {
        var current = head
        print("HEAD -> ")
        while (current != null) {
            print(current)
            print(" -> ")
            current = current.next
        }
        println("null")
    }
}