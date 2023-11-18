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

        Ship battleship = new Battleship();
        Ship cruiser = new Cruiser();
        Ship destroyer = new Destroyer();
        Ship submarine = new Submarine();


        //place the battleship
        this.placeOneKindOfShipRandomly(1, battleship);

        //place the cruiser
        this.placeOneKindOfShipRandomly(2, cruiser);

        //place the cruiser
        this.placeOneKindOfShipRandomly(3, destroyer);

        //place the destroyer
        this.placeOneKindOfShipRandomly(4, submarine);
    }

    /**
     * A private helper method to place one ship randomly
     * @param num the number of the ship
     * @param ship the ship
     */

    void placeOneKindOfShipRandomly (int num, Ship ship){
        while (num > 0){
            int row = (int) (Math.random() * 10);
            int column = (int) (Math.random() * 10);
            boolean horizontal = (Math.random() >= 0.5);

            if (ship.okToPlaceShipAt(row, column, horizontal, this)){
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
        //increment shotsFired
        this.shotsFired++;

        //if the location is occupied
        if (this.isOccupied(row, column)){
            //increment hitCount
            this.hitCount++;

            //if the ship is sunk
            if (this.ships[row][column].isSunk()){
                //increment shipsSunk
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
     * Prints the Ocean. To aid the user, row numbers should be displayed along the
     * left edge of the array, and column numbers should be displayed along the top.
     * Numbers should be 0 to 9, not 1 to 10.
     * o The top left corner square should be 0, 0.
     * o ‘x’: Use ‘x’ to indicate a location that you have fired upon and hit a (real) ship.
     * (reference the description of toString in the Ship class)
     * o ‘-’: Use ‘-’ to indicate a location that you have fired upon and found nothing
     * there. (reference the description of toString in the EmptySea class)
     * o ‘s’: Use ‘s’ to indicate a location containing a sunken ship. (reference the
     * description of toString in the Ship class)
     * o ‘.’: and use ‘.’ (a period) to indicate a location that you have never fired upon
     * o This is the only method in the Ocean class that does any input/output, and it is
     * never called from within the Ocean class, only from the BattleshipGame
     * class.
     */

    void print(){
        System.out.println("  0 1 2 3 4 5 6 7 8 9\n");
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
        System.out.println("  0 1 2 3 4 5 6 7 8 9\n");
        for (int i = 0; i < 10; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++){
                System.out.print(this.ships[i][j].toString() + " ");
            }
            System.out.println("\n");
        }
    }
}
