import org.example.Matrix
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MatrixTest {

    @Test
    fun set() {
        // Tests both set and get
        val myMatrix = Matrix(4)
        myMatrix[1,1] = 7.0
        myMatrix[1,2] = 12.0 // Testing non-diagonal
        myMatrix[0,3] = 15.0
        assert(myMatrix[1,1] == 7.0)
        assert(myMatrix[1,2] == 12.0)
        assert(myMatrix[0,3] == 15.0)
    }

    @Test
    fun times() {

    }

    @Test
    fun plus() {
    }

    @Test
    fun minus() {
    }

    @Test
    fun strassenMultiply() {
    }
}