package battleship;

/**
 * Represents a submarine in the game of Battleship
 * @author Haoyuan Zhu
 */

class Submarine extends Ship {

    //static variable

    /**
     * The length of the submarine
     */

    private static final int length = 1;

    /**
     * The name of the submarine
     */

    private static final String shipName = "submarine";

    //constructor

    /**
     * A zero-argument public constructor to set the length variable to the correct value
     */

    public Submarine() {
        super(length);
    }

    //methods

    /**
     * @return a string representation of the submarine
     */

    @Override
    public String getShipType() {
        return shipName;
    }
}
