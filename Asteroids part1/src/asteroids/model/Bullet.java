package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

public class Bullet extends CircularObject{
 
	
	/**
	 * Constant representing the density of a bullet
	 */
	
	private double DENSITY = 7.8*Math.pow(10,12);

	/**
	 * Constant representing the minimal radius of a bullet
	 */
	private final double MINIMUMBULLETRADIUS = 1;
	
	/**
	 * Variable representing the current number of border collisions
	 */
	public double boundaryCollisions = 0;
	
	/**
	 * Boolean variable representing whether or not the bullet is terminated
	 */
	private boolean isTerminated = false;
	
	/**
	 * Initialization of a bullet with given position in x and y coordinates, horizontal and vertical velocity and a radius. 
	 *@param xCoordinate
	 *		The xCoordinate of the bullet
	 *@param yCoordinate
	 *		The yCoordinate of the bullet
	 *@param xVelocity
	 *		The xVelocity of the bullet
	 *@param yVelocity
	 *		The yVelocity of the bullet
	 *@param radius
	 *		The radius of the bullet
	 *@post The position of the bullet is equal to the given xCoordinate and yCoordinate
	 *		|new.getPosition = {xCoordinate, yCoordinate}
	 *@post The velocity of the bullet is equal to the given xVelocity and yVelocity
	 *		|new.getVelocity = {xVelocity, yVelocity}
	 *@post The radius of the bullet is equal to the given radius
	 *		|new.getRadius = radius
	 *
	 */
	public Bullet(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius)
		throws IllegalArgumentException { 
			//using superclass Circular Object
			super(xCoordinate, yCoordinate, xVelocity, yVelocity, radius);
		}
	
	private Ship ship;
	
	//
	//GETTERS 
	//
	
	/**
	 * Retrieve the ship that has this certain bullet
	 * @return returns the ship where the bullet belongs to.
	 * 		|result == this.ship
	 */
	public Ship getSourceShip(){
		return this.ship;
	}
	
	/**
	 * Get the mass of the bullet
	 * @return the mass of the bullet
	 * 			|result == mass;
	 */
	public double getMass(){
		double bulletradius = getRadius();
		return( 3/4 * Math.PI * Math.pow(bulletradius, 3) * DENSITY);
	}
	
	/**
	 * Get the minimal radius of the bullet (1)
	 * @return The minimal radius of the bullet
	 * 			|result == MINIMUMBULLETRADIUS
	 */
	public double getMinimalRadius(){
		return MINIMUMBULLETRADIUS;
	}
	
	/**
	 * Retrieve the number of boundary collisions of a bullet
	 * @see implementation
	 */
	
	public double getBoundaryCollisions() {
		return this.boundaryCollisions;
	}
	
	
	
	/**
	 * Set the ship where the bullet belongs to.
	 * @param ship
	 * 		The ship where the bullet belongs to
	 * @post
	 * 		The bullet is assigned to the given ship
	 * 		|this.getsourceShip() == ship
	 */
	public void setSourceShip(Ship ship){
		this.ship = ship;
	}
	/**
	 * Method that increments the amount of times the bullet has collided with a boundary
	 */
	public void incrementBoundaryCollision() {
		this.boundaryCollisions ++;
	}
	/**
	 * Method to terminate a bullet
	 * @post
	 * 			|new.getWorld == null
	 * 			|isTerminated == true
	 */
	public void terminateBullet() {
		this.getWorld().removeBullet(this);
		this.isTerminated = true;
	}
	
	
	public boolean isBulletTerminated(){
		return isTerminated;
	}
	
	/**
	 * Method to resolve collision between a bullet and the boundary of a world, if a bullet collides with a boundary of a world for
	 * the third time, the bullet is terminated
	 * @post If the bullet collides with a boundary and hasn't collided with a boundary 3 times, then the boundaryCollisions variable is incremented and the velocities get negates,
	 * 			when a bullet collides with a boundary for the 3rd time, the bullet is terminated
	 * 			|if ((this.position.getPositionX() - this.getRadius() <= 0 || this.position.getPositionX() + this.getRadius() >= xBoundaryWorld) && this.boundaryCollisions < 3) 
	 * 				new.velocity.getXVelocity = -currentXVel && new.velocity.getYVelocity = -currentYVel
	 * 			|if (this.boundaryCollisions == 3) terminateBullet
	 * 
	 */
	public void collideWithBoundary() {
		double xBoundaryWorld = this.world.getWorldDimensionArray()[0];
		double yBoundaryWorld = this.world.getWorldDimensionArray()[1];
		if (this.boundaryCollisions >= 2) {
			this.terminateBullet();
		}
		if ((this.getPositionArray()[0] - this.getRadius() <= 0 || this.getPositionArray()[0] + this.getRadius() >= xBoundaryWorld) && this.boundaryCollisions < 3) {
			double currentXVel = this.getVelocityArray()[0];
			double currentYVel = this.getVelocityArray()[1];
			this.setVelocity(-currentXVel, currentYVel);
			this.incrementBoundaryCollision();
		}
		if ((this.getPositionArray()[1] - this.getRadius() <= 0 || this.getPositionArray()[1] + this.getRadius() >= yBoundaryWorld) && this.boundaryCollisions < 3) {
			double currentXVel = this.getVelocityArray()[0];
			double currentYVel = this.getVelocityArray()[1];
			this.setVelocity(currentXVel, -currentYVel);
			this.incrementBoundaryCollision();
		}
	}
	
	/**
	 * Method to resolve collision between a bullet and a circular object
	 * @param object2
	 * @post If the bullet collides with its source ship, then the bullet is loaded onto the ship
	 * 			|ship.loadBullet(this)
	 * @post If it collides with a different ship then both are terminated
	 * 			|ship.terminateShip() && this.terminateBullet() 		
	 */
	public void collisionCircularObject(CircularObject object2) {
		if (object2 instanceof Ship) {
			Ship ship = (Ship)object2;
			if (this.getSourceShip() == ship) ship.loadBullet(this);
			else {
				ship.terminateShip();
				this.terminateBullet();
			}
		}
		else {
			Bullet bullet = (Bullet)object2;
			this.terminateBullet();
			bullet.terminateBullet();
		}
	}
}
