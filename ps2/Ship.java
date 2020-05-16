package ps2;

public class Ship {

	private String type;
	private int length;
	private int numHits;
	public Ship(String type, int length)
	{
		//constructor of the ship, initializing the type, number of hits and length.
		if (type == null||type == ""||length<1)
		{
			throw new IllegalArgumentException();
		}
		this.type = type;
		this.length = length;
		this.numHits = 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String getType()
	{
		//returns the type of ship
		return this.type;
	}
	
	public int getLength()
	{
		//returns the length of the ship
		return this.length;
	}
	
	public int getNumHits()
	{
		//returns the number of times the ship has been hit
		return this.numHits;
	}
	
	public char getSymbol()
	{
		//returns the first character of the ship's type
		return this.type.charAt(0);
	}
	
	public void applyHit()
	{
		this.numHits +=1;
	}
	public boolean isSunk()
	{
		if (this.getNumHits()>=this.getLength())
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		return this.getType() + " of length " + this.getLength();
	}
}
