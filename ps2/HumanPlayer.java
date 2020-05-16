package ps2;

import java.util.*;

public class HumanPlayer extends Player
{
	public HumanPlayer(String name)
	{
		super(name);
	}
    public Guess nextGuess(Scanner console, Board otherBoard) 
	{
    	int row;
        int col;
        System.out.println("Enter your guess.");
        // Keep randomly selecting coordinates until we get 
        // a position that has not already been tried.
        System.out.println("row:");
        row = console.nextInt();
        System.out.println("collumn:");
        col = console.nextInt();
        Guess guess = new Guess(row, col);
        return guess;
	}
    
}
