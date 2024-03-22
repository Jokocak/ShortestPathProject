# Weighted Graph with Dijkstra's Algorithm

This project implements a weighted graph data structure using an Adjacency List, represented as an array of LinkedLists that contain GraphNodes. Additionally, it implements Dijkstra's Algorithm to find the shortest path between each node in the graph.

## Features

- **Weighted Graph**: The project provides a robust implementation of a weighted graph, allowing users to add vertices and edges with associated weights.
- **Adjacency List Representation**: The graph is represented internally using an Adjacency List, which efficiently stores connections between vertices.
- **Dijkstra's Algorithm**: Utilizing Dijkstra's Algorithm, the project can efficiently find the shortest path between any two vertices in the graph.
- **Testing**: The graph can be tested using the `ShortestPathManager` class, which provides methods to interact with the graph and test its functionalities.
- **CSV Import**: Users can create 'Landmark' (Vertex) and 'Trail' (Edge) CSV files to construct a graph internally with `TrailManager`. The `TrailManager` can compute distances from any landmark to all other landmarks, finding the shortest distance available. Example Landmark and Trail files are provided to show how to format.
  
## How to Use

1. **Installation**: Clone the repository to your local machine.

   ```bash
   git clone https://github.com/Jokocak/ShortestPathProject.git
   ```

2. **Usage**:
   - Include the necessary files in your project.
   - Create a new instance of the weighted graph.
   - Add vertices and edges with their respective weights.
   - Utilize the provided method to find the shortest path between nodes using Dijkstra's Algorithm.

## Example

```java
WeightedGraph graph = new WeightedGraph();
graph.addVertex("A");
graph.addVertex("B");
graph.addEdge("A", "B", 5); // Edge from A to B with weight 5
// Add more vertices and edges...

List<String> shortestPath = graph.dijkstraShortestPath("A", "B");
System.out.println("Shortest Path from A to B: " + shortestPath); // Output: [A, B]
```

## License

This project is licensed under the [MIT License](LICENSE).
