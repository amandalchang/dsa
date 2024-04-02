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

//    // Input string
//    val input = "ABABABABA"
//    println(input)
//    // Without an innovationCodebook
//    // "0,00000001 | 0,00000010 | 01,00000010 | 11,00000001 | 010,00000001"
//    // val expectedBinary = "0000000010000000100100000010110000000101000000001"
//
//    // With an innovationCodebook
//    // "0,00000000 | 0,00000001 | 01,00000001 | 11,00000000 | 010,00000000"
//    // With an innovationCodebook and innovationMinBitWidth "0,0 | 0,1 | 01,1 | 11,0 | 010,0"
//    val expectedBinary = "00010111100100"
//    val lempelZiv = LempelZiv(input, true)
//    val compressedData = lempelZiv.encode()
//
//    println("Compressed data is $compressedData")
//    println("Expected data is $expectedBinary")
//    println(expectedBinary == compressedData)
//    printCompressionRatio(compressedData, input)

    val peterShorExample = "AABABBBABAABABBBABBABB"
    println(peterShorExample)
    val expectedOutput = "001110100101001011100101100111"
    println(expectedOutput)
    val shorLempelZiv = LempelZiv(peterShorExample, true)
    val compression = shorLempelZiv.encode()
    println(compression)
    assert(expectedOutput == compression)

    val empty = ""
    val emptyBinary = ""
    val emptyLZ = LempelZiv(empty, true)
    println(emptyBinary == emptyLZ.encode())


//    // Bee Movie
//    val filePath = "the-full-bee-movie-script.txt"
//    val fileContent = File(filePath).readText()
//    val beeMovie = LempelZiv(fileContent, false)
//    val compressedBeeMovie = beeMovie.encode()
//
//    // characterizing how well it compressed
//    printCompressionRatio(compressedBeeMovie, fileContent)
}


/**
 * Prints the compression ratio between an uncompressed and compressed file;
 * assumes that the compressed file is a bitString while the original input
 * is a string.
 *
 * @param compression the compressed version of the input
 * @param input the original uncompressed string
 */
fun printCompressionRatio(compression: String?, input: String) {
    if (compression != null) {
        val compressedSize = ((compression.length + 7) / 8).toDouble()
        val originalSize = input.toByteArray().size.toDouble()
        println("The compression ratio is ${compressedSize/originalSize}")
    }
}