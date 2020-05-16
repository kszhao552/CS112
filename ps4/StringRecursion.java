package ps4;

public class StringRecursion 
{
	public static void main(String[] args)
	{
		System.out.println(find('P', "Rabbit")); 
	}
	
	public static void printReverse(String str)
	{
		if (str.equals(""))
		{
			
		}
		else
		{
			printReverse(str.substring(1,str.length()));
			System.out.print(str.charAt(0));
		}
	}
	
	public static String trim(String str)
	{
		if (!(str.charAt(0)==' ') && !(str.charAt(str.length()-1)== ' '))
		{
			return str;
		}
		else if (str.charAt(0)==' ' && str.charAt(str.length()-1) == ' ')
		{
			return trim(str.substring(1, str.length()-1));
		}
		else if (str.charAt(0)==' ')
		{
			return trim(str.substring(1));
		}
		else
		{
			return trim(str.substring(0, str.length()-1));
		}
	}
	
	public static int find(char ch, String str)
	{
		if (str.equals(""))
		{
			return -1;
		}
		else if (str.charAt(0)==ch)
		{
			return 0;
		}
		else
		{
			int curPos = find(ch, str.substring(1, str.length()));
			if (curPos!= -1)
			{
				return curPos +1;
			}
			return curPos;
		}
	}
}
