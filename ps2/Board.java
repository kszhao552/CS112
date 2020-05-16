package ps2;

/*
 * Board.java - blueprint class for objects that represent a board
 * in the game of Battleship.
 * 
 * Computer Science 112, Boston University
 * 
 * ********* YOU SHOULD NOT EDIT THIS FILE. *********
 */

import java.util.*;

public class Board {
    // The three possible states for a cell on the board.
    public static final int EMPTY = 0;
    public static final int UNHIT = 1;
    public static final int HIT = 2;
    public static final int MISS = 3;
    
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    
    public static final Random RAND = new Random();
    
    private int dimension;
    private int[][] status;      // status of each position on the board
    private Ship[][] shipAt;     // the ship (if any) located at each position
    private int shipsRemaining;  // number of unsunk ships
    
    /*
     * constructor for a Board with the specified dimension
     */
    public Board(int dimension) {
        this.dimension = dimension;
        
        // Initially, the array will be filled with 0s,
        // which corresponds to all of the positions being empty.
        this.status = new int[dimension][dimension];
        
        // Initially, the array will be filled with nulls,
        // which corresponds to all of the positions having no ships.
        this.shipAt = new Ship[dimension][dimension];
        
        // This will increase as ships are added by the addShip method.
        this.shipsRemaining = 0;
    }
    
    /*
     * getDimension - returns the dimension of the board
     */
    public int getDimension() {
        return this.dimension;
    }
    
    /*
     * getShipsRemaining - returns the number of unsunk ships on the board
     */
    public int getShipsRemaining() {
        return this.shipsRemaining;
    }
    
    /*
     * addShip - add the specified ship to the board
     * at a randomly selected location.
     */
    public void addShip(Ship ship) {
        int startRow, startCol, direction;
        int dRow, dCol;
        
        // Find a position and direction that works.
        do {
            startRow = RAND.nextInt(dimension);
            startCol = RAND.nextInt(dimension);
            direction = RAND.nextInt(2);
            if (direction == VERTICAL) {
                dRow = 1;
                dCol = 0;
            } else {      // HORIZONTAL
                dRow = 0;
                dCol = 1;
            }
        } while (!this.canPlaceShip(ship.getLength(), startRow, startCol, 
                                    dRow, dCol)); 
        
        // Update the status and shipAt arrays.
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < ship.getLength(); i++) {
            this.status[row][col] = UNHIT;
            this.shipAt[row][col] = ship;          
            row += dRow;
            col += dCol;
        }
        
        this.shipsRemaining++;
    }
    
    /*
     * canPlaceShip - determines if a ship with the specified length
     * can be placed on the board at the starting position
     * (startRow, startCol) and with the specified change in row (dRow)
     * and change in column (dColumn).
     * 
     * Returns true if a ship can be placed there, and false if it cannot.
     * 
     * This method is private, because we only want it to be accessible
     * by other Board methods.
     */
    private boolean canPlaceShip(int length, int startRow, int startCol, 
                                 int dRow, int dCol) {
        // Check all of the positions that would be occupied by the ship.
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < length; i++) {
            if (row < 0 || row >= this.dimension ||
                col < 0 || col >= this.dimension || 
                this.status[row][col] != EMPTY) {
                return false;
            }
            row += dRow;
            col += dCol;
        }
        
        // If we get here, all of the positions must have been valid
        // and unoccupied.
        return true;
    }
    
    /*
     * applyGuess - applies the specified guess to the board, 
     * updating the state accordingly.
     * 
     * If the guess is a hit, the method returns the hit ship.
     * Otherwise, it returns null.
     */
    public Ship applyGuess(Guess guess) {
        if (guess == null) {
            throw new IllegalArgumentException("guess cannot be null");
        }
        
        int row = guess.getRow();
        int col = guess.getColumn();
        
        // Previously tried cell.
        if (this.previousHit(row, col) || this.previousMiss(row, col)) {
            return null;
        }
        
        // Process a miss.
        if (this.status[row][col] == EMPTY) {
            this.status[row][col] = MISS;
            return null;
        }
        
        // Process a hit.
        this.status[row][col] = HIT;
        Ship hitShip = this.shipAt[row][col];
        hitShip.applyHit();
        if (hitShip.isSunk()) {
            this.shipsRemaining--;
        }
        return hitShip;
    }
    
    /*
     * previousHit - has the position at (row, col) already been 
     * the location of a hit?
     * Returns true if it has, and false otherwise.
     */
    public boolean previousHit(int row, int col) {
        return (this.status[row][col] == HIT || this.sunkShipAt(row, col));
    }
    
    /*
     * previousMiss - has the position at (row, col) already been the
     * location of a miss?
     * Returns true if it has, and false otherwise.
     */
    public boolean previousMiss(int row, int col) {
        return (this.status[row][col] == MISS);
    }
    
    /*
     * hasBeenTried - has the position at (row, col) already been 
     * tried by a previous guess (either a hit or a miss)?
     * Returns true if it has, and false otherwise.
     */
    public boolean hasBeenTried(int row, int col) {
        return (this.previousHit(row, col) || this.previousMiss(row, col));
    }
    
    /*
     * sunkShipAt - is their a sunk ship at the position (row, col)?
     * Returns true if there is, and false otherwise.
     */
    public boolean sunkShipAt(int row, int col) {
        return (this.shipAt[row][col] != null && this.shipAt[row][col].isSunk());
    }
    
    /*
     * getSymbol - returns the single-character symbol that should be
     * used for printing the position (row, col) when the board
     * is displayed
     */
    public char getSymbol(int row, int col) {
        if (this.status[row][col] == EMPTY) {
            return ' ';
        } else if (this.previousHit(row, col)) {
            return 'X';
        } else if (this.previousMiss(row, col)) {
            return '-';
        } else {
            return this.shipAt[row][col].getSymbol();
        }
    }   
    
    /*
     * display - displays the current state of the board,
     * calling getSymbol to determine what character to print
     * for each position.
     */
    public void display() {
        // Print the column numbers.
        System.out.print("   ");
        for (int col = 0; col < this.dimension; col++) {
            System.out.printf("%3d", col);
        }
        System.out.println();        
        
        // Print the rows, one at a time.
        for (int row = 0; row < this.dimension; row++) {
            System.out.printf("%3d:", row);
            for (int col = 0; col < this.dimension; col++) {
                System.out.print(" " + this.getSymbol(row, col) + " ");
            }
            System.out.println();
        }
        
        System.out.println();
    }
}
