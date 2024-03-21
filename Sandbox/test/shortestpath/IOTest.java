package shortestpath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shortestpath.IOManager;
import shortestpath.Landmark;
import shortestpath.Trail;
import shortestpath.TrailManager;

/**
 * Tests Graph class.
 * 
 * @author James Kocak
 */
class IOTest {
	/** List of Landmarks */
	List<Landmark> landmarks;
	/** List of Trails */
	List<Trail> trails;
	/** Instance of a TrailManager */
	TrailManager manager;
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
	/** Trail 1 */
	private Trail trailOne;
	/** Trail 2 */
	private Trail trailTwo;
	/** Trail 3 */
	private Trail trailThree;
	/** Trail 4 */
	private Trail trailFour;
	/** Trail 5 */
	private Trail trailFive;
	/** Trail 6 */
	private Trail trailSix;
	/** Trail 7 */
	private Trail trailSeven;
	/** Trail 8 */
	private Trail trailEight;
	/** Trail 9 */
	private Trail trailNine;
	/** Trail 10 */
	private Trail trailTen;
	/** Trail 11 */
	private Trail trailEleven;

	/**
	 * Initializes all landmarks
	 */
	@BeforeEach
	void setUp() {
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

		trailOne = new Trail("L01", "L12", 25);
		trailTwo = new Trail("L01", "L08", 50);
		trailThree = new Trail("L01", "L13", 25);
		trailFour = new Trail("L02", "L04", 75);
		trailFive = new Trail("L02", "L10", 100);
		trailSix = new Trail("L03", "L05", 125);
		trailSeven = new Trail("L03", "L09", 150);
		trailEight = new Trail("L04", "L06", 175);
		trailNine = new Trail("L07", "L08", 6000);
		trailTen = new Trail("L09", "L10", 225);
		trailEleven = new Trail("L10", "L12", 250);

		landmarks = IOManager.readLandmarks("input/Sonic_Landmarks.csv");
		trails = IOManager.readTrails("input/Sonic_Trails.csv");
	}

	@Test
	void testReadLandmarksAndTrails() {
		assertEquals(13, landmarks.size());
		assertEquals(landOne.getDescription(), landmarks.get(0).getDescription());
		assertEquals(landTwo.getDescription(), landmarks.get(1).getDescription());
		assertEquals(landThree.getDescription(), landmarks.get(2).getDescription());
		assertEquals(landFour.getDescription(), landmarks.get(3).getDescription());
		assertEquals(landFive.getDescription(), landmarks.get(4).getDescription());
		assertEquals(landSix.getDescription(), landmarks.get(5).getDescription());
		assertEquals(landSeven.getDescription(), landmarks.get(6).getDescription());
		assertEquals(landEight.getDescription(), landmarks.get(7).getDescription());
		assertEquals(landNine.getDescription(), landmarks.get(8).getDescription());
		assertEquals(landTen.getDescription(), landmarks.get(9).getDescription());
		assertEquals(landEleven.getDescription(), landmarks.get(10).getDescription());
		assertEquals(landTwelve.getDescription(), landmarks.get(11).getDescription());
		assertEquals(landThirteen.getDescription(), landmarks.get(12).getDescription());

		assertEquals(11, trails.size());
		assertEquals(trailOne.getLandmarkIdOne(), trails.get(0).getLandmarkIdOne());
		assertEquals(trailOne.getLandmarkIdTwo(), trails.get(0).getLandmarkIdTwo());
		assertEquals(trailTwo.getLandmarkIdOne(), trails.get(1).getLandmarkIdOne());
		assertEquals(trailTwo.getLandmarkIdTwo(), trails.get(1).getLandmarkIdTwo());
		assertEquals(trailThree.getLandmarkIdOne(), trails.get(2).getLandmarkIdOne());
		assertEquals(trailThree.getLandmarkIdTwo(), trails.get(2).getLandmarkIdTwo());
		assertEquals(trailFour.getLandmarkIdOne(), trails.get(3).getLandmarkIdOne());
		assertEquals(trailFour.getLandmarkIdTwo(), trails.get(3).getLandmarkIdTwo());
		assertEquals(trailFive.getLandmarkIdOne(), trails.get(4).getLandmarkIdOne());
		assertEquals(trailFive.getLandmarkIdTwo(), trails.get(4).getLandmarkIdTwo());
		assertEquals(trailSix.getLandmarkIdOne(), trails.get(5).getLandmarkIdOne());
		assertEquals(trailSix.getLandmarkIdTwo(), trails.get(5).getLandmarkIdTwo());
		assertEquals(trailSeven.getLandmarkIdOne(), trails.get(6).getLandmarkIdOne());
		assertEquals(trailSeven.getLandmarkIdTwo(), trails.get(6).getLandmarkIdTwo());
		assertEquals(trailEight.getLandmarkIdOne(), trails.get(7).getLandmarkIdOne());
		assertEquals(trailEight.getLandmarkIdTwo(), trails.get(7).getLandmarkIdTwo());
		assertEquals(trailNine.getLandmarkIdOne(), trails.get(8).getLandmarkIdOne());
		assertEquals(trailNine.getLandmarkIdTwo(), trails.get(8).getLandmarkIdTwo());
		assertEquals(trailTen.getLandmarkIdOne(), trails.get(9).getLandmarkIdOne());
		assertEquals(trailTen.getLandmarkIdTwo(), trails.get(9).getLandmarkIdTwo());
		assertEquals(trailEleven.getLandmarkIdOne(), trails.get(10).getLandmarkIdOne());
		assertEquals(trailEleven.getLandmarkIdTwo(), trails.get(10).getLandmarkIdTwo());
	}

}
