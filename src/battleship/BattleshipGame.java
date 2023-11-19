package battleship;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the game of Battleship
 * @author Haoyuan Zhu
 */

public class BattleshipGame {

    //The BattleshipGame class is the “main” class -- that is, it contains a main method. In this
    //class you will set up the game; accept ”shots” from the user; display the results; and print final
    //scores. All input/output is done here (although some of it is done by calling a print() method
    //in the Ocean class.) All computation will be done in the Ocean class and the various Ship
    //classes.
    //To aid the user, row numbers should be displayed along the left edge of the array, and column
    //numbers should be displayed along the top. Numbers should be 0 to 9, not 1 to 10. The top
    //left corner square should be 0, 0. Use different characters to indicate locations that contain a
    //hit, locations that contain a miss, and locations that have never been fired upon.

    /**
     * The main method to set up the game; accept ”shots” from the user; display the results; and print final scores
     * loop the game until all the ships are sunk
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        //create a new ocean
        Ocean ocean = new Ocean();

        //place all the ships randomly
        ocean.placeAllShipsRandomly();

        //print the ocean
        ocean.print();

        //loop the game until all the ships are sunk
        while (!ocean.isGameOver()) {

            //ask the user to enter the row and column of the shot
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the row of the shot: ");
            int row = scanner.nextInt();
            System.out.println("Enter the column of the shot: ");
            int column = scanner.nextInt();

            //shoot at the location
            ocean.shootAt(row, column);

            //print the ocean
            ocean.print();

            //print the number of shots fired
            System.out.println("The number of shots fired: " + ocean.getShotsFired());
        }

        //print the final scores
        System.out.println("The final scores: " + ocean.getShotsFired());
    }
}