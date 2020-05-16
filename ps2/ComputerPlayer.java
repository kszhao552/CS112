package ps2;

import java.util.*;

public class ComputerPlayer extends Player
{
	private Guess lastHit;
	private boolean above;
	private boolean below;
	private boolean left;
	private boolean right;
	public ComputerPlayer(String name)
	{
		super(name);
	}
	public Guess nextGuess(Scanner console, Board otherBoard) 
	{
    	Guess guess;
        if (this.lastHit == null)
        {
        	guess = super.nextGuess(console, otherBoard);
        }
        else
        {
        	if (otherBoard.previousHit(lastHit.getRow(), lastHit.getColumn())&&!otherBoard.sunkShipAt(lastHit.getRow(),lastHit.getColumn()))
        	{
        		if (lastHit.getColumn() == 7)
        		{
        			this.right = false;
        		}
        		else if (otherBoard.hasBeenTried(lastHit.getRow(), lastHit.getColumn()+1))
        		{
        			this.right = false;
        		}
        		else
        		{
        			this.right = true;
        		}
        	
        		if (lastHit.getColumn() == 0)
        		{
        			this.left = false;
        		}
        		else if (otherBoard.hasBeenTried(lastHit.getRow(), lastHit.getColumn()-1))
        		{
        			this.left = false;
        		}
        		else
        		{
        			this.left = true;
        		}
        	
        		if (lastHit.getRow() == 7)
        		{
        			this.below = false;
        		}
        		else if (otherBoard.hasBeenTried(lastHit.getRow()+1, lastHit.getColumn()))
        		{
        			this.below = false;
        		}
        		else
        		{
        			this.below = true;
        		}
        	
        		if (lastHit.getRow() == 0)
        		{
        			this.above = false;
        		}
        		else if (otherBoard.hasBeenTried(lastHit.getRow()-1, lastHit.getColumn()))
        		{
        			this.above = false;
        		}
        		else
        		{
        			this.above = true;
        		}
        	
        		if (this.right)
        		{
        			guess = new Guess(lastHit.getRow(), lastHit.getColumn()+1);
        		}
        		else if (this.left)
        		{
        			guess = new Guess(lastHit.getRow(), lastHit.getColumn()-1);
        		}
        		else if (this.above)
        		{
        			guess = new Guess(lastHit.getRow()-1, lastHit.getColumn());
        		}
        		else if (this.below)
        		{
        			guess = new Guess(lastHit.getRow()+1, lastHit.getColumn());
        		}
        		else
        		{
        			guess = super.nextGuess(console, otherBoard);
        		}
        	}
        	else
        	{
        		guess = super.nextGuess(console, otherBoard);
        	}
        }
        
		this.lastHit = guess;
        return guess;
	}
}
