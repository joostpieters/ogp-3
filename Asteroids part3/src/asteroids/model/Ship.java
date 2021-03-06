package asteroids.model;



import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
	private final double DENSITY = 1.42 * Math.pow(10, 12);
	
	/**
	 * Constant representing the thruster force
	 */
	
	private final double THRUSTERFORCE = 1.1E18;
	
	/**
	 * The state of the thruster
	 */
	private boolean thruster;
	
	/**
	 * The collection of bullets a ship has
	 */
	private Set<Bullet> bulletsCollection = new HashSet<Bullet>();
	
	
	/**
	 * The program that is/can be loaded on the ship
	 */
	private Program shipProgram;
	
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
			throws IllegalArgumentException {
			
		super(xCoordinate, yCoordinate, xVelocity, yVelocity, radius);
		this.setDirection(direction);
		this.setMass(mass);
	}
	
	
	/**
	 * Returns density of a ship
	 * 		|result == DENSITY
	 */
	@Override
	public double getDensity() {
		return DENSITY;
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
		double masswithformula = 4.0 *Math.PI*Math.pow(this.getRadius(),3)*DENSITY / 3.0;
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
	@Override
	public double getMass(){
		double shipMass = this.mass;
		double massOfBullets = 0;
		for (Bullet bullet : this.getBulletsOfShip())
			massOfBullets += bullet.getMass();
		return (shipMass + massOfBullets);
	}
	
	
	
	/**
	 * Get a set of bullets of the ship
	 * @return the set of bullets
	 * 			|result == bulletsCollection
	 */
	public Set<Bullet> getBulletsOfShip(){
		
		return this.bulletsCollection;
	}
	
	/**
	 * Get number of bullets on the ship
	 * @return The amount of bullets loaded on the ship
	 * 			|result == amount;
	 */
	public int getAmountOfBullets(){
		return this.bulletsCollection.size();
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
	 * Check if the bullet is a bullet that can be added to a ship, the bullet has to be initialized,
	 * the bullet cannot be loaded onto another ship and the bullet cannot be loaded if its part of a different world
	 * @param bullet
	 * @return
	 * 			|if (bullet == null) return false || (if (bullet.getSourceShip() != this && bullet.getSourceShip() != this)) return false 
	 * 			|if (bullet.getWorld() != null && bullet.getWorld() != this.getWorld()) return false
	 */
	public boolean canAddToShip(Bullet bullet) {
		if (bullet == null) return false;
		if (bullet.getSourceShip() != null && bullet.getSourceShip() != this) return false;
		if (bullet.isTerminated()) return false;
		if (bullet.getWorld() != null && bullet.getWorld() != this.getWorld()) return false;
		return (this.getDistanceBetween(bullet) < 0.99 * (this.getRadius() - bullet.getRadius()));
	}
	
	/**
	 * Method to load a bullet onto a ship
	 * @param bullet
	 * @post position, world and sourceShip is assigned to the bullet
	 * 		|bullet.setPosition(this.getPositionArray()[0], this.getPositionArray()[1]);
	 *		|bullet.setWorld(null);
	 *		|bullet.setSourceShip(this);
	 * @post The bullet is added to the collection of bullets
	 * 		|this.bulletsCollection.add(bullet);
	 * @throws IllegalArgumentException
	 * 			|!canAddToShip(bullet)
	 */
	public void loadBullet(Bullet bullet) throws IllegalArgumentException {
		if (!canAddToShip(bullet)) throw new IllegalArgumentException("This bullet can not be loaded onto the ship.");
		bullet.setPosition(this.getPositionArray()[0], this.getPositionArray()[1]);
		if (bullet.getWorld() != null) this.getWorld().removeBullet(bullet);
		bullet.setSourceShip(this);
		bullet.setWorld(null);
		bulletsCollection.add(bullet);
	}
	
	/**
	 * Method to load multiple bullets on the ship.
	 * @param bullets
	 * @post see previous method
	 */
	public void loadMultipleBullets(Collection<Bullet> bullets){
		for (Bullet bullet : bullets) loadBullet(bullet);
	}
	
	/**
	 * Method to remove a bullet from a ship
	 * @param bullet
	 * @throws IllegalArgumentException
	 * 			|bullet.getSourceShip() != this
	 * @post the bullet is removed from the collection of bullets.
	 * 		|this.bulletsCollection.remove(bullet)
	 */
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (bullet.getSourceShip() != this) throw new IllegalArgumentException("This bullet is not part of the ship");
		this.bulletsCollection.remove(bullet);
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
	 * @post the thruster is set to active
	 * 		|thruster == true
	 */
	public void thrustOn(){
		thruster = true;
	}
	
	
	/**
	 * Disable the thruster
	 * @post the thruster is set to non active
	 * 		|thruster == false
	 */
	public void thrustOff(){
		thruster = false;
	}
	
	/**
	 * Get the status of the thruster
	 * @return the status of the thruster
	 * 			|result == thruster
	 */
	public boolean checkThrusterStatus(){
		return thruster;
	}
	
	/**
	 * Calculate the acceleration
	 * @Pre only if the thruster is enabled, there is an acceleration
	 * @return the amount of acceleration if possible
	 * 			|acceleration = THRUSTERFORCE/getMass();
	 */
	public double getAcceleration(){
		double acceleration = 0;
		if (checkThrusterStatus() == false){
			acceleration = 0;
		}
		else {
			acceleration = THRUSTERFORCE/getMass();
		}
		return acceleration;
	}
	
	/**
	 * Move the ship for a certain amount of time with its calculated acceleration
	 * @param time
	 * @post The ship moves and accelerates if necessary for the given time.
	 * 		
	 */
	public void move(double time){
		super.move(time);
		thrust(getAcceleration()*time);
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
		
		double newXVelocity = (this.getVelocityArray()[0] + (amount*Math.cos(this.direction)));
		double newYVelocity = (this.getVelocityArray()[1] + (amount*Math.sin(this.direction)));
		
		this.setVelocity(newXVelocity, newYVelocity);	
	}
	
	
	/**
	 * Fire a bullet from the ship.
	 * @Pre The ship still needs to have available bullets and the ship itself should be located in this world.
	 * 		|if(this.getAmountOfBullets() < 0) return;
	 *	|if(this.getWorld() == null) return;
	 * @post After being fired, the bullet is removed from the collection of bullets of the ship.
	 * @post The velocity and position is modified.
	 * @post If allowed by addBulletToWorld, the bullet is added to the space, i.e. there is not overlapping.
	 * 		|firedbullet.setSourceShip(this);
		 	|this.bulletsCollection.remove(firedbullet);
			|firedbullet.setVelocity(bulletXVelocity, bulletYVelocity);
			|firedbullet.setPosition(bulletXPosition, bulletYPosition);
	 */
	
	public void fire() {
		if(this.getAmountOfBullets() < 0) return;
		if(this.getWorld() == null) return;
		Bullet firedbullet = this.getBulletsOfShip().iterator().next();
		firedbullet.boundaryCollisions = 0;
		double bulletdirection = this.getDirection();
		double bulletradius = firedbullet.getRadius();
		double[] shipPosition = this.getPositionArray();
		
		double bulletXVelocity = 250*Math.cos(bulletdirection);
		double bulletYVelocity = 250*Math.sin(bulletdirection);
		double bulletXPosition = shipPosition[0] + (this.getRadius() + bulletradius)*Math.cos(bulletdirection);
		double bulletYPosition = shipPosition[1] + (this.getRadius() + bulletradius)*Math.sin(bulletdirection);
		this.bulletsCollection.remove(firedbullet);
		firedbullet.setVelocity(bulletXVelocity, bulletYVelocity);
		firedbullet.setPosition(bulletXPosition, bulletYPosition);
		//firedbullet.setSourceShip(this);
		try {
			this.getWorld().addBulletToWorld(firedbullet);
		}
		catch (IllegalArgumentException exc) {
			firedbullet.setWorld(this.getWorld());
			for (CircularObject obj: this.getWorld().getAllCircularObjectsInWorld()){
				if (obj.overlap(firedbullet) && obj != firedbullet) {	
					firedbullet.collisionCircularObject(obj);
				}
			}
			if (this.getWorld().circularObjectOutOfBound(firedbullet)) {
				firedbullet.terminate();
			}
		}
	}
	
	/**
	 * Method to terminate the ship
	 * @post Ship is terminated and removed from this world
	 * 			|this.getWorld() != null
	 * 			|this.isTerminated == true
	 */
	@Override
	public void terminate() throws IllegalArgumentException {
		super.terminate();
		if (this.getWorld() != null) this.getWorld().removeShip(this);
		
	}
	
	/**
	 * Method to resolve collisions of a ship with another circular object
	 * @param object2
	 * @effect
	 * 		|if (object2 instanceof Ship) this.velocity.getVelocity() = new Velocity(newXVelocityThisObject, newYVelocityThisObject)
	 * 		&& object.velocity.getVelocity() = new Velocity(newXVelocityObject2, newYVelocityObject2);
	 */
	@Override
	public void collisionCircularObject(CircularObject object2) {
		if (object2 instanceof Ship) {
			double sumOfRadiusses = this.getRadius() + object2.getRadius();
			double sumOfMasses = this.getMass() + object2.getMass();
			
			Vector deltaV = new Vector(this.velocity.getDifferenceInVelocity(object2.velocity));
			Vector deltaR = new Vector(this.position.getDifferenceInPositions(object2.position));
			
			double J = 2 * this.getMass() * object2.getMass() * (deltaV.dotProductVectors(deltaR))
					/(sumOfRadiusses*sumOfMasses);
			
			double jX = J * this.position.getDifferenceInPositions(object2.position)[0]
					/ sumOfRadiusses;
			
			double jY = J * this.position.getDifferenceInPositions(object2.position)[1]
					/ sumOfRadiusses;
			
			double newXVelocityThisObject = this.getVelocityArray()[0] + jX/this.getMass();
			double newYVelocityThisObject = this.getVelocityArray()[1] + jY/this.getMass();
			double newXVelocityObject2 = object2.getVelocityArray()[0] - jX/object2.getMass();
			double newYVelocityObject2 = object2.getVelocityArray()[1] - jY/object2.getMass();		
			this.setVelocity(newXVelocityThisObject, newYVelocityThisObject);
			object2.setVelocity(newXVelocityObject2, newYVelocityObject2);
		}
		else{
			System.out.println("Ship met iets anders");
			object2.collisionCircularObject(this);
		}
	}	
	
	/**
	 * Method to get the program of a ship
	 * @return
	 * 		|result == this.shipProgram
	 */
	public Program getProgram(){
		return this.shipProgram;
	}
	/**
	 * Method to load a program onto a ship
	 * @effect
	 * 		|this.shipProgram = program
	 */
	public void loadProgram(Program program){
		this.shipProgram = program;
		if (program != null) program.setShip(this);
	}
	/**
	 * Method to run the program of the ship
	 * @param dt
	 */
	public List<Object> runProgram(double dt){
		//If program is not able to run at a valid time, it will not execute.
		if (dt < 0.2) return null; 
		System.out.println("Tijd niet valid");
		return shipProgram.run(dt);
	}

}
