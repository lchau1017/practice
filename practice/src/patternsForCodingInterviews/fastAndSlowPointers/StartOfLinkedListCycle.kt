package patternsForCodingInterviews.fastAndSlowPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743554403_14Unit
object StartOfLinkedListCycle {
    @JvmStatic
    fun main(args: Array<String>) {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        head.next!!.next!!.next!!.next!!.next = ListNode(6)
        head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next
        println(
            "LinkedList cycle start: " +
                    findCycleStart(head)!!.value
        )

        /*
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " +
                findCycleStart(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " +
                findCycleStart(head).value);

         */
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun findCycleStart(head: ListNode?): ListNode? {
        var cycleLength = 0
        //find the LinkedList cycle
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            fast = fast.next!!.next
            slow = slow!!.next
            if (slow === fast) {
                cycleLength = calculateCycleLength(slow)
                break
            }
        }
        return findStart(head, cycleLength)
    }

    private fun findStart(head: ListNode?, cycleLength: Int): ListNode? {
        var cycleLength = cycleLength
        var pointer1 = head
        var pointer2 = head
        //move pointer2 ahead 'cycleLength' nodes
        while (cycleLength > 0) {
            pointer2 = pointer2!!.next
            cycleLength--
        }
        //Increment both pointers until they meet at the start of the cycle
        while (pointer1 !== pointer2) {
            pointer1 = pointer1!!.next
            pointer2 = pointer2!!.next
        }
        return pointer1
    }

    private fun calculateCycleLength(slow: ListNode?): Int {
        var current = slow
        var cycleLength = 0
        do {
            current = current!!.next
            cycleLength++
        } while (current !== slow)
        return cycleLength
    }
}