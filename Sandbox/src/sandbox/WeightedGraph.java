package sandbox;

import java.util.LinkedList;
import java.util.Queue;
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
	private ConcurrentSkipListMap<E, Integer> indexMap;
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
	 * This method gets the weight between vertices.
	 * 
	 * RUNS AT O(n) WORST-CASE TIME WHERE n IS THE SIZE OF THE SELECTED LINKED LIST
	 * 
	 * @param u The first vertex
	 * @param v The second vertex
	 * @return The weight between the vertices
	 */
	private int getEdgeWeight(E u, E v) {
		// Initializes variables for search in linked list
		int uIndex = getIndexOfElement(u);
		int edgeSize = adjList[uIndex].size();

		// Searches through linked list for the second vertex
		GraphNode<E> node;
		for (int i = 0; i < edgeSize; i++) {
			node = adjList[uIndex].get(i);
			if (node.getData().equals(v)) {
				return node.getWeight();
			}
		}

		// Return 0 if weight not found
		return 0;
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
	 * This helper method to biDirectionalSearch() implements a Breadth First Search
	 * algorithm. This method enqueues all the neighbors of the current node.
	 * 
	 * RUNS AT O((log(n))^m) WORST-CASE TIME WHERE m IS THE SIZE OF THE LINKED LIST
	 * AND n IS THE SIZE OF THE ADJACENCY LIST
	 * 
	 * @param queue   The queue to enqueue the neighboring nodes to
	 * @param visited The array to mark where has been visited
	 * @param parent  The array to track the parents of each current node
	 */
	private void breadthFirstSearch(Queue<E> queue, boolean[] visited, int[] parent) {
		int index = getIndexOfElement(queue.poll());
		int currentIndex;
		for (GraphNode<E> current : adjList[index]) {
			currentIndex = getIndexOfElement(current.getData());
			if (!visited[currentIndex]) {
				// Set current as parent of this vertex
				parent[currentIndex] = index;

				// Mark this vertex as visited
				visited[currentIndex] = true;

				// Add to queue
				queue.add(current.getData());
			}
		}
	}

	/**
	 * This helper method to biDirectionalSearch() checks if either of the paths
	 * from the front or back search have intersected. If there is an intersection,
	 * there exists a path from the start to the end node, and return the index of
	 * the intersection. This method returns -1 otherwise.
	 * 
	 * RUNS AT O(n) WORST-CASE TIME WHERE n IS THE SIZE OF THE ADJACENCY LIST
	 * 
	 * @param frontVisited The boolean array of visited indexes in the front search
	 * @param backVisited  The boolean array of visited indexes in the back search
	 * @return The index of the intersecting node or -1 if there is no intersecting
	 *         node
	 */
	private int isIntersecting(boolean[] frontVisited, boolean[] backVisited) {
		for (int i = 0; i < vertices; i++) {
			if (frontVisited[i] && backVisited[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method returns the distance to travel between both the points.
	 * 
	 * RUNS AT O(n) WORST-CASE TIME WHERE n IS THE SIZE OF THE PARENT ARRAYS
	 * 
	 * @param frontParent  This array tracks the parents of the front search
	 * @param backParent   This array tracks the parents of the back search
	 * @param front        The index of the front of this search
	 * @param back         The index of the back of this search
	 * @param intersection The index of the intersection in both searches
	 * @return The total distance to traverse between the back and front search to
	 *         arrive from the start to the end node
	 */
	private int getDistance(int[] frontParent, int[] backParent, int front, int back, int intersection) {
		// Initialize variable to hold distance traveled
		int distance = 0;

		// Traverse from intersection to front
		for (int i = intersection; i != front; i = frontParent[i]) {
			distance += getEdgeWeight(adjList[i].get(0).getData(), adjList[frontParent[i]].get(0).getData());
		}

		// Traverse from intersection to back
		for (int i = intersection; i != back; i = backParent[i]) {
			distance += getEdgeWeight(adjList[i].get(0).getData(), adjList[backParent[i]].get(0).getData());
		}

		// Return the distance of the path
		return distance;
	}

	/**
	 * This method performs a Bidirectional Breadth First Search. This method
	 * utilizes queues, boolean arrays, and integer arrays to track the nodes to
	 * visit, the visited nodes, and the parents of the previously visited nodes.
	 * This method will return the minimum distance required to reach a node.
	 * 
	 * RUNS AT O(b^(d/2)) WORST-CASE TIME WHERE b IS THE BRANCHING FACTOR AND d IS
	 * THE DISTANCE OF THE GOAL VERTEX FROM THE SOURCE
	 * 
	 * @param front The start of the forward search
	 * @param back  The start of the backward search
	 * @return The distance between both nodes
	 * @throws IllegalArgumentException The getIndexOfElement() method can throw an
	 *                                  exception if the element does not exist in
	 *                                  the graph
	 */
	public int biDirectionalSearch(E front, E back) throws IllegalArgumentException {
		// Special Case - Same Node
		if (front.equals(back)) {
			return 0;
		}

		// Initialize indexes of front and back elements
		int frontIndex = getIndexOfElement(front);
		int backIndex = getIndexOfElement(back);

		// Initialize boolean arrays for front and back search
		boolean[] frontVisited = new boolean[vertices];
		boolean[] backVisited = new boolean[vertices];

		// Keep track of parents of front and back search
		int[] frontParent = new int[vertices];
		int[] backParent = new int[vertices];

		// Queue for front and back search
		Queue<E> frontQueue = new LinkedList<>();
		Queue<E> backQueue = new LinkedList<>();

		// Initialize variable to hold intersecting node index
		int intersection = -1;

		// Initialize visited arrays
		for (int i = 0; i < vertices; i++) {
			frontVisited[i] = false;
			backVisited[i] = false;
		}

		// Set front search data structures
		frontQueue.add(adjList[frontIndex].get(0).getData());
		frontVisited[frontIndex] = true;
		frontParent[frontIndex] = -1;

		// Set back search data structures
		backQueue.add(adjList[backIndex].get(0).getData());
		backVisited[backIndex] = true;
		backParent[backIndex] = -1;

		// Perform Bidirectional Search with BFS
		while (!frontQueue.isEmpty() && !backQueue.isEmpty()) {
			// Perform Breadth First Search from front and back
			breadthFirstSearch(frontQueue, frontVisited, frontParent);
			breadthFirstSearch(backQueue, backVisited, backParent);

			// Check for an intersecting node
			intersection = isIntersecting(frontVisited, backVisited);

			// If intersecting vertex is found, there exists a path
			if (intersection != -1) {
				return getDistance(frontParent, backParent, frontIndex, backIndex, intersection);
			}
		}

		// No path exists
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
	private class GraphNode<E> {
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

	}
}
