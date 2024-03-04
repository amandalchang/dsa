package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val list = intArrayOf(729, 29, 7, 2005, 5, 9, 2, 209374, 0, 32)
    println("Sorted elements of the array: ${mergeSort(list).joinToString(", ")}")
}
