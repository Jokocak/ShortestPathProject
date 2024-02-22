package sandbox;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeightedGraphTest {
	/** The graph to test */
	WeightedGraph<Landmark> map;
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
	/** Landmark 13 */
	private Landmark landThirteen;

	/**
	 * Initializes graph for testing
	 */
	@BeforeEach
	void setUp() {
		// Initialize Landmarks
		landOne = new Landmark("L01", "Green Hill Zone", "Zone");
		landTwo = new Landmark("L02", "Chemical Plant Zone", "Zone");
		landThree = new Landmark("L03", "Sky Sanctuary Zone", "Zone");
		landFour = new Landmark("L04", "Speed Highway", "Level");
		landFive = new Landmark("L05", "City Escape", "Level");
		landSix = new Landmark("L06", "Seaside Hill", "Level");
		landSeven = new Landmark("L07", "Crisis City", "Level");
		landEight = new Landmark("L08", "Rooftop Run", "Level");
		landNine = new Landmark("L09", "Planet Wisp", "Planet");
		landTen = new Landmark("L10", "Savannah Citadel", "Level");
		landEleven = new Landmark("L11", "Eggmanland", "Level");
		landTwelve = new Landmark("L12", "Dragon Road", "Level");
		landThirteen = new Landmark("L13", "Green Hill Paradise", "Zone");

		// Initializes Graph of Landmarks
		map = new WeightedGraph<>(13);

		// Add Vertices
		map.addVertex(landOne);
		map.addVertex(landTwo);
		map.addVertex(landThree);
		map.addVertex(landFour);
		map.addVertex(landFive);
		map.addVertex(landSix);
		map.addVertex(landSeven);
		map.addVertex(landEight);
		map.addVertex(landNine);
		map.addVertex(landTen);
		map.addVertex(landEleven);
		map.addVertex(landTwelve);
		map.addVertex(landThirteen);

		// Add Edges
		map.addEdge(landOne, landTwelve, 25);
		map.addEdge(landOne, landEight, 50);
		map.addEdge(landOne, landThirteen, 25);
		map.addEdge(landTwo, landFour, 75);
		map.addEdge(landTwo, landTen, 100);
		map.addEdge(landThree, landFive, 125);
		map.addEdge(landThree, landNine, 150);
		map.addEdge(landFour, landSix, 175);
		map.addEdge(landSeven, landEight, 6000);
		map.addEdge(landNine, landTen, 225);
		map.addEdge(landTen, landTwelve, 250);
	}

	@Test
	void testBiDirectionalSearch() {
		// Path to distant Landmark
		int distance = map.biDirectionalSearch(landOne, landTwo);
		assertEquals(375, distance);

		// Path to neighboring Landmark
		distance = map.biDirectionalSearch(landOne, landTwelve);
		assertEquals(25, distance);

		// Path to self
		distance = map.biDirectionalSearch(landOne, landOne);
		assertEquals(0, distance);

		// Path to where there is no connection
		distance = map.biDirectionalSearch(landEleven, landOne);
		assertEquals(0, distance);
	}

}
