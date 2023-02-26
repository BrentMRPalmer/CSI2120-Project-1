//Student Full Name: Brent Palmer
//Student ID: 300193610

/**
 * The class <b>Main</b> is used to run the RANSAC algorithm.
 * It may be used with or without user inputs. Default values are as follows:
 * 	iterations = 3
 * 	eps = 0.4
 * 	confidence = 0.99
 * 	percentageOfPointsOnPlane = 0.4
 * 	filename = "PointCloud1.XYZ"
 * 
 * If there is an error in entering the arguments, that argument will be set 
 * to the corresponding default outlined above. 
 * 
 * To manually set values, follow the format:
 * 
 * 	(int iterations, double eps, double confidence, double percentageOfPointsOnPlane, String filename)
 * 
 * The class has one static method, which is the main method that runs the program. 
 *
 * @author Brent Palmer
 */

public class Main {
	public static void main(String[] args){

		//the defaults
		int iterations = 3;
		double eps = 0.4;
		double confidence = 0.99;
		double percentageOfPointsOnPlane = 0.4;
		String filename = "PointCloud1.XYZ";
		

		if(args.length == 0){
			//if no user inputs, then run the default conditions on each of the three point clouds. 

			System.out.println("Running the default conditions since no arguments were provided");
			System.out.println("Default conditions: iterations = 3, eps = 0.4, confidence = 0.99, percentageOfPointsOnPlane = 0.4");
			System.out.println("Run on PointCloud1.XYZ, PointCloud2.XYZ, and PointCloud3.XYZ");
			System.out.println("To manually set values: (int iterations, double eps, double confidence, double percentageOfPointsOnPlane, String filename)");

			PointCloud cloud = new PointCloud(filename);
			PlaneRANSAC ransac = new PlaneRANSAC(cloud);

			ransac.run(ransac.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud1_p1.XYZ");
			ransac.run(ransac.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud1_p2.XYZ");
			ransac.run(ransac.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud1_p3.XYZ");
			cloud.save("PointCloud1_p().XYZ");

			PointCloud cloud2 = new PointCloud("PointCloud2.XYZ");
			PlaneRANSAC ransac2 = new PlaneRANSAC(cloud2);

			ransac2.run(ransac2.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud2_p1.XYZ");
			ransac2.run(ransac2.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud2_p2.XYZ");
			ransac2.run(ransac2.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud2_p3.XYZ");
			cloud2.save("PointCloud2_p().XYZ");

			PointCloud cloud3 = new PointCloud("PointCloud3.XYZ");
			PlaneRANSAC ransac3 = new PlaneRANSAC(cloud3);

			ransac3.run(ransac3.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud3_p1.XYZ");
			ransac3.run(ransac3.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud3_p2.XYZ");
			ransac3.run(ransac3.getNumberOfIterations(confidence, percentageOfPointsOnPlane), "PointCloud3_p3.XYZ");
			cloud3.save("PointCloud3_p().XYZ");

		}else{
			//each of these try and excepts will individually check each and every input argument
			//if there is an error with any input, the user will be informed and the default will be applied. 
			try{
				iterations = Integer.parseInt(args[0]);
			}catch(Exception e){
				System.out.println("Error in entered iterations. Set to default. (3)");
			}
			try{
				eps = Double.parseDouble(args[1]);
			}catch(Exception e){
				System.out.println("Error in entered eps. Set to default. (0.4)");
			}
			try{
				confidence = Double.parseDouble(args[2]);
			}catch(Exception e){
				System.out.println("Error in entered confidence. Set to default. (0.99)");
			}
			try{
				percentageOfPointsOnPlane = Double.parseDouble(args[3]);
			}catch(Exception e){
				System.out.println("Error in entered percentageOfPointsOnPlane. Set to default. (0.4)");
			}
			try{
				filename = args[4];
			}catch(Exception e){
				System.out.println("Error in entered filename. Set to default. (PointCloud1.XYZ)");
			}

			System.out.println("Running on user-defined settings:");
			System.out.println("iterations = " + iterations + " eps = " + eps + " confidence = " + confidence + " percentageOfPointsOnPlane = " + percentageOfPointsOnPlane + " filename = " + filename);

			PointCloud cloud = new PointCloud(filename);
			PlaneRANSAC ransac = new PlaneRANSAC(cloud);

			//run ransac as per how many requested iterations
			for(int i = 1 ; i <= iterations; i++){
				ransac.run(ransac.getNumberOfIterations(confidence, percentageOfPointsOnPlane), filename.substring(0, filename.length()-4) + "_p" + i + ".XYZ");
			}
			
			//save the extra points to a new point cloud
			cloud.save(filename.substring(0, filename.length()-4) + "_p().XYZ");
		}

	}
}