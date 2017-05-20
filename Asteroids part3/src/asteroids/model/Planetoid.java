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
	 * see implementation.
	 */
	public void move(double duration) {
		super.move(duration);
		setDistanceTraveled(getDistanceTraveled() + getSpeed()*duration);
	}
	
	public void terminate() {
		super.terminate();
		World world = this.getWorld();
		if (world != null) world.removePlanetoid();
		double radius = this.getRadius();
		if (radius >= 30) {
		}	
	}
	
	@Override
	public void collisionCircularObject(CircularObject object) {
		if(object instanceof Ship) {
			Ship ship = (Ship) object;
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
			super.collide(object);
		}
		
	}
	

	
	
}
