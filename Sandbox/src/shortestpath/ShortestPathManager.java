package shortestpath;

import java.io.FileNotFoundException;
import java.util.Map;

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
	 * @param args The command line arguments
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
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

		// Feel free to add more vertices and edges here ...

		// Returns the distance of the shortest path
		int shortestPath = graph.dijkstrasAlgorithm("A", "B");
		System.out.println("Shortest Path from A to B: " + shortestPath + "\n");

		// This portion of the code shows the input files being used to create a graph
		// with landmarks and trails, where landmarks would be vertices in the graph and
		// the trails would be edges.
		Map<Landmark, Integer> distances = distancesFromLandmark();

		// Print out each distance to each destination
		for (Landmark landmark : distances.keySet()) {
			System.out.println(
					"Landmark: " + landmark.getDescription() + "\nDistance: " + distances.get(landmark) + "\n");
		}
	}

	/**
	 * This method gets a map from the TrailManager using the Sonic information
	 * files to calculate the shortest distance from Green Hill Zone to all the
	 * other landmarks.
	 * 
	 * NOTE: Feel free to make your own landmarks and trails CSV file to try out
	 * your own graphs that you want to create!
	 * 
	 * @return A map of the shortest distances from the origin landmark to all the
	 *         other landmarks
	 * @throws FileNotFoundException This exception is thrown if the given file
	 *                               strings are not found
	 */
	private static Map<Landmark, Integer> distancesFromLandmark() throws FileNotFoundException {
		// Instantiates a TrailManager with given Landmarks and Trails
		TrailManager manager = new TrailManager("input/Sonic_Landmarks.csv", "input/Sonic_Trails.csv");

		// This method in TrailManager returns a map of all the destinations as keys
		// with the length of the shortest path as the value, where the passed in
		// Landmark ID is the starting Landmark. The getDistacnesToDestinations() method
		// utilizes dijkstra's algorithm to get the shortest distances!
		Map<Landmark, Integer> distances = manager.getDistancesToDestinations("L01");

		// Return the map of distances
		return distances;
	}
}
