public class Ship{
	
/**
 * Class which represents the implementation of the ship.
 * 
 * 
 * @author Kevin Van der Schueren en Steven Zegers
 * @version 1
 */
	
	/**
	* TODO  add comment
	*/
	private double xCoordinate;
	
	/**
	*	TODO  add comment
	*/
	private double yCoordinate;
	
	/**
	*TODO  add comment
	*/
	private double xVelocity;
	
	/**
	*TODO  add comment
	*/
	private double yVelocity;
	
	/**
	*TODO  add comment
	*/
	private double radius;
	
	/**
	*TODO  add comment
	*/
	private double angle;
	
	/**
	*TODO  add comment
	*/
	private double direction;
	
	/**
	*TODO  add comment
	*/
	private final double SPEEDOFLIGHT = 30000;
	
	/**
	*TODO  add comment
	*/
	private final double MINIMUMRADIUS = 10;
	
	
	/**
	*	Initialization of a new ship with given position in x and y coordinates, horizontal and vertical velocity, the direction, radius and angle.
	*
	*	@param xCoordinate
	*		The xCoordinate of the ship
	*		
	*	@param YCoordinate
	*	The YCoordinate of the ship
	*	
	*	@param xVelocity
	*	The horizontal velocity of the ship
	*	
	*	@param yVelocity
	*	The vertical velocity of the ship
	*	
	*	@param direction
	*	The direction of the ship
	*		
	*	@param angle
	*	The angle of the ship
	*	
	*	@param radius
	*	The radius of the ship
	*
	*	@pre	The radius neesd to be => 10
	* 			|new.getRadius >=10
	*
	*	@post
	*
	*
	*/
	
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double angle, double radius, double direction){
		
		//TODO met methodes implementeren
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.radius = radius;
		this.angle = angle;
	}
	
	
	/** 
	 * Return the X Coordinate of the ship.
	 */
	@Basic 
	public double getXCoordinate() {
		return xCoordinate;
	}
	
	/** 
	 * Return the Y Coordinate of the ship.
	 */
	@Basic 
	public double getYCoordinate() {
		return yCoordinate;
	}
	
	/** 
	 * Return the radius of the ship.
	 */
	@Basic 
	public double getRadius() {
		return radius;
	}
	
	/** 
	 * Return the X velocity of the ship.
	 */
	@Basic 
	public double getXVelocity() {
		return xVelocity;
	}
	
	/** 
	 * Return the Y Velocity of the ship.
	 */
	@Basic 
	public double getYVelocity() {
		return yVelocity;
	}
	
	
	/** 
	 * Return the angle of the ship.
	 */
	@Basic 
	public double getAngle() {
		return angle;
	}
	
	/** 
	 * Set the x coordinate of the ship.
	 */
	@Basic
	public void setX(double x) {
	
	    this.xCoordinate = x;
	
	}
	
	/** 
	 * Set the y coordinate of the ship.
	 */
	@Basic
	public void setY(double y) {
	
	    this.yCoordinate = y;
	
	}
	
	/** 
	 * Set the x velocity of the ship.
	 */
	@Basic
	public void setXvelocity(double xvelocity) {
	
	    this.xVelocity = xvelocity;
	
	}
	
	/** 
	 * Set the y velocity of the ship.
	 */
	@Basic
	public void setYvelocity(double yvelocity) {
	
	    this.yVelocity = yvelocity;
	
	}
	/**
	*
	*/
	public void setDirection(double direction)
		{
			this.direction = direction;
		}
	
	
	
		
}