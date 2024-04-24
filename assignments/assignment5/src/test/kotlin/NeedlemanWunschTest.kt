import org.example.NeedlemanWunsch
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class NeedlemanWunschTest {
    @Test
    fun alignSequences() {
        // Testing for the general case
        val wikipedia = NeedlemanWunsch("GCATGCG", "GATTACA")
        var aligned = wikipedia.alignSequences()
        assert(aligned.first == "GCA-TGCG" || aligned.first == "GCATG-CG")
        assert(aligned.second == "G-ATTACA")

        // Both sequences are identical
        val matching = NeedlemanWunsch("GATTACA", "GATTACA")
        aligned = matching.alignSequences()
        assert(aligned.first == aligned.second)

        // Sequences have nothing in common
        val mismatching = NeedlemanWunsch("AAAAAA", "TTTTTT")
        aligned = mismatching.alignSequences()
        assert(aligned.first == "AAAAAA")
        assert(aligned.second == "TTTTTT")

        // Sequences are not the same length
        val diffLength = NeedlemanWunsch("AA", "GATTA")
        aligned = diffLength.alignSequences()
        assert(aligned.first == "-A--A")
        assert(aligned.second == "GATTA")

        // Only one proper way to align
        val nw = NeedlemanWunsch("CTAC", "CTC")
        aligned = nw.alignSequences()
        assert(aligned.first == "CTAC")
        assert(aligned.second == "CT-C")

        // Empty sequences
        val empty = NeedlemanWunsch("","")
        aligned = empty.alignSequences()
        assert(aligned.first == "")
        assert(aligned.second == "")
    }
}