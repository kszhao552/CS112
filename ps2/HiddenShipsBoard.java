package ps2;

/*
 * HiddenShipsBoard.java - blueprint class for objects that represent a
 * board for the game of Battleship in which the unhit ships are not
 * displayed.
 * 
 * Computer Science 112, Boston University
 * 
 * ********* YOU SHOULD NOT EDIT THIS FILE. *********
 */

public class HiddenShipsBoard extends Board {
    /*
     * constructor
     */
    public HiddenShipsBoard(int dimension) {
        super(dimension);
    }
    
    // Override the inherited version so that a space will be
    // used for any position that has not already been tried.
    public char getSymbol(int row, int col) {
        if (! this.hasBeenTried(row, col)) {
            return ' ';
        } else {
            return super.getSymbol(row, col);
        }
    }
}