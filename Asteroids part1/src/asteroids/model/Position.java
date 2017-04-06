package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

@Value
public class Position {
	public Position(double x, double y) throws IllegalArgumentException {
		if (!isValidPosition(x)) throw new IllegalArgumentException("Position called with an invalid position for x");
		if (!isValidPosition(y)) throw new IllegalArgumentException("Position called with an invalid position for y");
		this.setPositionX(x);
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
	
	public void setPositionX(double x) throws IllegalArgumentException {
		if (!isValidPosition(x)) throw new IllegalArgumentException("setPositionX called with invalid argument");
		this.x = x;
	}
	
	public void setPositionY(double y) throws IllegalArgumentException {
		if (!isValidPosition(y)) throw new IllegalArgumentException("setPositionY called with invalid argument");
		this.y = y;
	}

	
	public double getPositionX() {
		return this.x;
	}
	
	public double getPositionY() {
		return this.y;
	}
	
	public Position getPosition() {
		return new Position(this.x, this.y);
	}
	

}
