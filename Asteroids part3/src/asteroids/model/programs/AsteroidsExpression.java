package asteroids.model.programs;

import java.util.*;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class AsteroidsExpression extends CircObj{
	
	
	//Initialize variables
	public Ship closestShip;
	private Set<Asteroid> AsteroidsInWorld = new HashSet<Asteroid>();


	//Constructor for AsteroidsExpression
	public AsteroidsExpression(SourceLocation location) {
		super(location);
	}

	//Calculate i.e. return the closests asteroids to the ship.
	@Override
	public Asteroid calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		AsteroidsInWorld = currentship.getWorld().getAllAsteroidsInWorld();
		Optional<Asteroid> closest = AsteroidsInWorld.stream().reduce((a1, a2) -> (currentship.getDistanceBetween(a1) < currentship.getDistanceBetween(a2) ? a1 : a2));
		if (closest.isPresent()) return closest.get();
		return null;
	}

	
}
