package battleship;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the game of Battleship
 * @author Haoyuan Zhu
 */

public class BattleshipGame {

    /**
     * The main method to set up the game; accept ”shots” from the user; display the results; and print final scores
     * loop the game until all the ships are sunk
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        System.out.println("Welcome to the game of Battleship!");

        //create a new ocean
        Ocean ocean = new Ocean();

        //place all the ships randomly
        ocean.placeAllShipsRandomly();

        System.out.println();

        //print the ocean
        ocean.print();

        boolean isShootAt = false;

        //loop the game until all the ships are sunk
        while (!ocean.isGameOver()) {

            System.out.println("----------------------------------");

            //print the number of shots that have been fired
            System.out.println("Shot " + (ocean.getShotsFired() + 1) + ": ");

            //ask the user to enter the row and column of the shot
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the row of your shot: ");
            int row = scanner.nextInt();
            System.out.println("Enter the column of your shot: ");
            int column = scanner.nextInt();

            //shoot at the location
            isShootAt = ocean.shootAt(row, column);

            System.out.println();

            //check if the shot hit a ship
            if(ocean.isOccupied(row, column)){
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }

            //check if the shot sunk a ship
            if(ocean.getShipArray()[row][column].isSunk() && isShootAt){
                System.out.println();
                System.out.println("You just sank a ship - " + ocean.getShipArray()[row][column].getShipType() + "!");
            }

            System.out.println();

            //print the ocean
            ocean.print();
        }

        System.out.println("----------------------------------");

        //print the game over message
        System.out.println("Game Over!");

        //print the best possible score
        System.out.println("The best possible score: 20");

        //print the final scores
        System.out.println("Your final scores: " + (ocean.getShotsFired()));
    }
}