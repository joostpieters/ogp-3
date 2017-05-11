package asteroids.model;

public class MinorPlanet extends CircularObject{

	
	/**
	 * Constant representing the minimal radius of a minor planet
	 */
	private final double MINIMUMMINORPLANETRADIUS = 1; 
	

	
	
	//Constructor using superclass CircularObject
	public MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}


	public double getMass() {
		double minorPlanetRadius= getRadius();
		return( 3/4 * Math.PI * Math.pow(minorPlanetRadius, 3) * getDensity());
	}

	
	


	public double getMinimalRadius(){
		return MINIMUMMINORPLANETRADIUS;
	}

	
	

	
	
	
}
