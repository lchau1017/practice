package academy.learnprogramming.heaps.delete

class Heap(capacity: Int) {
    private val heap: IntArray
    private var size = 0

    init {
        heap = IntArray(capacity)
    }

    fun insert(value: Int) {
        if (isFull) {
            throw IndexOutOfBoundsException("Heap is full")
        }
        heap[size] = value
        fixHeapAbove(size)
        size++
    }

    fun delete(index: Int): Int { // Get the index
        if (isEmpty) {
            throw IndexOutOfBoundsException("Heap is empty")
        }
        val parent = getParent(index)
        val deletedValue = heap[index]
        //Get the left most element in the heap
        heap[index] = heap[size - 1]
        //We look up or down the heap
        if (index == 0 || heap[index] < heap[parent]) { //Need to check below if root or parent is greater
            //Less than its parent, need to look at the heap below
            //size - 1 -> get the last Heap Index
            fixHeapBelow(index, size - 1)
        } else {
            //If it is greater than its parent, need to fix above
            fixHeapAbove(index)
        }
        size--
        return deletedValue
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

    // lastHeapIndex is useful for heap sort
    private fun fixHeapBelow(index: Int, lastHeapIndex: Int) {
        var index = index
        var childToSwap: Int
        while (index <= lastHeapIndex) {
            //Fixing below
            val leftChild = getChild(index, true) //Get left child from this index
            val rightChild = getChild(index, false) //Get right child from this index
            //Check if This node has a left child
            if (leftChild <= lastHeapIndex) { //If passes, has a left child, else do nothing, we already know node does not have a right child for sure
                //Check if This node has a right child
                childToSwap = if (rightChild > lastHeapIndex) {
                    //Does not have a right child
                    leftChild
                } else {
                    //Have a right child
                    //Get the bigger one between these two children
                    //to compare with the index
                    if (heap[leftChild] > heap[rightChild]) leftChild else rightChild
                }
                if (heap[index] < heap[childToSwap]) { //value is index is less than child to swap -> do the swap
                    //Swap
                    val tmp = heap[index]
                    heap[index] = heap[childToSwap]
                    heap[childToSwap] = tmp
                } else {
                    break
                }
                index = childToSwap
            } else { //does not have any children
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

    //Get the child from that index for left or right to add 1 or 2
    fun getChild(index: Int, left: Boolean): Int {
        return 2 * index + if (left) 1 else 2
    }
}