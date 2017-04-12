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
	 * The position of the bullet
	 */
	private Position position = new Position(0,0);
	
	/**
	 * The velocity of the bullet
	 */
	private Velocity velocity = new Velocity(0,0);
	
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
	 * Method that returns the x position of the bullet
	 * @see implementation
	 */
	public double getXPosition() {
		
		return this.position.getPositionX();
	}
	/**
	 * Method that returns the y position of the bullet
	 * @see implementation
	 */
	public double getYPosition() {
		
		return this.position.getPositionY();
	}
	/**
	 * Method that returns the x and y position of the bullet as an array of length 2
	 * @see implementation
	 */
	public double[] getPositionArray() {
		return this.position.getPositionArray();
	}
	/**
	 * Method that returns the position of the bullet
	 * @see implementation
	 */
	public Position getPosition() {
		return this.position.getPosition();
	}
	/**
	 * Returns the horizontal velocity of the bullet
	 * @see implementation
	 */
	public double getXVelocity() {
		return this.velocity.getXVelocity();
	}
	
	/**
	 * Returns the vertical velocity of the bullet
	 * @see implementation
	 */
	public double getYVelocity() {
		return this.velocity.getYVelocity();
	}
	
	/**
	 * Returns the horizontal and vertical velocity of the bullet as an array of length 2
	 * @see implementation
	 */
	public double[] getVelocityArray() {
		return this.velocity.getVelocityArray();
	}
	/**
	 * Returns the velocity of the bullet
	 * @see implementation
	 */
	public Velocity getVelocity() {
		return this.velocity.getVelocity();
	}
	
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
	
	//
	//SETTERS
	//
	/**
	 * Method to set the x position of the bullet
	 * @param x
	 * @post
	 * 			|new.getXPosition == x
	 */
	public void setXPosition(double x) {
		//TODO: isValidPosition and throw exception
		this.position.setPositionX(x);
	}
	/**
	 * Method to set the y position of the bullet
	 * @param y
	 * @post
	 * 			|new.getYPosition == y
	 */
	public void setYPosition(double y) {
		//TODO: isValidPosition and throw exception
		this.position.setPositionY(y);
	}
	/**
	 * Method to set both x and y position of the bullet
	 * @param x
	 * @param y
	 * @post
	 * 			|new.getPosition = new Position(x,y)
	 */
	public void setPosition(double x, double y) {
		//TODO: isValidPosition and throw exception
		this.position.setPosition(x, y);
	}
	
	public void setXVelocity(double x) {
		//TODO: isValidVelocity and throw exception
		this.velocity.setXVelocity(x);
	}
	
	public void setYVelocity(double y) {
		//TODO: isValidVelocity and throw exception
		this.velocity.setYVelocity(y);
	}
	
	public void setVelocity(double x, double y) {
		//TODO: isValidVelocity and throw exception
		this.velocity.setVelocity(x, y);
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
		if (ship == null){
			this.ship = ship;
		}
	}
	
	public void incrementBoundaryCollision() {
		this.boundaryCollisions ++;
	}
	
	public 
}
