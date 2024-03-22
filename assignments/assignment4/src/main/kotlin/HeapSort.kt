package org.example
/**
 * Performs the heap sort algorithm on a given array of integers.
 *
 * @param array The array of integers to be sorted.
 * @return The sorted array of integers.
 *
 * Algorithm Steps:
 * 1. Build a heap from the input array using `buildHeap`.
 * 2. Starting from the end of the heap, swap the root of the heap (maximum
 *    element) with the last element of the heap, then reduce the size of the
 *    heap by one. This effectively moves the maximum element to its correct
 *    sorted position.
 * 3. Re-heapify the reduced heap to ensure the heap property is maintained,
 *    then repeat step 2 until the heap size is reduced to 1.
 */
fun heapSort(array: IntArray) : IntArray {
    buildHeap(array)
    for (index in array.size - 1 downTo 0) {
        // Swap max node with back
        swap(array, index, 0)
        // heapify again
        heapify(array, index, 0)
    }
    return array
}

/**
 * Transforms an array into a max heap.
 *
 * @param array The array to be transformed into a heap.
 * @return The array modified in-place to represent a max heap.
 *
 * Algorithm Steps:
 * 1. Iterate over the array starting from the last non-leaf node all the way
 *    to the root node, calling `heapify` for each node to ensure the heap
 *    property is satisfied.
 */
fun buildHeap(array: IntArray): IntArray {
    for (largestI in (array.size / 2) - 1 downTo 0) {
        heapify(array, array.size, largestI)

    }
    return array
}

/**
 * Takes in a max heap with one unsorted element at index i and rearranges it
 * back into a max heap
 *
 * @param array The partially heapified array
 * @param n The size of the heap
 * @param i The index of the unheaped value
 *
 * Algorithm Steps:
 * 2. Re-heapify the reduced heap to unsure it remains a heap
 */
fun heapify(array: IntArray, n: Int, i: Int): IntArray {
    if (array.isEmpty()) {
        return array
    }
    var largestI = i
    val leftI = 2 * i + 1
    val rightI = 2 * i + 2
    if ((array[largestI] == 5) or (array[largestI] == 6)) {

    }

    if (leftI < n && array[largestI] < array[leftI]) {
        largestI = leftI
    }
    if (rightI < n && array[largestI] < array[rightI]) {
        largestI = rightI
    }
    if (largestI != i) {
        swap(array, largestI, i)
        heapify(array, n, largestI)
    }
    return array
}