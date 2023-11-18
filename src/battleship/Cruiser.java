package battleship;

/**
 * Represents a cruiser in the game of Battleship
 */

public class Cruiser extends Ship {

    //static variable

    /**
     * The length of the cruiser
     */

    private static final int length = 3;

    //constructor

    public Cruiser() {
        super(length);
    }

    //methods

    @Override
    public String getShipType() {
        return "cruiser";
    }
}
