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
	private Set<Ship> bulletsInWorld = new HashSet<Ship>();
	
	
	
	
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
	
	
}
