package patternsForCodingInterviews.fastAndSlowPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743546476_13Unit
object LinkedListCycle {
    @JvmStatic
    fun main(args: Array<String>) {
        var head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        head.next!!.next!!.next!!.next!!.next = ListNode(6)
        println("LinkedList has cycle: " + hasCycle(head))
        head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next
        println("LinkedList has cycle: " + hasCycle(head))
        head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next!!.next
        println("LinkedList has cycle: " + hasCycle(head))

        //Problem 1 similar
        head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        head.next!!.next!!.next!!.next!!.next = ListNode(6)
        head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next
        println(
            "LinkedList cycle length: "
                    + findCycleLength(head)
        )
        head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next!!.next
        println(
            "LinkedList cycle length: "
                    + findCycleLength(head)
        )
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            fast = fast.next!!.next
            slow = slow!!.next
            if (slow === fast) {
                return true //Found the cycle
            }
        }
        return false
    }

    /*
    Problem 1: Given the head of a LinkedList with a cycle, find the length of the cycle
     */
    /*
    Time: O(N)
    Space: O(1)
     */
    fun findCycleLength(head: ListNode?): Int {
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            fast = fast.next!!.next
            slow = slow!!.next
            if (slow === fast) { //Found a cycle
                return caculateLength(slow)
            }
        }
        return 0
    }

    private fun caculateLength(slow: ListNode?): Int {
        var current = slow
        var cycleLength = 0
        do {
            current = current!!.next
            cycleLength++
        } while (current !== slow)
        return cycleLength
    }
}