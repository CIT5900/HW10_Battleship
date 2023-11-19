package battleship;

/**
 * Represents a ship in the game of Battleship
 * @author Haoyuan Zhu & Chengjun Li
 */

public abstract class Ship {

    //instance variables

    /**
     * The row that contains the bow (front part of the ship)
     */

    private int bowRow;

    /**
     * The column that contains the bow (front part of the ship)
     */

    private int bowColumn;

    /**
     * The length of the ship
     */

    private int length;

    /**
     * A boolean that represents whether the ship is going to be placed horizontally or vertically
     */

    private boolean horizontal;

    /**
     * An array of booleans that indicate whether that part of the ship has been hit or not
     */

    private boolean [] hit;

    //constructor

    /**
     * This constructor sets the length property of the particular ship
     * and initializes the hit array based on that length
     * @param length the length of the ship
     */

    public Ship(int length) {

        //set the length of the ship
        this.length = length;

        //initial the hit array according to length
        this.hit = new boolean[length];

        //set all the elements in the hit array to false
        for (int i = 0; i<length; i++){
            this.hit[i] = false;
        }
    }

    //getter methods

    /**
     * @return the length of the ship
     */

    public int getLength() {
        return this.length;
    }

    /**
     * @return the row corresponding to the position of the bow
     */

    public int getBowRow() {
        return this.bowRow;
    }

    /**
     * @return the column corresponding to the position of the bow
     */

    public int getBowColumn() {
        return this.bowColumn;
    }

    /**
     * @return the hit array
     */

    public boolean[] getHit() {
        return this.hit; 
    }

    /**
     * @return whether the ship is horizontal or not
     */

    public boolean isHorizontal() {
        return this.horizontal;
    }

    //setter methods

    /**
     * Sets the value of bowRow
     * @param row the row corresponding to the position of the bow
     */

    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * Sets the value of bowColumn
     * @param column the column corresponding to the position of the bow
     */

    public void setBowColumn(int column) {
        this.bowColumn = column;  
    }

    /**
     * Sets the value of the instance variable horizontal
     * @param horizontal whether the ship is horizontal or not
     */

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    //abstract method

    /**
     * Every specific type of Ship has to override and implement this method
     * @return the type of ship as a String.
     */

    public abstract String getShipType();

    //other methods

    /**
     * Based on the given row, column, and orientation
     * Returns true if it is okay to put a ship of this length with its bow in this location; false otherwise.
     */

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        //create a boolean variable to store the result
        boolean okToPlace = true;

        if (horizontal){  //check if the ship is horizontal
            if (column - this.length < -1){  //check if the given location is out of the ocean
                okToPlace = false;
            }else{
                for (int i = 0; i < this.length; i++){  //check if the given location is adjacent to a ship
                    if (isAdjacentToBow(row, column - i, ocean)){
                        okToPlace = false;
                    }
                }
            }
        }else{
            if (row - this.length < -1){
                okToPlace = false;
            }else{
                for (int i = 0; i < this.length; i++){
                    if (isAdjacentToBow(row - i, column, ocean)){
                        okToPlace = false;
                    }
                }
            }
        }

        //return the result
        return okToPlace;
    }

    /**
     * A helper method for okToPlaceShipAt
     * @param row of the bow
     * @param column of the bow
     * @param ocean the ocean
     * @return true if the given location is adjacent to a ship, false otherwise
     */

    private boolean isAdjacentToBow(int row, int column, Ocean ocean){

        //create a boolean variable to store the result
        boolean adjacent = false;

        //iterate through the 3*3 area around the given location
        for (int i = -1; i < 2 ; i++){
            for (int j = -1; j < 2 ; j++){

                //check if the location is out of the ocean
                if (row + i >= 0 && row + i < 10 && column + j >= 0 && column + j < 10) {

                    //check if the location is occupied by a ship
                    if (ocean.isOccupied(row + i, column + j)) {
                        adjacent = true;
                    }
                }
            }
        }

        //return the result
        return adjacent;
    }

    /**
     * “Puts” the ship in the ocean
     * @param row for the bow
     * @param column  for the bow
     * @param horizontal whether the ship is horizontal or not
     * @param ocean the ocean
     */

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){

        //set the bowRow, bowColumn, and horizontal instance variables
        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        if (horizontal){  //check if the ship is horizontal

            //place the ship in the ocean
            for (int i = 0; i < this.length ; i++){
                ocean.getShipArray()[row][column-i] = this;
            }

        }else{
            for (int i = 0; i < this.length ; i++){
                ocean.getShipArray()[row-i][column] = this;
            }
        }
    }

    /**
     * If a part of the ship occupies the given row and column
     * and the ship has not been sunk, mark that part of the ship as “hit”
     * @param row for the bow
     * @param column for the bow
     * @return true if a part of the ship occupies the given row and column and the ship has not been sunk
     */

    public boolean shootAt(int row, int column){

        //create a boolean variable to store the result
        boolean hit = false;

        //check if the ship has been sunk
        if(this.isSunk()){

            //return false if the ship has been sunk
            return false;
        }

        //create an index variable to store the index of the hit array
        int index = 0;

        if (this.isHorizontal()){  //check if the ship is horizontal

            //calculate the index of the hit array
            index = this.bowColumn - column;

            //check if the given location is occupied by the ship
            if (row == this.bowRow && column <= this.bowColumn && column >= this.bowColumn - this.length + 1){

                //mark that part of the ship as “hit”
                this.hit[index] = true;

                //set the result to true
                hit = true;
            }
        }else{

            index = this.bowRow - row;
            if (column == this.bowColumn && row <= this.bowRow && row >= this.bowRow - this.length + 1){
                this.hit[index] = true;
                hit = true;
            }
        }

        //return the result
        return hit;
    }

    /**
     * @return true if every part of the ship has been hit, false otherwise
     */

    public boolean isSunk() {

        //create a boolean variable to store the result
        boolean sunk = true;

        //iterate through the hit array
        for (int i = 0; i < this.length; i++){

            //check if every part of the ship has been hit
            if (!this.hit[i]) {
                sunk = false;

                //break the loop if a part of the ship has not been hit
                break;
            }
        }

        //return the result
        return sunk;
    }

    /**
     * @return a single-character String to use in the Ocean’s print method
     */

    @Override
    public String toString(){

        if (this.isSunk()){  //check if the ship has been sunk

            //return "x" if the ship has been sunk
            return "s";
        }else{

            //return "x" if the ship has not been sunk
            return "x";
        }
    }
}
