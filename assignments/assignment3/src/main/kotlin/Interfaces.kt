package org.example

/**
 * This ``Graph`` that represents a directed graph
 * @param VertexType the representation of a vertex in the graph
 */
interface Graph<VertexType> {

    /**
     * @return the vertices in the graph
     */
    fun getVertices(): Set<VertexType>

    /**
     * Add the vertex [v] to the graph
     * @param v The vertex to be added
     * @return A boolean: true if the vertex was successfully added, false if it
     *  was already in the graph
     */
    fun addVertex(v: VertexType): Boolean

    /**
     * Add an edge between vertex [from] connecting to vertex [to]
     * @param from the vertex for the edge to originate from
     * @param to the vertex to connect the edge to
     * @param cost the cost associated with the edge between from and to
     * @return true if the edge is successfully added and false if the edge
     *     can't be added or already exists
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double): Boolean

    /**
     * @param from The vertex which we want to know the edges of
     * @return a map of all the vertices connected to the vertex and their
     * associated costs
     */
    fun getEdges(from: VertexType): Map<VertexType, Double>

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear()
}

/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 */
interface MinPriorityQueue<T> {
    /**
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean

    /**
     * This adds an [elem] at level [priority] into our priority queue
     *
     */
    fun addWithPriority(elem: T, priority: Double)

    /**
     * Get the next (lowest priority) element and return it. Delete it from the
     * priority queue.
     * @return the next element in terms of priority.  If empty, return null.
     */
    fun next(): T?

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double)
}
