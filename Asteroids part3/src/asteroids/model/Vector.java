package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A class to represent a vector
 */

@Value
public class Vector {
	/**
	 * Initialize a vector with an x (array[0]) component and a y (array[1]) component
	 * @param array
	 * 			An array of size 2 that we want to be interpreted as a vector
	 * @throws IllegalArgumentException
	 * 			x component has to be finite
	 * 			|!isValidComponent(array[0])
	 * @throws IllegalArgumentException
	 * 			y component has to be finite
	 * 			|!isValidComponent(array[1])
	 */
	public Vector(double[] array) throws IllegalArgumentException {
		if (!isValidComponent(array[0])) throw new IllegalArgumentException("X component invalid!");
		if (!isValidComponent(array[1])) throw new IllegalArgumentException("Y component invalid!");
		this.setXComponent(array[0]);
		this.setYComponent(array[1]);
	}
	
	/**
	 * Variable for the x component of the vector
	 */
	private double x;
	
	/**
	 * Variable for the y component of the vector
	 */
	private double y;
	
	/**
	 * Checks whether the given value is a valid component value
	 * @param value
	 * 			The value to be checked
	 * @see implementation
	 */
	public boolean isValidComponent(double value) {
		return Double.isFinite(value);
	}
	/**
	 * Returns the x component of the vector
	 * @see implementation
	 *
	 */
	public double getXComponent() {
		return this.x;
	}
	/**
	 * Returns the y component of the vector
	 * @see implementation
	 *
	 */
	public double getYComponent() {
		return this.y;
	}
	/**
	 * Sets the x component of the vector to x
	 * @param x
	 * 			The value of the x component
	 * @throws IllegalArgumentException
	 * 			|!isValidComponent(x)
	 */
	public void setXComponent(double x) throws IllegalArgumentException {
		if (!isValidComponent(x)) throw new IllegalArgumentException("Invalid x value for vector!");
		this.x = x;
	}
	/**
	 * Sets the x component of the vector to y
	 * @param x
	 * 			The value of the y component
	 * @throws IllegalArgumentException
	 * 			|!isValidComponent(y)
	 */
	public void setYComponent(double y) {
		if (!isValidComponent(y)) throw new IllegalArgumentException("Invalid y value for vector!");
		this.y = y;
	}
	
	/**
	 * Adds two vectors together
	 * @param otherVector
	 * 			The vector to be added to this vector
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 			|otherVector == null
	 */
	public Vector addVectors(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) throw new IllegalArgumentException("addVectors called with non-existent vector.");
		
		return new Vector(new double[] {this.getXComponent() + otherVector.getXComponent(), this.getYComponent() + otherVector.getYComponent()});
	}
	/**
	 * Subtracts two vectors
	 * @param otherVector
	 * 			The vector to be subtracted from this vector
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 			|otherVector == null
	 */
	public Vector subtractVectors(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) throw new IllegalArgumentException("subtractVectors called with non-existent vector.");
		return new Vector(new double[] {this.getXComponent() - otherVector.getXComponent(), this.getYComponent() - otherVector.getYComponent()});
	}
	/**
	 * Multiplies two vectors
	 * @param otherVector
	 * 			The vector we want to multiply with this vector
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 			|otherVector == null
	 */
	public Vector multiplyVectors(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) throw new IllegalArgumentException("multiplyVectors called with non-existent vector.");
		return new Vector(new double[] {this.getXComponent() * otherVector.getXComponent(), this.getYComponent() * otherVector.getYComponent()});
	}
	/**
	 * Calculates the dot product of two vectors
	 * @param otherVector
	 * 			The vector we want to calculate the dot product of with this vector
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 			|otherVector == null
	 */
	public double dotProductVectors(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) throw new IllegalArgumentException("dotProductVectors called with non-existent vector.");
		return this.getXComponent() * otherVector.getXComponent() + this.getYComponent() * otherVector.getYComponent();
	}
	/**
	 * Returns the result of multiplying this vector with a scalar
	 * @param scalar
	 * @see implementation
	 */
	public Vector scalarProductVectors(double scalar) {
		return new Vector(new double[] {scalar * this.getXComponent(), scalar * this.getYComponent()});
	}
	
	public double[] vectorArray() {
		return new double[] {this.x, this.y};
	}
}

