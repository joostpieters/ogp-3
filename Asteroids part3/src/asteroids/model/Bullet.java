package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

public class Bullet extends CircularObject{
 
	
	/**
	 * Constant representing the density of a bullet
	 */
	
	private double DENSITY = 7.8E12;

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
	public Ship getSourceShip() {
		return this.ship;
	}
	
	/**
	 * Get the mass of the bullet
	 * @return the mass of the bullet
	 * 			|result == mass;
	 */
	@Override
	public double getMass(){
		double bulletradius = getRadius();
		double mass = 4.0 * Math.PI * Math.pow(bulletradius, 3) * getDensity() / 3.0;
		return mass;
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
	 * Method that returns the bullet of the bullet
	 * @return
	 * 		|DENSITY
	 */
	@Override
	public double getDensity() {
		return DENSITY;
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
	 * @post boundaryCollisions is incremented
	 * 		|this.boundaryCollisions ++;
	 */
	public void incrementBoundaryCollision() {
		this.boundaryCollisions ++;
	}
	/**
	 * Method to terminate a bullet
	 * @effect If the bullet is part of a world, then remove the bullet from the world
	 * 		|if (this.getWorld() != null) this.getWorld().removeBullet(this)
	 * @effect If the bullet is not part of a world, therefore loaded onto a ship, remove bullet from the ship
	 * 		|this.getSourceShip().removeBullet(this)
	 * 		|this.setSourceShip(null)
	 */
	@Override
	public void terminate() {
		super.terminate();
		if (this.getWorld() != null) {
			this.getWorld().removeBullet(this);
		} else {
			this.getSourceShip().removeBullet(this);
			this.setSourceShip(null);
		}
	}
	
	/**
	 * Check if the bullet is terminated
	 * @return If the bullet is terminated or not
	 * 			|result == isTerminated
	 */
	public boolean isBulletTerminated(){
		return isTerminated;
	}
	
	/**
	 * Method to resolve collision between a bullet and the boundary of a world
	 * @effect If the bullet collides with a boundary for the third time it is terminated			
	 * 			|if (this.boundaryCollisions >= 3) this.terminate()
	 */
	@Override
	public void collideWithBoundary() {
		super.collideWithBoundary();
		this.incrementBoundaryCollision();
		if (this.boundaryCollisions >= 3) {
			this.terminate();
		}
		
	}
	
	/**
	 * Method to resolve collision between a bullet and a circular object
	 * @param object2
	 * @post If the bullet collides with its source ship, then the bullet is loaded onto the ship
	 * 			|ship.loadBullet(this)
	 * @post If it collides with a different circular object then both are terminated
	 * 			|object2.terminate() && this.terminate() 		
	 */
	@Override
	public void collisionCircularObject(CircularObject object2) {
		if (object2 instanceof Ship) {
			Ship ship = (Ship)object2;
			if (this.getSourceShip() == ship) ship.loadBullet(this);
			else {
				System.out.println("bullet met ship");
				ship.terminate();
				this.terminate();
			}
		}
		else {
			this.terminate();
			object2.terminate();
		}
	}

}
