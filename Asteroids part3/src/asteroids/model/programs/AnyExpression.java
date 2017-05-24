package asteroids.model.programs;

import java.util.*;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends CircObj{
	
	public Ship closestShip;
	private Set<CircularObject> CircularObjectsInWorld = new HashSet<CircularObject>();


	public AnyExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public CircularObject calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		CircularObjectsInWorld = currentship.getWorld().getAllCircularObjectsInWorld();
		Optional<CircularObject> co = CircularObjectsInWorld.stream().findAny();
		if (co.isPresent()) return co.get();
		return null;
	}
	

	
}
