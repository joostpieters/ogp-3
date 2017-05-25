package asteroids.model.programs;

import java.util.*;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends CircObj{
	
	
	//Initialize variables
	public Ship closestShip;
	private Set<CircularObject> CircularObjectsInWorld = new HashSet<CircularObject>();

	//Constructor for AnyExpression
	public AnyExpression(SourceLocation location) {
		super(location);
	}

	//Calculate i.e. return a random circular object from the world
	@Override
	public CircularObject calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		CircularObjectsInWorld = currentship.getWorld().getAllCircularObjectsInWorld();
		Optional<CircularObject> cobj = CircularObjectsInWorld.stream().findAny();
		if (cobj.isPresent()) return cobj.get();
		return null;
	}
	

	
}
