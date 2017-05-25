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
		return 4.0 * Math.PI * Math.pow(getRadius(), 3) * this.getDensity() / 3.0;
	}
	/**
	 * Method to terminate a minor planet
	 * @effect If the minor planet is of type Asteroid, remove asteroid from world
	 * 		|world.removeAsteroid(asteroid)
	 * @effect If the minor planet is of type Planetoid, remove planetoid from world
	 * 		|world.removePlanetoid(planetoid)
	 */
	@Override
	public void terminate() {
		super.terminate();
		World world = this.getWorld();
		if (world != null) {
			if (this instanceof Asteroid) {
				Asteroid asteroid = (Asteroid) this;
				world.removeAsteroid(asteroid);
			} else {
				Planetoid planetoid = (Planetoid) this;
				world.removePlanetoid(planetoid);
			}
		}
	}
	/**
	 * Method to resolve collisions between ships and minor planets and bullets and minor planets
	 * @effect If the minor planet collides with another ship, update their velocity to reflect this
	 * 		|this.setVelocity(newXVelocityThisObject, newYVelocityThisObject);
	 * 		|object2.setVelocity(newXVelocityObject2, newYVelocityObject2);
	 * @effect If the minor planet collides with a bullet, both bullet and minor planet die
	 * 		|this.terminate()
	 * 		|object2.terminate()
	 */
	@Override
	public void collisionCircularObject(CircularObject object2) {
		if (object2 instanceof MinorPlanet) {
			double sumOfRadiusses = this.getRadius() + object2.getRadius();
			double sumOfMasses = this.getMass() + object2.getMass();
			
			Vector deltaV = new Vector(this.velocity.getDifferenceInVelocity(object2.velocity));
			Vector deltaR = new Vector(this.position.getDifferenceInPositions(object2.position));
			
			double J = 2 * this.getMass() * object2.getMass() * (deltaV.dotProductVectors(deltaR))
					/(sumOfRadiusses*sumOfMasses);
			
			double jX = J * this.position.getDifferenceInPositions(object2.position)[0]
					/ sumOfRadiusses;
			
			double jY = J * this.position.getDifferenceInPositions(object2.position)[1]
					/ sumOfRadiusses;
			
			double newXVelocityThisObject = this.getVelocityArray()[0] + jX/this.getMass();
			double newYVelocityThisObject = this.getVelocityArray()[1] + jY/this.getMass();
			double newXVelocityObject2 = object2.getVelocityArray()[0] - jX/object2.getMass();
			double newYVelocityObject2 = object2.getVelocityArray()[1] - jY/object2.getMass();		
			this.setVelocity(newXVelocityThisObject, newYVelocityThisObject);
			object2.setVelocity(newXVelocityObject2, newYVelocityObject2);
			System.out.println("Minor planet met minor planet");
		}
		else if (object2 instanceof Bullet){
			this.terminate();
			object2.terminate();
		}
	}
	



	

	
	

	
	
	
}
