package battleship;

/**
 * Represents a ship in the game of Battleship
 * @author Haoyuan Zhu
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

    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        boolean okToPlace = true;
        if (horizontal){
            if (column - this.length < -1){
                okToPlace = false;
            }else{
                for (int i = 0; i < this.length; i++){
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
        return okToPlace;
    }

    /**
     * A helper method for okToPlaceShipAt
     * @param row of the bow
     * @param column of the bow
     * @param ocean the ocean
     * @return true if the given location is adjacent to a ship, false otherwise
     */

    boolean isAdjacentToBow(int row, int column, Ocean ocean){
        boolean adjacent = false;
        for (int i = -1; i < 2 ; i++){
            for (int j = -1; j < 2 ; j++){
                if (row + i >= 0 && row + i < 10 && column + j >= 0 && column + j < 10) {
                    if (ocean.isOccupied(row + i, column + j)) {
                        adjacent = true;
                    }
                }
            }
        }
        return adjacent;
    }

    /**
     * “Puts” the ship in the ocean
     * @param row for the bow
     * @param column  for the bow
     * @param horizontal whether the ship is horizontal or not
     * @param ocean the ocean
     */

    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){

        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        if (horizontal){
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

    boolean shootAt(int row, int column){

        boolean hit = false;

        if(this.isSunk()){
            return false;
        }

        int index = 0;

        if (this.isHorizontal()){

            index = this.bowColumn - column;
            if (row == this.bowRow && column <= this.bowColumn && column >= this.bowColumn - this.length + 1){
                this.hit[index] = true;
                hit = true;
            }
        }else{

            index = this.bowRow - row;
            if (column == this.bowColumn && row <= this.bowRow && row >= this.bowRow - this.length + 1){
                this.hit[index] = true;
                hit = true;
            }
        }
        return hit;
    }

    /**
     * @return true if every part of the ship has been hit, false otherwise
     */

    boolean isSunk() {
        boolean sunk = true;
        for (int i = 0; i < this.length; i++){

            if (!this.hit[i]) {
                sunk = false;
                break;
            }
        }
        return sunk;
    }

    /**
     * @return a single-character String to use in the Ocean’s print method
     */

    @Override
    public String toString(){
        if (this.isSunk()){
            return "s";
        }else{
            return "x";
        }
    }
}
