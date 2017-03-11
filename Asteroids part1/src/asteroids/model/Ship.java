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
	*	Variable representing the angle of the ship
	*/
	private double angle;
	
	/**
	*	Variable representing the direction of the ship
	*/
	private double direction;
	
	/**
	*	Constant representing the speed of the light
	*/
	private final double SPEEDOFLIGHT = 30000;
	
	/**
	* 	Constant representing the minimum value of the radius of each created ship
	*/
	private final double MINIMUMRADIUS = 10;
	
	
	/**
	*	Initialization of a new ship with given position in x and y coordinates, horizontal and vertical velocity, the direction, radius and angle.
	*
	*	@param xCoordinate
	*	The xCoordinate of the ship
	*	@param YCoordinate
	*	The YCoordinate of the ship
	*	@param xVelocity
	*	The horizontal velocity of the ship
	*	@param yVelocity
	*	The vertical velocity of the ship
	*	@param direction
	*	The direction of the ship
	*	@param radius
	*	The radius of the ship
	*	@param angle
	*	The angle of the ship
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
	*/
	
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius, double direction) throws IllegalArgumentException{
		if(Double.isNaN(xCoordinate)|| Double.isNaN(yCoordinate) ||  Double.isNaN(xVelocity) ||Double.isNaN(yVelocity) ||Double.isNaN(radius) || Double.isNaN(direction)||(radius <= MINIMUMRADIUS))
		{
			throw new IllegalArgumentException();
		}
		
		
		
		this.setPosition(xCoordinate,yCoordinate);
		this.setVelocity(xVelocity, yVelocity);
		this.position = this.getPosition();
		this.velocity = this.getVelocity();
		assert radius >= 10;
		this.radius = radius;
		this.direction = direction;
	}
	
	/**
	*	Initialization of a default ship with default values, i.e. x and y coordinates are zero as well as the vertical and horizontal velocity.
	*	The radius is a unit circle (1) and the direction is pointed to zero degrees. 
	*/
	public Ship(){
		this.setPosition(0,0);
		this.setVelocity(0,0);
		this.position = this.getPosition();
		this.velocity = this.getVelocity();
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
	 * Return the angle of the ship.
	 * @return The angle of the ship is returned
	 * 			|result == this.angle
	 */
	@Basic 
	public double getAngle() {
		return this.angle;
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
	 * Assign the given x-coordinate and y-coordinate to the x-coordinate and y-coordinate of a ship of the ship.
	 * @param x
	 * 		The value of x which will be assigned to the x-coordinate of the ship
	 * @param y
	 * 		The value of y which will be assigned to the y-coordinate of the ship
	 * @Post |new.getPosition() == {x,y}
	 */
	@Basic
	public void setPosition(double x, double y) {
		double[] pos = {x,y};
	    this.position = pos;
	
	}
	
	
	
	/** 
	 * Assign the given x and y-velocity to the x and y-velocity of the ship.
	 * @param xvelocity
	 * 		The value of xvel which will be assigned to the xvelocity of the ship
	 * @param yvelocity
	 * 		The value of yvel which will be assigned to the yvelocity of the ship
	 * @Post |new.getVelocity() == {xvel, yvel}
	 */
	@Basic
	public void setVelocity(double xvel, double yvel) {
		double[] vel = {xvel,yvel};
	    this.velocity = vel;
	
	}
	
	/**
	* Assign the given direction to the direction of the ship.
	 * @param direction
	 * 		The value of the direction which will be assigned to the direction of the ship
	 * @Pre The given direction is a valid value, i.e. it is between 0 and 2*PI
	 * @Post |new.getDirection() == direction
	*/
	@Basic
	public void setDirection(double direction)
		{
			assert(direction < 2*Math.PI && direction >= 0);
			this.direction = direction;
		}
		
		
	/**
	* Assign the given angle to the angle of the ship.
	 * @param angle
	 * 		The value of the angle which will be assigned to the angle of the ship
	 * @Post |new.getAngle() == angle
	*/
	@Basic
	public void setAngle(double angle)
		{
			this.angle = angle;
		}
		
		
	/**
	* Assign the given radius to the radius of the ship.
	 * @param angle
	 * 		The value of the radius which will be assigned to the radius of the ship
	 * @Post |new.getRadius() == radius
	*/
	@Basic
	public void setRadius(double radius) throws IllegalArgumentException {
			if (radius < MINIMUMRADIUS) throw new IllegalArgumentException("Radius is not valid!");
			this.radius = radius;
		}
			
		
	/**
	* Get the complete speed of the ship.
	* @post |new.getSpeed == speed
	*/


	public double getSpeed(){
		double speed = Math.sqrt((this.getVelocity()[0]* this.getVelocity()[0])+(this.getVelocity()[1]*this.getVelocity()[1]));
		 assert speed <= SPEEDOFLIGHT;
		 return speed;
	}
	
	/**
	 * 	Change the position of the spaceship based on the current position, velocity and a duration
	 *	@param duration
	 *	The time in which the ship moves
	 *	@post The position is set to the new position
	 *			|new.getPosition() == 
	 */
	public void move(double duration) throws IllegalArgumentException {
			if (duration < 0) throw new IllegalArgumentException("The given duration is not valid");
			double[] currentpos = this.getPosition();
			double[] currentvel = this.getVelocity();
			double newposx = currentpos[0] + (currentvel[0] * duration);
			double newposy = currentpos[1] + (currentvel[1] * duration);
			setPosition(newposx,newposy);
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


	public void turn(double givenangle){
		double newdirection = this.direction + givenangle;
		assert(newdirection < 2*Math.PI && newdirection >= 0);
		this.direction = newdirection;

	}
	
	
	/**
	 * Change the velocity of the ship based on a the orientation, a given amount and the current velocity
	 * @param amount
	 * 		The total amount of acceleration
	 * @pre amount > = 0
	 * @post Both velocities are changed based on the given amount, the current acceleration and the direction.
	 * 		|new.getVelocity = {newxvelocity,newyvelocity}
	 * @post The new velocity is not larger than the lightspeed
	 */
	public void thrust(double amount){
		if (amount < 0){
			amount = 0;
		}
		
		double newxvelocity = (this.getVelocity()[0] + (amount*Math.cos(this.direction)));
		double newyvelocity = (this.getVelocity()[1] + (amount*Math.sin(this.direction)));
		double newspeed = Math.sqrt((newxvelocity * newxvelocity) + (newyvelocity * newyvelocity));
		
		if (newspeed > SPEEDOFLIGHT){
			newxvelocity = Math.cos(this.getDirection()) * SPEEDOFLIGHT;
			newyvelocity = Math.sin(this.getDirection()) * SPEEDOFLIGHT;
		}
		this.setVelocity(newxvelocity,newyvelocity);
		
		
	}
	
	/**
	 * Returns the difference in positions (both x and y) between the ship and ship2, we don't throw an IllegalArgumentException
	 * since we already did that in the method that calls this method. 
	 * @param ship2 A ship named ship2
	 * @return Returns the difference in positions as an array
	 */
	private double[] getDifferenceInPositions(Ship ship2) {
		
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
	 */
	private double[] getDifferenceInVelocity(Ship ship2) {
		
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
	 * 		  ship2 is not created
	 * 		  |ship2 == null
	 */
	public double getDistanceBetween(Ship ship2) throws IllegalArgumentException{
		
		if (ship2 == null) throw new IllegalArgumentException("Ship2 does not exist!");
		
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
	 * 		  | if (this == ship2) return true
	 * @return If this ship is a different ship than ship2, then true is returned if the distance between them is negative,
	 * false if the distance between them is positive.
	 * 		  |this.getDistanceBetween(ship2) < 0
	 * @throws IllegalArgumentException
	 *		  ship2 is not created
	 * 		  |ship2 == null
	 */
	public boolean overlap(Ship ship2) throws IllegalArgumentException{
		
		if (ship2 == null) throw new IllegalArgumentException("Ship2 does not exist!");
		
		if (this == ship2) return true;
		else return this.getDistanceBetween(ship2) < 0;
	}
	
	/**
	 * If 2 ships will ever collide, returns the amount of seconds until that collision. If 2 ships
	 * will never collide with each other, Double.POSITIVE_INFINITY is returned. Additionally, a ship
	 * can never collide with itself.
	 * @param ship2 A ship named ship2.
	 * @return If this ship and ship2 are the same, Double.POSITIVE_INFINITY is returned
	 * @return If 
	 * @throws IllegalArgumentException
	 *		  ship2 is not created
	 * 		  |ship2 == null
	 */
	public double getTimeToCollision(Ship ship2) throws IllegalArgumentException{
		if (ship2 == null) throw new IllegalArgumentException("Ship2 does not exist!");
		
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
		
		double d = Math.pow(inproductVandR, 2) - (inproductVandV) * (inproductRandR - Math.pow(sumOfRadiusses, 2));
		
		if (inproductVandR >= 0) {
			return Double.POSITIVE_INFINITY;
		}else if (d <= 0) {
			return Double.POSITIVE_INFINITY;
		}else{
			double deltaT = - (inproductVandR + Math.sqrt(d)) / inproductVandV;
			return deltaT;
		}
	}
	/**
	 * This function calculates the position at which 2 ships will collide, if ever they will collide. This function does not apply to overlapping ships.
	 * @param ship2 A ship named ship2
	 * @return If the 2 ships never collide, null will be returned
	 * @return If the 2 ships will collide, an array containing the x and y coordinate of the collision is returned
	 * @throws IllegalArgumentException
	 *		  This ship and ship2 overlap or ship2 does not exist.
	 * 		  |this.overlap(ship2)
	 * 		  |ship2 == null
	 */
	public double[] getCollisionPosition(Ship ship2) throws IllegalArgumentException{
		
			if (ship2 == null) throw new IllegalArgumentException("Ship2 does not exist!");
			
			if (this.overlap(ship2)) throw new IllegalArgumentException("These ships overlap!");
			
			double timeToCollision = getTimeToCollision(ship2);
			
			if (timeToCollision == Double.POSITIVE_INFINITY) return null;
			
			double[] positionThisShip = this.getPosition();
			double[] velocityThisShip = this.getVelocity();
			
			double xPositionCollision = positionThisShip[0] + velocityThisShip[0] * velocityThisShip[0];
			double yPositionCollision = positionThisShip[1] + velocityThisShip[1] * velocityThisShip[1];
			
			return new double[] {xPositionCollision, yPositionCollision};
		
	}
}