import org.example.DijkstraShortestPath
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DijkstraShortestPathTest {

    @Test
    fun shortestPath() {
        // Testing for the general case where there are multiple paths but only
        // one optimal path
        val graph = MyGraph<String>()
        for (v in setOf("A", "B", "C", "D", "E")) {
            graph.addVertex(v)
        }
        graph.addEdge("A", "B", 2.0)
        graph.addEdge("A", "E", 10.0)
        graph.addEdge("A", "C", 4.0)
        graph.addEdge("B", "C", 3.0)
        graph.addEdge("B", "D", 0.5)
        graph.addEdge("C", "D", 3.0)
        graph.addEdge("D", "E", 7.0)

        val dijkstra = DijkstraShortestPath(graph)

        val startVertex = "A"
        val destinationVertex = "E"

        val shortestPath = dijkstra.shortestPath(startVertex, destinationVertex)
        assert(shortestPath?.equals(listOf("A", "B", "D", "E")) ?: false)


        // Testing that if there is no connection between the vertices, the path is null
        val edgeless = MyGraph<String>()
        edgeless.addVertex("A")
        edgeless.addVertex("B")
        val invalidPath = DijkstraShortestPath(edgeless).shortestPath("A", "B")
        assert(invalidPath == null)
    }
}