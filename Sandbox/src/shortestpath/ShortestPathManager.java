package shortestpath;

/**
 * This class allows the user to create a graph and run a search on the graph
 * from one vertex to all the others.
 * 
 * @author James Kocak
 */
public class ShortestPathManager {
	/**
	 * This main method asks for input in the console about which search algorithm
	 * to use for searching the graph
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize with number of vertices you wish to have
		WeightedGraph<String> graph = new WeightedGraph<>(5);
		
		// Add vertices
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		
		// Edge from A to B with weight 5
		graph.addEdge("A", "B", 5);
		
		// Add more vertices and edges...

		// Returns the distance of the shortest path
		int shortestPath = graph.dijkstrasAlgorithm("A", "B");
		System.out.println("Shortest Path from A to B: " + shortestPath);
	}
}
