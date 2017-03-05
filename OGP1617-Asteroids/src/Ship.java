public class Ship{
	
/**
 * Class which represents the implementation of the ship.
 * 
 * 
 * @author Kevin Van der Schueren en Steven Zegers
 * @version 1.1
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
	*	@pre	The radius needs to be => 10
	* 			|new.getRadius >=10
	*
	*	@post
	*
	*
	*/
	
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double angle, double radius, double direction){
		
		//TODO eventueel met get en set methodes implementeren
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.radius = radius;
		this.angle = angle;
		this.direction = direction;
	}
	
	
	/** 
	 * Return the x coordinate of the ship.
	 */
	@Basic 
	public double getXCoordinate() {
		return xCoordinate;
	}
	
	/** 
	 * Return the y coordinate of the ship.
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
	 * Return the velocity of the ship along the x axis.
	 */
	@Basic 
	public double getXVelocity() {
		return xVelocity;
	}
	
	/** 
	 * Return the the velocity of the ship along the y axis.
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
	 * Return the direction of the ship.
	 */
	@Basic 
	public double getDirection() {
		return direction;
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
	* Set the direction of the ship.
	*/
	@Basic
	public void setDirection(double direction)
		{
			this.direction = direction;
		}
		
		
	/**
	* Set the angle of the ship.
	*/
	@Basic
	public void setAngle(double angle)
		{
			this.angle = angle;
		}
		
		
	/**
	* Get the complete velocity of the ship.
	*/
	public void getVelocity(){
		double velocity = math.sqrt((this.getXVelocity()*this.getXVelocity())+(this.getYVelocity()*this.getYVelocity()));
		 assert velocity <= SPEEDOFLIGHT
		  return velocity;
	}
	
		
	
	
	public double getDistanceBetween(Ship ship1, Ship ship2){
		
		
		
	}
	
	public boolean overlap(Ship ship1, Ship ship2){
		if(ship1 == ship2){
			return true;
		}
		
		
	}
	
	public double getTimeToCollision(){
		
		
	}
	
	public double getCollisionPosition(){

		
	}
	
		
}