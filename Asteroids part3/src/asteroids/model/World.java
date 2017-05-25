package asteroids.model;


import java.util.HashSet;
import java.util.Iterator;
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
	 * @effect The given width and height are set to the ones of the world
	 * 		| this.width = width
	 * 		| this.height = height
	 */
	public World(double width, double height){
		if (height < 0) {
			height = Math.abs(height);
		}
		if (width < 0) {
			width = Math.abs(width);
		}
		
		if (!validSize(height)) {
			height = Double.MAX_VALUE;
		}
		if (!validSize(width)) {
			width = Double.MAX_VALUE;
		}
		this.width = width;
		this.height = height;
	}
	
	public boolean validSize(double size) {
		if (size > 0 && Double.isFinite(size)) return true;
		else return false;
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
		Set<Ship> ships = new HashSet<Ship>();
		ships.addAll(shipsInWorld);
		return shipsInWorld;
	}
	
	/**
	 * Return a set of all Ships in the given world
	 * @Return The collection of all ships
	 * 			|result == shipsInWorld
	 */
	public Set<Bullet> getAllBulletsInWorld(){
		Set<Bullet> bullets = new HashSet<Bullet>();
		bullets.addAll(bulletsInWorld);
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
	 * Return a set of all circular objects in the given world
	 * @Return The collection of all circular objects
	 * 			|result == circularObjectsInWorld
	 */
	public Set<CircularObject> getAllCircularObjectsInWorld() {
		circularObjectsInWorld.addAll(bulletsInWorld);
		circularObjectsInWorld.addAll(shipsInWorld);
		circularObjectsInWorld.addAll(planetoidsInWorld);
		circularObjectsInWorld.addAll(asteroidsInWorld);

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
		if (object == null) return false;
		if (object.getWorld() != null && object.getWorld() != this) return false;

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
	public boolean circularObjectOutOfBound(CircularObject object) {
		if (object.getPositionArray()[0] - object.getRadius() <= 0 || object.getPositionArray()[0] + object.getRadius() >= this.getWorldDimensionArray()[0]) return true;
		if (object.getPositionArray()[1] - object.getRadius() <= 0 || object.getPositionArray()[1] + object.getRadius() >= this.getWorldDimensionArray()[1]) return true;
		return false;
	}
	
	/**
	 * Add a ship to the given world
	 * @param ship
	 * 		The ship that will be added to the world
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 		|if (!validCircularObject(ship) || circularObjectOutOfBound(ship))
	 * 		
	 */
	public void addShipToWorld(Ship ship) throws IllegalArgumentException{
		if (!validCircularObject(ship) || circularObjectOutOfBound(ship)) throw new IllegalArgumentException("This ship can not be added to the world.");
		ship.setWorld(this);
		shipsInWorld.add(ship);
	}
	/**
	 * Method to add a planetoid to the world
	 * @param planetoid
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 		|if (!validCircularObject(planetoid) || circularObjectOutOfBound(planetoid))
	 */
	public void addPlanetoidToWorld(Planetoid planetoid) throws IllegalArgumentException{
		if (!validCircularObject(planetoid) || circularObjectOutOfBound(planetoid)) throw new IllegalArgumentException("This planetoid cannot be added to the world.");
		
		planetoid.setWorld(this);
		planetoidsInWorld.add(planetoid);
	}
	
	/**
	 * Method to add an asteroid to a world
	 * @param asteroid
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 		|if (!validCircularObject(asteroid) || circularObjectOutOfBound(asteroid))
	 */
	public void addAsteroidToWorld(Asteroid asteroid) throws IllegalArgumentException{
		if (!validCircularObject(asteroid) || circularObjectOutOfBound(asteroid)) throw new IllegalArgumentException("This asteroid cannot be added to the world.");
		asteroid.setWorld(this);
		asteroidsInWorld.add(asteroid);
	}

	
	/**
	 * Add a bullet to the given world
	 * @param ship
	 * 		The ship that will be added to the world
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 		|if (circularObjectOutOfBound(bullet) || !validCircularObject(bullet))
	 */
	public void addBulletToWorld(Bullet bullet) throws IllegalArgumentException {
		if (circularObjectOutOfBound(bullet) || !validCircularObject(bullet)) throw new IllegalArgumentException("This bullet is out of the world's bound.");
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
	 * @effect The ship is removed from the world
	 * 		|shipsInWorld.remove(ship);
	 *		|this.circularObjectsInWorld.remove(ship);
	 * @throws IllegalArgumentException
	 * 			The ship does not belong to this world
	 * 			|ship.getWorld() != this
	 */
	public void removeShip(Ship ship) throws IllegalArgumentException{
		if (ship == null) throw new IllegalArgumentException("removeShip called with non-existent ship.");
		if(ship.getWorld() != this) throw new IllegalArgumentException("The ship does not belong to this world");
		shipsInWorld.remove(ship);
		this.circularObjectsInWorld.remove(ship);
		ship.setWorld(null);
	}
	
	/**
	 * Method to remove a planetoid from a world
	 * @param planetoid
	 * @effect The planetoid is removed from the world
	 * 		|planetoidsInWorld.remove(planetoid)
	 * 		|minorPlanetsInWorld.remove(planetoid)
	 * 		|circularObjectsInWorld.remove(planetoid)
	 * @throws IllegalArgumentException
	 * 		|planetoid.getWorld() != this
	 */
	public void removePlanetoid(Planetoid planetoid) throws IllegalArgumentException{
		if(planetoid.getWorld() != this) throw new IllegalArgumentException("The planetoid does not belong to this world");
		planetoidsInWorld.remove(planetoid);
		circularObjectsInWorld.remove(planetoid);
		minorPlanetsInWorld.remove(planetoid);
		planetoid.setWorld(null);
		
	}
	/**
	 * Method to remove a asteroid from a world
	 * @param asteroid
	 * @effect The asteroid is removed from the world
	 * 		|asteroidsInWorld.remove(asteroid)
	 * 		|minorPlanetsInWorld.remove(asteroid)
	 * 		|circularObjectsInWorld.remove(asteroid)
	 * @throws IllegalArgumentException
	 * 		|asteroid.getWorld() != this
	 */
	public void removeAsteroid(Asteroid asteroid) {
		if(asteroid.getWorld() != this) throw new IllegalArgumentException("The asteroid does not belong to a world");
		asteroidsInWorld.remove(asteroid);
		circularObjectsInWorld.remove(asteroid);
		minorPlanetsInWorld.remove(asteroid);
		asteroid.setWorld(null);
	}
		
	/**
	 * Method that removes all ships and bullets from the world
	 * @see implementation	
	 */
	public void terminateWorld() {
		Set<Ship> ships = getAllShipsInWorld();
		while (! ships.isEmpty()) {
			this.removeShip(ships.iterator().next());
		}
		Set<Bullet> bullets = getAllBulletsInWorld();
		while (!bullets.isEmpty()) {
			this.removeBullet(bullets.iterator().next());
		}
		Set<Planetoid> planetoids = getAllPlanetoidsInWorld();
		while (!planetoids.isEmpty()) {
			this.removePlanetoid(planetoids.iterator().next());
		}
		Set<Asteroid> asteroids = getAllAsteroidsInWorld();
		while (!asteroids.isEmpty()) {
			this.removeAsteroid(asteroids.iterator().next());
		}

		circularObjectsInWorld.clear();
		minorPlanetsInWorld.clear();
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
	 * @return	Return null if there will be no collisions
	 * 			|if (collidingObjects[0] == null) return null;
	 * @return Return an array with a circular object at index 0 and null at index 1 if
	 * 			the next collision is with a boundary
	 * 			|if (collidingObjects[1] == null) return collidingObjects[0].getPositionCollisionBoundary()
	 * @return Return an array with 2 circular objects if the next collision is between 2 circular objects
	 * 			|return collidingObjects[0].getCollisionPosition(collidingObjects[1])
	 */
	public double[] getPositionNextCollision() {
		CircularObject[] collidingObjects = this.getNextCollidingObjects();
		if (collidingObjects[0] == null) return null;
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
		
		double deltaT = dt;
		if (!this.getAllCircularObjectsInWorld().isEmpty()) {
			double tC = getTimeNextCollision();
			if (tC != Double.POSITIVE_INFINITY) {
				CircularObject[] firstCollidingObjects = this.getNextCollidingObjects();
				double[] positionFirstCollision = this.getPositionNextCollision();
				while (tC <= deltaT) {
					for (CircularObject object1 : this.getAllCircularObjectsInWorld()) {
						if (object1 instanceof Ship) {
							Ship ship = (Ship) object1;
							if (ship.getProgram() != null) {
								ship.runProgram(tC);
							}
						}
						object1.move(tC);
						
					}
					if (firstCollidingObjects[1] == null) {
						if (collisionListener != null) {
							collisionListener.boundaryCollision(firstCollidingObjects[0], positionFirstCollision[0], positionFirstCollision[1]);
						}
						firstCollidingObjects[0].collideWithBoundary();
					}
					else {
						if (collisionListener != null) {
							collisionListener.objectCollision(firstCollidingObjects[0], firstCollidingObjects[1], positionFirstCollision[0], positionFirstCollision[1]);
						}
						firstCollidingObjects[0].collisionCircularObject(firstCollidingObjects[1]);
					}

					deltaT = deltaT - tC;
					tC = this.getTimeNextCollision();
					
					if (tC != Double.POSITIVE_INFINITY) {
						positionFirstCollision = this.getPositionNextCollision();
						firstCollidingObjects = this.getNextCollidingObjects();
					}
				}
			}
			for (CircularObject object1 : this.getAllCircularObjectsInWorld()) {
				if (object1 instanceof Ship) {
					Ship ship = (Ship) object1;
					if (ship.getProgram() != null) {
						ship.runProgram(tC);
					}
				}
				object1.move(deltaT);
			}
				
			
		}
		
	}
}
