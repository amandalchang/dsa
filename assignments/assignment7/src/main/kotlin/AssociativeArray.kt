package org.example

import kotlin.math.abs

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 */
class AssociativeArray<K, V>() {
    private var size = 0
    private var tableSizeIndex = 0
    private val tableSize = arrayOf(53, 97, 193, 389, 769, 1543, 3079, 6151,
    12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869,
    3145739, 6291469, 12582917, 25165843, 50331653, 100663319,
    201326611, 402653189, 805306457, 1610612741)
    private var buckets = MutableList (tableSize[tableSizeIndex]) { mutableListOf<Pair<K, V>>() }


    /**
     * Uses modular hashing to distribute keys into different buckets
     * @param k The key we want to distribute into a bucket
     * @return The index of the bucket we have assigned to the key
     */
    fun getIndex(k: K): Int {
        return abs(k.hashCode()) % tableSize[tableSizeIndex]
    }

    /**
     * Rehashes when the associative array becomes larger than the desired
     * load factor
     *
     */
    fun rehash() {
        tableSizeIndex++
        val flattenedArray = keyValuePairs()
        buckets = MutableList (tableSize[tableSizeIndex]) { mutableListOf() }
        for (pair in flattenedArray) {
            buckets[getIndex(pair.first)].add(pair)
        }
    }

    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    operator fun set(k: K, v: V) {
        val index = getIndex(k)
        val bucket = buckets[index]
        val pair = Pair(k, v)
        var existingNode = bucket.find { it.first == k }
        if (existingNode != null) {
            existingNode = pair
        } else {
            bucket.add(pair)
            size++
            val loadFactor = size / tableSize[tableSizeIndex]
            // ideal load factor is 0.75
            if (loadFactor > 0.75 && tableSize.size - 1 < tableSizeIndex) {
                rehash()
            }
        }
    }

    /**
     * @return true if [k] is a key in the associative array
     */
    operator fun contains(k: K): Boolean {
        val bucket = buckets[getIndex(k)]
        return bucket.find {it.first == k} != null
    }

    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    operator fun get(k: K): V? {
        val bucket = buckets[getIndex(k)]
        return bucket.find {it.first == k}?.second
    }

    /**
     * Remove the key, [k], from the associative array
     * @param k the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    fun remove(k: K): Boolean {
        val bucket = buckets[getIndex(k)]
        val deletion = bucket.removeIf {it.first == k}
        if (deletion) { size-- }
        return deletion
    }


    /**
     * @return the number of elements stored in the hash table
     */
    fun size(): Int = size

    /**
     * @return the full list of key value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K, V>> = buckets.flatten()
}