package ps2;

/*
 * Guess.java - blueprint class for objects that represent a single
 * guess (i.e., a single row, column pair) in the game of Battleship.
 * 
 * Computer Science 112, Boston University
 * 
 * ********* YOU SHOULD NOT EDIT THIS FILE. *********
 */

public class Guess {
    private int row;
    private int column;
    
    /*
     * constructor
     */
    public Guess(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    /*
     * getRow - returns the row coordinate of the guess
     */
    public int getRow() {
        return this.row;
    }
    
    /*
     * getColumn - returns the column coordinate of the guess
     */
    public int getColumn() {
        return this.column;
    }
    
    /*
     * toString - returns a string representation of the guess
     */
    public String toString() {
        return this.row + "," + this.column;
    }
}