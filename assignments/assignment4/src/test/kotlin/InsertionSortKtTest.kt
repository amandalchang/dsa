import org.example.insertionSort
import org.example.mergeSort
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class InsertionSortKtTest {
    @Test
    fun insertionSort() {
        // Validates insertionSort in the general case
        val array = intArrayOf(5, 2, 7, 3, 1, 6, 4)
        val sortedArray = insertionSort(array.copyOf())
        array.sort()
        assert(array contentEquals sortedArray)
        // Properly sorts empty array
        val empty = insertionSort(intArrayOf())
        assert(intArrayOf() contentEquals empty)
        // Properly sorts single element array
        val single = insertionSort(intArrayOf(10))
        assert(intArrayOf(10) contentEquals single)
        // Properly sorts arrays with repeated values
        assert(intArrayOf(0, 0, 0, 0, 1, 1, 1, 1) contentEquals
                insertionSort(intArrayOf(0, 1, 0, 1, 0, 0, 1, 1))
        )
        // Works when there is a mix of neg, pos, and 0
        assert(intArrayOf(-10, -8, -3, -2, 0, 1, 2, 29, 38) contentEquals
                insertionSort(intArrayOf(-8, -2, 29, 38, -3, 2, -10, 1, 0))
        )
    }
}