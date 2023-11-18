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
     * returns true if it is okay to put a ship of this length with its bow in this location
     * false otherwise.
     */

    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        boolean okToPlaceShip = true;
        int row_start, row_end, column_start, column_end ,row_length, column_length;
        int row_add = 1, row_sub = 1, column_add = 1, column_sub = 1;
        if (horizontal){
            row_length = this.length;
            column_length = 0;
        }else{
            row_length = 0;
            column_length = this.length;
        }

        if (row>9||((row - row_length)<0)||column>9||((column-column_length)<0)){
            okToPlaceShip = false;
            return okToPlaceShip;
        }

        if (row == 9){
            row_add = 0;
        }
        if ((row-row_length)==0){
            row_sub = 0;
        }
        if (column == 9){
            column_add = 0;
        }
        if ((column - column_length)==0){
            column_sub = 0;
        }
        
        row_end = row + row_add;
        row_start =  row - row_length - row_sub;
        column_end = column + column_add;
        column_start = column - column_length - column_sub;

        for (int column_index = column_start ; column_index< column_end + 1 ; column_index++){
            for (int row_index = row_start ; row_index < row_end + 1 ; row_index ++ ){
                if( ocean.isOccupied(row_index, column_index)){
                    okToPlaceShip = false;
                }
            }
        }

        return okToPlaceShip;
    }

    /**
     * “Puts” the ship in the ocean
     * @param row for the bow
     * @param column  for the bow
     * @param horizontal whether the ship is horizontal or not
     * @param ocean the ocean
     */

    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
        int row_start, row_end, column_start, column_end ,row_length, column_length;
        if (horizontal){
            row_length = this.length;
            column_length = 0;
        }else{
            row_length = 0;
            column_length = this.length;
        }

        row_end = row;
        row_start =  row - row_length;
        column_end = column;
        column_start = column - column_length;

        for (int column_index = column_start ; column_index< column_end + 1 ; column_index++){
            for (int row_index = row_start ; row_index < row_end + 1 ; row_index ++ ){
               ocean.getShipArray()[row_index][column_index] = this; 
            }
        }
    }

    /**
     * If a part of the ship occupies the given row and column
     * and the ship hasn’t been sunk
     * mark that part of the ship as “hit”
     * @param row for the bow
     * @param column for the bow
     * @return true if a part of the ship occupies the given row and column and the ship has not been sunk
     */

    boolean shootAt(int row, int column){
        boolean shootat = false;
        if (this.isSunk()){
            if (this.horizontal){
                if (this.bowRow == row && (column<=this.bowColumn && column>=this.bowColumn-this.length)){
                    shootat = true;
                }
            }else{
                if (this.bowColumn == column && (row<=this.bowRow && row>=this.bowRow-this.length)){
                    shootat = true;
                }
            }
        }
        return shootat;
    }

    /**
     * @return true if every part of the ship has been hit, false otherwise
     */

    boolean isSunk() {
        boolean issunk = true;
        for (int i = 0; i < this.length ; i++){
            if (!this.hit[i]){
                issunk = false;
            }
        }
        return issunk;
    }

    /**
     * @return a single-character String to use in the Ocean’s print method
     */

    @Override
    public String toString(){
        if (this.isSunk()){
            return "x";
        }else{
            return "S";
        }
    }
}
