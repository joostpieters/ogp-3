package asteroids.model;


import java.util.HashSet;
import java.util.Set;

import asteroids.part2.CollisionListener;






public class World {

	/**
	 * Variable representing the width of the world
	 */
	private double width;
	
	
	/**
	 * Variable representing the height of the world
	 */
	private double height;
	
	
	/**
	 * Collection of ships currently in the world
	 */
	private Set<Ship> shipsInWorld = new HashSet<Ship>();
	
	/**
	 * Collection of bullets currently in the world
	 */
	private Set<Bullet> bulletsInWorld = new HashSet<Bullet>();
	
	/**
	 * Collection of ships currently in the world
	 */
	private Set<CircularObject> circularObjectsInWorld = new HashSet<CircularObject>();
	
	/**
	 * Boolean variable that indicates whether or not the world is terminated
	 */
	private boolean isTerminated = false;
	
	
	
	/**
	 * Initialization of a world with a given width and height.
	 * @param width
	 * 		The with of the world
	 * @param height
	 * 		The height of the world
	 * @post The given width and height are set to the ones of the world
	 * 		| this.width = width
	 * 		| this.height = height
	 */
	public World(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	
	
	/**
	 * Get an array with both width and height
	 * @return The width and height of a world in an array
	 * 			|result == {this.width, this.height}
	 */
	public double[] getWorldDimensionArray(){
		double[] dimension = {this.width, this.height};
		return dimension;
	}
	
	///
	///GET SETS OF ELEMENTS IN WORLD
	///
	/**
	 * Return a set of all Ships in the given world
	 * @Return The collection of all ships
	 * 			|result == shipsInWorld
	 */
	public Set<Ship> getAllShipsInWorld(){
		return shipsInWorld;
	}
	
	/**
	 * Return a set of all Ships in the given world
	 * @Return The collection of all ships
	 * 			|result == shipsInWorld
	 */
	public Set<Bullet> getAllBulletsInWorld(){
		return bulletsInWorld;
	}
	
	
	/**
	 * Return a set of all Ships in the given world
	 * @Return The collection of all ships
	 * 			|result == shipsInWorld
	 */
	public Set<CircularObject> getAllCircularObjectsInWorld(){
		circularObjectsInWorld.addAll(bulletsInWorld);
		circularObjectsInWorld.addAll(shipsInWorld);
		return circularObjectsInWorld;
	}
	
	///
	///ADD ELEMENTS TO THE WORLD
	///
	
	/**
	 * Add a ship to the given world
	 * @param ship
	 * 		The ship that will be added to the world
	 * @Pre A Ship is located at most in one World. In other words, the ship should not be part of another world or added twice
	 * @post The ship is added to the world
	 */
	public void addShipToWorld(Ship ship){
		if (ship.getWorld() == this) throw new IllegalArgumentException("The ship is already part of this world");
		if (ship.getWorld() != null) throw new IllegalArgumentException("The ship is already part of another world");
		ship.setWorld(this);
		shipsInWorld.add(ship);
	}
	
	/**
	 * Add a ship to the given world
	 * @param ship
	 * 		The ship that will be added to the world
	 * @Pre A Ship is located at most in one World. In other words, the ship should not be part of another world or added twice
	 * @post The ship is added to the world
	 */
	public void addBulletToWorld(Bullet bullet) throws IllegalArgumentException {
		if (bullet.getWorld() == this) throw new IllegalArgumentException("The bullet is already part of this world");
		if (bullet.getWorld() != null) throw new IllegalArgumentException("The bullet is already part of another world");
		bullet.setWorld(this);
		bulletsInWorld.add(bullet);
	}
	
	/**
	 * Method to remove a bullet from this world
	 * @param bullet
	 * @throws IllegalArgumentException
	 * 			The bullet doesn't belong to this world
	 * 			|bullet.getWorld() == null || bullet.getWorld() != this
	 */
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (bullet.getWorld() == null || bullet.getWorld() != this) throw new IllegalArgumentException("This bullet does not belong to a world. ");
		bullet.setWorld(null);
	}
	/**
	 * Method to remove a ship from this world
	 * @param ship
	 * @throws IllegalArgumentException
	 * 			The ship does not belong to this world
	 * 			|ship.getWorld() == null || ship.getWorld() != this
	 */
	public void removeShip(Ship ship) throws IllegalArgumentException{
		if(ship.getWorld() == null || ship.getWorld() != this) throw new IllegalArgumentException("The ship does not belong to a world");
		ship.setWorld(null);
	}
	
	/**
	 * Method that removes all ships and bullets from the world
	 * @post
	 * 			|shipsInWorld.isEmpty() && bulletsInWorld.isEmpty()
	 */
	public void terminateWorld() {
		for (Ship ship : shipsInWorld) {
			this.removeShip(ship);
		}
		for (Bullet bullet : bulletsInWorld) {
			this.removeBullet(bullet);
		}
		this.isTerminated = true;
	}
	
	/**
	 * Method that returns whether or not this world is terminated
	 * @see implementation
	 */
	public boolean isWorldTerminated() {
		return this.isTerminated;
	}
	/**
	 * Method that returns the object at position x,y. This method returns null if there is no object at the position, null is returned
	 * @param x
	 * @param y
	 * @see implementation
	 */
	public Object getEntityAt(double x, double y) {
		for (CircularObject obj : this.getAllCircularObjectsInWorld()) {
			if (obj.position.getPositionX() == x && obj.position.getPositionY() == y) return obj;
		}
		return null;
	}	
	
