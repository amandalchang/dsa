package org.example

/**
 * Repeatedly finds the minimum element from the unsorted section of the array and swaps
 * it with the first unsorted element until the entire list is sorted.
 *
 * @param array unsorted integer array
 * @return A sorted version of the original integer array
 */
fun selectionSort(array: IntArray): IntArray {
    for (i in array.indices) {
        var min = i
        for (j in i + 1 until array.size) {
            if (array[j] < array[min]) {
                min = j
            }
        }
        swap(array, min, i)
    }
    return array
}

/**
 * Swaps the positions of two values in an intArray
 *
 * @param array The intArray containing numbers that need swapping
 * @param x The index of the first value to swap
 * @param y The index of the second value to swap
 */
fun swap(array: IntArray, x: Int, y: Int) {
    val temp = array[y]
    array[y] = array[x]
    array[x] = temp
}