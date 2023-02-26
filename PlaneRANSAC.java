//Student Full Name: Brent Palmer
//Student ID: 300193610

import java.util.*;

/**
 * The class <b>PlaneRANSAC</b> is used to run the RANSAC algorithm.
 * That is to say, it will compute the dominant plane from a given point cloud. 
 * 
 * In essence, it pulls three random points from a given cloud many times, creates 
 * a plane equation for each of them, and saves the plane which has the most
 * points in it. This is considered the "dominant" plane. 
 * 
 * The class has two instance variables. The first, pc, is used to store
 * the PointCloud that RANSAC will analyze. The second, eps, is used to store
 * the epsilon value - that is, the maximum distance a point can be from a plane
 * for the point to be considered part of the plane. 
 * 
 * The class has one constructor, which takes a point cloud as input. 
 * 
 * The class has 4 methods. There is a getter and setter for the eps value. 
 *  getNumberOfIterations is used to determine how many iterations are required in
 *  the RANSAC algorithm to return a dominant plane with a certain % chance (confidence) 
 * 	of being the correct most dominant plane.
 *  run is used to effectuate the RANSAC algorithm and to determine the most dominant plane.  
 *
 * @author Brent Palmer
 */

public class PlaneRANSAC {
	private PointCloud pc;
	private double eps;


	/**
     * The constructor for <B>PlaneRANSAC</b> will take a point cloud 
     * as input, which will be the point cloud processed by the
     * RANSAC algorithm when the program is run. 
     * 
     * @param pc
     * A PointCloud object that represents the point cloud to be processed. 
     */
	public PlaneRANSAC(PointCloud pc) {
		this.pc = pc;
		eps = 0.5;
	}

	/**
 	 * The method <b>setEps</b> is a setter method that
 	 * is used to set eps to a specific value.
 	 * 
 	 * Inputs and Outputs:
 	 * @param eps
 	 * A double used to set the eps value.
 	 * 
 	 * No return. 
 	 */
	public void setEps(double eps) {
		this.eps = eps;
	}

	/**
 	 * The method <b>getEps</b> is a getter method that
 	 * is used to return the eps value.
 	 * 
 	 * Inputs and Outputs:
 	 * No inputs parameters
 	 * @return
 	 * Returns a double that represents the eps value. 
 	 */
	public double getEps() {
		return eps;
	}

	/**
 	 * The method <b>getNumberOfIterations</b> is used to determine
 	 * how many RANSAC iterations are required to achieve a certain confidence
 	 * that the calculated dominant plane is, in fact, the dominant plane.
 	 * It is dependent on the percentage of points on the plane that are
 	 * expected to be part of the dominant plane. 
	 * 
	 * Inputs and Outputs:
	 * @param confidence
	 * A double that represents the desired chance of accurately 
	 * returning the dominant plane. 
	 * 
	 * @param percentageOfPointsOnPlane
	 * A double that represents the expected percentage of points 
	 * that are part of the dominant plane. 
	 * 
	 * @return
	 * Returns an int that represents the necessary number of 
	 * iterations of RANSAC to achieve a given confidence. 
	 */
	public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane) {
		return (int) Math.ceil( Math.log10(1 - confidence) / Math.log10( 1 - Math.pow(percentageOfPointsOnPlane, 3) ) );
	}

	/**
 	 * The method <b>run</b> is used to effectuate the RANSAC algorithm.
 	 * 
 	 * In essence, the algorithm iterates many times. Each time, three random
 	 * points from the processed point cloud are retrieved and the equation
 	 * of the plane that passes through them is developed. The number of
 	 * points in the point cloud that are within the chosen eps are tracked.
 	 * The plane that has the highest "support," that is the highest number
 	 * of points that roughly follow the plane equation, is saved. This
 	 * is the dominant plane. It is written to a file, a new point cloud, and the points that
 	 * are part of the dominant plane are removed from the original point cloud. 
	 * 
	 * Inputs and Outputs:
	 * @param numberOfIterations
	 * An int that represents the number of iterations for RANSAC to complete
	 * 
	 * @param filename
	 * A String that represents the name of the file that the dominant cloud 
	 * will be written to. 
	 * 
	 * No return. 
	 */
	public void run(int numberOfIterations, String filename) {
		int support = 0;
		int currentSupport;
		Plane3D dominantPlane = null;
		Plane3D currentPlane;
		PointCloud dominantCloud;
		Iterator<Point3D> iter;
		Point3D currentPoint;

		for(int i = 0; i < numberOfIterations; i++){
			//RANSAC algorithm begins here
			//pick three random points and create the plane
			currentPlane = new Plane3D(pc.getPoint(), pc.getPoint(), pc.getPoint());

			//use an iterator to find the number of points in the generated plane
			currentSupport = 0;
			iter = pc.iterator();
			while(iter.hasNext()) if( currentPlane.getDistance(iter.next()) < eps) currentSupport++;

			//if current plane has more points than the old dominant plane, set the current plane to the dominant plane
			if(currentSupport > support){
				support = currentSupport;
				dominantPlane = currentPlane;
			}
		}

		//now the dominant plane has been found
		//save these points to a new cloud and delete them from the original cloud
		dominantCloud = new PointCloud();
		iter = pc.iterator();
		while(iter.hasNext()){
			currentPoint = iter.next();
			if( dominantPlane.getDistance(currentPoint) < eps){
				dominantCloud.addPoint(currentPoint);
				iter.remove();
			}
		}

		//save the dominant cloud to a new file
		dominantCloud.save(filename);

	}
}