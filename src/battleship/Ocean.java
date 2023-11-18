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
     * The number of ships sunk (10 ships in all)
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
        for (int i = 0; i < 9; i++){
            for (int j = 0 ; j < 9; j++){
                this.ships[i][j] = new EmptySea();
            }
        }
    }

    //methods

    /**
     * Place all ten ships randomly on the ocean
     */

    void placeAllShipsRandomly(){
        //place battleships
        for (int i = 0 ; i < 1 ; i++){
            int row = (int) (Math.random()*10);
            int column = (int) (Math.random()*10);
            boolean horizontal = (Math.random() < 0.5);
            if (this.ships[row][column].okToPlaceShipAt(row,column,horizontal,this)){
                this.ships[row][column] = new Battleship();
                this.ships[row][column].placeShipAt(row,column,horizontal,this);
            }
        }
        //place cruisers
        for (int i = 0 ; i < 2 ; i++){
            int row = (int) (Math.random()*10);
            int column = (int) (Math.random()*10);
            boolean horizontal = (Math.random() < 0.5);
            if (this.ships[row][column].okToPlaceShipAt(row,column,horizontal,this)){
                this.ships[row][column] = new Cruiser();
                this.ships[row][column].placeShipAt(row,column,horizontal,this);
            }
        }
        //place destroyers
        for (int i = 0 ; i < 3 ; i++){
            int row = (int) (Math.random()*10);
            int column = (int) (Math.random()*10);
            boolean horizontal = (Math.random() < 0.5);
            if (this.ships[row][column].okToPlaceShipAt(row,column,horizontal,this)){
                this.ships[row][column] = new Destroyer();
                this.ships[row][column].placeShipAt(row,column,horizontal,this);
            }
        }
        //place submarines
        for (int i = 0 ; i < 4 ; i++){
            int row = (int) (Math.random()*10);
            int column = (int) (Math.random()*10);
            boolean horizontal = (Math.random() < 0.5);
            if (this.ships[row][column].okToPlaceShipAt(row,column,horizontal,this)){
                this.ships[row][column] = new Submarine();
                this.ships[row][column].placeShipAt(row,column,horizontal,this);
            }
        }
    }

    /**
     * @param row for the location
     * @param column for the location
     * @return true if the given location contains a ship, false if it does not
     */

    boolean isOccupied(int row, int column){
        return !Objects.equals(ships[row][column].getShipType(), "-");
    }

    /**
     * @param row for the location
     * @param column for the location
     * @return true if the given location contains a ”real” ship, still afloat; false if it does not
     */

    boolean shootAt(int row, int column){
        this.shotsFired++;

        if (this.ships[row][column].shootAt(row,column)){
            this.hitCount++;
            return true;
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
        System.out.println("  0 1 2 3 4 5 6 7 8 9\n");
        for (int i = 0 ; i < 9 ; i++){
            System.out.println("i ");
            for (int j = 0 ; j < 9 ; j++){
                System.out.println(this.ships[i][j].getShipType().toLowerCase().charAt(0)+" ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Prints the ocean with ships
     */

    void printWithShips() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9\n");
        for (int i = 0; i < 9; i++) {
            System.out.println("i ");
            for (int j = 0; j < 9; j++) {
                System.out.println(this.ships[i][j].getShipType().toLowerCase().charAt(0) + " ");
            }
            System.out.println("\n");
        }
    }
}
