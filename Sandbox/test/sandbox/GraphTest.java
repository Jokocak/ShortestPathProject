package sandbox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
	/** The graph to test */
	Graph<Landmark> map;
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
		map = new Graph<>(13);
		
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
		map.addEdge(landOne, landTwelve);
		map.addEdge(landOne, landEight);
		map.addEdge(landOne, landThirteen);
		map.addEdge(landTwo, landFour);
		map.addEdge(landTwo, landTen);
		map.addEdge(landThree, landFive);
		map.addEdge(landThree, landNine);
		map.addEdge(landFour, landSix);
		map.addEdge(landSeven, landEight);
		map.addEdge(landNine, landTen);
		map.addEdge(landTen, landTwelve);
	}
	
	@Test
	void testBiDirectionalSearch() {
		// Path to distant Landmark
		List<Landmark> path = map.biDirectionalSearch(landOne, landTwo);
		assertEquals(4, path.size());
		assertEquals(landOne, path.get(0));
		assertEquals(landTwelve, path.get(1));
		assertEquals(landTen, path.get(2));
		assertEquals(landTwo, path.get(3));
		
		// Path to neighboring Landmark
		List<Landmark> path2 = map.biDirectionalSearch(landOne, landTwelve);
		assertEquals(2, path2.size());
		assertEquals(landOne, path.get(0));
		assertEquals(landTwelve, path.get(1));
		
		// Path to self
		List<Landmark> path3 = map.biDirectionalSearch(landOne, landOne);
		assertEquals(1, path3.size());
		assertEquals(landOne, path.get(0));
		
		// Path to where there is no connection
		List<Landmark> path4 = map.biDirectionalSearch(landEleven, landOne);
		assertNull(path4);
	}

}
