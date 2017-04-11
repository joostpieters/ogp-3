package asteroids.model;

import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

public abstract class CircularObject {

	
	//
	// DECLARATION OF VARIABLES AND CONSTANTS
	//
	
	/**
	 * The position of the object
	 */
	public Position position = new Position(0,0);

	/**
	 * The velocity of the object
	 */
	public Velocity velocity = new Velocity(0,0);
	
	/**
	 * The radius of the object
	 */
	private double radius;
	
	/**
	 * The mass of the object
	 */
	public abstract double getMass();
	
	/**
	 * The minimal radius of the object
	 */
	public abstract double getMinimalRadius();
	
	/**
	 * Constant representing the speed of the light
	 */
	private final double SPEEDOFLIGHT = 300000;
	
	/**
	 * The world the object lives in
	 */
	private World world;
	
	
	/**
	 * Initialization of a circular object (ship or a bullet) with a given position, velocity and radius
	 *	@param xCoordinate
	*	The xCoordinate of the object
	*	@param yCoordinate
	*	The yCoordinate of the object
	*	@param xVelocity
	*	The horizontal velocity of the object
	*	@param yVelocity
	*	The vertical velocity of the object
	*	@param radius
	*	The radius of the object
	 */
	
	public CircularObject (double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		if(!position.isValidPosition(xCoordinate) || !position.isValidPosition(yCoordinate) ||  !velocity.isValidVelocity(xVelocity) || !velocity.isValidVelocity(yVelocity) || (!isValidRadius(radius))) {
			throw new IllegalArgumentException("Error. One or more values are not correct");
		}
	//using position and velocity classes	
	this.position.setPosition(xCoordinate,yCoordinate);
	this.velocity.setVelocity(xVelocity, yVelocity);
	
	this.setRadius(radius);
	
	}
	
	///
	///WORLD
	///
	
	/**
	 * Get the world the object lives in.
	 * @return the world where the object belongs to.
	 * 			|result == World
	 */
	public World getWorld(){
		return world;
	}
	
	/**
	 * Add an object to a given world
	 * 
	 */
	public void addToWorld(World world){
		this.world = world;
	}
	
	
	///
	///RADIUS
	///
	/**
	 * Get the radius of the circular object
	 * @return the radius is returned
	 * 			|result == this.radius
	 */
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Check if the given radius is valid. For a ship it should be larger than 10, for a bullet larger than 1
	 * @param radius
	 * @return The validity of the radius
	 */
	public boolean isValidRadius(double radius){
		return radius > getMinimalRadius();
	}
	
	/**
	 * Set the given radius to the radius of the circular object
	 * @param radius
	 * @post The given radius is set to the radius of the object
	 * 			|this.radius = radius
	 * @throws IllegalArgumentException if the the radius is not valid
	 */
	public void setRadius(double radius){
		if (isValidRadius(radius) == false) throw new IllegalArgumentException("Invalid radius detected.");
		this.radius = radius;
	}
	
	
	
	
	
	///
	///OTHER METHODS
	//
	/**
	 * 	Change the position of the space entity based on the current position, velocity and a duration
	 *	@param duration
	 *	The time in which the circular object moves
	 *	@post The position is set to the new position
	 *			|new.getPosition() == (newPosX, newPosY)
	 *	@throws IllegalArgumentException
	 *			The duration must be larger than zero
	 *			|duration < 0
	 */
	public void move(double duration) throws IllegalArgumentException {
			if (duration < 0) throw new IllegalArgumentException("The duration must be greater than zero");
			double[] currentPos = this.position.getPositionArray();
			double[] currentVel = this.velocity.getVelocityArray();
			double newPosX = currentPos[0] + (currentVel[0] * duration);
			double newPosY = currentPos[1] + (currentVel[1] * duration);
			this.position.setPosition(newPosX, newPosY);
	}
}
