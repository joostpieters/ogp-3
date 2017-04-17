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
	public Set<CircularObject> getAllCircularObjectsInWorld() {
		//TODO: start from empty set, then add all new objects to update set after collisions and termination of bullets and ships
		circularObjectsInWorld.addAll(bulletsInWorld);
		circularObjectsInWorld.addAll(shipsInWorld);
		return circularObjectsInWorld;
	}
	
	///
	///ADD ELEMENTS TO THE WORLD
	///
	
	/**
	 * Boolean method that indicates whether or not the ship in question can be part of this world
	 * @param ship
	 * @see implementation
	 */
	private boolean validShip(Ship ship) {
		if (ship.getWorld() != this && ship.getWorld() != null) return false;
		for (CircularObject object : this.getAllCircularObjectsInWorld()) {
			if (ship.overlap(object)) return false;
		}
		return true;
	}
	/**
	 * Boolean method that indicates whether or not the bullet in question can be part of this world
	 * @param bullet
	 * @see implementation
	 */
	private boolean validBullet(Bullet bullet) {
		if (bullet.getWorld() != this && bullet.getWorld() != null) return false;
		for (CircularObject object: this.getAllCircularObjectsInWorld()) {
			if (bullet.overlap(object)) return false;
		}
		return true;
	}
	/**
	 * checks if the ship is within boundaries of the world
	 * @param ship
	 * @see implementation
	 */
	private boolean shipOutOfBound(Ship ship) {
		if (ship.getPositionArray()[0] - ship.getRadius() <= 0 || ship.getPositionArray()[0] + ship.getRadius() >= this.getWorldDimensionArray()[0]) return true;
		if (ship.getPositionArray()[1] - ship.getRadius() <= 0 || ship.getPositionArray()[1] + ship.getRadius() >= this.getWorldDimensionArray()[1]) return true;
		return false;
	}
	/**
	 * checks if the bullet is within boundaries of this world
	 * @param bullet
	 * @see implementation
	 */
	private boolean bulletOutOfBound(Bullet bullet) {
		if (bullet.getPositionArray()[0] - bullet.getRadius() <= 0 || bullet.getPositionArray()[0] + bullet.getRadius() >= this.getWorldDimensionArray()[0]) return true;
		if (bullet.getPositionArray()[1] - bullet.getRadius() <= 0 || bullet.getPositionArray()[1] + bullet.getRadius() >= this.getWorldDimensionArray()[1]) return true;
		return false;
	}
	
	/**
	 * Add a ship to the given world
	 * @param ship
	 * 		The ship that will be added to the world
	 * @Pre A Ship is located at most in one World. In other words, the ship should not be part of another world or added twice
	 * @post The ship is added to the world
	 */
	public void addShipToWorld(Ship ship){
		if (!validShip(ship) || shipOutOfBound(ship)) throw new IllegalArgumentException("This ship can not be added to the world.");
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
		if (!validBullet(bullet) || bulletOutOfBound(bullet)) throw new IllegalArgumentException("This bullet cannot be added to the world.");
		bullet.setWorld(this);
		bulletsInWorld.add(bullet);
		circularObjectsInWorld.add(bullet);
	}
	
	/**
	 * Method to remove a bullet from this world
	 * @param bullet
	 * @throws IllegalArgumentException
	 * 			The bullet doesn't belong to this world
	 * 			|bullet.getWorld() == null || bullet.getWorld() != this
	 */
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (bullet.getWorld() != this) throw new IllegalArgumentException("This bullet does not belong to this world. ");
		this.bulletsInWorld.remove(bullet);
		this.circularObjectsInWorld.remove(bullet);
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
		if(ship.getWorld() != this) throw new IllegalArgumentException("The ship does not belong to a world");
		shipsInWorld.remove(ship);
		this.circularObjectsInWorld.remove(ship);
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
			if (obj.getPositionArray()[0] == x && obj.getPositionArray()[1] == y) return obj;
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
					//if (object1.apparantlyCollidesWith(object2)) return 0; // if 2 objects apparently collide with each other, this means that there is a collision so 0 is returned
					timeNextCollision = Math.min(timeNextCollision, object1.getTimeToCollision(object2));
				}
			}
		}
		return Math.max(0, timeNextCollision);
	}
	/**
	 * Method that returns the position of the next collision
	 * @return
	 * 			|collidingObjects[0].getCollisionPosition(collidingObjects[1])
	 */
	public double[] getPositionNextCollision() {
		CircularObject[] collidingObjects = this.getNextCollidingObjects();
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
			double T = object1.getTimeCollisionBoundary();
			if (T < timeNextCollision) {
				collidingObjects = new CircularObject[] {object1, null}; 
				timeNextCollision = T;
			}
			for (CircularObject object2 : this.getAllCircularObjectsInWorld()) {
				if (object1 != object2) {
					if (object1.apparantlyCollidesWith(object2)) return new CircularObject[] {object1, object2};
					T = object1.getTimeToCollision(object2);
					if (T < timeNextCollision) {
						collidingObjects = new CircularObject[] {object1, object2};
						timeNextCollision = T;
					}
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
		double deltaT = dt;
		double tC = this.getTimeNextCollision();
		CircularObject[] firstCollidingObjects = this.getNextCollidingObjects();
		double[] positionFirstCollision = this.getPositionNextCollision();
		while (tC <= deltaT) {
			for (CircularObject object1 : this.getAllCircularObjectsInWorld()) object1.move(tC);
			if (firstCollidingObjects[1] == null) {
				if (firstCollidingObjects[0] instanceof Ship) {
					Ship ship = (Ship)firstCollidingObjects[0];
					ship.collideWithBoundary();
				}
				if (firstCollidingObjects[0] instanceof Bullet) {
					Bullet bullet = (Bullet)firstCollidingObjects[0];
					bullet.collideWithBoundary();
				}
				collisionListener.boundaryCollision(firstCollidingObjects[0], positionFirstCollision[0], positionFirstCollision[1]);
			}
			else {
				if (firstCollidingObjects[0] instanceof Ship) {
					Ship ship = (Ship) firstCollidingObjects[0];
					ship.collisionCircularObject(firstCollidingObjects[1]);
				}
				
				if (firstCollidingObjects[0] instanceof Bullet) {
					Bullet bullet = (Bullet)firstCollidingObjects[0];
					bullet.collisionCircularObject(firstCollidingObjects[1]);
				}
				collisionListener.objectCollision(firstCollidingObjects[0], firstCollidingObjects[1], positionFirstCollision[0], positionFirstCollision[1]);
			}

			deltaT = deltaT - tC;
			tC = this.getTimeNextCollision();

			positionFirstCollision = this.getPositionNextCollision();
			firstCollidingObjects = this.getNextCollidingObjects();
		}
		for (CircularObject object : this.getAllCircularObjectsInWorld()) object.move(dt);
	}
}
