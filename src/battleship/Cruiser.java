package battleship;

/**
 * Represents a cruiser in the game of Battleship
 * @author Haoyuan Zhu
 */

public class Cruiser extends Ship {

    //static variable

    /**
     * The length of the cruiser
     */

    private static final int length = 3;

    /**
     * The name of the cruiser
     */

    private static final String shipName = "cruiser";

    //constructor

    /**
     * A zero-argument public constructor to set the length variable to the correct value
     */

    public Cruiser() {
        super(length);
    }

    //methods

    /**
     * @return a string representation of the cruiser
     */

    @Override
    public String getShipType() {
        return shipName;
    }
}
