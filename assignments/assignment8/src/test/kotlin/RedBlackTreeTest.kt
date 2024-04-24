import org.example.RedBlackTree
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RedBlackTreeTest {

    @Test
    fun insert() {
        // Checks left rotation
        val tree = RedBlackTree<Int>()
        tree.insert(4)
        assert(tree.checkInvariants())
        var searchResult = tree.search(4)
        // Here I check that 4 is actually in the tree
        assert(searchResult.data == 4)
        assert(searchResult.left!!.data == null)
        assert(searchResult.right!!.data == null)
        assert(searchResult.parent!!.data == null)
        assert(!searchResult.color)

        tree.insert(3)
        assert(tree.checkInvariants())
        searchResult = tree.search(4)
        assert(searchResult.data == 4)
        assert(searchResult.left!!.data == 3) // 4 acknowledges 3 as its child
        assert(searchResult.right!!.data == null)
        assert(searchResult.parent!!.data == null)
        assert(!searchResult.color) // root (4) is black

        assert(searchResult.left!!.left!!.data == null)
        assert(searchResult.left!!.right!!.data == null)
        // 3 acknowledges 4 as its parent
        assert(searchResult.left!!.parent!! == searchResult)
        assert(searchResult.left!!.color) // checks that 3 is red

        tree.insert(2) // here the tree should left rotate
        assert(tree.checkInvariants())
        var newRoot = tree.search(3)
        assert(!newRoot.color) //checks that 3, the new root, is black
        assert(newRoot.data == 3) // checks value is 3
        assert(newRoot.parent!!.data == null)
        assert(newRoot.left!!.data == 2) // checks that left child is 2
        assert(newRoot.right!!.data == 4) // checks that right child is 4

        assert(newRoot.left!!.color) // checks that left child is red
        assert(newRoot.left!!.data == 2) // checks that left child is 2
        assert(newRoot.left!!.parent!!.data == 3) // checks 2 knows 3 is its parent
        assert(newRoot.left!!.left!!.data == null) // children are null
        assert(newRoot.left!!.right!!.data == null)

        assert(newRoot.right!!.color) // checks that right child is red
        assert(newRoot.right!!.data == 4) // checks that right child is 4
        assert(newRoot.right!!.parent!!.data == 3) // checks 2 knows 3 is its parent
        assert(newRoot.right!!.left!!.data == null) // children are null
        assert(newRoot.right!!.right!!.data == null)


        // Checks right rotate
        val tree2 = RedBlackTree<Int>()
        tree2.insert(2)
        assert(tree.checkInvariants())
        searchResult = tree2.search(2)
        assert(searchResult.data == 2)
        assert(searchResult.left!!.data == null)
        assert(searchResult.right!!.data == null)
        assert(searchResult.parent!!.data == null)
        assert(!searchResult.color)

        tree2.insert(3)
        assert(tree.checkInvariants())
        searchResult = tree2.search(2)
        assert(searchResult.data == 2)
        assert(searchResult.left!!.data == null)
        assert(searchResult.right!!.data == 3) // 3 is 2's right child
        assert(searchResult.parent!!.data == null)
        assert(!searchResult.color)

        assert(searchResult.right!!.left!!.data == null)
        assert(searchResult.right!!.right!!.data == null)
        // checks that 2 is the parent
        assert(searchResult.right!!.parent!! == searchResult)
        assert(searchResult.right!!.color) // checks that 3 is red

        tree2.insert(4) // here the tree should right rotate
        assert(tree.checkInvariants())
        newRoot = tree2.search(3)
        assert(!newRoot.color) //checks the new root 3 is black
        assert(newRoot.data == 3) // checks value is 3
        assert(newRoot.parent!!.data == null)
        assert(newRoot.left!!.data == 2)
        assert(newRoot.right!!.data == 4)

        assert(newRoot.left!!.color) // checks that left child is red
        assert(newRoot.left!!.data == 2) // checks that left child is 2
        assert(newRoot.left!!.parent!!.data == 3) // checks 2 knows 3 is its parent
        assert(newRoot.left!!.left!!.data == null) // children are null
        assert(newRoot.left!!.right!!.data == null)

        assert(newRoot.right!!.color) // checks that right child is red
        assert(newRoot.right!!.data == 4) // checks that right child is 4
        assert(newRoot.right!!.parent!!.data == 3) // checks 2 knows 3 is its parent
        assert(newRoot.right!!.left!!.data == null) // children are null
        assert(newRoot.right!!.right!!.data == null)

    }

    @Test
    fun getHeight() {
        val tree = RedBlackTree<Int>()
        assert(tree.getHeight() == 0) // empty tree has height 0
        tree.insert(4)
        assert(tree.getHeight() == 1) // adding one item should have height 1
        tree.insert(3)
        assert(tree.getHeight() == 2) // adding another should have height 2
        tree.insert(2)
        assert(tree.getHeight() == 2) // after rotation, 3 values has height 2
    }

    @Test
    fun checkInvariants() {
        var tree = RedBlackTree<Int>()
        // checking black root invariant
        assert(tree.checkInvariants())
        tree.insert(4)
        assert(tree.checkInvariants())
        tree.search(4).color = true // setting root to red
        assert(!tree.checkInvariants())

        // equal numbers of black on each side
        tree = RedBlackTree()
        tree.insert(4)
        tree.insert(3)
        tree.insert(2)
        assert(tree.checkInvariants())
        tree.search(2).color = false // setting red node to black
        assert(!tree.checkInvariants())

        // red children invariant
        tree = RedBlackTree()
        tree.insert(4)
        tree.insert(3)
        tree.insert(2)
        tree.insert(1)
        assert(tree.checkInvariants())
        tree.search(2).color = true // setting 2 to red
        tree.search(4).color = true // setting 4 to red
        assert(!tree.checkInvariants()) // now we have red with red child

        // red leaf node invariant
        tree = RedBlackTree()
        tree.insert(4)
        tree.insert(3)
        tree.insert(2)
        assert(tree.checkInvariants())
        // setting all leaf nodes red
        tree.search(2).left!!.color = true
        tree.search(2).right!!.color = true
        tree.search(4).left!!.color = true
        tree.search(4).right!!.color = true
        assert(!tree.checkInvariants())
    }

    @Test
    fun search() {
        val tree = RedBlackTree<Int>()
        // if the node isn't in the tree, return the nilnode
        assert(tree.search(2).data == null)
        tree.insert(2)
        // if it is, return the node
        assert(tree.search(2).data == 2)
    }

    @Test
    fun isEmpty() {
        val tree = RedBlackTree<Int>()
        // checking empty tree
        assert(tree.isEmpty())
        tree.insert(1)
        // checking tree with something added is not empty
        assert(!tree.isEmpty())

    }
}