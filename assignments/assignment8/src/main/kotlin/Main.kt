package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val tree = RedBlackTree<Int>()
    for (i in arrayOf(4, 3, 2, 1)) {
        tree.insert(i)
    }
}