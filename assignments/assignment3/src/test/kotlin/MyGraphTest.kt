import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MyGraphTest {

    @Test
    fun getVertices() {
        // checking that it returns the vertices properly
        val graph = MyGraph<String>()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        assert(graph.getVertices() == setOf("A", "B", "C"))

        // checking to see that empty graphs are empty
        val empty = MyGraph<String>()
        assert(empty.getVertices().isEmpty())
    }

    @Test
    fun addVertex() {
        // checking that it adds the vertices properly
        val graph = MyGraph<Int>()
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        assert(graph.getVertices() == setOf(1, 2, 3))
    }

    @Test
    fun addEdge() {
        // checking that it returns the vertices properly
        // this also effectively checks getEdges in the general case
        val graph = MyGraph<String>()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addEdge("A", "B", 10.0)
        assert(graph.getEdges("A") == mapOf("B" to 10.0))
        // checking that only the edges added are the edges that exist
        // checking that getEdges does not hallucinate edges into existence
        assert(graph.getEdges("B").isEmpty())
        assert(graph.getEdges("C").isEmpty())
    }

    @Test
    fun clear() {
        val graph = MyGraph<String>()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addEdge("A", "B", 18.0)
        graph.addEdge("B", "C", 72.9)
        assert(graph.getEdges("A") == mapOf("B" to 18.0))
        graph.clear()
        // Vertices are cleared (addEdge should return false)
        assert(!graph.addEdge("A", "B", 2.0))
        assert(!graph.addEdge("B", "C", 2.0))
        // Edge is cleared
        assert(graph.getEdges("B").isEmpty())

    }
}