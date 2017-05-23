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
	 * The density of the object
	 */
	public abstract double getDensity();
	
	
	/**
	 * Constant representing the speed of the light
	 */
	private final double SPEEDOFLIGHT = 300000;
	
	/**
	 * The world the object lives in
	 */
	protected World world;
	/**
	 * Variable that indicates whether or not the circular object is terminated
	 */
	private boolean isTerminated = false;
	
	
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
	this.setPosition(xCoordinate,yCoordinate);
	this.setVelocity(xVelocity, yVelocity);
	this.setRadius(radius);
	
	}
	
	
	/**
	 * Method that returns the x and y position of the bullet as an array of length 2
	 * @see implementation
	 */
	public double[] getPositionArray() {
		return this.position.getPositionArray();
	}
	
	/**
	 * Method that returns the x and y position of the bullet as an array of length 2
	 * @see implementation
	 */
	public double[] getVelocityArray() {
		return this.velocity.getVelocityArray();
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
	
	public void setVelocity(double x, double y) {
		//TODO: isValidVelocity and throw exception
		this.velocity.setVelocity(x, y);
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
		return this.world;
	}
	
	/**
	 * Set the world of a circular object
	 * @throws IllegalArgumentException
	 * 			|this.world != null
	 */
	public void setWorld(World world) throws IllegalArgumentException {
		if (this.world != null && world != this.getWorld() && world != null) throw new IllegalArgumentException("This circular object is already part of a world.");
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
			double[] currentPos = this.getPositionArray();
			double[] currentVel = this.getVelocityArray();
			double newPosX = currentPos[0] + (currentVel[0] * duration);
			double newPosY = currentPos[1] + (currentVel[1] * duration);
			this.setPosition(newPosX, newPosY);
	}
	
	
	
	/**
	* Get the complete speed of the object.
	* @post |new.getSpeed == speed
	*/
	public double getSpeed() {
		double speed = Math.sqrt((this.velocity.getXVelocity()* this.velocity.getXVelocity())+(this.velocity.getYVelocity()*this.velocity.getYVelocity()));
		assert speed <= SPEEDOFLIGHT;
		return speed;
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
		
		if (object2 == null) throw new IllegalArgumentException("getDistanceBetween called with a non-existing circular object!");
		if (this == object2) return 0;
		
		double radiusObject1 = this.getRadius();
		double radiusObject2 = object2.getRadius();
		
		double[] differenceInPositions = this.position.getDifferenceInPositions(object2.position);
		
		double squaredDifferenceInX = Math.pow(differenceInPositions[0], 2);
		double squaredDifferenceInY = Math.pow(differenceInPositions[1], 2);
		
		double distanceBetweenCenters = Math.sqrt(squaredDifferenceInX + squaredDifferenceInY);
		return distanceBetweenCenters - (radiusObject1 + radiusObject2);
	}
	/**
	 * Boolean method that returns whether or not 2 circular objects apparently collide
	 * @param object1
	 * @param object2
	 * @return If the distance between the centers of the circular objects is bigger than 99% of the sum of the radiuses and
	 * smaller than 101% of the radiuses of the sum of the radiuses then true is returned, otherwise false is returned
	 * 			|if (0.99 * sumOfRadius < distanceBetweenCenters && distanceBetweenCenters < 1.01 * sumOfRadius) return true;
	 * @throws IllegalArgumentException
	 * 			|object2 == null
	 */
	public boolean apparantlyCollidesWith(CircularObject object2) throws IllegalArgumentException {
		if (object2 == null) throw new IllegalArgumentException("apparantlyCollidesWith called with non-existent circular object.");
		double sumOfRadius = this.getRadius() + object2.getRadius();
		double distanceBetweenCenters = this.getDistanceBetween(object2) + sumOfRadius;
		if (0.99 * sumOfRadius <= distanceBetweenCenters && distanceBetweenCenters <= 1.01 * sumOfRadius) return true;
		return false;
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
	 * 			|this.getDistanceBetween(object2) + this.getRadius() + object2.getRadius()) <= (0.99 * (this.getRadius() + object2.getRadius())
	 * @throws IllegalArgumentException
	 *			object2 is not created
	 *			|object2 == null
	 */
	public boolean overlap(CircularObject object2) throws IllegalArgumentException {
		if (object2 == null) throw new IllegalArgumentException("Overlap called with a non-existing circular object!");
		if (this == object2) return true;
		else return (this.getDistanceBetween(object2) + this.getRadius() + object2.getRadius()) < (0.99 * (this.getRadius() + object2.getRadius()));
	}
	//TODO
	public boolean apparantlyWithinBoundary() {
		return (apparantlyWithinBoundaryX() && apparantlyWithinBoundaryY());
	}
	
	//TODO
	public boolean apparantlyWithinBoundaryX() {
		if (this.getPositionArray()[0] <= 1.01 * this.getRadius()) return false;
		if (this.getWorld().getWorldDimensionArray()[0] - this.getPositionArray()[0] <= 1.01 * this.getRadius()) return false;
		return true;
	}
	
	//TODO
	public boolean apparantlyWithinBoundaryY() {
		if (this.getPositionArray()[1] <= 1.01 * this.getRadius()) return false;
		if (this.getWorld().getWorldDimensionArray()[1] - this.getPositionArray()[1] <= 1.01 * this.getRadius()) return false;
		return true;
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
		if (object2 == null) throw new IllegalArgumentException("getTimeToCollision called with a non-existing circular object!");
		if (this.overlap(object2)) throw new IllegalArgumentException("These two circular objects overlap!");
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
		if (object2 == null) throw new IllegalArgumentException("getCollisionPosition called with a non-existing circular object!");
		if (this.overlap(object2)) throw new IllegalArgumentException("These two circular objects overlap!");
		double timeToCollision = getTimeToCollision(object2);
			
		if (timeToCollision == Double.POSITIVE_INFINITY) return null;
		
		double[] positionThisShip = this.getPositionArray();
		double[] velocityThisShip = this.getVelocityArray();
		double[] positionShip2 = object2.getPositionArray();
		double[] velocityShip2 = object2.getVelocityArray();
		
		double xPositionCollisionThisShip = positionThisShip[0] + velocityThisShip[0] * timeToCollision;
		double yPositionCollisionThisShip = positionThisShip[1] + velocityThisShip[1] * timeToCollision;
		
		double xPositionCollisionShip2 = positionShip2[0] + velocityShip2[0] * timeToCollision;
		double yPositionCollisionShip2 = positionShip2[1] + velocityShip2[1] * timeToCollision;
		
		double slope = Math.atan2(yPositionCollisionShip2 - yPositionCollisionThisShip, xPositionCollisionShip2 - xPositionCollisionThisShip);
			
		return new double[] {xPositionCollisionThisShip + Math.cos(slope) * this.getRadius(), yPositionCollisionThisShip + Math.sin(slope) * this.getRadius()};
	}
	

	/**
	 * Method that returns the time to a first potential collision with a boundary
	 * @return If the object does not belong to a world, Double.POSITIVE_INFINITY is returned, otherwise the minimum time to the first collision is returned
	 * 			|if (this.getWorld() == null) return Double.POSITIVE_INFINITY
	 * 			|Math.min(timeToCollisionX, timeToCollisionY)
	 */
	public double getTimeCollisionBoundary() {
		if (this.getWorld() == null) return Double.POSITIVE_INFINITY;
		double timeToCollisionX = 0;
		double timeToCollisionY = 0;
		
		double positionX = this.getPositionArray()[0];
		double positionY = this.getPositionArray()[1];
		double velocityX = this.getVelocityArray()[0];
		double velocityY = this.getVelocityArray()[1];
		
		double xBoundary = this.getWorld().getWorldDimensionArray()[0];
		double yBoundary = this.getWorld().getWorldDimensionArray()[1];
		
		if (velocityX == 0) timeToCollisionX = Double.POSITIVE_INFINITY;
		if (velocityY == 0) timeToCollisionY = Double.POSITIVE_INFINITY;
		if (velocityX > 0) timeToCollisionX = (xBoundary - positionX  - this.getRadius())/velocityX;
		if (velocityX < 0) timeToCollisionX = Math.abs((positionX - this.getRadius())/velocityX);
		if (velocityY > 0) timeToCollisionY = (yBoundary - positionY - this.getRadius())/velocityY;
		if (velocityY < 0) timeToCollisionY = Math.abs((positionY - this.getRadius())/velocityY);
		return Math.min(timeToCollisionX, timeToCollisionY);
	}
	
	/**
	 * Method that returns the position of the first collision of a circular object with a boundary
	 * @return If the object doesn't belong to a world or it will not collide with a boundary, null is returned
	 * 			otherwise the position of the collision is returned
	 * 			|new double[] {xPosition + xVel * timeToCollision, yPosition + yVel * timeToCollision}
	 */
	public double[] getPositionCollisionBoundary() {
		if (this.getWorld() == null) return null;
		if (this.getTimeCollisionBoundary() == Double.POSITIVE_INFINITY) return null;
		double xPosition = this.getPositionArray()[0];
		double yPosition = this.getPositionArray()[1];
		double xVel = this.getVelocityArray()[0];
		double yVel = this.getVelocityArray()[1];
		double timeToCollision = this.getTimeCollisionBoundary();
		
		double xPosB = xPosition + xVel * timeToCollision;
		double yPosB = yPosition + yVel * timeToCollision;
		
		if (((xPosB - this.getRadius()) <= 0)) {
			xPosB = xPosB - this.getRadius();
		}
		if ((xPosB + this.getRadius()) >= this.getWorld().getWorldDimensionArray()[0]) {
			xPosB = xPosB + this.getRadius();
		}
		if (((yPosB - this.getRadius()) <= 0)) {
			yPosB = yPosB - this.getRadius();
		}
		if ((yPosB + this.getRadius()) >= this.getWorld().getWorldDimensionArray()[1]) {
			yPosB = yPosB + this.getRadius();
		}
		return new double[] {xPosB, yPosB};
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void terminate() {
		this.isTerminated = true;
	}
	
	/**
	 * Method that resolves collisions of a ship with a boundary
	 * @post
	 * 			|if (!(this.apparantlyWithinBoundaryX()))
	 * 			|new.velocity.getXVelocity == -currentXVel
	 * @post
	 * 			if (!(this.apparantlyWithinBoundaryY()))
	 * 			|new.velocity.getYVelocity == -currentYVel
	 */
	public void collideWithBoundary() {
		if (!(this.apparantlyWithinBoundaryX())) {
			double currentXVel = this.getVelocityArray()[0];
			this.setVelocity(-currentXVel, this.getVelocityArray()[1]);
		}
		if (!(this.apparantlyWithinBoundaryY())) {
			double currentYVel = this.getVelocityArray()[1];
			this.setVelocity(this.getVelocityArray()[0], -currentYVel);
		}
	}

	public abstract void collisionCircularObject(CircularObject object);
}
