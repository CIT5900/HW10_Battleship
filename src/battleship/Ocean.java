package battleship;

public class Ocean {

    //Used to quickly determine which ship is in any given location
    private Ship [][] ships = new Ship[10][10];

    //The total number of shots fired by the user
    private int shotsFired;

    //The number of times a shot hit a ship. If the user shoots the same part of a ship
    //more than once, every hit is counted, even though additional “hits” don’t do the
    //user any good.
    private int hitCount;

    //The number of ships sunk (10 ships in all)
    private int shipsSunk;

    //Creates an ”empty” ocean (and fills the ships array with EmptySea objects).
    //You could create a private helper method to do this.
    //Also initializes any game variables, such as how many shots have been fired.
    public Ocean(){
        this.ships = new EmptySea[10][10];
    }
    
    //Place all ten ships randomly on the (initially empty) ocean
    void placeAllShipsRandomly(){

    }

    //Returns true if the given location contains a ship, false if it does not
    boolean isOccupied(int row, int column){
        if (ships[row][column].getShipType()=="-"){
            return false;
        }else{
            return true;
        }
    }

    boolean isGameOver(){
        boolean GameOver = true;
        for (int i = 0; i<9;i++){
            for (int j =0 ; j< 9; j++){
                if (!(this.ships[i][j].isSunk()) && !(this.ships[i][j].getShipType()=="-")){
                    GameOver = false;
                }
            }
        }
        return GameOver;
    }

    int getShipsSunk(){
        return 1;
    }

    //Returns the 10x10 array of Ships.
    Ship[][] getShipArray(){
        return this.ships;
    }

    void print(){
        System.out.println("  0 1 2 3 4 5 6 7 8 9\n")
        for (int i = 0 ; i < 9 ; i++){
            System.out.println("i ");
            for (int j = 0 ; j < 9 ; j++){
                System.out.println(this.ships[i][j].getShipType().toLowerCase().charAt(0)+" ");
            }
            System.out.println("\n");
        }
    }
}
