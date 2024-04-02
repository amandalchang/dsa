import org.example.AssociativeArray
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AssociativeArrayTest {
    @Test
    fun rehash() {
        // An associative array with 57 elements will cause it to call rehash
        // internally since it should rehash for the first time when more than
        // 0.75*53 = 40 elements are added (desired load factor*number of buckets)
        val intMap = AssociativeArray<Int, Int>()
        for (i in 1..57) {
            intMap[i] = i+2
        }

        var verifications = 0
        for (i in 1..57){
            if (intMap[i] == i+2) {
                verifications++
            }
        }
        assert(verifications == 57)
    }

    @Test
    fun set() {
        // This also tests getIndex
        // Testing general case of adding key value pairs
        val map = AssociativeArray<String, Int>()
        map["orange"] = 963
        map["apple"] = 23
        map["coconut"] = 2978
        // This also tests the get function
        assert(map["orange"] == 963)
        assert(map["apple"] == 23)
        assert(map["coconut"] == 2978)
        // Testing the empty string
        map[""] = 0
        assert(map[""] == 0)
    }

    @Test
    fun contains() {
        // Testing that everything added to an array is in the array
        val map = AssociativeArray<String, Int>()
        map["orange"] = 963
        map["apple"] = 23
        map["coconut"] = 2978
        assert("orange" in map && "apple" in map && "coconut" in map)
        // Testing that things not added the array are not in the array
        assert("banana" !in map)
        // Testing that things removed from the array are no longe in the array
        map.remove("coconut")
        assert("coconut" !in map)
    }

    @Test
    fun remove() {
        // Testing that values are truly removed from the array by .remove()
        val map = AssociativeArray<String, Int>()
        map["orange"] = 963
        map["apple"] = 23
        map["coconut"] = 2978
        assert(map["orange"] == 963)
        map.remove("orange")
        assert(map["orange"] == null)
    }

    @Test
    fun size() {
        // Testing that as I remove things the size is updated
        val map = AssociativeArray<String, Int>()
        map["orange"] = 963
        map["apple"] = 23
        map["coconut"] = 2978
        assert(map.size() == 3)
        map.remove("orange")
        assert(map.size() == 2)
        map.remove("apple")
        assert(map.size() == 1)
        // Testing that as I add things the size is updated
        map["dragon fruit"] = 22
        assert(map.size() == 2)
    }

    @Test
    fun keyValuePairs() {
        // Testing that the associative array flattens properly
        val map = AssociativeArray<Int, Int>()
        map[1] = 33
        map[6] = 70
        val flatten = map.keyValuePairs()
        assert(flatten == listOf(Pair(1,33),Pair(6,70)))
    }
}