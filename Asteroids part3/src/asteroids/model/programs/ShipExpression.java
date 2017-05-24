package asteroids.model.programs;

import asteroids.model.CircularObject;
import java.util.*;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ShipExpression extends CircObj{
	
	public Ship closestShip;
	private Set<Ship> shipsInWorld = new HashSet<Ship>();


	public ShipExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Ship calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		shipsInWorld = currentship.getWorld().getAllShipsInWorld();
		Optional<Ship> closestShip = shipsInWorld.stream().filter(ship -> !ship.equals(currentship)).
			reduce((ship1, ship2) -> (currentship.getDistanceBetween(ship1) < currentship.getDistanceBetween(ship2) ? ship1 : ship2));
		if (closestShip.isPresent()) return closestShip.get();
		return null;
	}
	
	
	
}
