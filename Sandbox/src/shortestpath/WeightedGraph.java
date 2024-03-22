package shortestpath;

import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * This class represents a graph with weighted edges.
 * 
 * @param <E> Generic Class Parameter
 * @author James Kocak
 */
public class WeightedGraph<E> {
	/** The number of vertices that this graph can hold */
	private int vertices;
	/** The number of filled vertices */
	private int size;
	/** Map to hold index of each node */
	private Map<E, Integer> indexMap;
	/** An adjacency list for this graph */
	private LinkedList<GraphNode<E>>[] adjList;

	/**
	 * This constructor method initializes all of the nodes of this graph.
	 * 
	 * @param vertices The number of vertices in this graph
	 */
	@SuppressWarnings("unchecked")
	public WeightedGraph(int vertices) {
		// Initializes number of vertices
		this.vertices = vertices;
		size = 0;

		// Initializes nodes
		adjList = new LinkedList[vertices];
		for (int i = 0; i < vertices; i++)
			adjList[i] = new LinkedList<>();

		// Initialize Skip List
		indexMap = new ConcurrentSkipListMap<>();
	}

	/**
	 * This method adds a vertex to the graph.
	 * 
	 * RUNS AT O(log(n)) WORST-CASE TIME WHERE n IS THE SIZE OF THE SKIP LIST
	 * 
	 * @param u The element to add at a new node in the graph
	 */
	public void addVertex(E u) {
		adjList[size].add(new GraphNode<E>(u, 0));
		indexMap.put(u, size++);
	}

	/**
	 * This method adds an undirected edge to each of these nodes.
	 * 
	 * RUNS AT O(log(n)) WORST-CASE TIME WHERE n IS THE SIZE OF THE ADJACENCY LIST
	 * 
	 * @param u      The first vertex to add an edge for
	 * @param v      The second vertex to add an edge for
	 * @param weight The weight of this new edge
	 */
	public void addEdge(E u, E v, int weight) {
		adjList[getIndexOfElement(u)].add(new GraphNode<E>(v, weight));
		adjList[getIndexOfElement(v)].add(new GraphNode<E>(u, weight));
	}

	/**
	 * This method gets the index of a given element from the adjList and returns
	 * it. This method will throw an IllegalArgumentException if the element is not
	 * in the adjacency list.
	 * 
	 * RUNS AT O(log(n)) WORST-CASE TIME WHERE n IS THE SIZE OF THE ADJACENCY LIST
	 * 
	 * @param u The element to search for
	 * @return The index of the element in the adjacency list
	 * @throws IllegalArgumentException Throws exception when element does not exist
	 *                                  in the adjacency list
	 */
	private int getIndexOfElement(E u) {
		// Try to get index from map and catch exception if it does not exist
		try {
			int index = indexMap.get(u);
			return index;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This method implements Dijkstra's Algorithm, the search cuts off when the
	 * shortest path to a target vertex is found.
	 * 
	 * RUNS AT O((|E|+|V|)log|V|) WORST-CASE TIME WHERE E IS THE NUMBER OF EDGES AND
	 * V IS THE NUMBER OF VERTICES
	 * 
	 * @param originVertex The vertex to start searching from
	 * @param targetVertex The vertex to find the shortest path to
	 * @return The distance of the shortest path to the vertex
	 */
	public int dijkstrasAlgorithm(E originVertex, E targetVertex) {
		// Initialize skip list and set all distances to not found (Infinity)
		Map<E, Integer> distances = new ConcurrentSkipListMap<>();
		for (int i = 0; i < vertices; i++) {
			distances.put(adjList[i].get(0).getData(), Integer.MAX_VALUE);
		}

		// Set origin Vertex to 0
		distances.put(originVertex, 0);

		// Add origin vertex
		PriorityQueue<E> minHeap = new PriorityQueue<>();
		minHeap.add(originVertex);

		// Search for shortest path until target vertex is found
		E currentVertex;
		int vertexIndex;
		int altDist;
		while (!minHeap.isEmpty()) {
			// Remove vertex with shortest distance from queue
			currentVertex = minHeap.poll();

			// Break search if at target vertex
			if (currentVertex.equals(targetVertex)) {
				return distances.get(currentVertex);
			}

			vertexIndex = getIndexOfElement(currentVertex);
			for (GraphNode<E> neighbor : adjList[vertexIndex]) {
				// Skip neighbor if self
				if (neighbor.getData().equals(currentVertex)) {
					continue;
				}

				// Calculate alternative distance
				altDist = distances.get(currentVertex) + neighbor.getWeight();

				// Replace distance recorded if new distance is smaller
				if (altDist < distances.get(neighbor.getData())) {
					distances.put(neighbor.getData(), altDist);
					minHeap.add(neighbor.getData());
				}
			}
		}

		// Target is unreachable
		return 0;
	}

	/**
	 * This private inner class represents a node in the weighted graph, containing
	 * the data at this node and the weight.
	 * 
	 * @param <E> Generic Class Parameter
	 * @author James Kocak
	 */
	@SuppressWarnings("hiding")
	private class GraphNode<E> implements Comparable<GraphNode<E>> {
		/** The data this node holds */
		private E data;
		/** The weight of this node, 0 if start of this list */
		private int weight;

		/**
		 * The constructor method for a GraphNode, this method initializes the data and
		 * the weight at this node.
		 * 
		 * @param data   The data to initialize
		 * @param weight The weight to initialize
		 */
		public GraphNode(E data, int weight) {
			this.data = data;
			this.weight = weight;
		}

		/**
		 * This method returns the data at this node.
		 * 
		 * @return The data
		 */
		public E getData() {
			return data;
		}

		/**
		 * This method returns the weight of this node.
		 * 
		 * @return The weight
		 */
		public int getWeight() {
			return weight;
		}

		@Override
		public int compareTo(GraphNode<E> o) {
			return Integer.compare(this.weight, o.weight);
		}

	}
}
