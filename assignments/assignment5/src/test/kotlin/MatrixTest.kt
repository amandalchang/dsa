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
        myMatrix[1,1] = 2.0 // resetting
        assert(myMatrix[1,1] == 2.0)
        assert(myMatrix[1,2] == 12.0)
        assert(myMatrix[0,3] == 15.0)

        // Tests attempting to set() out of range
        try {
            myMatrix[10000, -239] = 12.0
        } catch (e: Exception) {
            if (e.message == "Index out of range") {
                assertTrue(true)
            } else { assert(false) }
        }
    }

    @Test
    fun get() {
        // Tests both set and get
        val myMatrix = Matrix(4)
        assert(myMatrix[1,1] == 0.0)
        assert(myMatrix[1,2] == 0.0)
        assert(myMatrix[0,3] == 0.0)
        myMatrix[1,1] = 7.0
        myMatrix[1,2] = 12.0 // Testing non-diagonal
        myMatrix[0,3] = 15.0
        assert(myMatrix[1,1] == 7.0)
        assert(myMatrix[1,2] == 12.0)
        assert(myMatrix[0,3] == 15.0)

        // Tests attempting to get() out of range
        try {
            myMatrix[10000, -239]
        } catch (e: Exception) {
            if (e.message == "Provided indices not in matrix") {
                assertTrue(true)
            } else { assert(false) }
        }
    }

    @Test
    fun times() {
        // Multiplying empty matrices
        var A = Matrix(2)
        var B = Matrix(2)
        val emptyMult = A*B
        assert(emptyMult[0,0] == 0.0)
        assert(emptyMult[0,1] == 0.0)
        assert(emptyMult[1,0] == 0.0)
        assert(emptyMult[1,1] == 0.0)

        // Testing general multiplication
        A = Matrix(2)
        A[0, 0] = 1.0
        A[0, 1] = 2.0
        A[1, 0] = 3.0
        A[1, 1] = 4.0
        B = Matrix(2)
        B[0, 0] = 5.0
        B[0, 1] = 6.0
        B[1, 0] = 7.0
        B[1, 1] = 8.0
        val product = A*B
        assert(product[0,0] == 19.0)
        assert(product[0,1] == 22.0)
        assert(product[1,0] == 43.0)
        assert(product[1,1] == 50.0)
    }

    @Test
    fun plus() {
        // Adding empty matrices
        var A = Matrix(2)
        var B = Matrix(2)
        val emptySum = A+B
        assert(emptySum[0,0] == 0.0)
        assert(emptySum[0,1] == 0.0)
        assert(emptySum[1,0] == 0.0)
        assert(emptySum[1,1] == 0.0)

        // Tests addition with positive and negative numbers
        A = Matrix(2)
        A[0, 0] = 1.0
        A[0, 1] = 2.0
        A[1, 0] = 3.0
        A[1, 1] = 4.0
        B = Matrix(2)
        B[0, 0] = -5.0
        B[0, 1] = 6.0
        B[1, 0] = 7.0
        B[1, 1] = 8.0
        val sum = A+B
        assert(sum[0,0] == -4.0)
        assert(sum[0,1] == 8.0)
        assert(sum[1,0] == 10.0)
        assert(sum[1,1] == 12.0)
    }

    @Test
    fun minus() {
        // Subtracting empty matrices
        var A = Matrix(2)
        var B = Matrix(2)
        val emptyDiff = A-B
        assert(emptyDiff[0,0] == 0.0)
        assert(emptyDiff[0,1] == 0.0)
        assert(emptyDiff[1,0] == 0.0)
        assert(emptyDiff[1,1] == 0.0)

        // Tests subtraction with negatives and positives
        A = Matrix(2)
        A[0, 0] = 1.0
        A[0, 1] = 2.5
        A[1, 0] = 30.0
        A[1, 1] = 4.0
        B = Matrix(2)
        B[0, 0] = 4.0
        B[0, 1] = 6.0
        B[1, 0] = 7.0
        B[1, 1] = 9.0
        val difference = A-B
        assert(difference[0,0] == -3.0)
        assert(difference[0,1] == -3.5)
        assert(difference[1,0] == 23.0)
        assert(difference[1,1] == -5.0)
    }

    @Test
    fun strassenMultiply() {
        // Strassen multiplying empty matrices
        var A = Matrix(2)
        var B = Matrix(2)
        val emptyMult = A.strassenMultiply(B)
        assert(emptyMult[0,0] == 0.0)
        assert(emptyMult[0,1] == 0.0)
        assert(emptyMult[1,0] == 0.0)
        assert(emptyMult[1,1] == 0.0)

        // Testing general multiplication
        A = Matrix(2)
        A[0, 0] = 1.0
        A[0, 1] = 2.0
        A[1, 0] = 3.0
        A[1, 1] = 4.0
        B = Matrix(2)
        B[0, 0] = 5.0
        B[0, 1] = 6.0
        B[1, 0] = 7.0
        B[1, 1] = 8.0
        val product = A.strassenMultiply(B)
        assert(product[0,0] == 19.0)
        assert(product[0,1] == 22.0)
        assert(product[1,0] == 43.0)
        assert(product[1,1] == 50.0)

        // Multiplying incompatible sizes
        val C = Matrix(1)
        C[0, 0] = 8.2
        try {
            A.strassenMultiply(C)
        } catch (e: Exception) {
            if (e.message == "Incompatible Strassen sizes") {
                assertTrue(true)
            } else { assert(false) }
        }
    }
}