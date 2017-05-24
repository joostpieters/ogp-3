package asteroids.model.programs;

import java.util.*;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class AsteroidsExpression extends CircObj{
	
	public Ship closestShip;
	private Set<Asteroid> AsteroidsInWorld = new HashSet<Asteroid>();


	public AsteroidsExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Asteroid calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		AsteroidsInWorld = currentship.getWorld().getAllAsteroidsInWorld();
		Optional<Asteroid> closest = AsteroidsInWorld.stream().
		reduce((asteroid1, asteroid2) -> (currentship.getDistanceBetween(asteroid1) < currentship.getDistanceBetween(asteroid2) ? asteroid1 : asteroid2));
		if (closest.isPresent()) return closest.get();
		return null;
	}

	
}
