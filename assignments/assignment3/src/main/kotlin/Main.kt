package org.example

import MyGraph

fun main() {
    // Initializing my road trip graph
    val roadTrip = MyGraph<String>()
    // Adding all the cities
    roadTrip.addVertex("Irvine")
    roadTrip.addVertex("Las Vegas")
    roadTrip.addVertex("Zion")
    roadTrip.addVertex("Denver")
    roadTrip.addVertex("Albuquerque")
    roadTrip.addVertex("St. Louis")
    roadTrip.addVertex("Cleveland")
    roadTrip.addVertex("Chicago")
    roadTrip.addVertex("Pittsburgh")
    roadTrip.addVertex("Philadelphia")
    roadTrip.addVertex("NYC")
    roadTrip.addVertex("Providence")
    roadTrip.addVertex("Albany")
    roadTrip.addVertex("Buffalo")
    roadTrip.addVertex("Needham")

    // Adding the distances between cities
    roadTrip.addEdge("Irvine", "Las Vegas", 234.0)
    roadTrip.addEdge("Las Vegas", "Zion", 137.0)
    roadTrip.addEdge("Las Vegas", "Albuquerque", 512.0)
    roadTrip.addEdge("Zion", "Denver", 566.0)
    roadTrip.addEdge("Denver", "Chicago", 874.0)
    roadTrip.addEdge("Denver", "St. Louis", 753.0)
    roadTrip.addEdge("Chicago", "Cleveland",320.0)
    roadTrip.addEdge("Albuquerque", "St. Louis", 900.0)
    roadTrip.addEdge("St. Louis", "Pittsburgh", 554.0)
    roadTrip.addEdge("St. Louis", "Cleveland", 499.0)
    roadTrip.addEdge("Cleveland", "Pittsburgh",139.0)
    roadTrip.addEdge("Cleveland", "Buffalo",176.0)
    roadTrip.addEdge("Pittsburgh", "Philadelphia", 286.0)
    roadTrip.addEdge("Pittsburgh", "Buffalo", 195.0)
    roadTrip.addEdge("Buffalo", "Albany",263.0)
    roadTrip.addEdge("Philadelphia", "NYC",94.0)
    roadTrip.addEdge("NYC", "Providence",180.0)
    roadTrip.addEdge("Providence", "Needham",47.0)
    roadTrip.addEdge("Albany", "Needham",156.0)

    val dijkstra = DijkstraShortestPath(roadTrip)

    val startCity = "Irvine"
    val destinationCity = "Needham"

    val shortestRoute = dijkstra.shortestPath(startCity, destinationCity)
    println(shortestRoute)
}
