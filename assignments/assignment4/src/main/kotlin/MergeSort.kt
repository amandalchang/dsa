package org.example

fun mergeSort(list: IntArray): IntArray {
    if (list.size == 1) {
        return list
    }
    val splitPoint = (list.size/2).toInt()
    val left = list.sliceArray(0 until splitPoint)
    val right = list.sliceArray(splitPoint until list.size)
    return merge(mergeSort(left), mergeSort(right))
}

fun merge(left: IntArray, right: IntArray): IntArray {
    val mergedList: IntArray = IntArray(left.size + right.size)
    var i = 0
    var j = 0
    for (n in mergedList.indices) {
        when {
            i >= left.size -> {
                mergedList[n] = right[j]
                j++
            } // we've finished sorting left and are adding in the remaining right side
            j >= right.size -> {
                mergedList[n] = left[i]
                i++
            } // we've finished sorting right and are adding in the remaining left side
            left[i] <= right[j] -> {
                mergedList[n] = left[i]
                i++
            } // if left[i] is smaller than right[j] stick in the left value and
            // increment to the next left element
            else -> {
                mergedList[n] = right[j]
                j++
            } // if left[i] is greater than right[j] stick in the right value and
            // increment to the next right element
        }
    }
    return mergedList
}