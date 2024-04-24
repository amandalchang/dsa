package org.example

/**
 * Defines a data structure for an [n]x[n] square matrix to enable the creation
 * and modification of a matrix object; provides functionality for to multiply,
 * divide, add and subtract other matrices from the matrix
 *
 * @param n the size parameter of the nxn square matrix object
 */
class Matrix (private val n: Int) {
    private val data: Array<DoubleArray> = Array(n) {DoubleArray(n)}

    /**
     * Sets the value at index [i],[j] in the matrix to be
     * [num]
     * @param i the index of the row (zero indexing)
     * @param j the index of the column (zero indexing)
     * @param num the Double whose value will be placed at the position
     * specified
     */
    operator fun set(i: Int, j: Int, num: Double) {
        if (i in 0..<n && j in 0..<n) {
            data[i][j] = num
        }
    }

    /**
     * Returns the value at the index [i],[j] in the matrix
     * @param i the index of the row (zero indexing)
     * @param j the index of the column (zero indexing)
     */
    operator fun get(i: Int, j: Int): Double {
        return data[i][j]
    }

    /**
     * Multiply [this] square matrix by [other] square matrix.
     * @return [this]*[other] if the dimensions are compatible and throws
     * an incompatible sizes exception otherwise
     */
    operator fun times(other: Matrix): Matrix {
        if (this.n != other.n) throw Exception("Incompatible sizes")
        val product = Matrix(n)
        for (i in product.data.indices) { // for each row
            for (j in product.data[i].indices) {
                for (k in 0 ..< n) {
                    product[i,j] += this[i,k]*other[k,j]
                }
            }
        }
        return product
    }

    /**
     * Adds [this] to [other]
     * @return [this] + [other] if the dimensions are the same and throws
     * an incompatible sizes exception otherwise
     */
    operator fun plus(other: Matrix): Matrix {
        if (this.n != other.n) throw Exception("Incompatible sizes")
        val sum = Matrix(n)
        for (i in sum.data.indices) { // for each row
            for (j in sum.data[i].indices) {
                sum[i,j] = this[i,j] + other[i,j]
            }
        }
        return sum
    }

    /**
     * Subtracts [other] from [this]
     * @return [this] - [other] if the dimensions are the same and throws
     * an incompatible sizes exception otherwise
     */
    operator fun minus(other: Matrix): Matrix {
        if (this.n != other.n) throw Exception("Incompatible sizes")
        val sub = Matrix(n)
        for (i in sub.data.indices) { // for each row
            for (j in sub.data[i].indices) {
                sub[i,j] = this[i,j] - other[i,j]
            }
        }
        return sub
    }


    /**
     * Multiply [this] matrix by [other].
     * Your code should use Strassen's algorithm
     * @return [this]*[other] if the dimensions are compatible and null otherwise
     */
    fun strassenMultiply(other: Matrix): Matrix {
        if (this.n != other.n) throw Exception("Incompatible Strassen sizes")
        if (n <= 2) {
            return this*other
        }
        val M = Matrix(n)
        val (A11, A12, A21, A22) = this.splitMatrix()
        val (B11, B12, B21, B22) = other.splitMatrix()

        val M1 = (A11 + A22).strassenMultiply(B11 + B22)
        val M2 = (A21 + A22).strassenMultiply(B11)
        val M3 = A11.strassenMultiply(B12 - B22)
        val M4 = A22.strassenMultiply(B21 - B11)
        val M5 = (A11 + A12).strassenMultiply(B22)
        val M6 = (A21 - A11).strassenMultiply(B11 + B12)
        val M7 = (A12 - A22).strassenMultiply(B21 + B22)

        val C11 = M1 + M4 - M5 + M7
        val C12 = M3 + M5
        val C21 = M2 + M4
        val C22 = M1 - M2 + M3 + M6

        for (i in 0..<n/2) {
            M.data[i] = C11.data[i] + C12.data[i]
            M.data[i+n/2] = C21.data[i] + C22.data[i]
        }
        return M
    }

    /**
     * Splits a matrix with side lengths divisible by two into four equal
     * portions
     * @return an array of the four matrices
     */
    private fun splitMatrix(): Array<Matrix> {
        val A11 = Matrix(n/2)
        val A12 = Matrix(n/2)
        val A21 = Matrix(n/2)
        val A22 = Matrix(n/2)
        for (i in 0..<(n/2)) {
            for (j in 0..<(n/2)) {
                A11[i,j] = this[i,j]
            }
        }
        for (i in n/2..<n) {
            for (j in 0..<(n/2)) {
                A12[i,j] = this[i,j]
            }
        }
        for (i in 0..<(n/2)) {
            for (j in n/2..<n) {
                A21[i,j] = this[i,j]
            }
        }
        for (i in n/2..<n) {
            for (j in n/2..<n) {
                A22[i,j] = this[i,j]
            }
        }
        return arrayOf(A11, A12, A21, A22)
    }
}