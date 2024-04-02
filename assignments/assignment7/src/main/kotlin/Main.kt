package org.example

import LempelZiv
// import Markov
import java.io.File
/**
 * Prints the compression ratio between an uncompressed and compressed file;
 * assumes that the compressed file is a bitString while the original input
 * is a string.
 *
 * @param input the original uncompressed string
 * @param debug a boolean which determines whether LempelZiv will print out its
 *              encodings (before translation to binary) for debugging
 * @return The ratio between the compressed size and the original size
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
fun main() {
//    val filePath = "the-full-bee-movie-script.txt"
//    try {
//        val fileContent = File(filePath).readText()
//        val numSentences = 16
//        println(Markov(fileContent).generateMarkov(numSentences))
//    } catch (e: Exception) {
//        println("An error occurred while reading the file: ${e.message}")
//    }

    // Super Repetitive and Non-unique String
    val repetitive = "ABABABABAABABABABAABABABABA"
    printCompressionRatio(repetitive)

    // Repetitive String
    val repeat = "olin olin olin olin olin olin olin olin olin olin olin"
    printCompressionRatio(repeat)

    // Non-repetitive Sentence
    val uniqueSent = "The quick brown fox jumps over the lazy dog"
    printCompressionRatio(uniqueSent)

    // Maximally Non-repetitive String (no spaces)
    val alphabet = "abcdefgjijklmnopqrstuvwxyz"
    printCompressionRatio(alphabet)

    // Peter Shor Example
    val peterShorExample = "AABABBBABAABABBBABBABB"
    printCompressionRatio(peterShorExample)

    // The Bee Movie
    val beeMoviePath = "the-full-bee-movie-script.txt"
    val beeMovieString = File(beeMoviePath).readText()
    printCompressionRatio(beeMovieString)
}


