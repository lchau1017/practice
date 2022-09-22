package academy.learnprogramming.heaps.heapsort

class Heap(capacity: Int) {
    private val heap: IntArray
    private var size = 0

    init {
        heap = IntArray(capacity)
    }

    //O(log N) due to fix the heap
    fun insert(value: Int) {
        if (isFull) {
            throw IndexOutOfBoundsException("Heap is full")
        }
        heap[size] = value //constant time
        fixHeapAbove(size)
        size++
    }

    fun peek(): Int {
        if (isEmpty) {
            throw IndexOutOfBoundsException("Heap is empty")
        }
        return heap[0]
    }

    //O(log N) log N is to fix the heap
    fun delete(index: Int): Int {
        if (isEmpty) {
            throw IndexOutOfBoundsException("Heap is empty")
        }
        val parent = getParent(index)
        val deletedValue = heap[index]
        heap[index] = heap[size - 1]
        if (index == 0 || heap[index] < heap[parent]) {
            fixHeapBelow(index, size - 1)
        } else {
            fixHeapAbove(index)
        }
        size--
        return deletedValue
    }

    //Sort in increasing order, the root will become the smaller element of the heap
    fun sort() {
        val lastHeapIndex = size - 1 //Get last index
        for (i in 0 until lastHeapIndex) { //heap reduce by one in each iteration
            val tmp = heap[0] //swap root (largest value) with the last item
            heap[0] = heap[lastHeapIndex - i]
            heap[lastHeapIndex - i] = tmp
            //Swap the root -> index 0
            // after Swapped, we exclude the old root placed at last index : (lastHeapIndex - i - 1)
            fixHeapBelow(0, lastHeapIndex - i - 1)
        }
    }

    private fun fixHeapAbove(index: Int) {
        var index = index
        val newValue = heap[index]
        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)]
            index = getParent(index)
        }
        heap[index] = newValue
    }

    private fun fixHeapBelow(index: Int, lastHeapIndex: Int) {
        var index = index
        var childToSwap: Int
        while (index <= lastHeapIndex) {
            val leftChild = getChild(index, true)
            val rightChild = getChild(index, false)
            if (leftChild <= lastHeapIndex) {
                childToSwap = if (rightChild > lastHeapIndex) {
                    leftChild
                } else {
                    if (heap[leftChild] > heap[rightChild]) leftChild else rightChild
                }
                if (heap[index] < heap[childToSwap]) {
                    val tmp = heap[index]
                    heap[index] = heap[childToSwap]
                    heap[childToSwap] = tmp
                } else {
                    break
                }
                index = childToSwap
            } else {
                break
            }
        }
    }

    fun printHeap() {
        for (i in 0 until size) {
            print(heap[i])
            print(", ")
        }
        println()
    }

    val isFull: Boolean
        get() = size == heap.size

    fun getParent(index: Int): Int {
        return (index - 1) / 2
    }

    val isEmpty: Boolean
        get() = size == 0

    fun getChild(index: Int, left: Boolean): Int {
        return 2 * index + if (left) 1 else 2
    }
}