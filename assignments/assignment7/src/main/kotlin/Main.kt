package org.example

import LempelZiv
import Markov
import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    val filePath = "the-full-bee-movie-script.txt"
//    try {
//        val fileContent = File(filePath).readText()
//        val numSentences = 16
//        println(Markov(fileContent).generateMarkov(numSentences))
//    } catch (e: Exception) {
//        println("An error occurred while reading the file: ${e.message}")
//    }

    // Repetitive String
    val input = "ABABABABAABABABABAABABABABA"
    printCompressionRatio(input)

    // Non-repetitive Sentence
    val uniqueString = "The quick brown fox jumps over the lazy dog"
    printCompressionRatio(uniqueString)

    // Non-repetitive String (no spaces)
    val alphabet = "abcdefgjijklmnopqrstuvwxyz"
    printCompressionRatio(alphabet)

    // Peter Shor Example
    val peterShorExample = "AABABBBABAABABBBABBABB"
    printCompressionRatio(peterShorExample)

    // Bee Movie
    val beeMoviePath = "the-full-bee-movie-script.txt"
    val beeMovieString = File(beeMoviePath).readText()
    printCompressionRatio(beeMovieString)
}


/**
 * Prints the compression ratio between an uncompressed and compressed file;
 * assumes that the compressed file is a bitString while the original input
 * is a string.
 *
 * @param input the original uncompressed string
 * @param debug a boolean which determines whether LempelZiv will print out its
 *              encodings (before translation to binary) for debugging
 */
fun printCompressionRatio(input: String, debug: Boolean = false) {
    val compression = LempelZiv(input, debug).encode()
    if (compression != null) {
        val compressedSize = ((compression.length + 7) / 8).toDouble()
        val originalSize = input.toByteArray().size.toDouble()
        println("Compression ratio: ${compressedSize/originalSize}")
        if (debug) println()
    }
}