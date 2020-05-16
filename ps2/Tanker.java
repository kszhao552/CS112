package ps2;

public class Tanker extends Ship
{
	public Tanker()
	{
		super("Tanker", 3);
	}
	public boolean isSunk()
	{
		if (this.getNumHits()>=1)
		{
			return true;
		}
		return false;
	}
	public static void main(String[] args)
	{
		Tanker t = new Tanker();
		System.out.println(t);
		System.out.println("before it is hit: " + t.isSunk());
		t.applyHit();
		System.out.println("after it is hit: " + t.isSunk());
	}
	
}
