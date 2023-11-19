package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];

				assertEquals("empty", ship.getShipType());
			}
		}

		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();

		int numBattleships = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattleships++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattleships);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		//test with empty sea
		assertFalse(ocean.isOccupied(0, 1));
		assertFalse(ocean.isOccupied(1, 0));
		assertFalse(ocean.isOccupied(3, 3));

		//test with a new battleship
		Battleship battleship = new Battleship();
		row = 5;
		column = 5;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(5, 5));
		assertFalse(ocean.isOccupied(5, 6));
		assertFalse(ocean.isOccupied(5, 7));

		//test with a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(9, 9));
		assertFalse(ocean.isOccupied(9, 8));
		assertFalse(ocean.isOccupied(9, 7));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());

		//TODO
		//More tests
		//test if it update the number of shots fired
		assertEquals(3, ocean.getShotsFired());
		//test if it update the number of hits
		assertEquals(2, ocean.getHitCount());
		//test if it update the number of ships sunk
		assertEquals(1, ocean.getShipsSunk());

		//test with a new battleship
		Battleship battleship = new Battleship();
		row = 5;
		column = 5;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 5));
		assertTrue(ocean.shootAt(3, 5));
		assertTrue(ocean.shootAt(2, 5));
		assertTrue(battleship.isSunk());

		//test if it update the number of shots fired
		assertEquals(7, ocean.getShotsFired());
		//test if it update the number of hits
		assertEquals(6, ocean.getHitCount());
		//test if it update the number of ships sunk
		assertEquals(2, ocean.getShipsSunk());

		//test with a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 9));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(8, 9));
		assertTrue(ocean.shootAt(7, 9));
		assertTrue(cruiser.isSunk());

		//test if it update the number of shots fired
		assertEquals(10, ocean.getShotsFired());
		//test if it update the number of hits
		assertEquals(9, ocean.getHitCount());
		//test if it update the number of ships sunk
		assertEquals(3, ocean.getShipsSunk());
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		//test with a new battleship
		Battleship battleship = new Battleship();
		row = 5;
		column = 5;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 5));
		assertTrue(ocean.shootAt(3, 5));
		assertTrue(ocean.shootAt(2, 5));
		assertTrue(battleship.isSunk());

		//test if it update the number of shots fired
		assertEquals(10, ocean.getShotsFired());

		//test with a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 9));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(8, 9));
		assertTrue(ocean.shootAt(7, 9));
		assertTrue(cruiser.isSunk());

		//test if it update the number of shots fired
		assertEquals(13, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		//test with a new battleship
		Battleship battleship = new Battleship();
		row = 5;
		column = 5;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 5));
		assertTrue(ocean.shootAt(3, 5));
		assertTrue(ocean.shootAt(2, 5));
		assertTrue(battleship.isSunk());

		//test if it update the number of hits
		assertEquals(5, ocean.getHitCount());

		//test with a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 9));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(8, 9));
		assertTrue(ocean.shootAt(7, 9));
		assertTrue(cruiser.isSunk());

		//test if it update the number of hits
		assertEquals(8, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		//test with a new battleship
		Battleship battleship = new Battleship();
		row = 5;
		column = 5;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(4, 5));
		assertTrue(ocean.shootAt(3, 5));
		assertTrue(ocean.shootAt(2, 5));
		assertTrue(battleship.isSunk());

		//test if it update the number of ships sunk
		assertEquals(1, ocean.getShipsSunk());

		//test with a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;

		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 9));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(8, 9));
		assertTrue(ocean.shootAt(7, 9));
		assertTrue(cruiser.isSunk());

		//test if it update the number of ships sunk
		assertEquals(2, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		//test with a new battleship
		Battleship battleship = new Battleship();
		int row = 5;
		int column = 5;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertEquals("battleship", shipArray[5][5].getShipType());


		//test with a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertEquals("cruiser", shipArray[9][9].getShipType());
	}
}
