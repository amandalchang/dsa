package org.example

/**
 * Sorts an array of integers using the Merge Sort algorithm.
 *
 * This function recursively divides the input array into two roughly equal
 * halves, sorts each half, and then merges the two sorted halves into a single
 * sorted array using the merge function
 *
 * @param list An array of integers to be sorted.
 * @return A new sorted array of integers.
 */
fun mergeSort(list: IntArray): IntArray {
    if (list.size <= 1) {
        return list
    }
    val splitPoint = (list.size/2).toInt()
    val left = list.sliceArray(0 until splitPoint)
    val right = list.sliceArray(splitPoint until list.size)
    return merge(mergeSort(left), mergeSort(right))
}

/**
 * Merges two sorted arrays into a single sorted array.
 *
 * This function iterates over the elements of both input arrays sequentially,
 * comparing the current elements of each array and adding the smaller one to the
 * resulting array until all elements are processed.
 *
 * @param left The first sorted array to be merged.
 * @param right The second sorted array to be merged.
 * @return A new array containing all the elements from both input arrays, sorted.
 */
fun merge(left: IntArray, right: IntArray): IntArray {
    val mergedList: IntArray = IntArray(left.size + right.size)
    var i = 0
    var j = 0
    for (n in mergedList.indices) {
        when {
            // we've finished sorting left and are adding in the remaining right side
            i >= left.size -> { mergedList[n] = right[j]; j++ }

            // we've finished sorting right and are adding in the remaining left side
            j >= right.size -> { mergedList[n] = left[i]; i++ }

            // if left[i] is smaller than right[j] stick in the left value and
            // increment to the next left element
            left[i] <= right[j] -> {mergedList[n] = left[i]; i++}

            // if left[i] is greater than right[j] stick in the right value and
            // increment to the next right element
            else -> { mergedList[n] = right[j]; j++ }
        }
    }
    return mergedList
}