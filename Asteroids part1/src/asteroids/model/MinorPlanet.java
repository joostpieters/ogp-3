package asteroids.model;

public class MinorPlanet extends CircularObject{

	private final double minimumRadius = 5;
	
	public MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMinimalRadius() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	
	
	
}
