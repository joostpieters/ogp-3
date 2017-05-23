package asteroids.model;

public class Asteroid extends MinorPlanet {
	
	private final double DENSITY = 2.65E12;
	
	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
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
	
	
	public double getDensity() {
		return DENSITY;
	}
}
