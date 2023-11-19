package battleship;

/**
 * Represents the game of Battleship
 * @author Haoyuan Zhu
 */

public class BattleshipGame {
    public static void main(String[] args) {
        //create a new ocean
        Ocean ocean = new Ocean();

        //place all the ships randomly
        ocean.placeAllShipsRandomly();

        Ship [][] ships = ocean.getShipArray();

        //print the ocean
        ocean.printWithShips();
    }
}