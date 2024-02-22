package sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to read landmark and trail csv files.
 * 
 * @author James Kocak
 */
public class IOManager {
	/**
	 * Reads the csv file of landmarks and returns a list of the landmarks.
	 * 
	 * @param path The file path to the csv file
	 * @return A list of the landmarks in the file
	 */
	public static List<Landmark> readLandmarks(String path) {
		// Create ArrayList to store Landmark objects
		ArrayList<Landmark> landmarks = new ArrayList<>();

		try {
			// Read the CSV file
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;

			// Skip the header line
			br.readLine();

			// Read each line and create Landmark objects
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				String id = data[0];
				String description = data[1];
				String type = data[2];

				// Create Landmark object and add to ArrayList
				Landmark landmark = new Landmark(id, description, type);
				landmarks.add(landmark);
			}

			// Close the BufferedReader
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Returns list of landmarks
		return landmarks;
	}

	/**
	 * Reads the csv file of trails and returns a list of trails.
	 * 
	 * @param path The file path to the csv file
	 * @return A list of the trails in the file
	 */
	public static List<Trail> readTrails(String path) {
		// Create ArrayList to store Distance objects
		ArrayList<Trail> trails = new ArrayList<>();

		try {
			// Read the CSV file using BufferedReader
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;

			// Skip the header line
			br.readLine();

			// Read each line and create Distance objects
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				String landmarkId1 = data[0];
				String landmarkId2 = data[1];
				int distance = Integer.parseInt(data[2]);

				// Create Distance object and add to ArrayList
				Trail distanceObj = new Trail(landmarkId1, landmarkId2, distance);
				trails.add(distanceObj);
			}

			// Close the BufferedReader
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Returns list of trails
		return trails;
	}
}
