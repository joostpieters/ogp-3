package asteroids.model.programs;

import java.util.*;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class PlanetExpression extends CircObj{
	
	public Ship closestShip;
	private Set<MinorPlanet> PlanetsInWorld = new HashSet<MinorPlanet>();


	public PlanetExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public MinorPlanet calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		PlanetsInWorld = currentship.getWorld().getAllMinorPlanetsInWorld();
		Optional<MinorPlanet> closestPlanet = PlanetsInWorld.stream().
				reduce((planet1, planet2) -> (currentship.getDistanceBetween(planet1) < currentship.getDistanceBetween(planet2) ? planet1 : planet2));
		if (closestPlanet.isPresent()) return (MinorPlanet)closestPlanet.get();
		return null;
	}
	

	
}
