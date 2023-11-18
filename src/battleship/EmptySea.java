package battleship;

class EmptySea extends Ship {

    public EmptySea() {
        super(1);
    }

    @Override
    public String getShipType() {
        return "empty";
    }

    @Override
    boolean shootAt(int row, int column){
        return false;
    }

    @Override
    boolean isSunk(){
        return false;
    }

    @Override
    public String toString(){
        return "-";
    }

}
