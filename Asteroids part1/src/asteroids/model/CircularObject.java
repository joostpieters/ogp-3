package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class CircularObject {

	/**
	 * Variable representing the position of the ship
	 */
	public Position position = new Position(0,0);

	/**
	*	Variable representing the velocity of the ship
	*/
	public Velocity velocity = new Velocity(0,0);
	/**
	*	Variable representing the radius of the ship
	*/
	private double radius;
	
	/**
	* 	Constant representing the minimum value of the radius of each created ship
	*/
	private final double MINIMUMRADIUS = 10;
	
	/**
	 * Initialisation of a circular object (ship or a bullet) with a given position, radius, velocity and mass
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
	*	@param mass
	*	The mass of the object
	 */
	
	public CircularObject (double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		if (isValidRadius(radius) == false) throw new IllegalArgumentException("The given radius was not valid");
		//if (isValidMass(mass) == false) throw new IllegalArgumentException("The given mass is not valid");
		if(!position.isValidPosition(xCoordinate) || !position.isValidPosition(yCoordinate) ||  !velocity.isValidVelocity(xVelocity) || !velocity.isValidVelocity(yVelocity)) {
			throw new IllegalArgumentException("Not a valid value");
		}
		
	this.position.setPosition(xCoordinate,yCoordinate);
	this.velocity.setVelocity(xVelocity, yVelocity);
	this.radius = radius;
	}
	
	
	
	private boolean isValidRadius(double radius){
		return (radius <= MINIMUMRADIUS);
	}
	
	
	
	/**
	* Assign the given radius to the radius of the entity.
	 * @param angle
	 * 		The value of the radius which will be assigned to the radius of the entity
	 * @Post |new.getRadius() == radius
	 * @throws IllegalArgumentException
	 * 			The radius must be larger than 10, the MINIMUMRADIUS
	 * 			|radius <= MINIMUMRADIUS
	*/
	@Basic
	public void setRadius(double radius) throws IllegalArgumentException {
			if (radius <= MINIMUMRADIUS) throw new IllegalArgumentException("The radius must be larger than 10!");
			this.radius = radius;
		}
			
	
	
	/** 
	 * Return the radius of the object.
	 * @return The radius of the object is returned
	 * 			|result == this.radius
	 */
	@Basic 
	public double getRadius() {
		return this.radius;
	}
	
	
	/**
	 * 	Change the position of the space entity based on the current position, velocity and a duration
	 *	@param duration
	 *	The time in which the ship moves
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
