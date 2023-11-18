package battleship;

/**
 * Represents a destroyer in the game of Battleship
 * @author Haoyuan Zhu
 */

class Destroyer extends Ship {

    //static variable

    /**
     * The length of the destroyer
     */

    private static final int length = 2;

    /**
     * The name of the destroyer
     */

    private static final String shipName = "destroyer";

    //constructor

    /**
     * A zero-argument public constructor to set the length variable to the correct value
     */

    public Destroyer() {
        super(length);
    }

    //methods

    /**
     * @return a string representation of the destroyer
     */

    @Override
    public String getShipType() {
        return shipName;
    }

}
