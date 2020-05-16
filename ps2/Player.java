package ps2;

/*
 * Player.java - blueprint class for objects that represent a single
 * "random" player in the game of Battleship.
 * 
 * Computer Science 112, Boston University
 */

import java.util.*;

public class Player {
    // a constant for the maximum number of ships per player
    public static final int SHIPS_PER_PLAYER = 5; 
    
    // fields
    private String name;
    private int numShips;

	private Ship[] ships;
    
    // PS 2: add the fields for the player's collection of ships
    
    
    /*
     * constructor for a Player with the specified name
     */
    public Player(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name must have at least one character");
        }
        
        this.name = name;
        this.ships = new Ship[SHIPS_PER_PLAYER];
        this.numShips =0;
        
        // PS 2: initialize the fields that you added above
        
    }
    
    /*
     * getName - returns the name of the player
     */
    public String getName() {
        return this.name;
    }
    
    /*
     * addShip - add the specified ship to the player's collection of ships
     */
    public void addShip(Ship s) {
        if (s == null) {
            throw new IllegalArgumentException("parameter must be non-null");
        }
        if (numShips>=SHIPS_PER_PLAYER)
        {
        	throw new IllegalStateException("exceeded max amount of ships");
        }
        for( int i =0; i<SHIPS_PER_PLAYER;i++)
        {
        	if (this.ships[i] == null)
        	{
        		this.ships[i] = s;
        		this.numShips +=1;
        		break;
        	}
        }
    }
    
    /*
     * removeShip - removes the specified ship from the player's collection of ships
     */
    public void removeShip(Ship s) {
        if (s == null) {
            throw new IllegalArgumentException("parameter must be non-null");
        }
        for (int i =0; i<this.numShips; i++)
        {
        	if (this.ships[i].equals(s))
        	{
        		this.ships[i] = this.ships[this.numShips-1];
        		this.ships[this.numShips-1] = null;
        		this.numShips -=1;
        		break;
        	}
        }
        
    }
    
    /*
     * printShips - prints whatever ships remain in the player's collection
     */
    public void printShips() {
        // PS 2: implement this method
    	for (int i=-0; i<this.numShips;i++)
    	{
    		System.out.println(this.ships[i]);
    	}
    	
    }
    
    /*
     * hasLost - has this player lost the game?
     * Returns true if this is the case, and false otherwise.
     */
    public boolean hasLost() {
       if (numShips ==0)
       {
    	   return true;
       }
       return false;
        
    }
    
    /*
     * nextGuess - returns a Guess object representing the player's
     * next guess for the location of a ship on the board specified
     * by the parameter otherBoard.
     */
    public Guess nextGuess(Scanner console, Board otherBoard) {
        int row;
        int col;
        
        // Keep randomly selecting coordinates until we get 
        // a position that has not already been tried.
        do {
            row = Board.RAND.nextInt(otherBoard.getDimension());
            col = Board.RAND.nextInt(otherBoard.getDimension());
        } while (otherBoard.hasBeenTried(row, col));
        
        Guess guess = new Guess(row, col);
        return guess;
    }
    
    /*
     * toString - returns a string representation of the player
     */
    public String toString() {
        return this.name;
    }
}