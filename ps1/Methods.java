package ps1;
/*
 * Problem Set 1
 *
 * Practice with static methods, part I
 */

public class Methods {
    /*
     * printVertical - takes a string s and prints the characters of 
     * the string vertically -- with one character per line.
     */
    public static void printVertical(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
        }
    }
    
    public static void printWithSpaces(String s)
    {
    	for (int i =0; i < s.length(); i++)
    	{
    		char c = s.charAt(i);
    		System.out.print(c + " ");
    	}
    }

    public static char middleChar(String s)
    {
    	int n = s.length()/2;
    	if (s.length()%2 ==0)
    	{
    		n-=1;
    	}
    	char mid = s.charAt(n);
    	return mid;
    }
    
    public static String moveToEnd(String s, int i)
    {
    	if (s.length() <= i)
    	{
    		return s;
    	}
    	else
    	{
    		String r = s.substring(i) + s.substring(0, i);
    		return r;
    	}
    	
    }
    
    public static void main(String[] args) {
        /* Sample test call */
        printVertical("method");   
        printWithSpaces("method");
        System.out.println(middleChar("clock"));
        System.out.println(moveToEnd("Boston", 4));
    }
}
