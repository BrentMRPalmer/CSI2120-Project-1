//Student Full Name: Brent Palmer
//Student ID: 300193610

import java.util.*;
import java.io.*;

/**
 * The class <b>PointCloud</b> is used to store a list of 3D points, 
 * alongside some additional functionality. 
 * 
 * The class has one instance variable, points, which is 
 * simply the list of 3D points.
 * 
 * The class has two constructors. The first of which takes a String of
 * the filename to be processed as input, and generates a list 
 * of the 3D points contained in the .XYZ file. The second takes no inputs
 * and simply instantiates an empty list of points. 
 * 
 * The class has 4 methods. 
 * 	addPoint allows for adding a specific point to the point cloud.
 * 	getPoint returns a random point from the point cloud.
 * 	save will write the list of points to a .XYZ file.
 * 	iterator will return an iterator used to traverse the list of points. 
 *
 * @author Brent Palmer
 */

public class PointCloud {

	private List<Point3D> points; 

	/**
     * The first constructor for <B>PointCloud</b> will take
     * the name of a .XYZ file of points, and initialize 
     * a list of points corresponding to the points
     * outlined in the file. 
     * 
     * @param filename
     * A String that represents the name of the file to be processed. 
     */
	public PointCloud(String filename) {
		//arraylist to store the new points
		points = new ArrayList<>();

		//string used to temporarily store each point processed
		String currentPoint;
		String[] currentPointSplit;


		try {
			//instantiate the scanner that reads the .XYZ file
			File file = new File(filename);
			Scanner scanner = new Scanner(file);

			//skip the header of the file (x, y, z)
			scanner.nextLine();

			while(scanner.hasNextLine()) {
				//scan in each point one at a time, and split by a tab to isolate xyz
				currentPoint = scanner.nextLine();
				currentPointSplit = currentPoint.split("\t");

				//add scanned point to the list of 3D points
				addPoint( new Point3D( Double.parseDouble(currentPointSplit[0]), Double.parseDouble(currentPointSplit[1]), Double.parseDouble(currentPointSplit[2]) ) );
			}

			scanner.close();

		} catch( FileNotFoundException e ) {
			System.out.println("File not found");
		}

	}

	/**
     * The second constructor for <B>PointCloud</b> will take
     * no inputs and initialize an empty list of points. 
     */
	public PointCloud(){
		points = new ArrayList<>();
	}

	/**
 	 * The method <b>addPoint</b> is used to add
 	 * a given Point3D to the point cloud. 
	 * 
	 * Inputs and Outputs:
	 * @param pt
	 * A Point3D object that will be added to the point cloud.
	 * 
	 * No return.
	 */
	public void addPoint(Point3D pt) {
		points.add(pt);
	}

	/**
 	 * The method <b>getPoint</b> is used to retrieve a random 
 	 * point from the point cloud. 
	 * 
	 * Inputs and Outputs:
	 * No input parameters.
	 * 
	 * @return
	 * Returns an arbitrary Point3D object from the cloud.
	 */
	public Point3D getPoint(){
		Random random = new Random();
		return points.get( random.nextInt( points.size() ) );
	}

	/**
 	 * The method <b>save</b> is used to write the list of 
 	 * 3D points to a .XYZ file. 
	 * 
	 * Inputs and Outputs:
	 * @param filename
	 * A string that represents the name of the file to be created. 
	 * 
	 * No return. 
	 */
	public void save(String filename) {
		//temporary point to be written to new file
		Point3D currentPoint;

		try {
			//instantiate the writer that writes the .XYZ file and create the file
			File file = new File(filename);
			FileWriter writer = new FileWriter(filename);

			//create the header of the new file
			writer.write("x\ty\tz\n");

			Iterator<Point3D> iter = iterator();

			while(iter.hasNext()) {
				//write each point into the file with coordinates delimited by tabs
				currentPoint = iter.next();
				writer.write(currentPoint.getX() + "\t" + currentPoint.getY() + "\t" + currentPoint.getZ() + "\n");
			}

			writer.close();

		} catch( IOException e ) {
			System.out.println("Error saving the file");
		}

	}

	/**
 	 * The method <b>iterator</b> is used to generate 
 	 * an iterator that allows for traversing the 
 	 * points in the point cloud. 
	 * 
	 * Inputs and Outputs:
	 * No input parameters. 
	 * 
	 * @return
	 * Returns an Iterator that iterates over the cloud. 
	 */
	public Iterator<Point3D> iterator() {
		return points.iterator();
	}
}