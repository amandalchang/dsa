package org.example

import kotlin.math.max
class RedBlackTree<T:Comparable<T>> {
    private var root = getNilNode<T>()
    private var size = 0

    /**
     * Lookup function that returns the node if k is in the tree and a black nil
     * node (a node with null for all properties but color) if k is not
     * @param k the value being searched for in the tree
     * @return the node with value k or the nil node
     */
    fun search(k: T): Node<T> {
        var current = root
        while (current.data != null && k != current.data) {
            if (k < current.data!!) {
                current = current.left!!
            } else {
                current = current.right!!
            }
        }
        return current
    }

    /**
     * Inserts a node with [data] into the red-black tree and makes corrections
     * as necessary to maintain the tree properties
     */
    fun insert(data: T) {
        val z = Node(getNilNode(), getNilNode(), getNilNode(), data, true)
        var current = root
        var newParent = getNilNode<T>()

        while (current.data != null) {
            if (data > current.data!!) {
                newParent = current
                current = current.right ?: getNilNode()
            } else {
                newParent = current
                current = current.left ?: getNilNode()
            }
        }
        z.parent = newParent
        when {
            newParent.data == null -> root = z
            data < newParent.data!! -> newParent.left = z // data is only null for nilNode
            data > newParent.data!! -> newParent.right = z
            else -> throw Exception("ERROR: Duplicate values in BST")
        }
        insertFixup(z)
        size++
    }


    /**
     * Corrects the tree properties that may have been broken by the insertion
     * of [startNode] via rotations and recolorings.
     * @param startNode the newly inserted node
     */
    private fun insertFixup(startNode: Node<T>) {
         var z = startNode
         while (z.parent?.data != null && z.parent!!.color) {
            if (z.parent == z.parent?.parent!!.left) {
                if (z.parent?.parent?.right!!.color) {   // red uncle, recolor
                    z.recolor()
                    z = z.parent?.parent!!
                } else {                // rotate parent, triangle case
                    if (z == z.parent!!.right) {
                        z = z.parent!!
                        println("I am left rotating $z")
                        z.leftRotate()
                    }
                    z.parent?.color = false
                    z.parent?.parent!!.color = true
                    println("I am right rotating ${z.parent?.parent!!}")
                    z.parent?.parent!!.rightRotate()
                }
            } else {
                if (z.parent?.parent?.left!!.color) {
                    z.recolor()
                    z = z.parent?.parent!!
                } else {
                    if (z == z.parent!!.left) {
                        // rotate parent, opposite triangle case
                        z = z.parent!!
                        println("I am right rotating ${z}")
                        z.rightRotate()
                    }
                    z.parent?.color = false
                    z.parent?.parent?.color = true
                    println("I am left rotating ${z.parent?.parent}")
                    z.parent?.parent?.leftRotate()
                }
            }
            if (z == root) {
                break
            }
         }
         while (root.parent?.data != null) {
             root = root.parent!!
         }
         root.color = false
    }

    /**
     * Returns the integer height of the red-black tree
     */
    fun getHeight(): Int {
        return root.getHeight() ?: 0
    }

    /**
     * Returns true if the conditions:
     * 1. all red nodes have black trees
     * 2. the root node is black
     * 3. all leaves are black
     * 4. there are equal numbers of black nodes on every branch
     * are all true, otherwise returns false
     */
    fun checkInvariants() : Boolean {
        try {
            root.countBlackNodes()
            return root.checkBlackLeaves() &&
                    root.checkRedChildren() &&
                    !root.color
        } catch (e: Exception) {
            return false
        }

    }

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return (size == 0)
    }
}

/**
 * Represents a node in a red-black binary search tree.
 *
 * @param T the type of data stored in the node; must implement the Comparable interface.
 * @property left the left child of this node. It is null if there is no left child.
 * @property right the right child of this node. It is null if there is no right child.
 * @property parent the parent of this node. It is null if this node is the root.
 * @property data the data stored in this node, of type T.
 * @property color the color of the node, represented as a boolean (true for red, false for black).
 */
class Node<T: Comparable<T>>(
    var left: Node<T>?, var right: Node<T>?,
    var parent: Node<T>?, val data: T?, var color: Boolean) {

    /**
     * Recursively calculates the height of the tree that the node is a part of
     * @return an integer representing the height of the tree
     */
    fun getHeight() : Int {
        if (left == null) {
            return 0
        } else {
            return (1 + max(left!!.getHeight(), right!!.getHeight()))
        }
    }


    /**
     * Recursively checks that all leaf nodes are black nil nodes
     * @return a boolean where true is where every leaf node is black and false
     * is where any leaf node is not black
     */
    fun checkBlackLeaves() : Boolean {
        if (data == null) {
            return !color
        }
        return left!!.checkBlackLeaves() && right!!.checkBlackLeaves()
    }

    /**
     *  Recursively checks that all children of all red nodes are black
     *  @return a boolean where true means every red node has only black children
     *  and false is where at least one red node has a red child
     */
    fun checkRedChildren() : Boolean {
        if (data == null) {
            return true
        }
        if (color) {
            return left!!.checkRedChildren() &&
                    right!!.checkRedChildren() &&
                    !(left!!.color || right!!.color)
        }
        return left!!.checkRedChildren() &&
                right!!.checkRedChildren()
    }

    /**
     * Recursively counts the number of black nodes in every path towards
     * a leaf node and throws an "Unequal number of black nodes on each
     * side" exception if the paths are ever unequal
     * @return an integer representing the number of black nodes to a leaf
     * node, including the node itself if it is black.
     */
    fun countBlackNodes() : Int {
        if (data == null) {
            return 1
        }
        var rightBlacks = right?.countBlackNodes() ?: 0
        var leftBlacks = left?.countBlackNodes() ?: 0
        if (rightBlacks == leftBlacks) {
            if (!color) {
                rightBlacks += 1
                leftBlacks += 1
            }
        } else throw Exception("Unequal number of black nodes on each side")
        return rightBlacks
    }

    /**
     * Returns a string representation of the contents of a Node<T>?
     * @return the filled out fields of a node's parameters as a string
     */
    override fun toString(): String {
        return "left: ${left?.data}, right: ${right?.data}, " +
                "parent: ${parent?.data}, data: ${data}, color: ${color})"
    }

    /**
     * Left rotates a node
     */
    fun leftRotate() {
        right?.let{it.parent = parent}
        parent = right
        right = right?.left
        parent?.let{it.left = this}
        right?.let{it.parent = this}
    }

    /**
     * Right rotates a node
     */
    fun rightRotate() {
        left?.let{it.parent = parent}
        parent = left
        left = left?.right
        parent?.let { it.right = this }
        left?.let { it.parent = this }
    }

    /**
     * Recolors the parent, grandparent and uncle of the
     * node from black to red or red to black.
     */
    fun recolor() {
        parent?.parent?.let { grandparent ->
            // recolors grandparent
            grandparent.color = !grandparent.color
            // recolors grandparent's children
            grandparent.left?.let {it.color = !it.color}
            grandparent.right?.let {it.color = !it.color}
        } ?: run {
            parent?.let {it.color = !it.color}
        }
    }

}

/**
 * Creates a new black nil node with a null left child, null right child, null
 * parent and null data when called
 * @return a black nil node: Node(null, null, null, null, false)
 */
fun <T : Comparable<T>>getNilNode(): Node<T> {
    return Node(null, null, null, null, false)
}