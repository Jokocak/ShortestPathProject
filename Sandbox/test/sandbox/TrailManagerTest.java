package sandbox;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the TrailManager class.
 * 
 * @author James Kocak
 */
class TrailManagerTest {
	/** The manager to use for testing */
	private TrailManager manager;
	/** Landmark 1 */
	private Landmark landOne;
	/** Landmark 2 */
	private Landmark landTwo;
	/** Landmark 3 */
	private Landmark landThree;
	/** Landmark 4 */
	private Landmark landFour;
	/** Landmark 5 */
	private Landmark landFive;
	/** Landmark 6 */
	private Landmark landSix;
	/** Landmark 7 */
	private Landmark landSeven;
	/** Landmark 8 */
	private Landmark landEight;
	/** Landmark 9 */
	private Landmark landNine;
	/** Landmark 10 */
	private Landmark landTen;
	/** Landmark 11 */
	private Landmark landEleven;
	/** Landmark 12 */
	private Landmark landTwelve;

	/**
	 * Initializes all landmarks
	 */
	@BeforeEach
	void setUp() {
		landOne = new Landmark("L01", "Park Entrance", "Location");
		landTwo = new Landmark("L02", "Entrance Fountain", "Fountain");
		landThree = new Landmark("L03", "Waste Station 1", "Pet Waste Station");
		landFour = new Landmark("L04", "Entrance Restrooms", "Restroom");
		landFive = new Landmark("L05", "Overlook 1", "Overlook");
		landSix = new Landmark("L06", "Rock Formation 1", "Rock Formation");
		landSeven = new Landmark("L07", "Overlook 2", "Overlook");
		landEight = new Landmark("L08", "Overlook Restrooms", "Restroom");
		landNine = new Landmark("L09", "Waste Station 2", "Pet Waste Station");
		landTen = new Landmark("L10", "Hidden Gardens", "Gardens");
		landEleven = new Landmark("L11", "Campsite 1", "Campsite");
		landTwelve = new Landmark("L12", "Campsite Restrooms", "Restroom");
	}

