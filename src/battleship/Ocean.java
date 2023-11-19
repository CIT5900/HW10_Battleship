package battleship;

import java.util.Objects;

/**
 * Represents the ocean in the game of Battleship
 * @author Haoyuan Zhu
 */

public class Ocean {

    //instance variables

    /**
     * Used to quickly determine which ship is in any given location
     */

    private Ship [][] ships = new Ship[10][10];

    /**
     * The total number of shots fired by the user
     */

    private int shotsFired;

    /**
     * The number of times a shot hit a ship.
     */

    private int hitCount;

    /**
     * The number of ships sunk
     */

    private int shipsSunk;

    //constructor

    /**
     * Creates an ”empty” ocean
     */

    public Ocean(){
        //initialize the ships array
        this.initializeShipsArray();

        //initialize the shotsFired, hitCount and shipsSunk
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;
    }

    /**
     * A private helper method to initialize the ships array
     */

    private void initializeShipsArray(){
        //initialize the ships array
        for (int i = 0; i < 10; i++){
            for (int j = 0 ; j < 10; j++){
                this.ships[i][j] = new EmptySea();

                this.ships[i][j].setBowRow(i);
                this.ships[i][j].setBowColumn(j);
            }
        }
    }

    //methods

    /**
     * Place all ten ships randomly on the ocean
     */

    void placeAllShipsRandomly(){

        //initialize the ships array
        this.initializeShipsArray();

        //place the battleship
        this.placeOneKindOfShipRandomly(1, "battleship");

        //place the cruiser
        this.placeOneKindOfShipRandomly(2, "cruiser");

        //place the cruiser
        this.placeOneKindOfShipRandomly(3, "destroyer");

        //place the destroyer
        this.placeOneKindOfShipRandomly(4, "submarine");
    }

    /**
     * A private helper method to place one ship randomly
     * @param num the number of the ship
     * @param shipType the ship type
     */

    void placeOneKindOfShipRandomly (int num, String shipType){

        //create a new ship
        Ship ship = null;

        while (num > 0){

            if (Objects.equals(shipType, "battleship")){
                ship = new Battleship();
            }
            else if (Objects.equals(shipType, "cruiser")){
                ship = new Cruiser();
            }
            else if (Objects.equals(shipType, "destroyer")){
                ship = new Destroyer();
            }
            else if (Objects.equals(shipType, "submarine")){
                ship = new Submarine();
            }

            int row = (int) (Math.random() * 10);
            int column = (int) (Math.random() * 10);
            boolean horizontal = (Math.random() >= 0.5);

            if (ship != null && ship.okToPlaceShipAt(row, column, horizontal, this)){
                ship.placeShipAt(row, column, horizontal, this);
                num--;
            }
        }
    }

    /**
     * @param row for the location
     * @param column for the location
     * @return true if the given location contains a ship, false if it does not
     */

    boolean isOccupied(int row, int column){
        return !(Objects.equals(this.ships[row][column].getShipType(), "empty"));
    }

    /**
     * @param row for the location
     * @param column for the location
     * @return true if the given location contains a ”real” ship, still afloat; false if it does not
     */

    boolean shootAt(int row, int column){
        this.shotsFired++;
        if (this.isOccupied(row, column)){
            if (this.ships[row][column].shootAt(row, column)){
                this.hitCount++;
                if (this.ships[row][column].isSunk()){
                    this.shipsSunk++;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @return the number of shots fired
     */
    int getShotsFired(){
        return this.shotsFired;
    }

    /**
     * @return the number of hits recorded
     */

    int getHitCount(){
        return this.hitCount;
    }

    /**
     * @return the number of ships sunk
     */

    int getShipsSunk(){
        return this.shipsSunk;
    }

    /**
     * @return true if all ships have been sunk, otherwise false
     */

    boolean isGameOver(){
        boolean GameOver = true;
        for (int i = 0; i<9;i++){
            for (int j =0 ; j< 9; j++){
                if (!(this.ships[i][j].isSunk()) && !(Objects.equals(this.ships[i][j].getShipType(), "-"))){
                    GameOver = false;
                }
            }
        }
        return GameOver;
    }

    /**
     * @return the 10x10 array of Ships
     */

    Ship[][] getShipArray(){
        return this.ships;
    }

    /**
     * Prints the ocean
     */

    void print(){
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++){
                System.out.print(this.ships[i][j].toString() + " ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Prints the ocean with ships
     */

    void printWithShips() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++){
                if (Objects.equals(this.ships[i][j].getShipType(), "battleship")){
                    System.out.print("b ");
                }
                else if (Objects.equals(this.ships[i][j].getShipType(), "cruiser")){
                    System.out.print("c ");
                }
                else if (Objects.equals(this.ships[i][j].getShipType(), "destroyer")){
                    System.out.print("d ");
                }
                else if (Objects.equals(this.ships[i][j].getShipType(), "submarine")){
                    System.out.print("s ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
