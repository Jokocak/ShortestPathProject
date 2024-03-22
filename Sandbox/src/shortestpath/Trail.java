package shortestpath;

public class Trail {
	/** The id of landmark one */
	private String landmarkIdOne;
	/** The id of landmark two */
	private String landmarkIdTwo;
	/** The distance between the two landmarks */
	private int distance;
	
	/**
	 * The constructor method for this class, initializes fields. 
	 *
	 * @param landmarkIdOne The id of landmarkOne
	 * @param landmarkIdTwo The id of landmarkTwo
	 * @param distance 
	 */
	public Trail(String landmarkIdOne, String landmarkIdTwo, int distance) {
		this.landmarkIdOne = landmarkIdOne;
		this.landmarkIdTwo = landmarkIdTwo;
		this.distance = distance;
	}

	/**
	 * Gets the id of landmark one.
	 * 
	 * @return The id of landmark one
	 */
	public String getLandmarkIdOne() {
		return landmarkIdOne;
	}

	/**
	 * Gets the id of landmark two.
	 * 
	 * @return The id of landmark two
	 */
	public String getLandmarkIdTwo() {
		return landmarkIdTwo;
	}
	
	/**
	 * Gets the distance between these two landmarks.
	 * 
	 * @return The distance between these two landmarks
	 */
	public int getDistance() {
		return distance;
	}

}
