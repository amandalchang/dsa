import org.example.buildHeap
import org.example.heapSort
import org.example.heapify
import org.example.insertionSort
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HeapSortKtTest {

    @Test
    fun heapSort() {
        // Validates heapSort in the general case
        val array = intArrayOf(5, 2, 7, 3, 1, 6, 4)
        val sortedArray = heapSort(array.copyOf())
        array.sort()
        assert(array contentEquals sortedArray)
        // Properly sorts empty array
        val empty = heapSort(intArrayOf())
        assert(intArrayOf() contentEquals empty)
        // Properly sorts single element array
        val single = heapSort(intArrayOf(10))
        assert(intArrayOf(10) contentEquals single)
        // Properly sorts arrays with repeated values
        assert(intArrayOf(0, 0, 0, 0, 1, 1, 1, 1) contentEquals
                heapSort(intArrayOf(0, 1, 0, 1, 0, 0, 1, 1))
        )
        // Works when there is a mix of neg, pos, and 0
        assert(intArrayOf(-10, -8, -3, -2, 0, 1, 2, 29, 38) contentEquals
                heapSort(intArrayOf(-8, -2, 29, 38, -3, 2, -10, 1, 0))
        )
    }

    @Test
    fun buildHeap() {
        // general case
        val sample = buildHeap(intArrayOf(100, 2, 65, 38, 23, 77, 4))
        assert(isMaxHeap(sample))
        // repeated values, negative values
        val repeats = buildHeap(intArrayOf(-2, -2, 65, 38, -2, 77, 77, 4))
        assert(isMaxHeap(repeats))
        // empty heap
        val empty = buildHeap(intArrayOf())
        assert(isMaxHeap(empty) && empty contentEquals intArrayOf())
    }

    @Test
    fun heapify() {
        // successfully heapifies an empty heap
        assert(intArrayOf() contentEquals heapify(intArrayOf(), 0, 0))
        // successfully heapifies in a general case
        val mostlyMaxHeap = intArrayOf(40, 70, 29, 10, 3)
        heapify(mostlyMaxHeap, mostlyMaxHeap.size, 0);
        assert(isMaxHeap(mostlyMaxHeap));
        // heapifies an existing heap
        val maxHeap = intArrayOf(70, 40, 29, 10, 3)
        heapify(maxHeap, maxHeap.size, 3)
        assert(isMaxHeap(maxHeap))
    }
}

/**
 * Tests whether an array is a max heap
 *
 * @param array an input IntArray to be tested
 * @return A boolean on whether the input was a max heap
 */
fun isMaxHeap(array: IntArray): Boolean {
    for (i in 0 until array.size / 2) {
        val left = 2 * i + 1
        val right = 2 * i + 2

        // if left, left > parent
        if (left < array.size && array[i] < array[left]) {
            return false
        }
        // if right, right > parent
        if (right < array.size && array[i] < array[right]) {
            return false
        }
    }
    return true
}


