import org.example.merge
import org.example.mergeSort
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MergeSortTest {

    @Test
    fun mergeSort() {
        //  validates mergeSort in the general case
        val array = intArrayOf(5, 2, 7, 3, 1, 6, 4)
        val sortedArray = mergeSort(array.copyOf())
        array.sort()
        assert(array contentEquals sortedArray)
        // properly sorts empty array
        val empty = mergeSort(intArrayOf())
        assert(intArrayOf() contentEquals empty)
        // properly sorts single element array
        val single = mergeSort(intArrayOf(10))
        assert(intArrayOf(10) contentEquals single)
        // properly sorts arrays with repeated values
        assert(intArrayOf(0,0,0,0,1,1,1,1) contentEquals
                mergeSort(intArrayOf(0, 1, 0, 1, 0, 0, 1, 1))
        )
        // works when there is a mix of neg, pos, and 0
        assert(intArrayOf(-10, -8, -3, -2, 0, 1, 2, 29, 38) contentEquals
                mergeSort(intArrayOf(-8, -2, 29, 38, -3, 2, -10, 1, 0))
        )
    }

    @Test
    fun merge() {
        // Testing that multiple of the same values works
        assert(intArrayOf(0,0,0,0,1,1,1,1) contentEquals
            merge(intArrayOf(1, 1, 1, 1), intArrayOf(0, 0, 0, 0))
        )
        // Testing that it works on unequally sized arrays
        assert(intArrayOf(0,1,2,3,4,5,6,7,8) contentEquals
                merge(intArrayOf(0, 1, 3, 8), intArrayOf(2, 4, 5, 6, 7))
        )
        // Testing that it works on empty arrays
        assert(intArrayOf() contentEquals
                merge(intArrayOf(), intArrayOf())
        )
        // Testing that it merges equal arrays
        assert(intArrayOf(5,5,5,5,5,5) contentEquals
                merge(intArrayOf(5, 5), intArrayOf(5, 5, 5, 5))
        )
    }
}