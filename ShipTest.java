package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	protected int bowRow; // � the row (0 to 9) which contains the bow (front) of the ship.
	protected int bowColumn; // � the column (0 to 9) which contains the bow (front) of the ship.
	protected int length; // � the number of squares occupied by the ship. An �empty sea� location has length 1.
	protected boolean horizontal;  // � true if the ship occupies a single row, false otherwise.
	protected boolean [] hit;  // = new boolean[4]; � an array of booleans telling whether that part of the ship has been hit. Only battleships use all four locations; cruisers use the first three; destroyers 2; submarines 1; and �empty sea� either one or none.
	protected Ocean ocean;//do i need this, cannot be resolved to type issue below
	Battleship newBattleship;
	Cruiser newCruiser;
	Destroyer newDestroyer;
	Submarine newSubmarine;
	Battleship newShip;
	EmptySea newEmptySea;
	
	@Before
	public void setUp() throws Exception {
		newBattleship = new Battleship();
		newCruiser = new Cruiser();
		newDestroyer = new Destroyer();
		newSubmarine = new Submarine();
		bowColumn = 5;
		bowRow = 5;
		length = 4;
		horizontal = true;
		ocean = new Ocean();
		newShip = new Battleship();;
		
	}

	@Test
	public void testGetLength() {
		
		assertEquals(1, newSubmarine.getLength());
		assertEquals(2, newDestroyer.getLength());
		assertEquals(3, newCruiser.getLength());
		assertEquals(4, newBattleship.getLength());
	}

	@Test
	public void testGetBowRow() {
		newBattleship.setBowRow(7);
		newCruiser.setBowRow(5);
		newDestroyer.setBowRow(5);
		newSubmarine.setBowRow(5);
		assertEquals(5, newCruiser.getBowRow());
		assertEquals(7, newBattleship.getBowRow());
		assertEquals(5, newDestroyer.getBowRow());
		assertEquals(5, newSubmarine.getBowRow());
		
	}

	@Test
	public void testGetBowColumn() {
		
		newShip.placeShipAt(5, 5, true, ocean);
		assertEquals(5, newShip.getBowColumn());
		
		newCruiser.placeShipAt(5, 7, true, ocean);
		assertEquals(7, newCruiser.getBowColumn());
	}

	@Test
	public void testIsHorizontal() {
		
		Ocean newOcean = new Ocean();
		newShip.placeShipAt(5, 5, true, newOcean);
		assertTrue(newShip.isHorizontal());
		
		newCruiser.placeShipAt(2, 2, false, newOcean);
		assertFalse(newCruiser.isHorizontal());
	}

	@Test
	public void testSetBowRow() {
		
		newShip.setBowRow(4);
		assertEquals(4, newShip.getBowRow());
		
		newCruiser.setBowRow(4);
	}

	@Test
	public void testSetBowColumn() {
		
		newShip.setBowColumn(4);
		assertEquals(4, newShip.getBowColumn());
	}

	@Test
	public void testSetHorizontal() {
		
		newShip.setHorizontal(false);
		assertFalse(newShip.isHorizontal());
	}

	@Test
	public void testGetShipType() {
		
		assertEquals("battleship", newShip.getShipType());
		
		assertEquals("cruiser", newCruiser.getShipType());
		
		assertEquals("destroyer", newDestroyer.getShipType());
	
		assertEquals("submarine", newSubmarine.getShipType());
	}

	@Test
	public void testOkToPlaceShipAt() {
		Ocean newOcean = new Ocean();
		assertTrue(newCruiser.okToPlaceShipAt(2, 2, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(2, 2, true, newOcean));
		assertTrue(newBattleship.okToPlaceShipAt(2, 2, true, newOcean));
		assertTrue(newSubmarine.okToPlaceShipAt(2, 2, true, newOcean));
		assertTrue(newCruiser.okToPlaceShipAt(5, 5, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(5, 5, true, newOcean));
		assertTrue(newBattleship.okToPlaceShipAt(5, 5, true, newOcean));
		assertTrue(newSubmarine.okToPlaceShipAt(5, 5, true, newOcean));
		assertTrue(newCruiser.okToPlaceShipAt(6, 5, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(6, 3, true, newOcean));
		assertTrue(newBattleship.okToPlaceShipAt(2, 5, true, newOcean));
		assertTrue(newSubmarine.okToPlaceShipAt(7, 5, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(9, 0, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(6, 0, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(0, 0, false, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(6, 0, false, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(1, 1, false, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(6, 0, false, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(9, 0, false, newOcean));
		//checks for bad input
		assertFalse(newDestroyer.okToPlaceShipAt(10, 0, false, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(0, 10, false, newOcean));
		//Battleship occupies 5,5; 5,6 ;5,7;5,8
		newShip.placeShipAt(5, 5, true, newOcean);
		assertTrue(newCruiser.okToPlaceShipAt(2, 2, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(6, 0, true, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(5, 5, true, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(6, 5, true, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(4, 5, true, newOcean));
		assertTrue(newCruiser.okToPlaceShipAt(2, 2, true, newOcean));
		assertTrue(newDestroyer.okToPlaceShipAt(6, 0, true, newOcean));
		//checks diagonals
		assertFalse(newDestroyer.okToPlaceShipAt(4, 3, true, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(6, 3, true, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(4, 9, true, newOcean));
		assertFalse(newDestroyer.okToPlaceShipAt(6, 9, true, newOcean));
		//checks alternative placing
		assertTrue(newDestroyer.okToPlaceShipAt(7, 5, true, newOcean));
		//checks out of bounds on the right
		assertFalse(newCruiser.okToPlaceShipAt(1, 9, true, newOcean));
		assertFalse(newCruiser.okToPlaceShipAt(1, 8, true, newOcean));
		assertTrue(newCruiser.okToPlaceShipAt(1, 7, true, newOcean));
		assertTrue(newCruiser.okToPlaceShipAt(0, 0, true, newOcean));
		
	}

	@Test
	public void testPlaceShipAt() {
		newShip.placeShipAt(5, 5, true, ocean);
		assertEquals(5, newShip.getBowColumn());
		assertEquals(newShip, ocean.getShipArray()[5][5]);
		assertEquals(newShip, ocean.getShipArray()[5][6]);
		assertNotEquals(newShip, ocean.getShipArray()[4][5]);
		assertNotEquals(newShip, ocean.getShipArray()[4][1]);
		assertNotEquals(newShip, ocean.getShipArray()[5][3]);
		assertNotEquals(newShip, ocean.getShipArray()[5][4]);
		assertEquals(newShip, ocean.getShipArray()[5][7]);
		assertEquals(newShip, ocean.getShipArray()[5][8]);
		Ocean cruiserOcean = new Ocean();
		newCruiser.placeShipAt(6, 6, true, cruiserOcean);
		assertEquals(6, newCruiser.getBowColumn());
		assertEquals(6, newCruiser.getBowRow());
		assertEquals("cruiser", newCruiser.getShipType());
		assertTrue(cruiserOcean.isOccupied(6, 6));
		assertTrue(newCruiser.isHorizontal());
		//tests if can place ship and not go out of array
		newCruiser.placeShipAt(6, 7, true, cruiserOcean);
		newCruiser.placeShipAt(9, 7, true, cruiserOcean);
		
		
	}

	@Test
	public void testShootAt() {

		newCruiser.placeShipAt(1,1 , false, ocean);
		newShip.placeShipAt(5, 5, true, ocean);
		newShip.shootAt(5, 5);
		assertTrue(newShip.shootAt(5, 5));
		assertTrue(newShip.shootAt(5, 6));
		assertTrue(newShip.shootAt(5, 7));
		assertTrue(newShip.shootAt(5, 8));
		assertFalse(newShip.shootAt(5, 9));
		assertFalse(newShip.shootAt(4, 9));
		assertFalse(newShip.shootAt(4, 5));
		assertFalse(newShip.shootAt(9,9));
		assertFalse(newCruiser.shootAt(1, 2));
		assertTrue(newCruiser.shootAt(1,1));
		assertTrue(newCruiser.shootAt(2,1));
		assertTrue(newCruiser.shootAt(3,1));
		assertFalse(newCruiser.shootAt(4,1));
	}

	@Test
	public void testIsSunk() {
		
		newSubmarine.placeShipAt(bowRow, bowColumn, horizontal, ocean);
		newSubmarine.shootAt(5, 5);
		assertTrue(newSubmarine.isSunk());
		
	}

	@Test
	public void testToString() {
		
		newSubmarine.placeShipAt(bowRow, bowColumn, horizontal, ocean);
		newSubmarine.shootAt(5, 5);
		assertTrue(newSubmarine.isSunk());
		assertTrue("x".equals(newSubmarine.toString()));
	}

}
