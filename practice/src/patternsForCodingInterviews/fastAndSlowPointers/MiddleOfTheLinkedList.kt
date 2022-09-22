package patternsForCodingInterviews.fastAndSlowPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743569684_16Unit
object MiddleOfTheLinkedList {
    @JvmStatic
    fun main(args: Array<String>) {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        println("Middle Node: " + findMiddle(head)!!.value)
        head.next!!.next!!.next!!.next!!.next = ListNode(6)
        println("Middle Node: " + findMiddle(head)!!.value)
        head.next!!.next!!.next!!.next!!.next!!.next = ListNode(7)
        println("Middle Node: " + findMiddle(head)!!.value)
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun findMiddle(head: ListNode?): ListNode? {
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }
        return slow
    }
}