package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

@Value
public class Position {
	/**
	 * Initialize a position with an x and y value
	 * @param x Position on the x-axis
	 * @param y Position on the y-axis
	 * @throws IllegalArgumentException
	 * 			If x is not a valid position
	 * 			|!isValidPosition(x)
	 * @throws IllegalArgumentException
	 * 			If y is not a valid position
	 * 			|!isValidPosition(y)
	 */
	public Position(double x, double y) throws IllegalArgumentException {
		if (!isValidPosition(x)) throw new IllegalArgumentException("Position called with an invalid position for x");
		if (!isValidPosition(y)) throw new IllegalArgumentException("Position called with an invalid position for y");
		this.setPosition(x,y);
	}
	/**
	 * Check if the given coordinates are valid to be assigned to the position.
	 * @param  x
	 *			The x-coordinate of the given position
	 * @param y
	 * 			The y-coordinate of the given position
	 * @return Returns if the position is valid.
	 *         | result = (Double.isNaN(x)||(Double.isNaN(y))
	 */
	public boolean isValidPosition(double value) {
		if (Double.isNaN(value))
		{
			return false;
		}
		else 
			return true;
	}
	
	private double x;
	private double y;
	/**
	 * Method to set the x value of the position
	 * @param x The position on the x axis
	 * @throws IllegalArgumentException
	 * 			If the given value for x is not valid
	 * 			|!isValidPosition(x)
	 */
	public void setPositionX(double x) throws IllegalArgumentException {
		if (!isValidPosition(x)) throw new IllegalArgumentException("setPositionX called with invalid argument");
		this.x = x;
	}
	/**
	 * Method to set the y value of the position
	 * @param y The position on the y axis
	 * @throws IllegalArgumentException
	 * 			If the given value for y is not valid
	 * 			|!isValidPosition(y)
	 */
	public void setPositionY(double y) throws IllegalArgumentException {
		if (!isValidPosition(y)) throw new IllegalArgumentException("setPositionY called with invalid argument");
		this.y = y;
	}
	/**
	 * Method to set both the x and y value of the position
	 * @param x The position on the x axis
	 * @param y The position on the y axis
	 * @throws IllegalArgumentException
	 * 			If the given value for x is not valid
	 * 			|!isValidPosition(x)
	 * @throws IllegalArgumentException
	 * 			If the given value for y is not valid
	 * 			|!isValidPosition(y)
	 */
	@Basic
	public void setPosition(double x, double y) throws IllegalArgumentException {
		if (!isValidPosition(x)) throw new IllegalArgumentException("setPosition called with invalid argument for x");
		if (!isValidPosition(y)) throw new IllegalArgumentException("setPosition called with invalid argument for y");
		this.setPositionX(x);
		this.setPositionY(y);
	}
	/**
	 * Method that returns the position on the x axis
	 * @see implementation
	 */
	@Basic
	public double getPositionX() {
		return this.x;
	}
	/**
	 * Method that returns the position on the y axis
	 * @see implementation
	 */
	public double getPositionY() {
		return this.y;
	}
	/**
	 * Method that returns the position
	 * @see implementation
	 */
	public Position getPosition() {
		return new Position(this.x, this.y);
	}
	
	/**
	 * Method that returns the position as an array of length 2
	 * @see implementation
	 */
	public double[] getPositionArray() {
		return new double[] {this.x, this.y};
	}
	
	/**
	 * Returns the difference in positions (both x and y) between this position and the other position.
	 * @param otherPosition The other position we use and want to know the difference to this position from 
	 * @see implementation
	 * @throws IllegalArgumentException
	 * 			otherPosition is not created
	 * 			|otherPosition == null
	 * @throws IllegalArgumentException
	 * 			Value for x of the otherPosition is not valid
	 * 			|!isValidPosition(otherPosition.getPositionArray()[0])
	 * @throws IllegalArgumentException
	 * 			Value for y of the otherPosition is not valid
	 * 			|!isValidPosition(otherPosition.getPositionArray()[1])
	 */
	public double[] getDifferenceInPositions(Position otherPosition) throws IllegalArgumentException {
		if (otherPosition == null) throw new IllegalArgumentException("getDifferenceInPositions called with a non-existing position!");
		if (!isValidPosition(otherPosition.getPositionArray()[0])) throw new IllegalArgumentException("getDifferenceInPositions called with position with invalid value for x");
		if (!isValidPosition(otherPosition.getPositionArray()[1])) throw new IllegalArgumentException("getDifferenceInPositions called with position with invalid value for y");
		
		double[] positionThisShip = this.getPositionArray();
		double[] positionShip2 = otherPosition.getPositionArray();
		
		return new double[] {positionThisShip[0] - positionShip2[0], positionThisShip[1] - positionShip2[1]};
	}

}
