package patternsForCodingInterviews.fastAndSlowPointers

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743582805_17Unit
object _1PalindromeLinkedList {
    @JvmStatic
    fun main(args: Array<String>) {
        val head = ListNode(2)
        head.next = ListNode(4)
        head.next!!.next = ListNode(6)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(2)
        println("Is palindrome: " + isPalindrome(head))

        /*
        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));

         */
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    fun isPalindrome(head: ListNode?): Boolean {
        var head = head
        if (head == null || head.next == null) {
            return true
        }
        //Find middle of the LinkedList
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }
        var headSecondhalf = reverse(slow) //reverse the second half
        //Store the head of reverse part to revert back later
        // 2 -> 4 -> (6 -> null) <- 4 <- 2
        val copyHeadSecondHalf = headSecondhalf
        //compare the first and the second half
        while (head != null && headSecondhalf != null) {
            if (head.value != headSecondhalf.value) {
                break //No palindrome
            }
            head = head.next
            headSecondhalf = headSecondhalf.next
        }
        reverse(copyHeadSecondHalf) // Revert the reverse of the second half
        return if (head == null || headSecondhalf == null) { //If both halves match
            true
        } else false
    }

    private fun reverse(head: ListNode?): ListNode? {
        var head = head
        var prev: ListNode? = null
        while (head != null) {
            val next = head.next
            head.next = prev
            prev = head
            head = next
        }
        return prev
    }
}