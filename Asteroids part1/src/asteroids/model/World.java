package asteroids.model;


import java.util.HashSet;
import java.util.Set;






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
	
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (bullet.getWorld() == null) throw new IllegalArgumentException("This bullet does not belong to a world. ");
		bullet.setWorld(null);
	}
	
	public void removeShip(Ship ship) throws IllegalArgumentException{
		if(ship.getWorld() == null) throw new IllegalArgumentException("The ship does not belong to a world");
		ship.setWorld(null);
	}
	
	public void terminateWorld() {
		for (Ship ship : shipsInWorld) {
			this.removeShip(ship);
		}
		for (Bullet bullet : bulletsInWorld) {
			this.removeBullet(bullet);
		}
		this.isTerminated = true;
	}
	
	public boolean isWorldTerminated() {
		return this.isTerminated;
	}
	
	
}
