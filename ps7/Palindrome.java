package ps7;


/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name:
 *     username:
 */
   
public class Palindrome {
    // Add your definition of isPal here.
    public static boolean isPal(String s) {
    
    	if (s==null) {
    		throw new IllegalArgumentException();
    	}else if (s.length() ==1)
    	{
    		return true;
    	}else {
    		LLQueue<Character> queue = new LLQueue<Character>();
    		LLStack<Character> stack = new LLStack<Character>();
    		
    		for (int i=0; i<s.length();i++) {
        		char c = s.toLowerCase().charAt(i);
        		if (c>=97&&c<=122)
        		{
        			queue.insert(c);
        			stack.push(c);
        		}
        	}
    		
    		while (!queue.isEmpty()) {
    			char qItem = queue.remove();
    			char sItem = stack.pop();
    			if (qItem != sItem) {
    				return false;
    			}
    		}
    		return true;
    	}
    	
    }
	
	
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */
        
        System.out.println("(1) Testing on \"This is not a Palindrome\"");
        try {
            boolean results = isPal("This is not a Palindrome");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("(2) Testing on \"racecar\"");
        try {
            boolean results = isPal("racecar");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("(3) Testing on \"Was it a car or a cat I saw?\"");
    try {
        boolean results = isPal("Was it a car or a cat I saw?");
        System.out.println("actual results:");
        System.out.println(results);
        System.out.println("expected results:");
        System.out.println("true");
        System.out.print("MATCHES EXPECTED RESULTS?: ");
        System.out.println(results == true);
    } catch (Exception e) {
        System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
    }
    
    System.out.println();    // include a blank line between tests
    
    System.out.println("(4) Testing on \"test\"");
    try {
        boolean results = isPal("test");
        System.out.println("actual results:");
        System.out.println(results);
        System.out.println("expected results:");
        System.out.println("false");
        System.out.print("MATCHES EXPECTED RESULTS?: ");
        System.out.println(results == false);
    } catch (Exception e) {
        System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
    }
    
    System.out.println();    // include a blank line between tests
    
    System.out.println("(5) Testing on \"Last one!\"");
    try {
        boolean results = isPal("Last one!");
        System.out.println("actual results:");
        System.out.println(results);
        System.out.println("expected results:");
        System.out.println("false");
        System.out.print("MATCHES EXPECTED RESULTS?: ");
        System.out.println(results == false);
    } catch (Exception e) {
        System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
    }
    
    System.out.println();    // include a blank line between tests
    }
    
   
}