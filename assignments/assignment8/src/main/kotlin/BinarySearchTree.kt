package org.example
interface BinarySearchTree<Int> {
    fun isEmpty(): Boolean
}
class RedBlackTree<T:Comparable<T>>: BinarySearchTree<Int> {
    private var nilNode: Node<Int> = Node(null, null, null, null, false)

    init {
        nilNode.left = nilNode
        nilNode.right = nilNode
        nilNode.parent = nilNode
    }

    private var root = nilNode
    private var size = 0


    fun insert(data: Int) {
        val z = Node(nilNode, nilNode, nilNode, data, true)
        var current = root
        var newParent = nilNode

        while (current != nilNode) {
            if (data > current.data!!) {
                newParent = current
                current = current.right ?: nilNode
            } else {
                newParent = current
                current = current.left ?: nilNode
            }
        }
        z.parent = newParent
        when {
            newParent == nilNode -> root = z
            data < newParent.data!! -> newParent.left = z // data is only null for nilNode
            data > newParent.data!! -> newParent.right = z
            else -> throw Exception("ERROR: Duplicate values in BST")
        }
        insertFixup(z)
    }
    //     def insert_fixup(self, z):
    //        while z.p and z.p.color == RED:
    //            if z.p == z.p.p.left:
    //                y = z.p.p.right
    //                if y.color == RED:
    //                    z.p.color = BLACK
    //                    y.color = BLACK
    //                    z.p.p.color = RED
    //                    z = z.p.p
    //                else:
    //                    if z == z.p.right:
    //                        z = z.p
    //                        self.left_rotate(z)
    //                    z.p.color = BLACK
    //                    z.p.p.color = RED
    //                    self.right_rotate(z.p.p)
    //            else:
    //                y = z.p.p.left
    //                if y.color == RED:
    //                    z.p.color = BLACK
    //                    y.color = BLACK
    //                    z.p.p.color = RED
    //                    z = z.p.p
    //                else:
    //                    if z == z.p.left:
    //                        z = z.p
    //                        self.right_rotate(z)
    //                    z.p.color = BLACK
    //                    z.p.p.color = RED
    //                    self.left_rotate(z.p.p)
    //            if z == self.root:
    //                break
    //        self.root.color = BLACK
     fun insertFixup(z: Node<Int>) {
        while (z.parent != nilNode && z.parent!!.color) {
            if (z.parent == z.parent.parent.left) {
                if (z.parent.parent.right.color) {   // red uncle, recolor
                    z.recolor()
                    z = z.parent.parent
                } else {                // rotate parent, triangle case
                    if (z == z.parent.right) {
                        z = z.parent
                        z.leftRotate()
                    }
                    z.parent.color = 0
                    z.parent.parent.color = 1
                    z.parent.parent.rightRotate()
                }
            } else {
                if (z.parent.parent.left.color) {
                    z.recolor()
                    z = z.parent.parent
                } else {
                    if (z == z.parent.left) {
                        // rotate parent, opposite triangle case
                        z = z.parent
                        z.rightRotate()
                    }
                    z.parent.color = 0
                    z.parent.parent.color = 1
                    z.parent.parent.leftRotate()
                }
            }
            if (z == root) {
                break
            }
        }
        root.color = 0
    }

//    fun insertFixup(z: Node<Int>) {
//        while ((z.parent != nilNode) && z.parent!!.color) {
//            if (z == root) return
//            if (z.parent!!.parent != nilNode) {
//                when {
//                    z.parent == z.parent!!.parent!!.left -> leftFixup()
//                    z.parent == z.parent!!.parent!!.right -> rightFixup()
//                }
//            }
//            }
//        }
//        when {
//            z == root -> z.color = 0 // recoloring z black in case 1
//            z.uncle().color = 1 -> z.recolorPrntGrprntUnc()
//            z.uncle().color == 0 && isTriangleFormed() -> if (z.parent z.parent.rotate()
//            z.uncle().color == 0 && isLineFormed() -> z.parent.parent.rotate()
//        }

    fun isTriangleFormed(z: Node<Int>) {
        if (z.parent?.data > z) {



    }



    /**
     * Returns true if z, its parent and its grandparent form a triangle
     * @param z the node causing the graph to not be a Red-Black BST
     */
    fun isTriangleFormed(z: Node<Int>?) {
        if (z == z.parent.parent.right.right || z == z.parent.parent.left.left){
            return true
        }
        return false
    }

    /**
     * Returns true if a line is formed by z, its parent and its grandparent
     * @param z the node causing the graph to not be a Red-Black BST
     */
    fun isLineFormed(z: Node<Int>?) {
        z?.parent?.parent?.left?.right?.let {
            if (z == z.parent.parent.left.right || z == z.parent.parent.right.left){
                return true
            }
        }
    }
        return false
    }

    /**
     * @return true if the list is empty and false otherwise
     */
    override fun isEmpty(): Boolean {
        return (size == 0)
    }
}

/**
 * Stores the value within the stack and the location of the next value
 * @param T the type of data to store
 * @property left the left node in our Triply Linked List
 * @property right the right node in our Triply Linked List
 * @property parent the parent node in our Binary Search Tree
 * @property data the string data associated the node
 * @property color the boolean representation of node color (red: true, black: false)
 */
class Node<Int>(
    var left: Node<Int>?, var right: Node<Int>?,
    var parent: Node<Int>?, val data: Int?, var color: Boolean) {

    /**
     * Returns the uncle of the node
     */
    fun uncle(): Node<Int>? {
        parent?.parent?.let { grandparent ->
            if (grandparent.left == parent) {
            return grandparent.right
        }
            else return grandparent.left
        } ?: return null
    }

    fun leftRotate() {
        parent = right
        right = right?.left
        parent?.let{it.left = this}
        right?.let{it.parent = this}
    }

    fun rightRotate() {
        parent = left
        left = left?.right
        parent?.let { it.right = this }
        left?.let { it.parent = this }
    }

    /**
     * Recolors the parent, grandparent and uncle of the
     * node
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

enum class Color {RED, BLACK}