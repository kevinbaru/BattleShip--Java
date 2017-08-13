package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	int shotsFired;
	int hitCount;
	EmptySea newEmptySea;
	Ship[][] ships = new Ship[10][10];
	Ocean newOcean;
	
	@Before
	public void setUp() throws Exception {
		newEmptySea = new EmptySea();
		newOcean = new Ocean();
	}

	@Test
	public void testOcean() {
		assertEquals(0, newOcean.getHitCount());
		assertEquals(0, newOcean.getShotsFired());
		assertEquals(10, newOcean.getShipArray().length);
		assertEquals(newEmptySea.getShipType(), newOcean.getShipArray()[0][1].getShipType());
		Ocean newOcean2 = new Ocean();
		assertEquals(0, newOcean2.getHitCount());
		assertEquals(0, newOcean2.getShotsFired());
		assertEquals(10, newOcean2.getShipArray().length);
		assertEquals(newEmptySea.getShipType(), newOcean2.getShipArray()[0][0].getShipType());
		int count = 0;
		for(int i=0; i<10; i++){
			for(int y=0; y<10; y++){
				//if a square is holding an emptysea than increments the count
				if(newOcean.getShipArray()[i][y].getShipType() == ("empty")){
					count = count + 1;
				}
			}
		}
		assertEquals(100, count);
	}
		
	
	//how do we test the following method
	@Test
	public void testPlaceAllShipsRandomly() {
		newOcean.placeAllShipsRandomly();
		//ship squares filled = 4+3+3+2+2+2+1+1+1+1 = 20
		//sets count to 0
		int count = 0;
		int countB = 0;
		int countD= 0;
		int countC= 0;
		int countM= 0;
		//loops through the whole ocean array
		for(int i=0; i<10; i++){
			for(int y=0; y<10; y++){
				//if a square is holding an emptysea than increments the count
				if(newOcean.getShipArray()[i][y].getShipType() == ("empty")){
					count = count + 1;
				}
				if(newOcean.getShipArray()[i][y].getShipType() == ("battleship")){
					countB = countB + 1;
				}
				if(newOcean.getShipArray()[i][y].getShipType() == ("destroyer")){
					countD = countD + 1;
				}
				if(newOcean.getShipArray()[i][y].getShipType() == ("cruiser")){
					countC = countC + 1;
				}
				if(newOcean.getShipArray()[i][y].getShipType() == ("submarine")){
					countM = countM + 1;
				}
				
			}
		}
		assertEquals(80, count);
		assertEquals(4, countB);
		assertEquals(6, countD);
		assertEquals(6, countC);
		assertEquals(4, countM);
		/*
		 * - check that there are indeed 20 ship locations occupied, i.e. 80 EmptySeas

- maybe run the method once, store the ships array in a temporary variable, run it again, check that the two arrays are not equal
[Bo Tian]
Bo Tian 22 hours ago Thank you for that! Just coded it in my test.

		 */
	}

	@Test
	public void testIsOccupied() {
		Ocean newOcean = new Ocean();
		for(int i=0; i<10;i++){
			for(int y = 0; y<10;y++){
				assertFalse(newOcean.isOccupied(i, y));
			}
		}
		Cruiser newCruiser = new Cruiser();
		newCruiser.placeShipAt(5, 5, true, newOcean);
		assertTrue(newOcean.isOccupied(5, 5));
		assertFalse(newOcean.isOccupied(9, 9));
		newCruiser.placeShipAt(1, 1, true, newOcean);
		assertTrue(newOcean.isOccupied(1, 1));
		assertFalse(newOcean.isOccupied(8, 8));
		assertFalse(newOcean.isOccupied(0, 0));
		assertFalse(newOcean.isOccupied(9, 9));
	}

	@Test
	public void testShootAt() {
		Ocean newOcean = new Ocean();
		Cruiser newCruiser = new Cruiser();
		newCruiser.placeShipAt(5, 5, true, newOcean);
		assertTrue(newOcean.shootAt(5, 5));
		assertFalse(newOcean.shootAt(9, 9));
		newCruiser.placeShipAt(1, 1, true, newOcean);
		assertTrue(newOcean.shootAt(1, 1));
		assertFalse(newOcean.shootAt(8, 8));
	}


	@Test
	public void testIsGameOver() {
		//places ships in the ocean
		newOcean.placeAllShipsRandomly();
		//shoots each square in the ocean
		for(int i=0; i<10; i++){
			for(int y=0; y<10; y++){
				newOcean.shootAt(i, y);
			}
		}
		assertTrue(newOcean.isGameOver());
		Ocean newOcean2 = new Ocean();
		newOcean2.placeAllShipsRandomly();
		assertFalse(newOcean2.isGameOver());

	}

}
