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
	private double x;
	private double y;

	public Vector(double x, double y) throws IllegalArgumentException {
		if (!validComponent(x)) throw new IllegalArgumentException("X component invalid!");
		if (!validComponent(y)) throw new IllegalArgumentException("Y component invalid!");
		this.setXComponent(x);
		this.setYComponent(y);
	}
	
	public boolean validComponent(double value) {
		return Double.isFinite(value);
	}

	public double getXComponent() {
		return this.x;
	}
	
	public double getYComponent() {
		return this.y;
	}
	
	public void setXComponent(double x) throws IllegalArgumentException {
		if (!validComponent(x)) throw new IllegalArgumentException("Invalid x value for vector!");
		this.x = x;
	}
	
	public void setYComponent(double y) {
		if (!validComponent(y)) throw new IllegalArgumentException("Invalid y value for vector!");
		this.y = y;
	}
	
	public Vector addVectors(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) throw new IllegalArgumentException("addVectors called with non-existent vector.");
		return new Vector(this.getXComponent() + otherVector.getXComponent(), this.getYComponent() + otherVector.getYComponent());
	}
	
	public Vector multiplyVectors(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) throw new IllegalArgumentException("multiplyVectors called with non-existent vector.");
		return new Vector(this.getXComponent() * otherVector.getXComponent(), this.getYComponent() * otherVector.getYComponent());
	}

	public double dotProductVectors(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) throw new IllegalArgumentException("dotProductVectors called with non-existent vector.");
		return this.getXComponent() * otherVector.getXComponent() + this.getYComponent() * otherVector.getYComponent();
	}
	
	public Vector scalarProductVectors(double scalar) {
		return new Vector(scalar * this.getXComponent(), scalar * this.getYComponent());
	}
}

