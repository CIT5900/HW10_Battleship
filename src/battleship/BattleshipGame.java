package battleship;

import java.util.Scanner;

/**
 * Represents the game of Battleship
 * @author Haoyuan Zhu & Chengjun Li
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

        boolean isShootAt;

        //loop the game until all the ships are sunk
        while (!ocean.isGameOver()) {

            System.out.println("----------------------------------");

            //print the number of shots that have been fired
            System.out.println("Shot " + (ocean.getShotsFired() + 1) + ": ");

            //ask the user to enter the row and column of the shot
            Scanner scanner = new Scanner(System.in);

            //create a variable to store the input
            String input;

            //create variables to store the row and column of the shot
            int row = 0;
            int column = 0;
            boolean isInputValid = false;

            //loop until the input is valid
            while (!isInputValid) {

                //ask the user to enter the row and column of the shot
                System.out.println("Enter the row and column of the shot, in ’row, column‘: ");

                //store the input
                input = scanner.nextLine();

                //split the input
                String[] inputArray = input.split(",");

                if (inputArray.length == 2) {  //check if the input is in the format of 'row, column'

                    //trim the input
                    String rowString = inputArray[0].trim();
                    String columnString = inputArray[1].trim();

                    try {  //check if the row and column are int
                        row = Integer.parseInt(rowString);
                        column = Integer.parseInt(columnString);

                        //check if the row and column are in the range of 0 to 9
                        if (row >= 0 && row <= 9 && column >= 0 && column <= 9) {
                            isInputValid = true;

                        } else {  //print the error message
                            System.out.println("The row and column must be in the range of 0 to 9!");
                        }

                        //catch the NumberFormatException
                    } catch (NumberFormatException e) {
                        System.out.println("The row and column must be int!");
                    }

                    //print the error message
                } else {
                    System.out.println("The input must be in the format of 'row, column'!");
                }
            }

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