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
	 * Collection of planetoids currently in the world
	 */
	private Set<Planetoid> planetoidsInWorld = new HashSet<Planetoid>();
	
	/**
	 * Collection of asteroids currently in the world
	 */
	private Set<Asteroid> asteroidsInWorld = new HashSet<Asteroid>();
	
	/**
	 * Collection of minor planets currently in the world
	 */
	private Set<MinorPlanet> minorPlanetsInWorld = new HashSet<MinorPlanet>();
	
	
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
	 * Return a set of all planetoids in the given world
	 * @Return The collection of all planetoids
	 * 			|result == planetoidsInWorld
	 */
	public Set<Planetoid> getAllPlanetoidsInWorld() {
		return planetoidsInWorld;
	}
	/**
	 * Return a set of all asteroids in the given world
	 * @Return The collection of all asteroids
	 * 			|result == asteroidsInWorld
	 */
	public Set<Asteroid> getAllAsteroidsInWorld() {
		return asteroidsInWorld;
	}
	/**
	 * Return a set of all minor planets in the given world
	 * @Return The collection of all minor planets
	 * 			|result == minorPlanetsInWorld
	 */
	public Set<MinorPlanet> getAllMinorPlanetsInWorld() {
		minorPlanetsInWorld.addAll(planetoidsInWorld); 
		minorPlanetsInWorld.addAll(asteroidsInWorld);
		return minorPlanetsInWorld;
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
		circularObjectsInWorld.addAll(planetoidsInWorld);
		circularObjectsInWorld.addAll(asteroidsInWorld);
		circularObjectsInWorld.addAll(minorPlanetsInWorld);
		return circularObjectsInWorld;
	}
	
	///
	///ADD ELEMENTS TO THE WORLD
	///
	
	/**
	 * Boolean method that indicates whether or not the circular object in question can be part of this world
	 * @param object
	 * @see implementation
	 */
	private boolean validCircularObject(CircularObject object) {
		if (object.getWorld() != this && object.getWorld() != null) return false;
		if (object instanceof Bullet) {
			Bullet bullet = (Bullet) object;
			if (bullet.getSourceShip() != null) return false;
		}
		if(object.isTerminated() || this.isTerminated) return false;
		for (CircularObject object2 : this.getAllCircularObjectsInWorld()) {
			if (object.overlap(object2)) return false;
		}
		return true;
	}

	/**
	 * checks if the circular object is within boundaries of the world
	 * @param object
	 * @see implementation
	 */
	private boolean circularObjectOutOfBound(CircularObject object) {
		if (object.getPositionArray()[0] - object.getRadius() <= 0 || object.getPositionArray()[0] + object.getRadius() >= this.getWorldDimensionArray()[0]) return true;
		if (object.getPositionArray()[1] - object.getRadius() <= 0 || object.getPositionArray()[1] + object.getRadius() >= this.getWorldDimensionArray()[1]) return true;
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
		if (!validCircularObject(ship) || circularObjectOutOfBound(ship)) throw new IllegalArgumentException("This ship can not be added to the world.");
		ship.setWorld(this);
		shipsInWorld.add(ship);
	}
	
	public void addPlanetoidToWorld(Planetoid planetoid) {
		if (!validCircularObject(planetoid) || circularObjectOutOfBound(planetoid)) throw new IllegalArgumentException("This planetoid cannot be added to the world.");
		planetoid.setWorld(this);
		planetoidsInWorld.add(planetoid);
	}
	
	
	public void addAsteroidToWorld(Asteroid asteroid) {
		if (!validCircularObject(asteroid) || circularObjectOutOfBound(asteroid)) throw new IllegalArgumentException("This planetoid cannot be added to the world.");
		asteroid.setWorld(this);
		asteroidsInWorld.add(asteroid);
	}

	
	/**
	 * Add a ship to the given world
	 * @param ship
	 * 		The ship that will be added to the world
	 * @Pre A Ship is located at most in one World. In other words, the ship should not be part of another world or added twice
	 * @post The ship is added to the world
	 */
	public void addBulletToWorld(Bullet bullet) throws IllegalArgumentException {
		if (!validCircularObject(bullet) || circularObjectOutOfBound(bullet)) throw new IllegalArgumentException("This bullet cannot be added to the world.");
		bullet.setWorld(this);
		bulletsInWorld.add(bullet);
	}
	
	/**
	 * Method to remove a bullet from this world
	 * @param bullet
	 * @post The bullet is removed from the world
	 * 		|this.circularObjectsInWorld.remove(bullet);
	 *		|bullet.setWorld(null);
	 * @throws IllegalArgumentException
	 * 			The bullet doesn't belong to this world
	 * 			|bullet.getWorld() != this
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
	 * @post The ship is removed from the world
	 * 		|shipsInWorld.remove(ship);
	 *		|this.circularObjectsInWorld.remove(ship);
	 * @throws IllegalArgumentException
	 * 			The ship does not belong to this world
	 * 			|ship.getWorld() != this
	 */
	public void removeShip(Ship ship) throws IllegalArgumentException{
		if(ship.getWorld() != this) throw new IllegalArgumentException("The ship does not belong to this world");
		shipsInWorld.remove(ship);
		this.circularObjectsInWorld.remove(ship);
		ship.setWorld(null);
	}
	
	//TODO
	public void removePlanetoid(Planetoid planetoid) {
		if(planetoid.getWorld() != this) throw new IllegalArgumentException("The planetoid does not belong to this world");
		planetoidsInWorld.remove(planetoid);
		circularObjectsInWorld.remove(planetoid);
		planetoid.setWorld(null);
		
	}
	//TODO
	public void removeAsteroid(Asteroid asteroid) {
		if(asteroid.getWorld() != this) throw new IllegalArgumentException("The asteroid does not belong to a world");
		asteroidsInWorld.remove(asteroid);
		circularObjectsInWorld.remove(asteroid);
		asteroid.setWorld(null);
	}
		
	/**
	 * Method that removes all ships and bullets from the world
	 * @post	Ships and bullets are removed.
	 * 			|this.removeShip(ship)
	 * 			|this.removeBullet(bullet)
	 */
	public void terminateWorld() {
		for (Ship ship : shipsInWorld) {
			this.removeShip(ship);
		}
		for (Bullet bullet : bulletsInWorld) {
			this.removeBullet(bullet);
		}
		for (Planetoid planetoid : planetoidsInWorld) {
			this.removePlanetoid(planetoid);
		}
		
		for (Asteroid asteroid : asteroidsInWorld) {
			this.removeAsteroid(asteroid);
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
					timeNextCollision = Math.min(timeNextCollision, object1.getTimeToCollision(object2));
				}
			}
		}
		return timeNextCollision;
	}
	/**
	 * Method that returns the position of the next collision
	 * @return	the position of the next collision
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
	 */
	public void evolve(double dt, CollisionListener collisionListener) throws IllegalArgumentException {
		if (dt < 0 || Double.isNaN(dt)) throw new IllegalArgumentException("Given time is not valid.");
		if (collisionListener == null) throw new IllegalArgumentException("Method evolve called with collisionListener that equals null.");
		double deltaT = dt;
		double tC = this.getTimeNextCollision();
		CircularObject[] firstCollidingObjects = this.getNextCollidingObjects();
		double[] positionFirstCollision = this.getPositionNextCollision();
		while (tC <= deltaT) {
			for (CircularObject object1 : this.getAllCircularObjectsInWorld()) object1.move(tC);
			if (firstCollidingObjects[1] == null) {
				//if (firstCollidingObjects[0] instanceof Ship) {
					//Ship ship = (Ship)firstCollidingObjects[0];
					//ship.collideWithBoundary();
				//}
				//if (firstCollidingObjects[0] instanceof Bullet) {
					//Bullet bullet = (Bullet)firstCollidingObjects[0];
					//bullet.collideWithBoundary();
				//}
				firstCollidingObjects[0].collideWithBoundary();
				collisionListener.boundaryCollision(firstCollidingObjects[0], positionFirstCollision[0], positionFirstCollision[1]);
			}
			else {
				//if (firstCollidingObjects[0] instanceof Ship) {
					//Ship ship = (Ship) firstCollidingObjects[0];
					//ship.collisionCircularObject(firstCollidingObjects[1]);
				//}
				
				//if (firstCollidingObjects[0] instanceof Bullet) {
					//Bullet bullet = (Bullet)firstCollidingObjects[0];
					//bullet.collisionCircularObject(firstCollidingObjects[1]);
				//}
				firstCollidingObjects[0].collisionCircularObject(firstCollidingObjects[1].getClass().cast(firstCollidingObjects[1]));
				collisionListener.objectCollision(firstCollidingObjects[0], firstCollidingObjects[1], positionFirstCollision[0], positionFirstCollision[1]);
			}

			deltaT = deltaT - tC;
			tC = this.getTimeNextCollision();

			positionFirstCollision = this.getPositionNextCollision();
			firstCollidingObjects = this.getNextCollidingObjects();
		}
		for (CircularObject object : this.getAllCircularObjectsInWorld()) object.move(deltaT);
	}



}
