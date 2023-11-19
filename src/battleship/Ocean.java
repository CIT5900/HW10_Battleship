package battleship;

import java.util.Objects;

/**
 * Represents the ocean in the game of Battleship
 * @author Haoyuan Zhu & Chengjun Li
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
        //iterate through the ships array and initialize each element to an empty sea
        for (int i = 0; i < 10; i++){
            for (int j = 0 ; j < 10; j++){

                //initialize each element to an empty sea
                this.ships[i][j] = new EmptySea();

                //set the bowRow and bowColumn of each element
                this.ships[i][j].setBowRow(i);
                this.ships[i][j].setBowColumn(j);
            }
        }
    }

    //methods

    /**
     * Place all ten ships randomly on the ocean
     */

    public void placeAllShipsRandomly(){

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

    private void placeOneKindOfShipRandomly (int num, String shipType){

        //create a new ship
        Ship ship = null;

        //loop until all the ships are placed
        while (num > 0){

            //create a new battleship
            if (Objects.equals(shipType, "battleship")){
                ship = new Battleship();
            }

            //create a new cruiser
            else if (Objects.equals(shipType, "cruiser")){
                ship = new Cruiser();
            }

            //create a new destroyer
            else if (Objects.equals(shipType, "destroyer")){
                ship = new Destroyer();
            }

            //create a new submarine
            else if (Objects.equals(shipType, "submarine")){
                ship = new Submarine();
            }

            //generate a random row, column and orientation
            int row = (int) (Math.random() * 10);
            int column = (int) (Math.random() * 10);
            boolean horizontal = (Math.random() >= 0.5);

            //place the ship at the given location
            if (ship != null && ship.okToPlaceShipAt(row, column, horizontal, this)){
                ship.placeShipAt(row, column, horizontal, this);

                //decrease the number of ships
                num--;
            }
        }
    }

    /**
     * @param row for the location
     * @param column for the location
     * @return true if the given location contains a ship, false if it does not
     */

    public boolean isOccupied(int row, int column){

        //return the result
        return !(Objects.equals(this.ships[row][column].getShipType(), "empty"));
    }

    /**
     * @param row for the location
     * @param column for the location
     * @return true if the given location contains a ”real” ship, still afloat; false if it does not
     */

    public boolean shootAt(int row, int column){

        //increase the number of shots fired
        this.shotsFired++;

        //check if the location is occupied by a ship
        if (this.ships[row][column].shootAt(row, column)){

            //increase the number of hits recorded
            this.hitCount++;

            //check if the ship has been sunk
            if (this.ships[row][column].isSunk()){
                this.shipsSunk++;
            }

            //return true
            return true;
        }

        //return false
        return false;
    }

    /**
     * @return the number of shots fired
     */
    public int getShotsFired(){
        return this.shotsFired;
    }

    /**
     * @return the number of hits recorded
     */

    public int getHitCount(){
        return this.hitCount;
    }

    /**
     * @return the number of ships sunk
     */

    public int getShipsSunk(){
        return this.shipsSunk;
    }

    /**
     * @return true if all ships have been sunk, otherwise false
     */

    public boolean isGameOver(){
        //return the result
        return this.shipsSunk == 10;
    }

    /**
     * @return the 10x10 array of Ships
     */

    public Ship[][] getShipArray(){
        return this.ships;
    }

    /**
     * Prints the ocean
     */

    public void print(){

        //print the first line
        System.out.println("  0 1 2 3 4 5 6 7 8 9");

        //create a boolean variable to store the result
        boolean isHit;

        //create an index variable
        int index;

        //iterate through the ships array and print each element
        for (int i = 0; i < 10; i++){

            //print the row number
            System.out.print(i + " ");

            //iterate through the row
            for (int j = 0; j < 10; j++){

                //check if the ship is horizontal
                if (this.ships[i][j].isHorizontal()) {

                    //calculate the index of the hit array
                    index = this.ships[i][j].getBowColumn() - j;

                    //check if the ship has been hit
                } else {
                    index = this.ships[i][j].getBowRow() - i;
                }

                //check if the ship has been hit
                isHit = this.ships[i][j].getHit()[index];

                //check if the ship has been sunk
                if (this.ships[i][j].isSunk()){
                    System.out.print(this.ships[i][j].toString() + " ");
                }
                else if (isHit){  //check if the ship has been hit
                    System.out.print(this.ships[i][j].toString() + " ");
                }
                else {  //print a dot
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints the ocean with ships
     */

    public void printWithShips() {

        //print the first line
        System.out.println("  0 1 2 3 4 5 6 7 8 9");

        //iterate through the ships array and print each element
        for (int i = 0; i < 10; i++){

            //print the row number
            System.out.print(i + " ");

            //iterate through the row
            for (int j = 0; j < 10; j++){

                //check the type of the ship
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
