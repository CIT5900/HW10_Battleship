package battleship;

/**
 * Represents a battleship in the game of Battleship
 * @author Haoyuan Zhu
 */

class Battleship extends Ship {

    //static variable

    /**
     * The length of the battleship
     */

    private static final int length = 4;

    /**
     * The name of the battleship
     */

    private static final String shipName = "battleship";

    //constructor

    /**
     * A zero-argument public constructor to set the length variable to the correct value
     */

    public Battleship() {
        super(length);
    }

    //methods

    /**
     * @return a string representation of the battleship
     */

    @Override
    public String getShipType() {
        return shipName;
    }
}
