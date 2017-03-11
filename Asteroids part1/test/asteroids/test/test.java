package asteroids.test;

import static org.junit.Assert.*;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.util.ModelException;
import asteroids.part1.facade.IFacade;

import org.junit.Before;
import org.junit.BeforeClass;
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
	public void testgetPosition()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		double[] position = facade.getShipPosition(ship);
		assertEquals(100, position[0], EPSILON);
		assertEquals(100, position[1], EPSILON);
	}
	
	@Test
	public void testgetVelocity()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		double[] vel = facade.getShipVelocity(ship);
		assertEquals(30, vel[0], EPSILON);
		assertEquals(-15, vel[1], EPSILON);
		
	}
	
	@Test
	public void testgetRadius() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		double rad = facade.getShipRadius(ship);
		assertEquals(20, rad, EPSILON);
		
	}
	
	@Test
	public void testgetDirection()  throws ModelException {
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
	public void testturn() throws ModelException {
		Ship ship = facade.createShip(100, 70, 23, 18, 20, 0.5*Math.PI);
		facade.turn(ship, 0.5*Math.PI);
		double dir = facade.getShipOrientation(ship);
		assertEquals(Math.PI, dir, EPSILON);
		
	}
	@Test
	public void testthrust() throws ModelException {
		Ship ship = facade.createShip(100, 70, 23, 18, 20,0);
		facade.thrust(ship, 3);
		double[] vel = facade.getShipVelocity(ship);
		assertEquals(26, vel[0], EPSILON);
		assertEquals(18, vel[1], EPSILON);
		
		
	}
	
	@Test
	public void testGetDistanceBetween() {
		Ship ship1 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
		Ship ship2 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
	}
	
	@Test
	public void testOverlap() {
		Ship ship1 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
		Ship ship2 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
	}
	
	@Test
	public void testGetTimeToCollision() {
		Ship ship1 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
		Ship ship2 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
	}
	
	@Test
	public void testGetCollisionPosition() {
		Ship ship1 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
		Ship ship2 = facade.createShip(x, y, xVelocity, yVelocity, radius, orientation);
		
	}
	
	
	
	
}