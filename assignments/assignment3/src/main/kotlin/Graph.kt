import org.example.Graph

class MyGraph<VertexType> : Graph<VertexType> {

    private val vertices: MutableSet<VertexType> = mutableSetOf()
    private val edges: MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()

    /**
     * Returns all the vertices in the graph in a set
     */
    override fun getVertices(): Set<VertexType> {
        return vertices
    }

    /**
     * Add the vertex [v] to the graph
     * @param v the vertex to add
     * @return true if the vertex is successfully added, false if the vertex
     *   was already in the graph
     */
    override fun addVertex(v: VertexType): Boolean {
        if (vertices.contains(v)) {
            return false
        }
        vertices.add(v)
        return true
    }

    /**
     * Add an edge between vertex [from] connecting to vertex [to]
     * @param from the vertex for the edge to originate from
     * @param to the vertex to connect the edge to
     * @param cost the cost associated with the edge between from and to
     * @return true if the edge is successfully added and false if the edge
     *     can't be added or already exists
     */
    override fun addEdge(from: VertexType, to: VertexType, cost: Double): Boolean {
        if (!vertices.contains(from) || !vertices.contains(to)) {
            return false
        }
        edges[from]?.also { currentAdjacent ->
            if (currentAdjacent.containsKey(to) && currentAdjacent[to] == cost) {
                return false
            }
            currentAdjacent[to] = cost
        } ?: run {
            edges[from] = mutableMapOf(to to cost)
        }
        return true
    }

    /**
     * Returns the edges and their associated costs from [from] in a map
     * @param from is the vertex we want to know the edges of
     * @return an edge map where the keys are the vertexes [from] is connected
     *     to and the values are the costs associated with those edges
     */
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        return edges[from] ?: emptyMap()
    }

    /**
     * Clears all the vertices and edges in the directed, weighted graph
     */
    override fun clear() {
        vertices.clear()
        edges.clear()
    }
}