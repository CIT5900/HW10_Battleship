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

        //print the ocean
        System.out.println("The ocean is: ");
        ocean.print();

        //print the number of shots fired
        System.out.println("The number of shots fired is: " + ocean.getShotsFired());


    }
}