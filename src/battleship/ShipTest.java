package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the unit test of ship in the game of Battleship
 * @author Haoyuan Zhu & Chengjun Li
 */

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {

		//Scenario 1
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		ship = new Cruiser();
		//Check that the length is 3
		assertEquals(3, ship.getLength());

		//Scenario 3
		//Create a new ship of type Destroyer
		ship = new Destroyer();
		//Check that the length is 2
		assertEquals(2, ship.getLength());

		//Scenario 4
		//Create a new ship of type Submarine
		ship = new Submarine();
		//Check that the length is 1
		assertEquals(1, ship.getLength());

		//Scenario 5
		//Create a new ship of type EmptySea
		ship = new EmptySea();
		//Check that the length is 1
		assertEquals(1, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.placeShipAt(row1, column1, horizontal1, ocean);
		assertEquals(row1, cruiser.getBowRow());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 5;
		int column2 = 4;
		boolean horizontal2 = true;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row2, destroyer.getBowRow());

		//Scenario 4
		//Create a new ship of type Submarine
		Ship submarine = new Submarine();
		int row3 = 7;
		int column3 = 4;
		boolean horizontal3 = true;
		submarine.placeShipAt(row3, column3, horizontal3, ocean);
		assertEquals(row3, submarine.getBowRow());

		//Scenario 5
		//Create a new ship of type EmptySea
		Ship emptySea = new EmptySea();
		int row4 = 9;
		int column4 = 4;
		boolean horizontal4 = true;
		emptySea.placeShipAt(row4, column4, horizontal4, ocean);
		assertEquals(row4, emptySea.getBowRow());
	}

	@Test
	void testGetBowColumn() {

		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.placeShipAt(row1, column1, horizontal1, ocean);
		cruiser.setBowColumn(column1);
		assertEquals(column1, cruiser.getBowColumn());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 4;
		boolean horizontal2 = true;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
		destroyer.setBowColumn(column2);
		assertEquals(column2, destroyer.getBowColumn());

		//Scenario 4
		//Create a new ship of type Submarine
		Ship submarine = new Submarine();
		int row3 = 6;
		int column3 = 4;
		boolean horizontal3 = true;
		submarine.placeShipAt(row3, column3, horizontal3, ocean);
		submarine.setBowColumn(column3);
		assertEquals(column3, submarine.getBowColumn());

		//Scenario 5
		//Create a new ship of type EmptySea
		Ship emptySea = new EmptySea();
		int row4 = 8;
		int column4 = 4;
		boolean horizontal4 = true;
		emptySea.placeShipAt(row4, column4, horizontal4, ocean);
		emptySea.setBowColumn(column4);
		assertEquals(column4, emptySea.getBowColumn());
	}

	@Test
	void testGetHit() {
		//Scenario 1
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		ship = new Cruiser();
		boolean[] hits1 = new boolean[3];
		assertArrayEquals(hits1, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);

		//Scenario 3
		//shoot at the ship bow
		ship = new Cruiser();
		//Place the ship at (2, 3) horizontally
		ship.placeShipAt(2, 3, true, ocean);
		//Shoot at the ship bow
		ship.shootAt(2, 3);
		//Check that the hit array is updated
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);

		//Scenario 4
		//shoot at the ship middle
		ship = new Cruiser();
		//Place the ship at (4, 3) horizontally
		ship.placeShipAt(4, 3, true, ocean);
		//Shoot at the ship middle
		ship.shootAt(4, 2);
		//Check that the hit array is updated
		boolean[] hits2 = {false, true, false};
		assertArrayEquals(hits2, ship.getHit());
	}
	@Test
	void testGetShipType() {

		//Scenario 1
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());

		//Scenario 3
		//Create a new ship of type Destroyer
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());

		//Scenario 4
		//Create a new ship of type Submarine
		ship = new Submarine();
		//Check that the ship type is submarine
		ship.placeShipAt(2, 3, true, ocean);
		assertEquals("submarine", ocean.getShipArray()[2][3].getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = false;
		cruiser.placeShipAt(row1, column1, horizontal1, ocean);
		assertFalse(cruiser.isHorizontal());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 7;
		boolean horizontal2 = false;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
	}
	
	@Test
	void testSetBowRow() {
		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.setBowRow(row1);
		assertEquals(row1, cruiser.getBowRow());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 7;
		boolean horizontal2 = true;
		destroyer.setBowRow(row2);
		assertEquals(row2, destroyer.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.setBowColumn(column1);
		assertEquals(column1, cruiser.getBowColumn());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 7;
		boolean horizontal2 = true;
		destroyer.setBowColumn(column2);
		assertEquals(column2, destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {

		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		//Scenario 2
		horizontal = false;
		battleship.setHorizontal(horizontal);
		assertFalse(battleship.isHorizontal());

		//Scenario 3
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.setHorizontal(horizontal1);
		assertTrue(cruiser.isHorizontal());

		//Scenario 4
		//create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 7;
		boolean horizontal2 = false;
		destroyer.setHorizontal(horizontal2);
		assertFalse(destroyer.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {

		//Scenario 1
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		//Scenario 2
		//test when the left end exceeds the ocean
		Ship cruiser = new Cruiser();
		row = 0;
		column = 1;
		horizontal = true;
		boolean ok1 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok1, "Not OK to place ship here.");

		//Scenario 3
		//test when the top end exceeds the ocean
		Ship destroyer = new Destroyer();
		row = 0;
		column = 0;
		horizontal = false;
		boolean ok2 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {

		//Scenario 1
		//test when other ships are in the ocean
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//Scenario 2
		//test second ship
		//horizontally adjacent
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//Scenario 3
		//test third ship
		//diagonally adjacent
		Battleship battleship3 = new Battleship();
		row = 4;
		column = 5;
		horizontal = false;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship horizontally adjacent to the right.");

		//Scenario 4
		//test fourth ship
		//vertically adjacent
		Battleship battleship4 = new Battleship();
		row = 3;
		column = 0;
		horizontal = false;
		boolean ok4 = battleship4.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship vertically adjacent above.");

		//Scenario 5
		//test fifth ship
		//successfully placed
		Battleship battleship5 = new Battleship();
		row = 5;
		column = 3;
		horizontal = false;
		boolean ok5 = battleship5.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok5, "OK to place ship here.");
	}

	@Test
	void testPlaceShipAt() {

		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.placeShipAt(row1, column1, horizontal1, ocean);
		assertEquals(row1, cruiser.getBowRow());
		assertEquals(column1, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		//Check that the ship is placed in the ocean
		assertEquals("empty", ocean.getShipArray()[2][0].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[2][3]);

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 7;
		boolean horizontal2 = true;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row2, destroyer.getBowRow());
		assertEquals(column2, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());
		//Check that the ship is placed in the ocean
		assertEquals("empty", ocean.getShipArray()[4][0].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[4][6]);
	}

	@Test
	void testShootAt() {

		//Scenario 1
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		Ship cruiser = new Cruiser();
		int row1 = 2;
		int column1 = 4;
		boolean horizontal1 = true;
		cruiser.placeShipAt(row1, column1, horizontal1, ocean);
		//Shoot at the ship middle
		assertTrue(cruiser.shootAt(2, 3));
		boolean[] hitArray1 = {false, true, false};
		assertArrayEquals(hitArray1, cruiser.getHit());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 7;
		boolean horizontal2 = false;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
		//Shoot at the ship end
		assertTrue(destroyer.shootAt(3, 7));
		boolean[] hitArray2 = {false, true};
		assertArrayEquals(hitArray2, destroyer.getHit());

		//Scenario 4
		//test with empty sea
		Ship emptySea = new EmptySea();
		int row3 = 6;
		int column3 = 7;
		boolean horizontal3 = false;
		emptySea.placeShipAt(row3, column3, horizontal3, ocean);
		//Shoot at the ship bow
		assertFalse(emptySea.shootAt(6, 7));
		boolean[] hitArray3 = {true};
		assertArrayEquals(hitArray3, emptySea.getHit());
	}
	
	@Test
	void testIsSunk() {

		//Scenario 1
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		//Scenario 2
		Ship battleship = new Battleship();
		row = 0;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		//Shoot at the empty sea
		assertFalse(battleship.shootAt(1, 9));
		assertFalse(battleship.isSunk());
		//Shoot at the ship
		battleship.shootAt(0, 9);
		assertFalse(battleship.isSunk());
		battleship.shootAt(0, 8);
		assertFalse(battleship.isSunk());
		battleship.shootAt(0, 7);
		assertFalse(battleship.isSunk());
		battleship.shootAt(0, 6);
		assertTrue(battleship.isSunk());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		row = 1;
		column = 5;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		//Shoot at the ship bow
		assertTrue(destroyer.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		//Shoot at the ship middle
		assertTrue(destroyer.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
	}

	@Test
	void testToString() {

		//Scenario 1
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		//Scenario 2
		//Create a new ship of type Cruiser
		battleship.shootAt(8, 1);
		assertEquals("x", battleship.toString());
		battleship.shootAt(7, 1);
		assertEquals("x", battleship.toString());
		battleship.shootAt(6, 1);
		//Check that the ship is sunk
		assertEquals("s", battleship.toString());

		//Scenario 3
		//Create a new ship of type Destroyer
		Ship destroyer = new Destroyer();
		row = 1;
		column = 5;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		//Shoot at the ship bow
		destroyer.shootAt(1, 5);
		assertEquals("x", destroyer.toString());
		//Shoot at the ship emd
		destroyer.shootAt(0, 5);
		//Check that the ship is sunk
		assertEquals("s", destroyer.toString());
	}

}
