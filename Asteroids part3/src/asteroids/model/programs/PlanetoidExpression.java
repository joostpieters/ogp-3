package asteroids.model.programs;

import java.util.*;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class PlanetoidExpression extends CircObj{
	
	public Ship closestShip;
	private Set<Planetoid> PlanetoidsInWorld = new HashSet<Planetoid>();


	public PlanetoidExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Planetoid calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		PlanetoidsInWorld = currentship.getWorld().getAllPlanetoidsInWorld();
		Optional<Planetoid> closest = PlanetoidsInWorld.stream().
				reduce((planetoid1, planetoid2) -> (currentship.getDistanceBetween(planetoid1) < currentship.getDistanceBetween(planetoid2) ? planetoid1 : planetoid2));
		if (closest.isPresent()) return closest.get();
		return null;

	}
}
	