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
	
	public void testgetPosition() {
		
	}
	
	public void testgetVelocity(){
		
	}
	
	public void testgetRadius(){
		
	}
	
	public void testgetAngle(){
		
	}
	
	public void testgetDirection(){
		
	}
	
	public void testsetPosition(){
		
	}
	
	public void testsetVelocity(){
		
	}
	
	public void testsetDirection(){
		
	}
	
	public void testsetAngle(){
		
	}
	
	public void testsetRadius(){
		
	}
	
	public void testgetSpeed(){
		
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
	
	public void testturn(){
		
	}
	
	public void testthrust() {
		
	}
	
	@Test
	public void testGetDistanceBetween() {
		
	}
	
	@Test
	public void testOverlap() {
	
	}
	
	@Test
	public void testGetTimeToCollision() {
		
	}
	
	@Test
	public void testGetCollisionPosition() {
		
	}
	
	
	
	
}