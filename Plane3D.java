//Student Full Name: Brent Palmer
//Student ID: 300193610

/**
 * The class <b>Plane3D</b> is used to represent the
 * equation of a plane in 3D space. 
 * 
 * The class has four instance variables, doubles a, b, 
 * c, and d. Each of these letters correspond to the
 * equivalent letters in the plane equation:
 * 
 * 	Ax + By + Cz = D.
 * 
 * The class has two constructors. The first constructor
 * finds the equation of the plane from three input points.
 * The second constructor directly takes the coeffecients
 * a, b, c, and d. 
 * 
 * The class has two methods. 
 * getDistance is used to find the distance between a 
 * given point and the plane.
 * toString is used to output the plane in a readable format. 
 *
 * @author Brent Palmer
 */

public class Plane3D {
	private double a;
	private double b;
	private double c;
	private double d;

	/**
     * The first constructor for <B>Plane3D</b> will determine
     * the equation of the plane from three given input points. 
     * 
     * @param p1
     * A Point3D object that represents the first point on the plane. 
     * 
     * @param p2
     * A Point3D object that represents the second point on the plane. 
     * 
     * @param p3
     * A Point3D object that represents the third point on the plane. 
     */
	public Plane3D(Point3D p1, Point3D p2, Point3D p3) {
		//Assuming plane in Ax + By + Cz = D form
		//calculating vector 1 (p2p1)
		double x1 = p2.getX() - p1.getX();
		double y1 = p2.getY() - p1.getY();
		double z1 = p2.getZ() - p1.getZ();

		//calculating vector 2 (p3p1)
		double x2 = p3.getX() - p1.getX();
		double y2 = p3.getY() - p1.getY();
		double z2 = p3.getZ() - p1.getZ();

		//calculating the normal of the two vectors
		a = y1 * z2 - z1 * y2;
		b = z1 * x2 - x1 * z2;
		c = x1 * y2 - y1 * x2;

		//using a point to find the value of d
		d = a * p3.getX() + b * p3.getY() + c * p3.getZ();
	}

	/**
     * The second constructor for <B>Plane3D</b> will create
     * a plane with the 4 coefficients being directly provided
     * as inputs. 
     * 
     * @param a
     * A double that represents the A coefficient of the plane equation.
     * 
     * @param b
     * A double that represents the B coefficient of the plane equation.
     * 
     * @param c
     * A double that represents the C coefficient of the plane equation.
     * 
     * @param d
     * A double that represents the D coefficient of the plane equation.
     */
	public Plane3D(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	/**
     * The method <b>getDistance</b> is used to calculate the distance
     * between a given Point3D as an input and the plane.
     * 
     * It uses the equation:
     * 
     * 	l = |a*x + b*y + c*z - d| / sqrt(a^2, b^2, c^2)
     * 
     * to calculate the distance. 
     * 
     * Inputs and Outputs:
     * @param pt
     * A Point3D object that represents the point whose distance from the 
     * plane will be calculated. 
     * 
     * @return
     * Returns a double that represents the distance from the point to the plane. 
     */
	public double getDistance(Point3D pt) {
		return Math.abs(a*pt.getX() + b*pt.getY() + c*pt.getZ() + -1*d) / Math.sqrt(a*a + b*b + c*c);
	}

	/**
     * The method <b>toString</b> is used to give a readable 
     * string representation of a 3D plane. Specifically, it was
     * used for modular verification of functionality.   
     * 
     * Inputs and Outputs:
     * 
     * No input parameters.
     * 
     * @return
     * Returns a String that is a string representation of a 3D plane
     */
	public String toString(){
		return a + "x + " + b + "y + " + c + "z = " + d;
	}
}