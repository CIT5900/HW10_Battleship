package battleship;

/**
 * Represents an empty sea in the game of Battleship
 * @author Haoyuan Zhu & Chengjun Li
 */

class EmptySea extends Ship {

    //static variable

    /**
     * The length of the empty sea
     */

    private static final int length = 1;

    //constructor

    /**
     * A zero-argument public constructor to set the length variable to the correct value
     */

    public EmptySea() {
        super(length);
    }

    //methods

    /**
     * @return false to indicate that nothing was hit
     */

    @Override
    public boolean shootAt(int row, int column){
        this.getHit()[0] = true;
        return false;
    }

    /**
     * @return false to indicate that nothing was sunk
     */

    @Override
    public boolean isSunk(){
        return false;
    }

    /**
     * @return a single-character “-“ String to use in the Ocean’s print method
     */

    @Override
    public String toString(){
        return "-";
    }

    /**
     * @return a string representation of the empty sea
     */

    @Override
    public String getShipType() {
        return "empty";
    }
}
