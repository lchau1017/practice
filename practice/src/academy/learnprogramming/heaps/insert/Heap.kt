package academy.learnprogramming.heaps.insert

class Heap(capacity: Int) {
    private val heap: IntArray
    private var size = 0

    init {
        heap = IntArray(capacity)
    }

    //Always put the new element at the end of the index
    fun insert(value: Int) {
        if (isFull) {
            throw IndexOutOfBoundsException("Heap is full")
        }
        heap[size] = value
        fixHeapAbove(size)
        size++
    }

    //Look above the heap, to compare with his parent to check if value is greater or not
    private fun fixHeapAbove(index: Int) {
        var index = index
        val newValue = heap[index]
        while (index > 0 && newValue > heap[getParent(index)]) { //Compare with his parent
            //Swap the value
            heap[index] = heap[getParent(index)]
            index = getParent(index)
        }
        //Set the new value
        heap[index] = newValue
    }

    val isFull: Boolean
        get() = size == heap.size

    fun getParent(index: Int): Int {
        return (index - 1) / 2
    }
}