package shortestpath;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * This class contains various methods for dealing with Landmarks and Trails.
 * The most notable method being the getDistancesToDestinations() method where
 * the distance between two Landmarks is found via a Bidirectional Breadth First
 * Search.
 * 
 * @author James Kocak
 */
public class TrailManager {
	/** A list of all the Landmarks */
	private List<Landmark> landmarks;
	/** A list of all the Trails */
	private List<Trail> trails;
	/** A graph of the Landmarks */
	private WeightedGraph<Landmark> map;
	/** A map of IDs to Landmarks */
	Map<String, Landmark> ids;
	/** A map of Landmarks to its Trails */
	Map<Landmark, List<Trail>> landmarkTrails;

	public TrailManager(String pathToLandmarkFile, String pathToTrailFile) throws FileNotFoundException {
		// Read pathToLandmarkFile into landmarks
		landmarks = IOManager.readLandmarks(pathToLandmarkFile);

		// Read pathToTrailFile into trails
		trails = IOManager.readTrails(pathToTrailFile);

		// Record landmarks size
		int landmarkSize = landmarks.size();
		int trailSize = trails.size();

		// Initialize graph, ids map, and landmaekTrails map
		map = new WeightedGraph<>(landmarkSize);
		ids = new ConcurrentSkipListMap<>();
		landmarkTrails = new ConcurrentSkipListMap<>();

		// Add vertices to maps and initialize
		Landmark landmark;
		for (int i = 0; i < landmarkSize; i++) {
			landmark = landmarks.get(i);
			map.addVertex(landmark);
			ids.put(landmark.getLandmarkId(), landmark);
			landmarkTrails.put(landmark, new LinkedList<Trail>());
		}

		// Add edges to maps
		Trail trail;
		for (int i = 0; i < trailSize; i++) {
			trail = trails.get(i);
			landmark = getLandmarkByID(trail.getLandmarkIdOne());
			map.addEdge(landmark, getLandmarkByID(trail.getLandmarkIdTwo()), trail.getDistance());
			landmarkTrails.get(landmark).add(trail);
		}
	}

	public Map<Landmark, Integer> getDistancesToDestinations(String landmarkID) {
		// Initialize Map to return
		Map<Landmark, Integer> distances = new ConcurrentSkipListMap<>();

		// Check if origin exists
		Landmark origin = getLandmarkByID(landmarkID);
		if (origin == null) {
			return distances;
		}

		// Gets distances with Dijkstra's Search Algorithm
		for (Landmark landmark : landmarks) {
			distances.put(landmark, map.dijkstrasAlgorithm(origin, landmark));
		}

		// Return map with distances
		return distances;
	}

	public Landmark getLandmarkByID(String landmarkID) {
		return ids.get(landmarkID);
	}

	public Map<Landmark, List<Trail>> getProposedFirstAidLocations(int numberOfIntersectingTrails) {
		// Initialize map
		Map<Landmark, List<Trail>> proposedFirstAidLocations = new ConcurrentSkipListMap<>();

		// Checks number of trails
		List<Trail> trails;
		for (Landmark landmark : landmarkTrails.keySet()) {
			trails = landmarkTrails.get(landmark);
			if (trails.size() >= numberOfIntersectingTrails) {
				proposedFirstAidLocations.put(landmark, trails);
			}
		}

		// Return map of proposed first aid locations
		return proposedFirstAidLocations;
	}
}
