package patternsForCodingInterviews.inPlaceReversalOfALinkedList

object _2RotateALinkedList {
    @JvmStatic
    fun main(args: Array<String>) {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        head.next!!.next!!.next!!.next!!.next = ListNode(6)
        var result = rotate(head, 3)
        print("Nodes of the reversed LinkedList are: ")
        while (result != null) {
            print(result.value.toString() + " ")
            result = result.next
        }
    }

    fun rotate(head: ListNode?, rotations: Int): ListNode? {
        var head = head
        var rotations = rotations
        if (head == null || head.next == null || rotations <= 0) return head
        //find the length and the last node of the list
        var lastNode = head
        var listLength = 1
        while (lastNode!!.next != null) {
            lastNode = lastNode.next
            listLength++
        }
        lastNode.next = head //connect the last node the head to a circular list
        rotations %= listLength //no need to do rotations more than the length of the list
        val skipLength = listLength - rotations
        var lastNodeOfRotatedList = head
        for (i in 0 until skipLength - 1) {
            lastNodeOfRotatedList = lastNodeOfRotatedList!!.next
        }
        // 'lastNodeOfRotatedList.next' is pointing to the sub-list of 'k' ending nodes
        head = lastNodeOfRotatedList!!.next
        lastNodeOfRotatedList.next = null
        return head
    }
}