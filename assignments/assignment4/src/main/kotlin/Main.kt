package org.example

import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.measureTime

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // random sequence benchmarking; random array of size 2, 4, 8, 16, ... 128
    var arraySize = 2 // I manually changed it to 3 to get more data
    while (arraySize <= 128){
        randomSequenceBench(100, arraySize)
        arraySize *= 2
    }

    // pre-sorted array
    var sortedArraySize = 2 // I manually changed it to 3 to get more data
    while (sortedArraySize <= 128) {
        val sortedSequence = IntArray(sortedArraySize) { it }
        benchmark(sortedSequence, numRuns = 1000000)
        sortedArraySize *= 2
    }

    // backwards array
    val backwards300Sequence = IntArray(300) { 300 - it }
    println("backwards sequence input array 300 downTo 1")
    benchmark(backwards300Sequence)
}

/**
 * Takes the mean of the benchmarks for heapSort, mergeSort, selectionSort and
 * insertionSort on a given array over 20,000 runs and prints them
 *
 * @param array The array that the sorting algorithms will be benchmarked on
 * @param printOut A boolean which controls whether benchmark
 *                 will print its results
 * @param numRuns The number of times benchmark will make the algorithms sort
 *                a given sequence, by default 20000
 * @return An array containing the averaged benchmark times for heapSort,
 *         mergeSort, selectionSort, and insertionSort in that order
 */
fun benchmark(array: IntArray, printOut: Boolean = true, numRuns: Int = 20000): Array<Duration> {
    var heapAvg = 0.0.nanoseconds
    var mergeAvg = 0.0.nanoseconds
    var selectionAvg = 0.0.nanoseconds
    var insertionAvg = 0.0.nanoseconds
    for (i in 1..numRuns) {
        heapAvg += measureTime { heapSort(array.copyOf()) }
        mergeAvg += measureTime { mergeSort(array.copyOf()) }
        selectionAvg += measureTime { selectionSort(array.copyOf()) }
        insertionAvg += measureTime { insertionSort(array.copyOf()) }
    }
    if (printOut) {
        println("Array Length: ${array.size}")
        println("heapSort: ${heapAvg / numRuns}")
        println("mergeSort: ${mergeAvg / numRuns}")
        println("selectionSort: ${selectionAvg / numRuns}")
        println("insertionSort: ${insertionAvg / numRuns}")
        println()
    }
    return arrayOf(heapAvg/numRuns, mergeAvg/numRuns, selectionAvg/numRuns, insertionAvg/numRuns)
}

fun randomSequenceBench(randRuns: Int, arrayLength: Int) {
    // random array
    val range = 100
    var heapAvg = 0.0.nanoseconds
    var mergeAvg = 0.0.nanoseconds
    var selectionAvg = 0.0.nanoseconds
    var insertionAvg = 0.0.nanoseconds
    //println("input array ${randomArr.contentToString()}")
    for (i in 1..randRuns) {
        val randomArr = IntArray(arrayLength) { Random.nextInt(range) }
        val benchRun = benchmark(randomArr, false)
        heapAvg += benchRun[0]
        mergeAvg += benchRun[1]
        selectionAvg += benchRun[2]
        insertionAvg += benchRun[3]
    }
//    println("A random sequence benchmark with $randRuns runs on " +
//            "a(n) array of length $arrayLength resulted in...")
    println("Array Length: $arrayLength")
    println("heapSort: ${heapAvg / randRuns}")
    println("mergeSort: ${mergeAvg / randRuns}")
    println("selectionSort: ${selectionAvg / randRuns}")
    println("insertionSort: ${insertionAvg / randRuns}")
    println()
}