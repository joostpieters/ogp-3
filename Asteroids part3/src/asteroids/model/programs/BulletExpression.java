package asteroids.model.programs;

import java.util.*;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends CircObj{
	
	public Ship closestShip;
	private Set<Bullet> BulletsInWorld = new HashSet<Bullet>();


	public BulletExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Bullet calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		BulletsInWorld = currentship.getWorld().getAllBulletsInWorld();
		Optional<Bullet> closest = currentship.getWorld().getAllBulletsInWorld().stream().filter(bullet -> bullet.getSourceShip() == currentship).
				reduce((bullet1, bullet2) -> (currentship.getDistanceBetween(bullet1) < currentship.getDistanceBetween(bullet2) ? bullet1 : bullet2));
		if (closest.isPresent()) return closest.get();
		return null;
	}

	
}
