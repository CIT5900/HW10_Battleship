package battleship;

class Battleship extends Ship {

    public Battleship(int length) {
        super(4);
        
    }

    @Override
    public String getShipType() {
        return "battleship";
    }
    
}
