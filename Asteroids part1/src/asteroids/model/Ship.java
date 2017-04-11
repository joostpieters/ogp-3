package asteroids.model;



import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Ship extends CircularObject{
	
/**
 * Class which represents the implementation of the ship.
 * 
 * 
 * @author Kevin Van der Schueren en Steven Zegers
 * @version Part 1
 */
	/**
	 * Variable representing the position of the ship
	 */
	public Position position = new Position(0,0);

	/**
	*	Variable representing the velocity of the ship
	*/
	public Velocity velocity = new Velocity(0,0);
	

	/**
	*	Variable representing the direction of the ship
	*/
	private double direction;
	
	/**
	 * 	Variable representing the mass of the ship
	 */
	private double mass;
	
	
	/**
	*	Constant representing the speed of the light
	*/
	private final double SPEEDOFLIGHT = 300000;
	

	/**
	 * Constant representing the minimal radius of a bullet
	 */
	private final double MINIMUMSHIPRADIUS = 10;
	
	/**
	* 	Constant representing the minimum density of the radius of each created ship
	*/
	private final double DENSITY = 1.42*(10^12);
	
	/**
	 * The state of the thruster
	 */
	private boolean thruster;
	
	/**
	 * The collection of bullets a ship has
	 */
	private Set<Bullet> bulletsCollection = new HashSet<Bullet>();

	
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
	
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius, double direction, double mass) 
			throws IllegalArgumentException{
				super(xCoordinate, yCoordinate, xVelocity, yVelocity, radius);
				
		this.setDirection(direction);
		this.setMass(mass);
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
	 * Get the minimal radius of the ship (10)
	 * @return The minimal radius of the bullet
	 * 			|result == MINIMUMBULLETRADIUS
	 */
	public double getMinimalRadius(){
		return MINIMUMSHIPRADIUS;
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
	 * Set the mass of the ship
	 * @param mass
	 * 		the mass which will be assigned to the ship
	 */
	public void setMass(double mass){
		double masswithformula = (4/3)*Math.pow(this.getRadius(),3)*DENSITY;
		if (mass >= masswithformula){
			this.mass = mass;
		}
		else
			this.mass = masswithformula;
	}
	
	
	
	/**
	 * Get the total mass of the ship i.e. the bullets + the ship
	 * @return The total mass
	 * 			|result = shipMass + massOfBullets;
	 */
	public double getMass(){
		double shipMass = this.mass;
		double massOfBullets = 0;
		for (Bullet bullet : this.getBulletsOfShip())
			massOfBullets += bullet.getMass();
		
		return (shipMass + massOfBullets);
	}
	
	
	
	/**
	 * Get a list of bullets of the ship
	 * @return the list of bullets
	 * 			|result == bulletsCollection
	 */
	public Set<Bullet> getBulletsOfShip(){
		
		return this.bulletsCollection;
	}
	
	
	
			
	/**
	* Get the complete speed of the ship.
	* @post |new.getSpeed == speed
	*/
	public double getSpeed() {
		double speed = Math.sqrt((this.velocity.getXVelocity()* this.velocity.getXVelocity())+(this.velocity.getYVelocity()*this.velocity.getYVelocity()));
		assert speed <= SPEEDOFLIGHT;
		return speed;
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
	 * Enable the thruster
	 */
	public void thrustOn(){
		thruster = true;
	}
	
	
	/**
	 * Disable the thruster
	 */
	public void thrustOff(){
		thruster = false;
	}
	
	public boolean checkThrusterStatus(){
		return thruster;
	}
	
	/**
	 * Calculate the acceleration
	 * @Pre only if the thruster is enabled, there is an acceleration
	 * @return the amount of acceleration
	 */
	public double getAcceleration(){
		double acceleration = 0;
		if (checkThrusterStatus() == false){
			acceleration = 0;
		}
		else{
		
			double F = 1.1*Math.pow(10, 21);
			acceleration = F/getMass();
		}
		return acceleration;
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
		
		double newXVelocity = (this.velocity.getXVelocity() + (amount*Math.cos(this.direction)));
		double newYVelocity = (this.velocity.getYVelocity() + (amount*Math.sin(this.direction)));
		double newSpeed = Math.sqrt((newXVelocity * newXVelocity) + (newYVelocity * newYVelocity));
		
		if (newSpeed > SPEEDOFLIGHT) {
			newXVelocity = Math.cos(this.getDirection()) * SPEEDOFLIGHT;
			newYVelocity = Math.sin(this.getDirection()) * SPEEDOFLIGHT;
		}
		this.velocity.setVelocity(newXVelocity,newYVelocity);	
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
		
		double[] differenceInPositions = this.position.getDifferenceInPositions(ship2.position);
		
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
		
		double[] differenceInPositions = this.position.getDifferenceInPositions(ship2.position);
		double[] differenceInVelocities = this.velocity.getDifferenceInVelocity(ship2.velocity);
		
		Vector deltaR = new Vector(differenceInPositions);
		Vector deltaV = new Vector(differenceInVelocities);
		
		double inproductRandR = deltaR.dotProductVectors(deltaR);
		double inproductVandV = deltaV.dotProductVectors(deltaV);
		double inproductVandR = deltaV.dotProductVectors(deltaR);
		
		double d = Math.pow(inproductVandR, 2) - inproductVandV * (inproductRandR - Math.pow(sumOfRadiusses, 2));
		
		if (inproductVandV == 0) {
			return Double.POSITIVE_INFINITY;
		} else if (inproductVandR >= 0) {
			return Double.POSITIVE_INFINITY;
		} else if (d <= 0) {
			return Double.POSITIVE_INFINITY;
		} else {
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
		
		double[] positionThisShip = this.position.getPositionArray();
		double[] velocityThisShip = this.velocity.getVelocityArray();
		double[] positionShip2 = ship2.position.getPositionArray();
		double[] velocityShip2 = ship2.velocity.getVelocityArray();
		
		double xPositionCollisionThisShip = positionThisShip[0] + velocityThisShip[0] * timeToCollision;
		double yPositionCollisionThisShip = positionThisShip[1] + velocityThisShip[1] * timeToCollision;
		
		double xPositionCollisionShip2 = positionShip2[0] + velocityShip2[0] * timeToCollision;
		double yPositionCollisionShip2 = positionShip2[1] + velocityShip2[1] * timeToCollision;
		
		double slope = Math.atan2(yPositionCollisionShip2 - yPositionCollisionThisShip, xPositionCollisionShip2 - xPositionCollisionThisShip);
			
		double xPositionCollision = xPositionCollisionThisShip + Math.cos(slope) * this.getRadius();
		double yPositionCollision = yPositionCollisionThisShip + Math.sin(slope) * this.getRadius();
			
		return new double[] {xPositionCollision, yPositionCollision};
	}
}
