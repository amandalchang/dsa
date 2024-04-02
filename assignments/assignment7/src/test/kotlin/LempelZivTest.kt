import org.example.printCompressionRatio
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class LempelZivTest {

    @Test
    fun encode() {
        // Tests encode() with only two character options to make it easy to
        // manually generate expectedBinary
        val input = "ABABABABA"
        // With an innovationCodebook and innovationMinBitWidth:
        // "0,0 | 0,1 | 01,1 | 11,0 | 010,0"
        val expectedBinary = "00010111100100"
        val lempelZiv = LempelZiv(input, true)
        assert(expectedBinary == lempelZiv.encode())


        // Testing the Peter Shor example, although I edited the last portion
        val peterShorExample = "AABABBBABAABABBBABBABB"
        val expectedOutput = "0011101001010010111001011001001"
        val shorLempelZiv = LempelZiv(peterShorExample)
        assert(expectedOutput == shorLempelZiv.encode())

        // Checking that an empty string doesn't break it
        val empty = ""
        val emptyBinary = ""
        val emptyLZ = LempelZiv(empty, true)
        println(emptyBinary == emptyLZ.encode())
    }
}