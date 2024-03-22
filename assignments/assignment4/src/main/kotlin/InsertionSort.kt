package org.example

/**
 * Sorts an integer array
 *
 * @param array The intArray to be sorted
 * @return A sorted intArray with the values of array
 */
fun insertionSort(array: IntArray): IntArray {
    if (array.size <= 1) {
        return array
    }

    // This would only sort a list of length 2
    for (i in 1 until array.size) {
        val unsorted = array[i]
        var j = i - 1

        while (j >= 0 && array[j] > unsorted) {
            array[j + 1] = array[j]
            j--
        }
        array[j + 1] = unsorted
    }
    return array
    }