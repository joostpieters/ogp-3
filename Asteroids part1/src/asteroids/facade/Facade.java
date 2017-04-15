package asteroids.facade;


import asteroids.model.Ship;

import java.util.Collection;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.CircularObject;
import asteroids.model.Velocity;
import asteroids.model.World;
import asteroids.model.Position;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;


public class Facade implements IFacade {
	
	
	
	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation and mass (in radians).
	 */
	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double orientation, double mass)
			throws ModelException {
		try {	
			return new Ship(x,y,xVelocity,yVelocity,radius,orientation, mass);
		} 
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
	}

	
	/**
	 * Return the position of <code>ship</code> as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 */
	@Override
	public double[] getShipPosition(Ship ship) throws ModelException{
		return ship.getPositionArray();
	}
	
	

	/**
	 * Return the velocity of <code>ship</code> as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 */
	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocityArray();
	}

	/**
	 * Return the radius of <code>ship</code>.
	 */
	@Override
	public double getShipRadius(Ship ship) throws ModelException{
		return ship.getRadius();
	}

	/**
	 * Return the orientation of <code>ship</code> (in radians).
	 */
	@Override
	public double getShipOrientation(Ship ship) throws ModelException{
		return ship.getDirection();
	}

//**
//* Update <code>ship</code>'s position, assuming it moves <code>dt</code>
//* seconds at its current velocity.
//*/
//ublic void move(Ship ship, double dt) throws ModelException{
//	try{ ship.move(dt);
//	} 
//	catch(IllegalArgumentException excep){
//		ModelException mod = new ModelException(excep);
//		throw mod;
//	}
//
//
//**
//* Update <code>ship</code>'s velocity based on its current velocity, its
//* direction and the given <code>amount</code>.
//*/
//
//

	/**
	 * Update the direction of <code>ship</code> by adding <code>angle</code>
	 * (in radians) to its current direction. <code>angle</code> may be
	 * negative.
	 */
	@Override
	public void turn(Ship ship, double angle) throws ModelException{
		ship.turn(angle);
	}

	/**
	 * Return the distance between <code>ship1</code> and <code>ship2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 */
	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException{
		try{ return ship1.getDistanceBetween(ship2);
		}
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
	}

	/**
	 * Check whether <code>ship1</code> and <code>ship2</code> overlap. A ship
	 * always overlaps with itself.
	 */
	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
	try { return ship1.overlap(ship2);
	}
	catch(IllegalArgumentException excep){
		ModelException mod = new ModelException(excep);
		throw mod;
	}
	}
	
	
	/**
	 * Return the number of seconds until the first collision between
	 * <code>ship1</code> and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 */
	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException{
		try {
			return ship1.getTimeToCollision(ship2);
		} catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
	}

	/**
	 * Return the first position where <code>ship1</code> and <code>ship2</code>
	 * collide, or <code>null</code> if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 */
	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException{
		try {
			return ship1.getCollisionPosition(ship2);
		} catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
	}


	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminateShip();
		
	}


	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isShipTerminated();
	}


	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}


	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}


	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.checkThrusterStatus();
	}


	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active) ship.thrustOn();
		else ship.thrustOff();
		
	}


	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}


	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException{
	try {	
		return new Bullet(x,y,xVelocity,yVelocity,radius);
	} 
	catch(IllegalArgumentException excep){
		ModelException mod = new ModelException(excep);
		throw mod;
	}
	}


	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminateBullet();
		
	}


	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isBulletTerminated();
	}


	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPositionArray();
	}


	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getVelocityArray();
	}


	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}


	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}


	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}


	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getSourceShip();
	}


	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getSourceShip();
	}


	@Override
	public World createWorld(double width, double height) throws ModelException {
		try {	
			return new World(width, height);
		} 
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
	}


	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminateWorld();
		
	}


	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isWorldTerminated();
	}


	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return world.getWorldDimensionArray();
	}


	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getAllShipsInWorld();
	}


	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getAllBulletsInWorld();
	}


	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {	
			world.addShipToWorld(ship);
		} 
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
		
	}


	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {	
			world.removeShip(ship);
		} 
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
	}


	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {	
			world.addBulletToWorld(bullet);
		} 
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
		
	}


	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {	
			world.removeBullet(bullet);
		} 
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
		
	}


	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBulletsOfShip();
	}


	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getAmountOfBullets();
	}


	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {	
			ship.loadBullet(bullet);
		} 
		catch(IllegalArgumentException excep){
			ModelException mod = new ModelException(excep);
			throw mod;
		}
		
	}


	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		ship.loadMultipleBullets(bullets);
		
	}


	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBullet(bullet);
		
	}


	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fire();
		
	}


	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return ((CircularObject)object).getTimeCollisionBoundary();
	}


	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((CircularObject)object).getPositionCollisionBoundary();
	}


	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		if (!(entity1 instanceof CircularObject && entity2 instanceof CircularObject)) throw new ModelException("The given objects are not entities.");
		return ((CircularObject)entity1).getTimeToCollision((CircularObject)entity2);
	}


	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		if (!(entity1 instanceof CircularObject && entity2 instanceof CircularObject)) throw new ModelException("The given objects are not entities.");
		return ((CircularObject)entity1).getCollisionPosition((CircularObject)entity2);
	}


	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeNextCollision();
	}


	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getPositionNextCollision();
	}


	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		world.evolve(dt,collisionListener);
	
		
	}


	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x, y);
	}


	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getAllCircularObjectsInWorld();
	}


	
	
	
}