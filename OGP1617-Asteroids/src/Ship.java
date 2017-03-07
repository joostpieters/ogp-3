public class Ship{
	
/**
 * Class which represents the implementation of the ship.
 * 
 * 
 * @author Kevin Van der Schueren en Steven Zegers
 * @version 1.2
 */
	/**
	*	Variable representing the position of the ship in an array of lenght 2
	*/
	private double[] position = new double[2]

	/**
	*	Variable representing the velocity of the ship in an array of lenght 2
	*/
	private double[] velocity = new double[2]
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
	*		The xCoordinate of the ship
	*	@param YCoordinate
	*	The YCoordinate of the ship
	*	@param xVelocity
	*	The horizontal velocity of the ship
	*	@param yVelocity
	*	The vertical velocity of the ship
	*	@param direction
	*	The direction of the ship
	*	@param angle
	*	The angle of the ship
	*	@param radius
	*	The radius of the ship
	*	@pre	The radius needs to be => 10
	* 			|new.getRadius >=10
	*	@post  The given xCoordinate and yCoordinate is assigned to the position of the ship.
	*			|new.getPosition = {xCoordinate,yCoordinate}
	*	@post  The given x-velocity and y-velocity is assigned to the velocity of the ship.
	*			|new.getVelocity = {xVelocity,yVelocity}
	*	@post  The given radius, if allowed, is assigned to the radius of the ship.
	*			|new.getRadius = radius
	*	@post  The given direction is assigned to the direction of the ship.
	*			|new.getDirection = direction
	*/
	
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius, double direction){
		
		this.position = setPosition(xCoordinate,yCoordinate);
		this.velocity = setVelocity(xVelocity,yVelocity);
		assert radius >= 10;
		this.radius = radius;
		this.angle = angle;
		this.direction = direction;
	}
	
	
	
	
	/** 
	 * Return the position of the ship with an array of both coordinates.
	 */
	@Basic 
	public double[] getPosition() {
		return this.position;
	}
	
	/** 
	 * Return the radius of the ship.
	 */
	@Basic 
	public double getShipRadius() {
		return this.radius;
	}
	
	/** 
	 * Return the velocity of the ship in an array of x and y velocity.
	 */
	@Basic 
	public double[] getVelocity() {
		return this.velocity;
	}
	
	
	/** 
	 * Return the radius of the ship.
	 */
	@Basic 
	public double getRadius() {
		return this.radius;
	}
	
	/** 
	 * Return the angle of the ship.
	 */
	@Basic 
	public double getAngle() {
		return this.angle;
	}
	
	/** 
	 * Return the direction of the ship.
	 */
	@Basic 
	public double getDirection() {
		return this.direction;
	}
	
	/** 
	 * Assign the given x-coordinate and y-coordinate to the x-coordinate and y-coordinate of a ship of the ship.
	 * @param x
	 * 		The value of x which will be assigned to the x-coordinate of the ship
	 @param y
	 * 		The value of y which will be assigned to the y-coordinate of the ship
	 * @Post |this.getPosition() == {x,y}
	 */
	@Basic
	public void setPosition(x,y) {
		double[] pos = {x,y}
	    this.position = pos;
	
	}
	
	
	
	/** 
	 * Assign the given x and y-velocity to the x and y-velocity of the ship.
	 * @param xvelocity
	 * 		The value of xvel which will be assigned to the xvelocity of the ship
	 @param yvelocity
	 * 		The value of yvel which will be assigned to the yvelocity of the ship
	 * @Post |this.getVelocity() == {xvel,yvel}
	 */
	@Basic
	public void setVelocity(xvel, yvel) {
		double[] vel = {xvel,yvel};
	    this.velocity = vel;
	
	}
	
	/**
	* Assign the given direction to the direction of the ship.
	 * @param direction
	 * 		The value of the direction which will be assigned to the direction of the ship
	 * @Post |this.getDirection() == direction
	*/
	@Basic
	public void setDirection(double direction)
		{
			this.direction = direction;
		}
		
		
	/**
	* Assign the given angle to the angle of the ship.
	 * @param angle
	 * 		The value of the angle which will be assigned to the angle of the ship
	 * @Post |this.getAngle() == angle
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
	 * @Post |this.getRadius() == radius
	*/
	@Basic
	public void setRadius(double radius)
		{
			this.radius = radius;
		}
			
		
	/**
	* Get the complete speed of the ship.
	*/


	public double getSpeed(){
		double speed = Math.sqrt((this.getVelocity()[0]* this.getVelocity()[0])+(this.getVelocity()[1]*this.getVelocity()[1]));
		 assert speed <= SPEEDOFLIGHT;
		 return speed;
	}
	
	/**
	 * 
	 *
	 */
	public void move(double time){
		//nog te implementeren
	}
	
		
		
	/**
	 * 
	 * @param givenangle
	 * 		The angle the ship has to turn
	 * @post
	 * 		The new direction is the old direction + the given angle
	 * 		|this.getDirection == this.setDirection(this.getDirection() + givenangle)
	 *
	 */


	public void turn(double givenangle){
		this.direction += givenangle;

	}
	
	
	/**
	 * 
	 * @param amount
	 * 		The total amount of acceleration
	 * @pre amount > = 0
	 * @post Both velocities are changed based on the given amount, the current acceleration and the direction.
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
		this.setVelocity()(newxvelocity,newyvelocity);
		
		
	}
	
	/**
	 * This method calculates the minimum amount of distance that our ship should travel in order to be adjacent to ship2.
	 * If the ships overlap, the distance will be negative, if the 2 ships are the same the returned value will be 0.
	 * If the ships don't overlap and aren't the same, a positive value will be returned
	 * 
	 * @param ship2 A ship named ship2 of which we want to know how far away it is from our ship.
	 * @return Returns the distance between the ship and another ship, named ship2.
	 */
	public double getDistanceBetween(Ship ship2) {
		
		if (this == ship2) {

			return 0;
		}
		
		double xCoordinateShip1 = this.getXCoordinate();
		double yCoordinateShip1 = this.getYCoordinate();
		double xCoordinateShip2 = ship2.getXCoordinate();
		double yCoordinateShip2 = ship2.getYCoordinate();
		double radiusShip1 = this.getRadius();
		double radiusShip2 = ship2.getRadius();
		
		double differenceInX = xCoordinateShip1 - xCoordinateShip2;
		double differenceInY = yCoordinateShip1 - yCoordinateShip2;
		
		double squaredDifferenceInX = Math.pow(differenceInX, 2);
		double squaredDifferenceInY = Math.pow(differenceInY, 2);
		
		double distanceBetweenCenters = Math.sqrt(squaredDifferenceInX + squaredDifferenceInY);
		double distance = distanceBetweenCenters - radiusShip1 - radiusShip2;
		
		return distance;
		
	}
	/**
	 * This method returns true of false, depending if the ships overlap or not. 
	 * As explained for the function getDistanceBetween, 2 ships that aren't the same overlap when the
	 * distance between them is negative, then true will be returned. If two ships are the same, 
	 * by convention they overlap. 
	 * 
	 * @param ship2 A ship named ship2.
	 * @return Returns true if the ships overlap, false if they don't.
	 */
	public boolean overlap(Ship ship2) {
		
		if (this == ship2) {
			return true;
		} else if (getDistanceBetween(ship2) > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * If 2 ships will ever collide, returns the amount of seconds until that collision. If 2 ships
	 * will never collide with each other, Double.POSITIVE_INFINITY is returned. Additionally, a ship
	 * can never collide with itself.
	 * @param ship2 A ship named ship2.
	 * @return Returns the time until collision between our ship and ship2.
	 */
	public double getTimeToCollision(Ship ship2) {
		
		if (this == ship2) {
			
			return Double.POSITIVE_INFINITY;
		}
		
		double xVelocityShip1 = this.getXVelocity();
		double yVelocityShip1 = this.getYVelocity();
		double xVelocityShip2 = ship2.getXVelocity();
		double yVelocityShip2 = ship2.getYVelocity();
		
		double xCoordinateShip1 = this.getXCoordinate();
		double yCoordinateShip1 = this.getYCoordinate();
		double xCoordinateShip2 = ship2.getXCoordinate();
		double yCoordinateShip2 = ship2.getYCoordinate();
		
		double radiusShip1 = this.getRadius();
		double radiusShip2 = ship2.getRadius();
		double sumOfRadiusses = radiusShip1 + radiusShip2;
		
		double deltaX = xCoordinateShip1 - xCoordinateShip2;
		double deltaY = yCoordinateShip1 - yCoordinateShip2;
		double deltaVx = xVelocityShip1 - xVelocityShip2;
		double deltaVy = yVelocityShip1 - yVelocityShip2;
		
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
	
	public double getCollisionPosition() {

		
	}
	
		
}