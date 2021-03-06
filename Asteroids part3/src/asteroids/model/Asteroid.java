package asteroids.model;

public class Asteroid extends MinorPlanet {
	
	private final double DENSITY = 2.65E12;
	
	
	
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
	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		
	}
	/**
	 * Get the density of the asteroid
	 * @return |result  == DENSITY
	 */
	@Override
	public double getDensity() {
		return DENSITY;
	}
	/**
	 * Method to resolve collisions between asteroids and other circular objects
	 */
	@Override
	public void collisionCircularObject(CircularObject object) {
		if (object instanceof Ship) {
			Ship ship = (Ship) object;
			ship.terminate();
			System.out.println("Asteroid met ship");
		} else {
			System.out.println("Asteroid met iets anders");
			super.collisionCircularObject(object);
		}
		
	}
	
	



	
	
}