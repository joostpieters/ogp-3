package asteroids.model;

public abstract class MinorPlanet extends CircularObject{

	
	/**
	 * Constant representing the minimal radius of a minor planet
	 */
	private final double MINIMUMMINORPLANETRADIUS = 5; 
	
	
	
	/**
	 * Constructor to create a new asteroid
	 * @param x
	 * 		The x-coordinate of the asteroid
	 * @param y
	 * 		The y-coordinate of the asteroid
	 * @param xVelocity
	 * 		The xVelocity of the asteroid
	 * @param yVelocity
	 * 		The yVelocity of the asteroid
	 * @param radius
	 * 		The radius of the asteroid
	 * @throws IllegalArgumentException
	 * 		See superclass
	 */
	public MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	/**
	 * Get the minimal radius of the minor planet.
	 * @return  |result == MINIMUMMINORPLANETRADIUS
	 */
	public double getMinimalRadius(){
		return MINIMUMMINORPLANETRADIUS;
	}
	

	/**
	 * Get the mass of the minor planet.
	 * @see implementation
	 */
	@Override
	public double getMass() {
		return 4/3.*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}



	

	
	

	
	
	
}
