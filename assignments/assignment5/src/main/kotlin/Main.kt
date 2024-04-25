package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val wikipedia = NeedlemanWunsch("GCATGCG", "GATTACA")
    wikipedia.alignSequences(true)
}