package academy.learnprogramming.heaps.priorityQueues

import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val pq = PriorityQueue<Int> { o1, o2 ->
            //Min heap, lower number higher priority
            o1 - o2
        }
        pq.add(25)
        pq.add(-22)
        pq.add(1343)
        pq.add(54)
        pq.add(0)
        pq.add(-3492)
        pq.add(429)

        //System.out.println(pq.peek());
        pq.remove() //remove element with the higher priority (lower element based on the comparator)
        //System.out.println(pq.peek());
        pq.poll()
        println(pq.peek())
        println("--------------------------")

//        System.out.println(pq.peek());
//        System.out.println(pq.remove());
//        System.out.println(pq.peek());
//        System.out.println(pq.poll());
//        System.out.println(pq.peek());
        println(pq.remove(54))
        val ints: Array<Any> = pq.toTypedArray()
        for (num in ints) {
            println(num)
        }

        //System.out.println(pq.peek());
        pq.add(-1)
        //System.out.println(pq.peek());
    }
}