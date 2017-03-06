public class Ship{
	
/**
 * Class which represents the implementation of the ship.
 * 
 * 
 * @author Kevin Van der Schueren en Steven Zegers
 * @version 1.2
 */
	
	/**
	*	Variable representing the x coordinate of the ship
	*/
	private double xCoordinate;
	
	/**
	*	Variable representing the y coordinate of the ship
	*/
	private double yCoordinate;
	
	/**
	*	Variable representing the velocity of the ship along the x axis
	*/
	private double xVelocity;
	
	/**
	*	Variable representing the velocity of the ship along the y axis
	*/
	private double yVelocity;
	
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
	*	@post  The given x-coordinate is assigned to the x-coordinate of the ship.
	*			|new.getXCoordinate = xCoordinate
	*	@post  The given y-coordinate is assigned to the y-coordinate of the ship.
	*			|new.getYCoordinate = yCoordinate
	*	@post  The given x-velocity is assigned to the x-velocity of the ship.
	*			|new.getXVelocity = xVelocity
	*	@post  The given y-velocity is assigned to the y-velocity of the ship.
	*			|new.getYVelocity = yVelocity
	*	@post  The given radius, if allowed, is assigned to the radius of the ship.
	*			|new.getRadius = radius
	*	@post  The given direction is assigned to the direction of the ship.
	*			|new.getDirection = direction
	*/
	
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius, double direction){
		
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		assert radius >= 10;
		this.radius = radius;
		this.angle = angle;
		this.direction = direction;
	}
	
	
	/** 
	 * Return the x coordinate of the ship.
	 */
	@Basic 
	public double getXCoordinate() {
		return this.xCoordinate;
	}
	
	/** 
	 * Return the y coordinate of the ship.
	 */
	@Basic 
	public double getYCoordinate() {
		return this.yCoordinate;
	}
	
	/** 
	 * Return the radius of the ship.
	 */
	@Basic 
	public double getShipRadius() {
		return this.radius;
	}
	
	/** 
	 * Return the velocity of the ship along the x axis.
	 */
	@Basic 
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	/** 
	 * Return the velocity of the ship along the y axis.
	 */
	@Basic 
	public double getYVelocity() {
		return this.yVelocity;
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
	 * Assign the given x-coordinate to the x-coordinate of the ship.
	 * @param x
	 * 		The value of x which will be assigned to the x-coordinate of the ship
	 * @Post |this.getXCoordinate() == x
	 */
	@Basic
	public void setX(double x) {
	
	    this.xCoordinate = x;
	
	}
	
	/** 
	 * Assign the given y-coordinate to the y-coordinate of the ship.
	 * @param y
	 * 		The value of y which will be assigned to the y-coordinate of the ship
	 * @Post |this.getYCoordinate() == y
	 */
	@Basic
	public void setY(double y) {
	
	    this.yCoordinate = y;
	
	}
	
	/** 
	 * Assign the given x-velocity to the x-velocity of the ship.
	 * @param xvelocity
	 * 		The value of xvelocity which will be assigned to the xvelocity of the ship
	 * @Post |this.getXVelocity() == xvelocity
	 */
	@Basic
	public void setXvelocity(double xvelocity) {
	
	    this.xVelocity = xvelocity;
	
	}
	
	/** 
	 * Assign the given y-velocity to the y-velocity of the ship.
	 * @param yvelocity
	 * 		The value of yvelocity which will be assigned to the yvelocity of the ship
	 * @Post |this.getYVelocity() == yvelocity
	 */
	@Basic
	public void setYvelocity(double yvelocity) {
	
	    this.yVelocity = yvelocity;
	
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
		double speed = Math.sqrt((this.getXVelocity()*this.getXVelocity())+(this.getYVelocity()*this.yVelocity));
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
		
		double newxvelocity = (this.getXVelocity() + (amount*Math.cos(this.direction)));
		double newyvelocity = (this.getYVelocity() + (amount*Math.sin(this.direction)));
		double newspeed = Math.sqrt((newxvelocity * newxvelocity) + (newyvelocity * newyvelocity));
		
		if (newspeed > SPEEDOFLIGHT){
			newxvelocity = Math.cos(this.getDirection()) * SPEEDOFLIGHT;
			newyvelocity = Math.sin(this.getDirection()) * SPEEDOFLIGHT;
		}
		this.setXvelocity(newxvelocity);
		this.setYvelocity(newyvelocity);
		
	}
	
	
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
	
	public boolean overlap(Ship ship2) {
		
		if (this == ship2) {
			return true;
		} else if (getDistanceBetween(ship2) > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
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