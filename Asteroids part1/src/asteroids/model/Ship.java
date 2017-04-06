package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

public class Ship{
	
/**
 * Class which represents the implementation of the ship.
 * 
 * 
 * @author Kevin Van der Schueren en Steven Zegers
 * @version Part 1
 */
	/**
	*	Variable representing the position of the ship in an array of length 2
	*/
	private double[] position = new double[2];

	/**
	*	Variable representing the velocity of the ship in an array of length 2
	*/
	private double[] velocity = new double[2];
	/**
	*	Variable representing the radius of the ship
	*/
	private double radius;
	

	/**
	*	Variable representing the direction of the ship
	*/
	private double direction;
	
	/**
	*	Constant representing the speed of the light
	*/
	private final double SPEEDOFLIGHT = 300000;
	
	/**
	* 	Constant representing the minimum value of the radius of each created ship
	*/
	private final double MINIMUMRADIUS = 10;
	
	
	/**
	*	Initialization of a new ship with given position in x and y coordinates, horizontal and vertical velocity, the direction and radius.
	*
	*	@param xCoordinate
	*	The xCoordinate of the ship
	*	@param yCoordinate
	*	The yCoordinate of the ship
	*	@param xVelocity
	*	The horizontal velocity of the ship
	*	@param yVelocity
	*	The vertical velocity of the ship
	*	@param direction
	*	The direction of the ship
	*	@param radius
	*	The radius of the ship
	*	@Pre	The radius needs to be => 10
	* 			|new.getRadius >=10
	*	@Post  The given xCoordinate and yCoordinate is assigned to the position of the ship.
	*			|new.getPosition = {xCoordinate,yCoordinate}
	*	@Post  The given x-velocity and y-velocity is assigned to the velocity of the ship.
	*			|new.getVelocity = {xVelocity,yVelocity}
	*	@Post  The given radius, if allowed, is assigned to the radius of the ship.
	*			|new.getRadius = radius
	*	@Post  The given direction is assigned to the direction of the ship.
	*			|new.getDirection = direction
	*	@throws IllegalArgumentException
	 *		  One of the parameters is not valid i.e. not a number or the radius is invalid
	 * 		  |(Double.isNaN(x)|| Double.isNaN(y) ||  Double.isNaN(xVelocity) ||Double.isNaN(yVelocity) ||Double.isNaN(radius) || Double.isNaN(direction)||(radius <= MINIMAL_RADIUS))
	*/
	
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius, double direction) 
			throws IllegalArgumentException{
		if(Double.isNaN(xCoordinate) || Double.isNaN(yCoordinate) ||  Double.isNaN(xVelocity) ||Double.isNaN(yVelocity) 
				|| Double.isNaN(radius) || Double.isNaN(direction)|| (radius <= MINIMUMRADIUS)) {
			throw new IllegalArgumentException();
		}
		this.setPosition(xCoordinate,yCoordinate);
		this.setVelocity(xVelocity, yVelocity);
		this.position = this.getPosition();
		this.velocity = this.getVelocity();
		this.radius = radius;
		this.direction = direction;
	}
	
	/**
	*	Initialization of a default ship with default values, i.e. x and y coordinates are zero as well as the vertical and horizontal velocity.
	*	The radius is a unit circle (1) and the direction is pointed to zero degrees. 
	*/
	public Ship() {
		this.setPosition(0,0);
		this.setVelocity(0,0);
		this.radius = 1;
		this.direction = 0;
	}
	
	/** 
	 * Return the position of the ship in an array of both coordinates.
	 * @return The array with coordinates is returned
	 * 			|result == this.position
	 */
	@Basic 
	public double[] getPosition() {
		return this.position;
	}
	
	/** 
	 * Return the velocity of the ship in an array of x and y velocity.
	 * @return The array with both velocities is returned
	 * 			|result == this.velocity
	 */
	@Basic 
	public double[] getVelocity() {
		return this.velocity;
	}
	
	/** 
	 * Return the radius of the ship.
	 * @return The radius of the ship is returned
	 * 			|result == this.radius
	 */
	@Basic 
	public double getRadius() {
		return this.radius;
	}
	

	
	/** 
	 * Return the direction of the ship.
	 * @return The direction of the ship is returned
	 * 			|result == this.direction
	 */
	@Basic 
	public double getDirection() {
		return this.direction;
	}
	
	/** 
	 * Assign the given x-coordinate and y-coordinate to the x-coordinate and y-coordinate of the ship.
	 * @param x
	 * 		The value of x which will be assigned to the x-coordinate of the ship
	 * @param y
	 * 		The value of y which will be assigned to the y-coordinate of the ship
	 * @Post |new.getPosition() == {x,y}
	 */
	@Basic
	public void setPosition(double x, double y) {
		if (isValidPosition(x,y) == false) throw new IllegalArgumentException("The given position is not valid."); 
		double[] pos = {x,y};
	    this.position = pos;
	}
	
	/**
	 * Check if the given coordinates are valid to be assigned to the position.
	 * @param  x
	 *			The x-coordinate of the given position
	 * @param y
	 * 			The y-coordinate of the given position
	 * @return Returns if the position is valid.
	 *         | result = (Double.isNaN(x)||(Double.isNaN(y))
	 */
	
	public boolean isValidPosition(double x, double y) {
		if (Double.isNaN(x) || Double.isNaN(y))
		{
			return false;
		}
		else 
			return true;
	}
	
	/** 
	 * Assign the given x-velocity and y-velocity to the x-velocity and y-velocity of the ship.
	 * @param xvelocity
	 * 		The value of xVel which will be assigned to the x-velocity of the ship
	 * @param yvelocity
	 * 		The value of yVel which will be assigned to the y-velocity of the ship
	 * @Post |new.getVelocity() == {xVel, yVel}
	 */
	@Basic
	public void setVelocity(double xVel, double yVel) {
		if (isValidVelocity(yVel, yVel) == false){
			return;
		}
		double[] vel = {xVel,yVel};
	    this.velocity = vel;
	}
	
	/**
	 * Check if the given coordinates are valid to be assigned to the velocity.
	 * @param  x
	 *			The x-coordinate of the given velocity
	 * @param y
	 * 			The y-coordinate of the given velocity
	 * @return Returns if the position is valid.
	 *         | result = (Double.isNaN(x)||(Double.isNaN(y))
	 */
	
	public boolean isValidVelocity(double xVel, double yVel) {
		if (Double.isNaN(xVel) || Double.isNaN(yVel))
		{
			return false;
		}
		else 
			return true;
	}
	
	/**
	* Assign the given direction to the direction of the ship.
	 * @param direction
	 * 		The value of the direction which will be assigned to the direction of the ship
	 * @Pre The given direction is a valid value, i.e. it is between 0 and 2*PI
	 * 		|isValidDirection == true
	 * @Post The current direction is set to the given direction.
	 * 		|new.getDirection() == direction
	*/
	@Basic
	public void setDirection(double direction) {
			isValidDirection(direction);
			this.direction = direction;
		}
		
	/**
	 * Check if the direction is valid
	 * @param  direction
	 *			The direction that needs verification
	 * @return Returns if the direction is valid.
	 *         | result = ((direction < 2*Math.PI && direction >= 0))
	 */
	public boolean isValidDirection(double direction){
		return (direction < 2*Math.PI && direction >= 0);
	}
	
	
	/**
	* Assign the given radius to the radius of the ship.
	 * @param angle
	 * 		The value of the radius which will be assigned to the radius of the ship
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
	* Get the complete speed of the ship.
	* @post |new.getSpeed == speed
	*/
	public double getSpeed() {
		double speed = Math.sqrt((this.getVelocity()[0]* this.getVelocity()[0])+(this.getVelocity()[1]*this.getVelocity()[1]));
		assert speed <= SPEEDOFLIGHT;
		return speed;
	}
	
	/**
	 * 	Change the position of the spaceship based on the current position, velocity and a duration
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
			double[] currentPos = this.getPosition();
			double[] currentVel = this.getVelocity();
			double newPosX = currentPos[0] + (currentVel[0] * duration);
			double newPosY = currentPos[1] + (currentVel[1] * duration);
			setPosition(newPosX,newPosY);
	}
	
	/**
	 * Turn the ship i.e. move the direction
	 * @Pre The angle is valid. This means the new direction is still between 0 and 2*PI
	 * @param givenangle
	 * 		The angle the ship has to turn
	 * @post
	 * 		The new direction is the old direction + the given angle
	 * 		|new.getDirection == this.setDirection(this.getDirection() + givenangle)
	 *
	 */
	public void turn(double givenAngle) {
		double newDirection = this.direction + givenAngle;
		setDirection(newDirection);
	}
	
	/**
	 * Change the velocity of the ship based on a the orientation, a given amount and the current velocity
	 * @param amount
	 * 		The total amount of acceleration
	 * @Pre amount > = 0
	 * @post Both velocities are changed based on the given amount, the current acceleration and the direction.
	 * 		|new.getVelocity = {newXVelocity,newYVelocity}
	 * @post The new velocity is not larger than the SPEEDOFLIGHT
	 */
	public void thrust(double amount) {
		if (amount < 0) {
			amount = 0;
		}
		
		double newXVelocity = (this.getVelocity()[0] + (amount*Math.cos(this.direction)));
		double newYVelocity = (this.getVelocity()[1] + (amount*Math.sin(this.direction)));
		double newSpeed = Math.sqrt((newXVelocity * newXVelocity) + (newYVelocity * newYVelocity));
		
		if (newSpeed > SPEEDOFLIGHT) {
			newXVelocity = Math.cos(this.getDirection()) * SPEEDOFLIGHT;
			newYVelocity = Math.sin(this.getDirection()) * SPEEDOFLIGHT;
		}
		this.setVelocity(newXVelocity,newYVelocity);	
	}
	
	/**
	 * Returns the difference in positions (both x and y) between the ship and ship2.
	 * @param ship2 A ship named ship2
	 * @return Returns the difference in positions as an array
	 * @throws IllegalArgumentException
	 * 			ship2 is not created
	 * 			|ship2 == null
	 */
	private double[] getDifferenceInPositions(Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("getDifferenceInPositions called with a non-existing ship!");
		double[] positionThisShip = this.getPosition();
		double[] positionShip2 = ship2.getPosition();
		
		double[] differenceInPositions = {positionThisShip[0] - positionShip2[0], positionThisShip[1] - positionShip2[1]};
		
		return differenceInPositions;
	}
	
	/**
	 * Returns the difference in velocities (both x and y) between the ship and ship2, we don't throw an IllegalArgumentException
	 * since we already did that in the method that calls this method. 
	 * @param ship2 A ship named ship2
	 * @return Returns the difference in velocities as an array
	 * @throws IllegalArgumentException
	 * 			ship2 is not created
	 * 			|ship2 == null
	 */
	private double[] getDifferenceInVelocity(Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("getDifferenceInVelocity called with a non-existing ship!");
		double[] velocityThisShip = this.getVelocity();
		double[] velocityShip2 = ship2.getVelocity();
		
		double[] differenceInVelocity = {velocityThisShip[0] - velocityShip2[0], velocityThisShip[1] - velocityShip2[1]};
		
		return differenceInVelocity;
	}
	
	/**
	 * This method calculates the minimum amount of distance that our ship should travel in order to be adjacent to ship2.
	 * If the ships overlap, the distance will be negative, if the 2 ships are the same the returned value will be 0.
	 * If the ships don't overlap and aren't the same, a positive value will be returned
	 * 
	 * @param ship2 A ship named ship2 of which we want to know how far away it is from our ship.
	 * @return Returns the distance between the ship and another ship, named ship2
	 * 		  |distanceBetweenCenters - radiusShip1 - radiusShip2
	 * @return If the 2 ships are the same, return 0
	 * @throws IllegalArgumentException
	 * 			ship2 is not created
	 * 			|ship2 == null
	 */
	public double getDistanceBetween(Ship ship2) throws IllegalArgumentException {
		
		if (ship2 == null) throw new IllegalArgumentException("getDistanceBetween called with a non-existing ship!");
		if (this == ship2) {
			return 0;
		}
		
		double radiusShip1 = this.getRadius();
		double radiusShip2 = ship2.getRadius();
		
		double[] differenceInPositions = this.getDifferenceInPositions(ship2);
		
		double squaredDifferenceInX = Math.pow(differenceInPositions[0], 2);
		double squaredDifferenceInY = Math.pow(differenceInPositions[1], 2);
		
		double distanceBetweenCenters = Math.sqrt(squaredDifferenceInX + squaredDifferenceInY);
		return distanceBetweenCenters - radiusShip1 - radiusShip2;
	}
	/**
	 * This method returns true of false, depending if the ships overlap or not. 
	 * As explained for the function getDistanceBetween, 2 ships that aren't the same overlap when the
	 * distance between them is negative, then true will be returned. If two ships are the same, 
	 * by convention they overlap. 
	 * 
	 * @param ship2 A ship named ship2.
	 * @return If this ship is the same as ship2, then true is returned
	 * 			|if (this == ship2) return true
	 * @return If this ship is a different ship than ship2, then true is returned if the distance between them is negative,
	 * false if the distance between them is positive.
	 * 			|this.getDistanceBetween(ship2) < 0
	 * @throws IllegalArgumentException
	 *			ship2 is not created
	 *			|ship2 == null
	 */
	public boolean overlap(Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("Overlap called with a non-existing ship!");
		
		if (this == ship2) return true;
		else return this.getDistanceBetween(ship2) <= 0;
	}
	
	/**
	 * If 2 ships will ever collide, returns the amount of seconds until that collision. If 2 ships
	 * will never collide with each other, Double.POSITIVE_INFINITY is returned. Additionally, a ship
	 * can never collide with itself. As this method does not apply to overlapping ships, an exception
	 * is thrown if the ships overlap.
	 * 
	 * @param ship2 A ship named ship2.
	 * @return If this ship and ship2 are the same, Double.POSITIVE_INFINITY is returned
	 * @return If the 2 ships never collide, Double.POSITIVE_INFINITY is returned
	 * 			|if (inproductVandV == 0)|if (inproductVandR >= 0)|if (d <= 0)
	 * @return 
	 * 			If there is in fact a point and time for which the 2 ships collide, the amount of time until that moment is returned
	 * 			|return -(inproductVandR + Math.sqrt(d)) / inproductVandV
	 * @throws IllegalArgumentException
	 *			ship2 is not created or this ship and ship2 overlap
	 *			|ship2 == null || this.overlap(ship2)
	 */
	public double getTimeToCollision(Ship ship2) throws IllegalArgumentException{
		if (ship2 == null) throw new IllegalArgumentException("getTimeToCollision called with a non-existing ship!");
		if (this.overlap(ship2)) throw new IllegalArgumentException("These two ships overlap!");
		if (this == ship2) {
			return Double.POSITIVE_INFINITY;
		}
		
		double radiusThisShip = this.getRadius();
		double radiusShip2 = ship2.getRadius();
		double sumOfRadiusses = radiusThisShip + radiusShip2;
		
		double[] differenceInPositions = this.getDifferenceInPositions(ship2);
		double[] differenceInVelocities = this.getDifferenceInVelocity(ship2);
		
		double deltaX = differenceInPositions[0];
		double deltaY = differenceInPositions[1];
		
		double deltaVx = differenceInVelocities[0];
		double deltaVy = differenceInVelocities[1];
		
		double inproductRandR = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
		double inproductVandV = Math.pow(deltaVx, 2) + Math.pow(deltaVy, 2);
		double inproductVandR = deltaVx * deltaX + deltaVy * deltaY;
		
		double d = Math.pow(inproductVandR, 2) - inproductVandV * (inproductRandR - Math.pow(sumOfRadiusses, 2));
		
		if (inproductVandV == 0) {
			return Double.POSITIVE_INFINITY;
		} else if (inproductVandR >= 0) {
			return Double.POSITIVE_INFINITY;
		} else if (d <= 0) {
			return Double.POSITIVE_INFINITY;
		} else{
			return - (inproductVandR + Math.sqrt(d)) / inproductVandV;
		}
	}
	
	/**
	 * This function calculates the position at which 2 ships will collide, if ever they will collide. This function does not apply to overlapping ships.
	 * @param ship2 A ship named ship2
	 * @return If the 2 ships never collide, null will be returned
	 * @return If the 2 ships will collide, an array containing the x and y coordinate of the collision is returned
	 * @throws IllegalArgumentException
	 *			This ship and ship2 overlap or ship2 does not exist.
	 *			|this.overlap(ship2) || ship2 == null
	 */
	public double[] getCollisionPosition(Ship ship2) throws IllegalArgumentException {	
		if (ship2 == null) throw new IllegalArgumentException("getCollisionPosition called with a non-existing ship!");
		if (this.overlap(ship2)) throw new IllegalArgumentException("These two ships overlap!");
		double timeToCollision = getTimeToCollision(ship2);
			
		if (timeToCollision == Double.POSITIVE_INFINITY) return null;

		double[] positionThisShip = this.getPosition();
		double[] velocityThisShip = this.getVelocity();
		double[] positionShip2 = ship2.getPosition();
		double[] velicityShip2 = ship2.getVelocity();
		
		double slope = Math.atan2(positionShip2[1]-positionThisShip[1], positionShip2[0] - positionThisShip[0]);
		
			
		double xPositionCollision = positionThisShip[0] + velocityThisShip[0] * timeToCollision + Math.cos(slope) * this.getRadius();
		double yPositionCollision = positionThisShip[1] + velocityThisShip[1] * timeToCollision + Math.sin(slope) * this.getRadius();
			
		return new double[] {xPositionCollision, yPositionCollision};
		
	}
}