	/**
	 * Method that returns the time to the next collisions in the world, if there are 2 objects that apparently collide with each other, then 0 is returned
	 * if there are no collisions, Double.POSITIVE_INFINITY will be returned
	 * @see implementation
	 */
	public double getTimeNextCollision() {
		double timeNextCollision = Double.POSITIVE_INFINITY;
		for (CircularObject object1 : this.getAllCircularObjectsInWorld()) {
			// if the object collides with a boundary in a time shorter than the previous shortest time, this is the new shortest time
			timeNextCollision = Math.min(timeNextCollision, object1.getTimeCollisionBoundary());
			// we now check if this object will collide with any other entity (bullet/ship) in a time shorter than what was our previous shortest time
			// if so we change the shortest time variable
			for (CircularObject object2 : this.getAllCircularObjectsInWorld()) {
				if (object1 != object2) {
					if (object1.apparantlyCollidesWith(object2)) return 0; // if 2 objects apparently collide with each other, this means that their is a collision so 0 is returned
					if (object1.getTimeToCollision(object2) < timeNextCollision) timeNextCollision = object1.getTimeToCollision(object2);
				}
			}
		}
		return timeNextCollision;
	}
	/**
	 * Method that returns the position of the next collision
	 * @return
	 * 			|collidingObjects[0].getCollisionPosition(collidingObjects[1])
	 */
	public double[] getPositionNextCollision() {
		double timeNextCollision = this.getTimeNextCollision();
		CircularObject[] collidingObjects = this.getNextCollidingObjects();
		if (timeNextCollision == Double.POSITIVE_INFINITY) return null;
		if (collidingObjects[0] == null && collidingObjects[1] == null) return null;
		if (collidingObjects[1] == null) return collidingObjects[0].getPositionCollisionBoundary();
		else return collidingObjects[0].getCollisionPosition(collidingObjects[1]);
	}
	
	/**
	 * Help method that returns an array containing either a null array if there will be no collision, 
	 * an array with 1 object (second element null if colliding with a boundary) 
	 * or an array with 2 objects if the next collision is between 2 entities
	 * @see implementation
	 */
	public CircularObject[] getNextCollidingObjects() {
		// this method uses the same reasoning as the getTimeNextCollision method, but instead this time we will return an array of length 2
		// containing either 2 null objects if there is no collision, 1 object (if colliding with a boundary) or 2 initialized objects if 2 circular
		// objects collide
		double timeNextCollision = Double.POSITIVE_INFINITY;
		CircularObject[] collidingObjects = {null, null};
		for (CircularObject object1 : this.getAllCircularObjectsInWorld()) {
			if (object1.getTimeCollisionBoundary() < timeNextCollision) {
				collidingObjects = new CircularObject[] {object1, null}; 
				timeNextCollision = object1.getTimeCollisionBoundary();
			}
			for (CircularObject object2 : this.getAllCircularObjectsInWorld()) {
				if (object1.apparantlyCollidesWith(object2)) return new CircularObject[] {object1, object2};
				if (object1.getTimeToCollision(object2) < timeNextCollision) {
					collidingObjects = new CircularObject[] {object1, object2};
					timeNextCollision = object1.getTimeToCollision(object2);
				}
			}
		}
		return collidingObjects;
	}
	
	/**
	 * Method to evolve the world a certain amount of seconds
	 * @param dt 
	 * @param collisionListener
	 * @post All of the circular objects are moved to the correct position after dt seconds and velocities are updated
	 * 			|for (CircularObject object : this.getAllCircularObjectsInWorld()) object.move(dt) && 
	 * 			for (Ship ship : this.getAllShipsInWorld()) {
	 *				if (ship.checkThrusterStatus()) {
	 *					ship.updateVelocity(dt);
	 *				}
	 * 			
	 */
	public void evolve(double dt, CollisionListener collisionListener) {
		double tC = this.getTimeNextCollision();
		
		while (tC <= dt) {
			CircularObject[] firstCollidingObjects = this.getNextCollidingObjects();
			double[] positionFirstCollision = this.getPositionNextCollision();
			for (CircularObject object1 : this.getAllCircularObjectsInWorld()) object1.move(tC);
			for (Ship ship : this.getAllShipsInWorld()) {
				if (ship.checkThrusterStatus()) ship.updateVelocity(tC);
			}
			if (firstCollidingObjects[1] == null) {
				if (firstCollidingObjects[0] instanceof Ship) {
					Ship ship = (Ship)firstCollidingObjects[0];
					ship.collideWithBoundary();
					collisionListener.boundaryCollision(ship, positionFirstCollision[0], positionFirstCollision[1]);
				}
				if (firstCollidingObjects[1] instanceof Bullet) {
					Bullet bullet = (Bullet)firstCollidingObjects[1];
					bullet.collideWithBoundary();
					collisionListener.boundaryCollision(bullet, positionFirstCollision[0], positionFirstCollision[1]);
				}
			}
			else {
				if (firstCollidingObjects[0] instanceof Ship) {
					Ship ship = (Ship) firstCollidingObjects[0];
					ship.collisionCircularObject(firstCollidingObjects[1]);
					collisionListener.objectCollision(firstCollidingObjects[0], firstCollidingObjects[1], positionFirstCollision[0], positionFirstCollision[1]);
				}
				if (firstCollidingObjects[1] instanceof Bullet) {
					Bullet bullet = (Bullet)firstCollidingObjects[0];
					bullet.collisionCircularObject(firstCollidingObjects[1]);
				}
			}
			dt = dt - tC;
			tC = this.getTimeNextCollision();
		}
		for (CircularObject object : this.getAllCircularObjectsInWorld()) object.move(dt);
		for (Ship ship : this.getAllShipsInWorld()) {
			if (ship.checkThrusterStatus()) {
				ship.updateVelocity(dt);
			}
		}
		
	}
}
