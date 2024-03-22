import org.example.swap
import org.junit.jupiter.api.Test
import org.example.selectionSort
import org.junit.jupiter.api.Assertions.*

class SelectionSortKtTest {

    @Test
    fun selectionSort() {
        // Validates selectionSort in the general case
        val array = intArrayOf(5, 2, 7, 3, 1, 6, 4)
        val sortedArray = selectionSort(array.copyOf())
        array.sort()
        assert(array contentEquals sortedArray)
        // Properly sorts empty array
        val empty = selectionSort(intArrayOf())
        assert(intArrayOf() contentEquals empty)
        // Properly sorts single element array
        val single = selectionSort(intArrayOf(10))
        assert(intArrayOf(10) contentEquals single)
        // Properly sorts arrays with repeated values
        assert(intArrayOf(0, 0, 0, 0, 1, 1, 1, 1) contentEquals
                selectionSort(intArrayOf(0, 1, 0, 1, 0, 0, 1, 1))
        )
        // Works when there is a mix of neg, pos, and 0
        assert(intArrayOf(-10, -8, -3, -2, 0, 1, 2, 29, 38) contentEquals
                selectionSort(intArrayOf(-8, -2, 29, 38, -3, 2, -10, 1, 0))
        )

    }

    @Test
    fun swap() {
        val array = intArrayOf(1, 2, 3, 4, 5, 6)
        swap(array, 0, 5)
        swap(array, 1, 4)
        swap(array, 2, 3)
        assert(array contentEquals intArrayOf(6, 5, 4, 3, 2, 1))
    }
}