package asteroids.test;

import static org.junit.Assert.*;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.util.ModelException;
import asteroids.part1.facade.IFacade;

import org.junit.Before;
import org.junit.Test;


public class test{
	
	IFacade facade;
	
	private static final double EPSILON = 0.0001;
	
	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	@Test
	public void testCreateShip() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		assertNotNull(ship);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		assertEquals(20, facade.getShipRadius(ship), EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, -Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -20, -Math.PI);
	}
	
	@Test
	public void testGetPosition()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		double[] position = facade.getShipPosition(ship);
		assertEquals(100, position[0], EPSILON);
		assertEquals(100, position[1], EPSILON);
	}
	
	@Test
	public void testGetVelocity()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		double[] vel = facade.getShipVelocity(ship);
		assertEquals(30, vel[0], EPSILON);
		assertEquals(-15, vel[1], EPSILON);
		
	}
	
	@Test
	public void testGetRadius() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		double rad = facade.getShipRadius(ship);
		assertEquals(20, rad, EPSILON);
		
	}
	
	@Test
	public void testGetDirection()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		double dir = facade.getShipOrientation(ship);
		assertEquals(0, dir, EPSILON);
		
		
	}
	
	
	@Test
	public void testMove() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
	}
	@Test
	public void testTurn() throws ModelException {
		Ship ship = facade.createShip(100, 70, 23, 18, 20, 0.5*Math.PI);
		facade.turn(ship, 0.5*Math.PI);
		double dir = facade.getShipOrientation(ship);
		assertEquals(Math.PI, dir, EPSILON);
		
	}
	@Test
	public void testThrust() throws ModelException {
		Ship ship = facade.createShip(100, 70, 23, 18, 20,0);
		facade.thrust(ship, 3);
		double[] vel = facade.getShipVelocity(ship);
		assertEquals(26, vel[0], EPSILON);
		assertEquals(18, vel[1], EPSILON);
	}
	@Test
	public void testThrustlightspeed() throws ModelException {
		Ship ship = facade.createShip(100, 70, 23, 18, 20,0);
		facade.thrust(ship, 300005);
		double[] vel = facade.getShipVelocity(ship);
		assertEquals(300000, vel[0], EPSILON);
		assertEquals(0, vel[1], EPSILON);
	}
	
	@Test
	public void testGetDistanceBetweenOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0);
		double distanceBetweenOverlappingShips = facade.getDistanceBetween(ship1, shipOverlappingShip1);
		assertEquals(-19, distanceBetweenOverlappingShips, EPSILON);
	}
	
	@Test
	public void testGetDistanceBetweenSameShip() throws ModelException {
		Ship ship1 = facade.createShip(20, 20, 0, 0, 20, 0);
		double distanceBetweenSameShip = facade.getDistanceBetween(ship1, ship1);
		assertEquals(0, distanceBetweenSameShip , EPSILON);
	}
	
	@Test
	public void testGetDistanceBetweenNonOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0);
		Ship shipNotOverlappingShip1 = facade.createShip(80, 50, 0, 0, 12, 0);
		double distanceBetweenNonOverlappingShips = facade.getDistanceBetween(ship1, shipNotOverlappingShip1);
		assertEquals(26, distanceBetweenNonOverlappingShips, EPSILON);
	}
	
	@Test
	public void testOverlapOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0);
		boolean overlappingShips = facade.overlap(ship1, shipOverlappingShip1);
		assertTrue(overlappingShips);
	}
	
	@Test
	public void testOverlapSameShip() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0);
		boolean overlappingSameShip = facade.overlap(ship1, ship1);		
		assertTrue(overlappingSameShip);
	}
	
	@Test
	public void testOverlapNonOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(50, 40, 0, 0, 25, 0);
		Ship shipNotOverlappingShip1 = facade.createShip(0, 0, 0, 0, 25, 0);
		boolean nonOverlappingShips = facade.overlap(ship1, shipNotOverlappingShip1);
		assertFalse(nonOverlappingShips);
	}
	
	@Test
	public void testGetTimeToCollisionCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 15, 0);
		Ship collidingShip = facade.createShip(0, 20, 10, 0, 15, 0);
		
		double time = facade.getTimeToCollision(ship1, collidingShip);
		assertEquals(1, time, EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionNonCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 10, 0, 15, 0);
		Ship nonCollidingShip = facade.createShip(0, 20, 0, 0, 15, 0);
		double time = facade.getTimeToCollision(ship1, nonCollidingShip);
		assertEquals(Double.POSITIVE_INFINITY, time, EPSILON);
	}
	
	// Not sure if this is the correct way to check it
	@Test(expected = ModelException.class)
	public void testGetTimeToCollisionOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0);
		double time = facade.getTimeToCollision(ship1, shipOverlappingShip1);
	}
	
	@Test
	public void testGetCollisionPositionCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(120, 40, 0, 0, 20, 0);
		Ship collidingShip = facade.createShip(0, 40, 20, 0, 20, 0);
		double[] collisionPosition = facade.getCollisionPosition(ship1, collidingShip);
		assertEquals(120, collisionPosition[0], EPSILON);
		assertEquals(40, collisionPosition[1], EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionNonCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 15, 0);
		Ship nonCollidingShip = facade.createShip(20, 80, 10, 0, 15, 0);
		double[] collisionPosition = facade.getCollisionPosition(ship1, nonCollidingShip);
		assertNull(collisionPosition);
	}
	// Not sure if this is the correct way to check it
	@Test(expected = ModelException.class)
	public void testGetCollisionPositionOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0);
		double[] position = facade.getCollisionPosition(ship1, shipOverlappingShip1);
	}
}