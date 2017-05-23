package asteroids.model;

public abstract class MinorPlanet extends CircularObject{

	private final double minimumRadius = 5;
	
	public MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public double getMass() {
		return 4/3*Math.PI * Math.pow(this.getRadius(), 3) * getDensity();
	}

	@Override
	public double getMinimalRadius() {
		return minimumRadius;
	}
	
	public void collide(CircularObject object2) {
		if (object2 instanceof MinorPlanet) {
			double sumOfRadiusses = this.getRadius() + object2.getRadius();
			double sumOfMasses = this.getMass() + object2.getMass();
			
			Vector deltaV = new Vector(this.velocity.getDifferenceInVelocity(object2.velocity));
			Vector deltaR = new Vector(this.position.getDifferenceInPositions(object2.position));
			
			double J = 2 * this.getMass() * object2.getMass() * (deltaV.dotProductVectors(deltaR))
					/(sumOfRadiusses*sumOfMasses);
			
			double jX = J * this.position.getDifferenceInPositions(object2.position)[0]
					/ sumOfRadiusses;
			
			double jY = J * this.position.getDifferenceInPositions(object2.position)[1]
					/ sumOfRadiusses;
			
			double newXVelocityThisObject = this.getVelocityArray()[0] + jX/this.getMass();
			double newYVelocityThisObject = this.getVelocityArray()[1] + jY/this.getMass();
			double newXVelocityObject2 = object2.getVelocityArray()[0] - jX/object2.getMass();
			double newYVelocityObject2 = object2.getVelocityArray()[1] - jY/object2.getMass();		
			this.setVelocity(newXVelocityThisObject, newYVelocityThisObject);
			object2.setVelocity(newXVelocityObject2, newYVelocityObject2);
		}
		
	}
}