	/**
	 * Tests getDistancesToDestinations method, checks that each entry in return map
	 * has correct integer value with small file.
	 * 
	 * @throws FileNotFoundException Throws exception if either file is unable to be
	 *                               opened
	 */
	@Test
	void testGetDistancesToDestinationsSmall() throws FileNotFoundException {
		// Sets up manager for smaller test file
		manager = new TrailManager("input/check_distance_multiple_landmarks.csv",
				"input/check_distance_multiple_trails.csv");

		Map<Landmark, Integer> distances = manager.getDistancesToDestinations("L01");
		assertEquals(4, distances.size());
		assertEquals(0, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(1046, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(5250, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(6289, distances.get(manager.getLandmarkByID("L06")));

		distances = manager.getDistancesToDestinations("L03");
		assertEquals(4, distances.size());
		assertEquals(1046, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(4204, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(5243, distances.get(manager.getLandmarkByID("L06")));

		distances = manager.getDistancesToDestinations("L05");
		assertEquals(4, distances.size());
		assertEquals(5250, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(4204, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(1039, distances.get(manager.getLandmarkByID("L06")));

		distances = manager.getDistancesToDestinations("L06");
		assertEquals(4, distances.size());
		assertEquals(6289, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(5243, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(1039, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L06")));
	}

	/**
	 * Tests getDistancesToDestinations method, checks that each entry in return map
	 * has correct integer value with large file.
	 * 
	 * @throws FileNotFoundException Throws exception if either file is unable to be
	 *                               opened
	 */
	@Test
	void testGetDistancesToDestinationsLarge() throws FileNotFoundException {
		// Sets up manager for larger test file
		manager = new TrailManager("input/landmarks_sample.csv", "input/trails_sample.csv");

		Map<Landmark, Integer> distances = manager.getDistancesToDestinations("L01");
		assertEquals(12, distances.size());
		assertEquals(0, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(3013, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(1046, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(1179, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(5250, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(6289, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(9201, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(11092, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(3490, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(6626, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L02");
		assertEquals(12, distances.size());
		assertEquals(3013, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(4059, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(4192, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(8263, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(9302, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(12214, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(14105, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(6503, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(3613, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L03");
		assertEquals(12, distances.size());
		assertEquals(1046, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(4059, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(2225, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(4204, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(5243, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(8155, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(10046, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(4536, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(7672, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L04");
		assertEquals(12, distances.size());
		assertEquals(1179, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(4192, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(2225, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(6429, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(7468, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(10380, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(12271, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(2311, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(7805, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L05");
		assertEquals(12, distances.size());
		assertEquals(5250, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(8263, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(4204, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(6429, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(1039, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(3951, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(5842, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(8740, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(11876, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L06");
		assertEquals(12, distances.size());
		assertEquals(6289, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(9302, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(5243, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(7468, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(1039, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(2912, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(4803, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(9779, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(12915, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L07");
		assertEquals(12, distances.size());
		assertEquals(9201, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(12214, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(8155, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(10380, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(3951, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(2912, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(1891, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(12691, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(15827, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L08");
		assertEquals(12, distances.size());
		assertEquals(11092, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(14105, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(10046, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(12271, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(5842, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(4803, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(1891, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(14582, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(17718, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L09");
		assertEquals(12, distances.size());
		assertEquals(3490, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(6503, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(4536, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(2311, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(8740, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(9779, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(12691, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(14582, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(10116, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L10");
		assertEquals(12, distances.size());
		assertEquals(6626, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(3613, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(7672, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(7805, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(11876, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(12915, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(15827, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(17718, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(10116, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L11");
		assertEquals(12, distances.size());
		assertEquals(0, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(1066, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("L12");
		assertEquals(12, distances.size());
		assertEquals(0, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(1066, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));

		distances = manager.getDistancesToDestinations("Goku");
		assertEquals(0, distances.size());
	}

	/**
	 * Uses sonic test files to check with a location with no trails
	 * 
	 * @throws FileNotFoundException Throws exception if either file could not be
	 *                               opened
	 */
	@Test
	void testGetDistancesToDestinations() throws FileNotFoundException {
		// Sets up manager for sonic test file
		manager = new TrailManager("input/Sonic_Landmarks.csv", "input/Sonic_Trails.csv");

		// Tests distances with Landmark with no trails
		Map<Landmark, Integer> distances = manager.getDistancesToDestinations("L11");
		assertEquals(13, distances.size());
		assertEquals(0, distances.get(manager.getLandmarkByID("L01")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L02")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L03")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L04")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L05")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L06")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L07")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L08")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L09")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L10")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L11")));
		assertEquals(0, distances.get(manager.getLandmarkByID("L12")));
	}

	/**
	 * This tests if the correct Landmark ID is grabbed from the list of Landmarks.
	 * 
	 * @throws FileNotFoundException Throws Exception when either file cannot be
	 *                               opened
	 */
	@Test
	void testGetLandmarkByID() throws FileNotFoundException {
		// Sets up manager for larger test file
		manager = new TrailManager("input/landmarks_sample.csv", "input/trails_sample.csv");

		assertEquals(landOne.getDescription(), manager.getLandmarkByID("L01").getDescription());
		assertEquals(landTwo.getDescription(), manager.getLandmarkByID("L02").getDescription());
		assertEquals(landThree.getDescription(), manager.getLandmarkByID("L03").getDescription());
		assertEquals(landFour.getDescription(), manager.getLandmarkByID("L04").getDescription());
		assertEquals(landFive.getDescription(), manager.getLandmarkByID("L05").getDescription());
		assertEquals(landSix.getDescription(), manager.getLandmarkByID("L06").getDescription());
		assertEquals(landSeven.getDescription(), manager.getLandmarkByID("L07").getDescription());
		assertEquals(landEight.getDescription(), manager.getLandmarkByID("L08").getDescription());
		assertEquals(landNine.getDescription(), manager.getLandmarkByID("L09").getDescription());
		assertEquals(landTen.getDescription(), manager.getLandmarkByID("L10").getDescription());
		assertEquals(landEleven.getDescription(), manager.getLandmarkByID("L11").getDescription());
		assertEquals(landTwelve.getDescription(), manager.getLandmarkByID("L12").getDescription());
		assertNull(manager.getLandmarkByID("Goku"));
	}

	@Test
	void testGetProposedFirstAidLocations() throws FileNotFoundException {
		// Sets up manager for larger test file
		manager = new TrailManager("input/landmarks_sample.csv", "input/trails_sample.csv");

		Map<Landmark, List<Trail>> firstAidLocations = manager.getProposedFirstAidLocations(3);
		assertEquals(1, firstAidLocations.size());
		assertEquals("L01", firstAidLocations.get(landOne).get(0).getLandmarkIdOne());
		assertEquals("L02", firstAidLocations.get(landOne).get(0).getLandmarkIdTwo());
		assertEquals("L01", firstAidLocations.get(landOne).get(1).getLandmarkIdOne());
		assertEquals("L03", firstAidLocations.get(landOne).get(1).getLandmarkIdTwo());
		assertEquals("L01", firstAidLocations.get(landOne).get(2).getLandmarkIdOne());
		assertEquals("L04", firstAidLocations.get(landOne).get(2).getLandmarkIdTwo());
	}

}
