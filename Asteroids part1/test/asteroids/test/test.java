package asteroids.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.util.ModelException;
import asteroids.part2.facade.IFacade;

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
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI,10);
		assertNotNull(ship);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		assertEquals(20, facade.getShipRadius(ship), EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, -Math.PI,10);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -20, -Math.PI,10);
	}
	
	@Test
	public void testGetPosition()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0,10);
		double[] position = facade.getShipPosition(ship);
		assertEquals(100, position[0], EPSILON);
		assertEquals(100, position[1], EPSILON);
	}
	
	@Test
	public void testGetVelocity()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0,10);
		double[] vel = facade.getShipVelocity(ship);
		assertEquals(30, vel[0], EPSILON);
		assertEquals(-15, vel[1], EPSILON);
		
	}
	
	@Test
	public void testGetRadius() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0,10);
		double rad = facade.getShipRadius(ship);
		assertEquals(20, rad, EPSILON);
		
	}
	
	@Test
	public void testGetDirection()  throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0,10);
		double dir = facade.getShipOrientation(ship);
		assertEquals(0, dir, EPSILON);
		
		
	}
	
	

	@Test
	public void testTurn() throws ModelException {
		Ship ship = facade.createShip(100, 70, 23, 18, 20, 0.5*Math.PI,10);
		facade.turn(ship, 0.5*Math.PI);
		double dir = facade.getShipOrientation(ship);
		assertEquals(Math.PI, dir, EPSILON);
		
	}
	
	
	@Test
	public void testGetDistanceBetweenOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0,10);
		double distanceBetweenOverlappingShips = facade.getDistanceBetween(ship1, shipOverlappingShip1);
		assertEquals(-19, distanceBetweenOverlappingShips, EPSILON);
	}
	
	@Test
	public void testGetDistanceBetweenSameShip() throws ModelException {
		Ship ship1 = facade.createShip(20, 20, 0, 0, 20, 0,10);
		double distanceBetweenSameShip = facade.getDistanceBetween(ship1, ship1);
		assertEquals(0, distanceBetweenSameShip , EPSILON);
	}
	
	@Test
	public void testGetDistanceBetweenNonOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		Ship shipNotOverlappingShip1 = facade.createShip(80, 50, 0, 0, 12, 0,10);
		double distanceBetweenNonOverlappingShips = facade.getDistanceBetween(ship1, shipNotOverlappingShip1);
		assertEquals(26, distanceBetweenNonOverlappingShips, EPSILON);
	}
	
	@Test
	public void testOverlapOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0,10);
		boolean overlappingShips = facade.overlap(ship1, shipOverlappingShip1);
		assertTrue(overlappingShips);
	}
	
	@Test
	public void testOverlapSameShip() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		boolean overlappingSameShip = facade.overlap(ship1, ship1);		
		assertTrue(overlappingSameShip);
	}
	
	@Test
	public void testOverlapNonOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(50, 40, 0, 0, 25, 0,10);
		Ship shipNotOverlappingShip1 = facade.createShip(0, 0, 0, 0, 25, 0,10);
		boolean nonOverlappingShips = facade.overlap(ship1, shipNotOverlappingShip1);
		assertFalse(nonOverlappingShips);
	}
	
	@Test
	public void testGetTimeToCollisionCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 15, 0,10);
		Ship collidingShip = facade.createShip(0, 20, 10, 0, 15, 0,10);
		
		double time = facade.getTimeToCollision(ship1, collidingShip);
		assertEquals(1, time, EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionNonCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 10, 0, 15, 0,10);
		Ship nonCollidingShip = facade.createShip(0, 20, 0, 0, 15, 0,10);
		double time = facade.getTimeToCollision(ship1, nonCollidingShip);
		assertEquals(Double.POSITIVE_INFINITY, time, EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testGetTimeToCollisionOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0,10);
		double time = facade.getTimeToCollision(ship1, shipOverlappingShip1);
	}
	
	@Test
	public void testGetCollisionPositionCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(120, 40, 0, 0, 20, 0,10);
		Ship collidingShip = facade.createShip(0, 40, 20, 0, 20, 0,10);
		double[] collisionPosition = facade.getCollisionPosition(ship1, collidingShip);
		assertEquals(100, collisionPosition[0], EPSILON);
		assertEquals(40, collisionPosition[1], EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionNonCollidingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 15, 0,10);
		Ship nonCollidingShip = facade.createShip(20, 80, 10, 0, 15, 0,10);
		double[] collisionPosition = facade.getCollisionPosition(ship1, nonCollidingShip);
		assertNull(collisionPosition);
	}
	
	@Test(expected = ModelException.class)
	public void testGetCollisionPositionOverlappingShips() throws ModelException {
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		Ship shipOverlappingShip1 = facade.createShip(40, 25, 0, 0, 12, 0,20);
		double[] position = facade.getCollisionPosition(ship1, shipOverlappingShip1);
	}
	
	//PART2
	
	
	//Create a world
	@Test
	public void testCreateWorld() throws ModelException {
		World world = facade.createWorld(1000, 800);
		assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
		assertTrue(facade.getWorldShips(world).isEmpty());
		assertTrue(facade.getWorldBullets(world).isEmpty());
	}
	
	//Check thruster status after setting to active
	@Test
	public void testThrusterStatusON() throws ModelException{
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		boolean active = true;
		facade.setThrusterActive(ship1, active );
		assertEquals(facade.isShipThrusterActive(ship1), true);
	}
	
	//Check thruster status after setting to not active
	@Test
	public void testThrusterStatusOFF() throws ModelException{
		Ship ship1 = facade.createShip(40, 20, 0, 0, 12, 0,10);
		boolean active = false;
		facade.setThrusterActive(ship1, active );
		assertEquals(facade.isShipThrusterActive(ship1), false);
	}

	//Check the getMass of a ship
	@Test
	public void testGetMassShip() throws ModelException{
		Ship ship10 = facade.createShip(40,20,0,0,12,0,6E18);
		assertEquals(facade.getShipMass(ship10), 6E18, EPSILON);
	}
	
	
	//Check if ship is terminated	
	@Test
	public void testShipTerminated() throws ModelException{
		Ship ship1 = facade.createShip(40,20,0,0,12,0,10);
		facade.terminateShip(ship1);
		assert (facade.isTerminatedShip(ship1));
	}
	
	//Check if ship is not terminated
	@Test
	public void testShipNotTerminated() throws ModelException{
		Ship ship1 = facade.createShip(40,20,0,0,12,0,10);
		assert (!facade.isTerminatedShip(ship1));
	}
	
	//Check if bullet is terminated
	@Test
	public void testBulletTerminated() throws ModelException{
		Bullet bullet = facade.createBullet(40,20,0,0,5);
		facade.terminateBullet(bullet);
		assert (facade.isTerminatedBullet(bullet));
	}
	
	//Check if bullet is not terminated
	@Test
	public void testBulletNotTerminated() throws ModelException{
		Bullet bullet = facade.createBullet(40,20,0,0,12);
		assert (!facade.isTerminatedBullet(bullet));
	}
	
	//Create a random bullet
	@Test
	public void createBullet() throws ModelException{
		Bullet bullet = facade.createBullet(0, 0, 0, 0, 5);
	}

	//Create a bullet with invalid radius -> should give modelexception
	@Test(expected = ModelException.class)
	public void createBulletNotValidRadius() throws ModelException{
		Bullet bullet = facade.createBullet(0, 0, 0, 0, -10);
	}
	
	//Check if world is terminated
	@Test 
	public void testWorldTerminated() throws ModelException{
		World world1 = facade.createWorld(2000,5000);
		world1.terminateWorld();
		assert (facade.isTerminatedWorld(world1));
	}
	
	//Check if world is not terminated
	@Test 
	public void testWorldNotTerminated() throws ModelException{
		World world1 = facade.createWorld(2000,5000);
		assert (!facade.isTerminatedWorld(world1));
	}
	
	//Test is ship added to world is part of the world and in the collection of worldships.
	@Test 
	public void testAddShipToWorld() throws ModelException{
		World world1 = facade.createWorld(5000, 3000);
		Ship ship1 = facade.createShip(250,250,12,16,15,20,151115);
		facade.addShipToWorld(world1, ship1);
		assert (facade.getShipWorld(ship1) == world1);
		assert (facade.getWorldShips(world1).contains(ship1));
	}
	
	//Test if bullet added to world is part of the world and in the collection of worldbullets
	@Test
	public void testAddBulletToWorld() throws ModelException{
		World world1 = facade.createWorld(5000, 3000);
		Bullet bullet = facade.createBullet(10, 10, 5, 5, 2);
		facade.addBulletToWorld(world1, bullet);
		assert (facade.getBulletWorld(bullet) == world1);
		assert (facade.getWorldBullets(world1).contains(bullet));
	}
	
	//Test if bullets are loaded on ship and the ship contains the two bullets.
	@Test
	public void testLoadBulletOnShipOverlappingBullets() throws ModelException {
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
		Bullet bullet2 = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet1);
		facade.loadBulletOnShip(ship, bullet2);
		assertEquals(2, facade.getNbBulletsOnShip(ship));
	}

	//Test if a set of bullets is loaded correctly on the ship
	@Test
	public void testLoadBulletSetOnShip() throws ModelException{
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		Set<Bullet> bullets = new HashSet<Bullet>();
		bullets.add(facade.createBullet(100, 120, 10, 5, 50));
		bullets.add(facade.createBullet(200, 150, 10, 5, 30));
		facade.loadBulletsOnShip(ship, bullets);
		assertEquals(2, facade.getNbBulletsOnShip(ship));
	}
	
	//Test to remove a bullet from a ship
	@Test
	public void testRemoveBullet() throws ModelException{
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
		Bullet bullet2 = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet1);
		facade.removeBulletFromShip(ship, bullet1);
		assertEquals(0, facade.getNbBulletsOnShip(ship));
	}
	
	//firebullet nbbullets test
	@Test
	public void testFirebullet() throws ModelException{
		World world1 = facade.createWorld(5000, 3000);
		Bullet bullet = facade.createBullet(10, 10, 5, 5, 2);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		facade.addShipToWorld(world1, ship);
		facade.loadBulletOnShip(ship, bullet);
		assertEquals(1, facade.getNbBulletsOnShip(ship));
		facade.fireBullet(ship);
		assertEquals(0, facade.getNbBulletsOnShip(ship));
	}
	
	
	@Test
	public void testgetTimeToCollision() throws ModelException {
		World world = facade.createWorld(8000, 4000);
		Ship ship1 = facade.createShip(80, 0, 0, 0, 20, 0, 1.1E18);
		Ship ship2 = facade.createShip(0, 0, 20, 0, 20, 0, 1.1E18);
		double time = ship1.getTimeToCollision(ship2);
		assertNotNull(time);
		assertEquals(2, time, EPSILON);
	}
	
	@Test
	public void testgetTimeToCollisionNoCollision() throws ModelException {
		Ship ship1 = facade.createShip(150, 150, 0, 0, 20, 0, 2000);
		Ship ship2 = facade.createShip(0, 0, 20, 0, 20, 0, 2000);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testcollisionTimeAndPositionBoundary() throws ModelException{
		Ship ship = facade.createShip(800, 100, 10, 0, 11, 0,500);
		World world = facade.createWorld(3000, 3000);
		facade.addShipToWorld(world, ship);
		double time = facade.getTimeCollisionBoundary(ship);
		assertEquals(220,time,EPSILON);
		double[] position = facade.getPositionCollisionBoundary(ship);
		assertEquals(3000,position[0],EPSILON);
		assertEquals(100,position[1],EPSILON);
	}
	
	
	//DEZE TEST WERKT NIET
	@Test
	public void testEvolveShipWithActiveThruster() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
		facade.addShipToWorld(world, ship);
		facade.setThrusterActive(ship, true);
		assertEquals(1000.0, facade.getShipAcceleration(ship), EPSILON);
		assertTrue(facade.isShipThrusterActive(ship));
		facade.evolve(world, 1, null);
		assertEquals(-990, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}

	
}	
	