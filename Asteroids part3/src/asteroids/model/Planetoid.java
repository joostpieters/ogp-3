package asteroids.model;

public class Planetoid extends MinorPlanet {

	private double distanceTraveled;
	private final double DENSITY = 0.917E12;
	
	/**
	 * Constructor to create a new planetoid
	 * @param x
	 * 		The x-coordinate of the planetoid
	 * @param y
	 * 		The y-coordinate of the planetoid
	 * @param xVelocity
	 * 		The xVelocity of the planetoid
	 * @param yVelocity
	 * 		The yVelocity of the planetoid
	 * @param radius
	 * 		The radius of the planetoid
	 * @param distanceTraveled
	 * 		The total distance the planetoid already traveled
	 * @throws IllegalArgumentException
	 * 		See superclass
	 */
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double distanceTraveled) throws IllegalArgumentException{
		super(x, y, xVelocity, yVelocity, radius);
		setDistanceTraveled(distanceTraveled);
	}
	
	/**
	 * Get the total traveled distance of the current planetoid
	 * @return |result == this.distanceTraveled
	 */
	public double getDistanceTraveled(){
		return this.distanceTraveled;
	}
	
	
	/**
	 * Set the traveledDistance to a given value
	 * @param distance
	 * 		The distance you want to set the distanceTraveled to.
	 */
	private void setDistanceTraveled(double distance){
		this.distanceTraveled = distance;
	}

	/**
	 * Get the radius of the planetoid. The planetoid shrinks with 0.00001 times the distance it already traveled
	 * @return |result == this.getRadius() - (0.000001*distanceTraveled)
	 */
	public double getRadius(){
		return super.getRadius() - (0.000001 * distanceTraveled);
	}
	
	/**
	 * Get the density
	 * @return |result == DENSITY
	 */
	public double getDensity(){
		return DENSITY;
	}
	
	/**
	 * Move the planetoid. The distance traveled will change
	 *@see implementation.
	 */
	public void move(double duration) {
		super.move(duration);
		setDistanceTraveled(getDistanceTraveled() + getSpeed()*duration);
	}
	
	@Override
	public void terminate() {
		super.terminate();
		World world = this.getWorld();
		if (world != null) world.removePlanetoid(this);
		double radius = this.getRadius();
		if (radius >= 30) {
			double childRadius = radius/2;
			double directionChild1 = Math.random() * 2 * Math.PI; 
			double directionChild2 = - directionChild1;
			double newXChild1 = this.getPositionArray()[0] + Math.cos(directionChild1) * childRadius;
			double newYChild1 = this.getPositionArray()[1] + Math.sin(directionChild1) * childRadius;
			double newXChild2 = this.getPositionArray()[0] + Math.cos(directionChild2) * childRadius;
			double newYChild2 = this.getPositionArray()[1] + Math.sin(directionChild2) * childRadius;
			
			double speed = 1.5 * this.getSpeed();
			double newXSpeedChild1 = speed * Math.cos(directionChild1);
			double newYSpeedChild1 = speed * Math.sin(directionChild1);
			double newXSpeedChild2 = speed * Math.cos(directionChild2);
			double newYSpeedChild2 = speed * Math.sin(directionChild2);
			
			Asteroid child1 = new Asteroid(newXChild1, newYChild1, newXSpeedChild1, newYSpeedChild1, childRadius);
			Asteroid child2 = new Asteroid(newXChild2, newYChild2, newXSpeedChild2, newYSpeedChild2, childRadius);
			world.addAsteroidToWorld(child1);
			world.addAsteroidToWorld(child2);
		}	
	}
	
	@Override
	public void collisionCircularObject(CircularObject object2) {
		if(object2 instanceof Ship) {
			Ship ship = (Ship) object2;
			double radius = ship.getRadius();
			double worldX = ship.getWorld().getWorldDimensionArray()[0];
			double worldY = ship.getWorld().getWorldDimensionArray()[1];
			double newX = Math.random() * worldX;
			double newY = Math.random() * worldY;
			
			if (newX - radius < 0) newX = radius;
			if (newX + radius >= worldX) newX = worldX - radius;
			if (newY - radius > 0) newY = radius;
			if (newY + radius >= worldY) newY = worldY - radius;
			
			for (CircularObject otherObject : this.getWorld().getAllCircularObjectsInWorld()) {
				if (ship != otherObject && otherObject.overlap(ship)) {
					ship.terminate();
				}
			}
		} else {
			super.collisionCircularObject(object2);
		}
		
	}
	

	
	
}
