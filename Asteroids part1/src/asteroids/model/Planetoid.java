package asteroids.model;

public class Planetoid extends MinorPlanet {

	private double distanceTraveled;
	private final double DENSITY = 0.917E12;
	
	
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double distanceTraveled) throws IllegalArgumentException{
		super(x, y, xVelocity, yVelocity, radius);
		setDistanceTraveled(distanceTraveled);
	}
	
	private void setDistanceTraveled(double distance){
		this.distanceTraveled = distance;
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
