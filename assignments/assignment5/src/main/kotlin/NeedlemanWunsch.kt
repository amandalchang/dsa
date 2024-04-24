package org.example
/**
 * Defines a class for the Needleman-Wunsch algorithm for the purpose of
 * aligning [seqOne] and [seqTwo]. Also provides functionality for storing
 * and printing the scoring matrix.
 *
 * @param seqOne the first sequence
 * @param seqTwo the second sequence
 */
class NeedlemanWunsch(private val seqOne: String, private val seqTwo: String) {
    private val height = seqOne.length + 1
    private val width = seqTwo.length + 1
    private val nwMatrix: MutableList<MutableList<Score>> =
        MutableList(height) {MutableList(width) {Score(0, 0)}}
    // The initialization function fills in the matrix with the starting scores
    // from matching any character with epsilon (as referred to in the Needleman
    // Wunsch wikipedia page). We end up with a filled topmost row and leftmost
    // column.
    init{
        for (j in 0..<width) {
            nwMatrix[0][j] = Score(1, -j)
        }
        for (i in 0..<height) {
            nwMatrix[i][0] = Score(2, -i)
        }
        fillMatrix()
    }

    /**
     * Fills a matrix with the dimensions of seqOne (height) and seqTwo (width)'s
     * size from left to right using a Needleman-Wunsch algorithm. The matrix is
     * initialized with one filled row and one filled column, so that subsequent
     * values can be calculated using the maxiumum of the value above, to the
     * upper left, and to the left of the cell.
     *
     * Each cell contains an instance of the data class Score, which contains a
     * value and the direction from which the value was calculated.
     *
     * Matches have a score of 1, while mismatches and "indels"
     * (insertion/deletion) have value -1.
     */
    private fun fillMatrix() {
        for (j in 1..<width) {
            for (i in 1..<height) {
                if (seqOne[i-1] == seqTwo[j-1]) {
                    nwMatrix[i][j] = Score(3, nwMatrix[i-1][j-1].value + 1)
                } else {
                    val maximum =
                        maxOf(nwMatrix[i-1][j-1].value,
                        nwMatrix[i][j-1].value,
                        nwMatrix[i-1][j].value)
                    when (maximum) {
                        nwMatrix[i-1][j-1].value ->
                            nwMatrix[i][j] = Score(3, maximum-1)// Substitution
                        nwMatrix[i-1][j].value ->
                            nwMatrix[i][j] = Score(2, maximum-1)// Insertion
                        nwMatrix[i][j-1].value ->
                            nwMatrix[i][j] = Score(1, maximum-1)// Deletion
                    }

                }
            }
        }
    }

    /**
     *  Prints the Needleman-Wunsch matrix used to calculate the best alignment
     *  between the sequences
     */
    private fun printMatrix() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                print("(${nwMatrix[i][j].value}, " +
                        "${directionToString(nwMatrix[i][j].direction)})\t")
            }
            println()  // Move to the next line after each row
        }
    }

    /**
     * Returns the associated string direction from the integer value
     * representing direction
     * @param direction the integer representing direction
     * @return a string representing direction more explicitly
     */
    private fun directionToString(direction: Int): String = when (direction) {
        3 -> "Diag"
        2 -> "Vert"
        1 -> "Horiz"
        else -> "None"
    }

    /**
     * Moves from the bottom right corner of the Needleman-Wunsch matrix and
     * follows the directions to find the most optimal alignment of a given
     * two sequences. Inserts dashes for indels to represent spaces in the
     * alignment. Optionally prints the Needleman Wunsch and/or final aligned
     * sequences.
     *
     * @param printSequences a default false boolean that determines whether the
     * aligned sequences are printed
     * @param printMatrix a default false boolean that determines whether the
     * associated Needleman-Wunsch matrix is printed
     * @return a pair of strings where the first string is seqOne with
     * appropriately placed spacings and the second string is seqTwo with
     * appropriately placed spacings
     */
    fun alignSequences(
        printMatrix: Boolean = false,
        printSequences: Boolean = false
    ) : Pair<String, String> {
        val seqOneBuilder = StringBuilder()
        val seqTwoBuilder = StringBuilder()
        var i = height - 1
        var j = width - 1
        while (i > 0 || j > 0) {
            when {
                i > 0 && j > 0 && nwMatrix[i][j].direction == 3 -> { // Moving diagonally (up)
                    seqOneBuilder.append(seqOne[i-1])
                    seqTwoBuilder.append(seqTwo[j-1])
                    i--; j--
                }
                i > 0 && (j == 0 || nwMatrix[i][j].direction == 2) -> { // Moving vertically (up), insertion
                    seqOneBuilder.append(seqOne[i-1])
                    seqTwoBuilder.append("-")
                    i--
                }
                j > 0 && (i == 0 || nwMatrix[i][j].direction == 1) -> { // Moving horizontally (left), deletion
                    seqOneBuilder.append("-");
                    seqTwoBuilder.append(seqTwo[j-1])
                    j--
                }
                else -> { throw Exception("(j: ${j},i: ${i}) Directionless Error") }
            }
        }
        val alignedSeqOne = seqOneBuilder.reverse().toString()
        val alignedSeqTwo = seqTwoBuilder.reverse().toString()
        if (printMatrix) {
            printMatrix()
            println()
        }
        if (printSequences) {
            println("Your new sequences are:")
            println(alignedSeqOne)
            println(alignedSeqTwo)
            println()
            println()
        }
        return Pair(alignedSeqOne, alignedSeqTwo)
    }

}

/**
 * A data class containing a score with direction and value
 * @property direction an integer representing the direction of the score:
 * 0 is empty, 1 is horizontal, 2 is vertical and 3 is diagonal
 * @property value an integer representing the value of the score
 */
data class Score(val direction: Int, val value: Int)