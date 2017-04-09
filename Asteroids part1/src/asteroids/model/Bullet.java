package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

public class Bullet extends CircularObject{
 
	
	private static final int DENSITIY = 0;
	private double DENSITY = 7.8*Math.pow(10,12);

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
			super(xCoordinate, yCoordinate, xVelocity, yVelocity, radius);
		}
	
	
	private Ship ship;
	
	/**
	 * Retrieve the ship that has this certain bullet
	 * @return 
	 * @return returns the ship where the bullet belongs to.
	 * 		|result == this.ship
	 */
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * Set the ship where the bullet belongs to.
	 * @param ship
	 * 		The ship where the bullet belongs to
	 * @post
	 * 		The bullet is assigned to the given ship
	 * 		|this.getShip() == ship
	 */
	public void setShip(Ship ship){
		if (ship == null){
			this.ship = ship;
		}
	}
	
	public double getMass(){
		double radius = getRadius();
			
		return( 3/4 * Math.PI * Math.pow(radius, 3) * DENSITIY);
	
	}
	
	
	
	
}
