import org.example.AssociativeArray
import org.example.BinaryWriter
import java.math.BigInteger

/**
 * LempelZiv encoder class that compresses an input string using the Lempel-Ziv algorithm.
 *
 * @param input The string to be compressed.
 * @param debug Flag to enable debug mode. Default is False.
 */
class LempelZiv(
    private val input: String,
    private val debug: Boolean = false,
) {
    private var codebook = AssociativeArray<String, Int>()
    private var innovationCodebook = AssociativeArray<String, Int>()
    private var binary = BinaryWriter(true)
    private var currentPosition = 1
    private var result = mutableListOf("")
    private var debugList = mutableListOf<Int>()
    private var innovationMinBitWidth = 0

    /**
     * Builds the character codebook required for compression, where each key
     * corresponds to a unique character from the original string and the values
     * are the assigned indices; this guarantees that the codes corresponding
     * to the innovation symbol in the encode function can fit in a byte.
     */
    private fun buildCharacterCodebook() {
        var innovationPosition = 0
        for (char in input) {
            if (char+"" !in innovationCodebook) {
                innovationCodebook[char+""] = innovationPosition++
            }
        }
        innovationMinBitWidth =
            BigInteger.valueOf((innovationCodebook.size()-1).toLong()).bitLength()
    }

    /**
     * Builds the codebook required for compression, where each key corresponds
     * to a unique subsequence from the original string and the values are the
     * assigned indices
     */
    private fun buildCodebook() {
        codebook[""] = 0
        buildCharacterCodebook()

        var lastCode = ""
        for (char in input) {
            val currentString = lastCode + char
            if (currentString !in codebook) {
                result.add(currentString)
                codebook[currentString] = currentPosition++
                lastCode = ""
            } else {
                lastCode += char
            }
        }
        if (lastCode.isNotEmpty()) result.add(lastCode)
    }

    /**
     * Encodes the input string using the Lempel-Ziv algorithm and returns the compressed binary string.
     *
     * @return Compressed binary string.
     */
    fun encode(): String? {
        buildCodebook()
        var runCounter = 0
        var bitWidth = 1
        for (phrase in result) {
            if (phrase.isNotEmpty()) {
                val substringIndex = codebook[phrase.dropLast(1)] ?: 0
                val innovationSymbolIndex = innovationCodebook[phrase.last().toString()] ?: 0
                //

                // Dynamically calculates the bitWidth of the substring given
                // there must be room for all possible substring indexes at that time
                while (runCounter >= (1 shl bitWidth)) {
                    bitWidth++
                }
                binary.write(substringIndex, bitWidth)
                // Encoding the index of the char into the minimum number of bits based off
                // how many possible characters there are
                binary.write(innovationSymbolIndex, innovationMinBitWidth)


                if (debug) debugList.addAll(listOf(substringIndex, innovationSymbolIndex))
                runCounter++
            }
        }
        if (debug) {
            println("Encodings: $debugList")
        }
        return binary.toBinaryString()
    }
}