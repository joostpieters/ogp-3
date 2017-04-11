package asteroids.model;



import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Ship extends CircularObject{
	
/**
 * Class which represents the implementation of the ship.
 * 
 * 		@Invar The position is valid
 * 			|position.isValidPosition(xCoordinate)
 * 			|position.isValidPosition(yCoordinate)
 *	 	
 * 		@Invar The Velocity is valid
 *			|velocity.isValidVelocity(xVelocity) 
 * 			|velocity.isValidVelocity(yVelocity)
 * 
 * 		@Invar The direction is valid
 *			isValidDirection(getDirection()) 
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
	 * Constant representing the thruster force
	 */
	
	private final double THRUSTERFORCE = 1.1E21;
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
		
			
			acceleration = THRUSTERFORCE/getMass();
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
	 * This method calculates the minimum amount of distance that a circular object should travel in order to be adjacent to object2.
	 * If the objects overlap, the distance will be negative, if the objects are the same, the returned value will be 0.
	 * If the objects don't overlap and aren't the same, a positive value will be returned
	 * 
	 * @param object2 A circular object named object2 of which we want to know how far away it is from this object.
	 * @return Returns the distance between this object and another object, named object2
	 * 		  	|distanceBetweenCenters - radiusObject1 - radiusObject2
	 * @return 
	 * 			|if (this == object2) return 0;
	 * @throws IllegalArgumentException
	 * 			object2 is not created
	 * 			|object2 == null
	 */
	public double getDistanceBetween(CircularObject object2) throws IllegalArgumentException {
		
		if (object2 == null) throw new IllegalArgumentException("getDistanceBetween called with a non-existing ship!");
		if (this == object2) return 0;
		
		double radiusObject1 = this.getRadius();
		double radiusObject2 = object2.getRadius();
		
		double[] differenceInPositions = this.position.getDifferenceInPositions(object2.position);
		
		double squaredDifferenceInX = Math.pow(differenceInPositions[0], 2);
		double squaredDifferenceInY = Math.pow(differenceInPositions[1], 2);
		
		double distanceBetweenCenters = Math.sqrt(squaredDifferenceInX + squaredDifferenceInY);
		return distanceBetweenCenters - radiusObject1 - radiusObject2;
	}
	/**
	 * This method returns true of false, depending on if the objects overlap or not. 
	 * As explained for the function getDistanceBetween, 2 circular objects that aren't the same overlap when the
	 * distance between them is negative, then true will be returned. If two ships are the same, 
	 * by convention they overlap. 
	 * 
	 * @param object2 A circular object called object2
	 * @return If this object is the same as object2, then true is returned
	 * 			|if (this == object2) return true
	 * @return If this object is a different circular object than object2, then true is returned if the distance between them is negative,
	 * false if the distance between them is positive.
	 * 			|this.getDistanceBetween(object2) <= 0
	 * @throws IllegalArgumentException
	 *			object2 is not created
	 *			|object2 == null
	 */
	public boolean overlap(CircularObject object2) throws IllegalArgumentException {
		if (object2 == null) throw new IllegalArgumentException("Overlap called with a non-existing ship!");
		
		if (this == object2) return true;
		else return this.getDistanceBetween(object2) <= 0;
	}
	
	/**
	 * If 2 circular objects will ever collide, returns the amount of seconds until that collision. If 2 objects
	 * will never collide with each other, Double.POSITIVE_INFINITY is returned. Additionally, a circular object
	 * can never collide with itself. As this method does not apply to overlapping circular objects, an exception
	 * is thrown if the objects overlap.
	 * 
	 * @param object2 A circular object called object2
	 * @return If this object and object2 are the same, Double.POSITIVE_INFINITY is returned
	 * @return If the 2 circular objects never collide, Double.POSITIVE_INFINITY is returned
	 * 			|if (inproductVandV == 0)|if (inproductVandR >= 0)|if (d <= 0)
	 * @return 
	 * 			If there is in fact a point and time for which the 2 circular objects collide, the amount of time until that moment is returned
	 * 			|return -(inproductVandR + Math.sqrt(d)) / inproductVandV
	 * @throws IllegalArgumentException
	 *			object2 is not created or this circular object and object2 overlap
	 *			|object2 == null || this.overlap(object2)
	 */
	public double getTimeToCollision(CircularObject object2) throws IllegalArgumentException{
		if (object2 == null) throw new IllegalArgumentException("getTimeToCollision called with a non-existing ship!");
		if (this.overlap(object2)) throw new IllegalArgumentException("These two ships overlap!");
		if (this == object2) {
			return Double.POSITIVE_INFINITY;
		}
		
		double radiusThisShip = this.getRadius();
		double radiusShip2 = object2.getRadius();
		double sumOfRadiusses = radiusThisShip + radiusShip2;
		
		double[] differenceInPositions = this.position.getDifferenceInPositions(object2.position);
		double[] differenceInVelocities = this.velocity.getDifferenceInVelocity(object2.velocity);
		
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
	 * This function calculates the position at which the 2 circular objects will collide, if ever they will collide. This function does not apply to overlapping ships.
	 * @param object2 A circular object called object2
	 * @return If the 2 circular objects never collide, null will be returned
	 * 			|if (timeToCollision == Double.POSITIVE_INFINITY) return null
	 * @return If the 2 circular objects will collide, an array containing the x and y coordinate of the collision is returned
	 * 			|return new double[] {xPositionCollisionThisShip + Math.cos(slope) * this.getRadius(), yPositionCollisionThisShip + Math.sin(slope) * this.getRadius()}
	 * @throws IllegalArgumentException
	 *			This object and object2 overlap or object2 does not exist.
	 *			|this.overlap(ship2) || ship2 == null
	 */
	public double[] getCollisionPosition(CircularObject object2) throws IllegalArgumentException {
		if (object2 == null) throw new IllegalArgumentException("getCollisionPosition called with a non-existing ship!");
		if (this.overlap(object2)) throw new IllegalArgumentException("These two ships overlap!");
		double timeToCollision = getTimeToCollision(object2);
			
		if (timeToCollision == Double.POSITIVE_INFINITY) return null;
		
		double[] positionThisShip = this.position.getPositionArray();
		double[] velocityThisShip = this.velocity.getVelocityArray();
		double[] positionShip2 = object2.position.getPositionArray();
		double[] velocityShip2 = object2.velocity.getVelocityArray();
		
		double xPositionCollisionThisShip = positionThisShip[0] + velocityThisShip[0] * timeToCollision;
		double yPositionCollisionThisShip = positionThisShip[1] + velocityThisShip[1] * timeToCollision;
		
		double xPositionCollisionShip2 = positionShip2[0] + velocityShip2[0] * timeToCollision;
		double yPositionCollisionShip2 = positionShip2[1] + velocityShip2[1] * timeToCollision;
		
		double slope = Math.atan2(yPositionCollisionShip2 - yPositionCollisionThisShip, xPositionCollisionShip2 - xPositionCollisionThisShip);
			
		return new double[] {xPositionCollisionThisShip + Math.cos(slope) * this.getRadius(), yPositionCollisionThisShip + Math.sin(slope) * this.getRadius()};
	}
	
	
	public double[] velocityAfterCollision(CircularObject object2) {
		if(object2 instanceof Bullet) {
			
		}
	}
}
