package ps1;

public class MyMethods
{
	public static void main(String[] args)
	{
		printDecreasing("method");
		System.out.println(firstAndLast("Boston"));
	}
	
	public static void printDecreasing(String s)
	{
		int limit = s.length();
		for (int i =0; i<limit; i++)
		{
			System.out.println(s);
			s = s.substring(0, s.length()-1);
		}
	}
	
	public static int lastIndexOf(String s, char a)
	{
		int index = -1;
		for (int i= 0; i<s.length(); i++)
		{
			if (s.charAt(i) == a)
			{
				index =i;
			}
		}
		return index;
	}
	
	public static String firstAndLast(String str)
	{
		if (str.length() == 1)
		{
			return str;
		}
		else
		{
			String s = str.charAt(0) + "" + str.charAt(str.length()-1);
			return s;
		}
	}
	
	public static String repeat(String str, int n)
	{
		String s ="";
		for (int i=0; i<n; i++)
		{
			s = s + str;
		}
		return s;
	}
}
