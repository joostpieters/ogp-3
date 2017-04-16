package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

@Value
public class Velocity {
	/**
	 * Initializes a velocity
	 * @param xVelocity The velocity along the x axis
	 * @param yVelocity The velocity along the y axis
	 * @throws IllegalArgumentException
	 */
	public Velocity(double xVelocity, double yVelocity) throws IllegalArgumentException {
		if (!isValidVelocity(xVelocity)) throw new IllegalArgumentException("Velocity called with invalid value for the x velocity");
		if (!isValidVelocity(yVelocity)) throw new IllegalArgumentException("Velocity called with invalid value for the y velocity");
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;	
	}
	
	private double xVelocity;
	private double yVelocity;
	
	/**
	 * Check if the given coordinates are valid to be assigned to the velocity.
	 * @param  x
	 *			The x-coordinate of the given velocity
	 * @param y
	 * 			The y-coordinate of the given velocity
	 * @return Returns if the position is valid.
	 *         | result = (Double.isNaN(x)||(Double.isNaN(y))
	 */
	public boolean isValidVelocity(double value) {
		if (Double.isNaN(value))
		{
			return false;
		}
		else 
			return true;
	}
	
	/**
	 * Method to set the velocity, both along the x and y axis
	 * @param xVel The velocity along the x axis
	 * @param yVel The velocity along the y axis
	 * @throws IllegalArgumentException
	 * 			If the given xVel is not a valid value for the velocity along an axis
	 * 			|!isValidVelocity(xVel)
	 * @throws IllegalArgumentException
	 * 			If the given yVel is not a valid value for the velocity along an axis
	 * 			|!isValidVelocity(yVel)
	 * @post
	 * 			|new.getVelocity() == new Velocity(x,y)
	 */
	@Basic
	public void setVelocity(double xVel, double yVel) throws IllegalArgumentException {
		if (!isValidVelocity(xVel)) throw new IllegalArgumentException("setVelocity called with invalid x velocity");
		if (!isValidVelocity(yVel)) throw new IllegalArgumentException("setVelocity called with invalid y velocity");
		this.xVelocity = xVel;
		this.yVelocity = yVel;
	}
	
	/**
	 * Method to only set the x value of the velocity
	 * @param xVel The velocity along the x axis
	 * @throws IllegalArgumentException
	 * 			If the given xVel is not a valid value
	 * 			|!isValidVelocity(xVel)
	 * @post
	 * 			|new.getXVelocity == xVel
	 */
	@Basic
	public void setXVelocity(double xVel) throws IllegalArgumentException {
		if (!isValidVelocity(xVel)) throw new IllegalArgumentException("setXVelocity called with invalid x velocity");
		this.xVelocity = xVel;
	}
	
	/**
	 * Method to only set the y value of the velocity
	 * @param yVel The velocity along the y axis
	 * @throws IllegalArgumentException
	 * 			If the given yVel is not a valid value
	 * 			|!isValidVelocity(yVel)
	 * @post
	 * 			|new.getYVelocity == yVel
	 */
	@Basic
	public void setYVelocity(double yVel) throws IllegalArgumentException {
		if (!isValidVelocity(yVel)) throw new IllegalArgumentException("setYVelocity called with invalid y velocity");
		this.yVelocity = yVel;
	}
	
	/**
	 * Method that returns the velocity
	 * @see implementation
	 */
	@Basic
	public Velocity getVelocity() {
		return new Velocity(this.xVelocity, this.yVelocity);
	}
	
	/**
	 * Method that returns the velocity along the x axis
	 * @see implementation
	 */
	@Basic
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	/**
	 * Method that returns the velocity along the y axis
	 * @see implementation
	 */
	@Basic
	public double getYVelocity() {
		return this.yVelocity;
	}
	@Basic
	
	/**
	 * Method that returns the velocity as an array of length 2
	 * @see implementation
	 */
	public double[] getVelocityArray() {
		return new double[] {this.xVelocity, this.yVelocity};
	}
	
	/**
	 * Returns the difference in velocities (both x and y) between this velocity and otherVelocity
	 * @param otherVelocity The velocity of which we want to know the difference from this velocity
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 			otherVelocity is not created
	 * 			|otherVelocity == null
	 * @throws IllegalArgumentException
	 * 			X velocity of otherVelocity is not valid
	 * 			|!isValidVelocity(otherVelocity.getVelocityArray()[0])
	 * @throws IllegalArgumentException
	 * 			Y velocity of otherVelocity is not valid
	 * 			|!isValidVelocity(otherVelocity.getVelocityArray()[1])
	 */
	public double[] getDifferenceInVelocity(Velocity otherVelocity) throws IllegalArgumentException {
		if (otherVelocity == null) throw new IllegalArgumentException("getDifferenceInVelocity called with a non-existing velocity!");
		if (!isValidVelocity(otherVelocity.getVelocityArray()[0])) 
			throw new IllegalArgumentException("getDifferenceInVelocity called with a velocity that has an invalid x velocity");
		if (!isValidVelocity(otherVelocity.getVelocityArray()[1])) 
			throw new IllegalArgumentException("getDifferenceInVelocity called with a velocity that has an invalid y velocity");
		double[] velocityThisShip = this.getVelocityArray();
		double[] velocityShip2 = otherVelocity.getVelocityArray();
		
		return new double[] {velocityShip2[0]-velocityThisShip[0], velocityShip2[1]-velocityThisShip[1]};
	}
}
