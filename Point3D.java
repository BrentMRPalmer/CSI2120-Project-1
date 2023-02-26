//Student Full Name: Brent Palmer
//Student ID: 300193610

/**
 * The class <b>Point3D</b> is used to represent a specific point
 * in 3-dimensions. 
 * 
 * The class has three instance variables, doubles x, y, and z.
 * Each of these variables correponds to the coordinates of the
 * point along the respective axis.
 * 
 * The class has one constructor, which takes as input three 
 * doubles representing the coordinates of the point.
 * 
 * The class has 4 methods. There are three getters, used to 
 * return the x, y, and z coordinates of the point. There is
 * also a toString method, used to print an instance of the 
 * class is a readable format.
 *
 * @author Brent Palmer
 */

public class Point3D {
	private double x;
	private double y;
	private double z;

	/**
 	 * The constructor for <B>Point3D</b> will initialize 
 	 * each of the three coordinates of the point using the
 	 * given input parameters. 
	 * 
	 * @param x
	 * A double that represents the x coordinate of the point. 
	 * 
	 * @param y
	 * A double that represents the y coordinate of the point. 
	 * 
	 * @param z
	 * A double that represents the z coordinate of the point. 
	 */
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

    /**
     * The method <b>getX</b> is a getter method that
     * is used to return the x coordinate of the 3D point.
     * 
     * Inputs and Outputs:
     * No inputs parameters
     * @return
     * Returns a double that represents x coordinate of the 3D point. 
     */
	public double getX() {
		return x;
	}

	/**
     * The method <b>getY</b> is a getter method that
     * is used to return the y coordinate of the 3D point.
     * 
     * Inputs and Outputs:
     * No inputs parameters
     * @return
     * Returns a double that represents y coordinate of the 3D point. 
     */
	public double getY() {
		return y;
	}

	/**
     * The method <b>getZ</b> is a getter method that
     * is used to return the z coordinate of the 3D point.
     * 
     * Inputs and Outputs:
     * No inputs parameters
     * @return
     * Returns a double that represents z coordinate of the 3D point. 
     */
	public double getZ() {
		return z;
	}

	/**
     * The method <b>toString</b> is used to give a readable 
     * string representation of a 3D point. Specifically, it was
     * used for modular verification of functionality.   
     * 
     * Inputs and Outputs:
     * 
     * No input parameters.
     * 
     * @return
     * Returns a String that is a string representation of a 3D point
     */
	public String toString(){
		return "(" + x + ", " + y + ", " + z +")";
	}
}