package sandbox;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * First Implementation with Array of LinkedLists
 * Will try implementation with ConcurrentSkipListMap Next
 * 
 * @param <E>
 */
public class Graph<E> {
	/** The number of vertices that this graph can hold */
	private int vertices;
	/** The number of filled vertices */
	private int size;
	/** An adjacency list for this graph */
	private LinkedList<E>[] adjList;
	
	@SuppressWarnings("unchecked")
	public Graph(int vertices) {
		// Initializes number of vertices
		this.vertices = vertices;
		size = 0;
		
		// Initializes nodes
		adjList = new LinkedList[vertices];
		for (int i = 0; i < vertices; i++)
			adjList[i] = new LinkedList<>();
	}
	
	// Adds Vertex
	public void addVertex(E u) {
		adjList[size++].add(u);
	}
	
	// Adds undirected edge
	public void addEdge(E u, E v) {
		adjList[getIndexOfElement(u)].add(v);
		adjList[getIndexOfElement(v)].add(u);
	}
	
	private int getIndexOfElement(E u) {
		// Returns index if found
		for (int i = 0; i < vertices; i++) {
			if (adjList[i].get(0).equals(u)) {
				return i;
			}
		}
		
		// Throw exception if element does not exist
		throw new IllegalArgumentException("Element does not exist in graph");
	}
	
	// Breadth First Search
	private void breadthFirstSearch(Queue<E> queue, boolean[] visited, int[] parent) {
		int index = getIndexOfElement(queue.poll());
		int currentIndex;
		for (E current : adjList[index]) {
			currentIndex = getIndexOfElement(current);
			if (!visited[currentIndex]) {
				// Set current as parent of this vertex
				parent[currentIndex] = index;
				
				// Mark this vertex as visited
				visited[currentIndex] = true;
				
				// Add to queue
				queue.add(current);
			}
		}
	}
	
	private int isIntersecting(boolean[] frontVisited, boolean[] backVisited) {
		for (int i = 0; i < vertices; i++) {
			if (frontVisited[i] && backVisited[i]) {
				return i;
			}
		}
		return -1;
	}
	
	private List<E> getPath(int[] frontParent, int[] backParent, int front, int back, int intersection) {
		LinkedList<E> path = new LinkedList<>();
		
		path.add(adjList[intersection].get(0));
		
		int i = intersection;
		
		// Traverse from intersection to front
		while (i != front) {
			path.add(adjList[frontParent[i]].get(0));
			i = frontParent[i];
		}
		
		// Reverse the list and reinitialize i to the intersection
		Collections.reverse(path);
		i = intersection;
		
		// Traverse from intersection to back
		while (i != back) {
			path.add(adjList[backParent[i]].get(0));
			i = backParent[i];
		}
		
		// Return the list with the path
		return path;
	}
	
	// Bidirectional Search Algorithm
	public List<E> biDirectionalSearch(E front, E back) throws IllegalArgumentException {
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
		
		// Initialize visited arrays, queues, and parents
		for (int i = 0; i < vertices; i++) {
			frontVisited[i] = false;
			backVisited[i] = false;
		}
		
		frontQueue.add(adjList[frontIndex].get(0));
		frontVisited[frontIndex] = true;
		frontParent[frontIndex] = -1;
		
		backQueue.add(adjList[backIndex].get(0));
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
				return getPath(frontParent, backParent, frontIndex, backIndex, intersection);
			}
		}
		
		// No path exists
		return null;
	}
}
