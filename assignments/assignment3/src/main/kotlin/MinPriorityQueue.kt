package org.example

class MyMinPriorityQueue<VertexType> : MinPriorityQueue<VertexType> {
    private val heap = MinHeap<VertexType>()
    /**
     * @return true if the queue is empty, false otherwise
     */
    override fun isEmpty(): Boolean {
        return heap.isEmpty()
    }
    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    override fun next(): VertexType? {
        return heap.getMin()

    }

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    override fun adjustPriority(elem: VertexType, newPriority: Double) {
        heap.adjustHeapNumber(elem, newPriority)
    }

    /**
     * Add [elem] with at level [priority]
     */
    override fun addWithPriority(elem: VertexType, priority: Double) {
        heap.insert(elem, priority)
    }
}