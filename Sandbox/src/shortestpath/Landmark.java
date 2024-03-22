package shortestpath;

/**
 * This class represents a Landmark to travel to, storing data on its id,
 * description, and type.
 * 
 * @author James Kocak
 */
public class Landmark implements Comparable<Landmark> {
	/** The id of this landmark */
	private String landmarkId;
	/** The description of this landmark */
	private String description;
	/** The type of this landmark */
	private String type;

	/**
	 * The constructor method, initializes all the fields.
	 * 
	 * @param landmarkId The id of this landmark
	 * @param description The description of this landmark
	 * @param type The type of this landmark
	 */
	public Landmark(String landmarkId, String description, String type) {
		this.landmarkId = landmarkId;
		this.description = description;
		this.type = type;
	}

	/**
	 * Gets the id of this landmark.
	 * 
	 * @return The id of this landmark
	 */
	public String getLandmarkId() {
		return landmarkId;
	}

	/**
	 * Gets the description of this landmark.
	 * 
	 * @return The description of this landmark
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the type of this landmark
	 * 
	 * @return The type of this landmark
	 */
	public String getType() {
		return type;
	}

	@Override
	public int compareTo(Landmark o) {
		return landmarkId.compareTo(o.landmarkId);
	}

}
