/*
 * Problem Set 1
 * 
 * A simple interactive program that performs operations 
 * on a set of three integers.
 */
package ps1;
import java.util.*;

public class SimpleStats {

    public static void printMenu() {
        System.out.println("(0) Enter new numbers");
        System.out.println("(1) Find the largest");
        System.out.println("(2) Compute the sum");
        System.out.println("(3) Compute the range (largest - smallest)");
        System.out.println("(4) Compute the average");
        System.out.println("(5) Print the numbers in ascending order");
        System.out.println("(6) Quit");
        System.out.println();
    }
    
    /*** PUT YOUR SEPARATE HELPER METHODS FOR OPTIONS 1-5 HERE ***/
    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);        

        // the three integers
        int n1 = 2;
        int n2 = 4;
        int n3 = 6;

        boolean more_input = true;
        
        do {
            System.out.print("The current numbers are: ");
            System.out.println(n1 + " " + n2 + " " + n3);
            
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            
            /*
             * Expand this conditional statement to process choices 1-5.
             * Make sure to follow the guidelines in the assignment for
             * doing so.
             */
            if (choice == 0) {
                System.out.print("Enter three new numbers: ");
                n1 = scan.nextInt();
                n2 = scan.nextInt();
                n3 = scan.nextInt();
            }
            else if (choice == 1)
            {
            	System.out.println("The largest of the numbers is " + findLargest(n1, n2, n3));
            }
            else if (choice ==2)
            {
            	System.out.println("The sum of the numbers is " + sum(n1, n2, n3));
            }
            else if (choice ==3)
            {
            	System.out.println("The range of the numbers is " + range(n1, n2, n3));
            }
            else if (choice ==4)
            {
            	System.out.println("The average of the numbers is " + avg(n1, n2, n3));
            }
            else if (choice ==5)
            {
            	System.out.print("In order, the numbers are: ");
            	ascendOrder(n1, n2, n3);
            }
            else if (choice == 6) {
                more_input = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println();
        } while (more_input);
        
        System.out.println("Have a nice day!");
    }
    
    public static int findLargest(int n1, int n2, int n3)
    {
    	if (n1<n2)
    	{
    		if (n2<n3)
    		{
    			return n3;
    		}
    		else 
    		{
    			return n2;
    		}
    	}
    	else
    	{
    		if (n1<n3)
    		{
    			return n3;
    		}
    		else
    		{
    			return n1;
    		}
    	}
    }
    	
    	public static int range(int n1, int n2, int n3)
    	{
    		int largest = findLargest(n1, n2, n3);
    		if (n1>n2)
        	{
        		if (n2>n3)
        		{
        			return largest -n3;
        		}
        		else 
        		{
        			return largest -n2;
        		}
        	}
        	else
        	{
        		if (n1>n3)
        		{
        			return largest-n3;
        		}
        		else
        		{
        			return largest -n1;
        		}
    				
    	}
    }
    	public static double avg(int n1, int n2, int n3)
    	{
    		double avg = sum(n1, n2, n3)/3.0;
    		return avg;
    	}
    	public static void ascendOrder(int n1, int n2, int n3)
    	{
    		int largest = findLargest(n1, n2, n3);
    		int smallest;
    		if (n1>n2)
        	{
        		if (n2>n3)
        		{
        			smallest= n3;
        			System.out.print(smallest +" " + n2 + " " + largest);
        		}
        		else 
        		{
        			smallest= n2;
        			if (n3>n1)
        			{
            			System.out.print(smallest +" " + n1 + " " + largest);
        			}
        			else
        			{
            			System.out.print(smallest +" " + n3 + " " + largest);
        			}

        		}
        	}
        	else
        	{
        		if (n1>n3)
        		{
        			smallest = n3;
        			System.out.print(smallest +" " + n1 + " " + largest);
        		}
        		else
        		{
        			smallest = n1;
        			if (n3>n2)
        			{
            			System.out.print(smallest +" " + n2 + " " + largest);

        			}
        			else
        			{
        				System.out.print(smallest +" " + n3 + " " + largest);
        			}
        		}
        	}
    	}
    	public static int sum(int n1, int n2, int n3)
    	{
    		int sum = n1+n2+n3;
    		return sum;
    	}
}
