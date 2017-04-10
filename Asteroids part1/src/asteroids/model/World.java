package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

public class World {
	
	private double width;
	private double height;
	
	public World(double width, double height) {
		this.setHeight(height);
		this.setWidth(width);
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
}
