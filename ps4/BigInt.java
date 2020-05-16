package ps4;

import java.util.Arrays;
/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }
    
    //constructor if the input is an array -- creates a big int representing the array
    public BigInt(int[] arr)
    {
    	validate(arr);
        this.digits = new int[SIZE];
    	for (int i = 0; i<arr.length; i++)
    	{
    		this.digits[i+BigInt.SIZE-arr.length] = arr[i];
    	}   
    	this.numSigDigits = sigFig(this.digits);
    }
    
    //constructor if the input is an int -- creates a BigInt that represents n
    public BigInt(int n)
    {
    	if (n <0)
    	{
    		throw new IllegalArgumentException();
    	}
    	this.digits = new int[BigInt.SIZE];
    	int i =0;
    	do
    	{
    		this.digits[this.digits.length -i -1] = n%10;
    		n /=10;
    		i++;
    	}while(i< this.digits.length);
    	this.numSigDigits = sigFig(this.digits);
    }
    
    
    // compares two BigInts. returns 1 if the first is bigger, -1 if its smaller, and 0 if they're the same
    public int compareTo(BigInt other)
    {
    	if (other  == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	if (this.numSigDigits > other.numSigDigits)
    	{
    		return 1;
    	}
    	else if (other.numSigDigits> this.numSigDigits)
    	{
    		return -1;
    	}
    	else
    	{
    		for (int i = this.digits.length - this.numSigDigits; i <this.digits.length; i++)
    		{
    			if (this.digits[i] > other.digits[i])
    			{
    				return 1;
    			}
    			else if (other.digits[i] > this.digits[i])
    			{
    				return -1;
    			}
    		}
    		return 0;
    	}
    }
    
    public BigInt add(BigInt other)
    {
    	if (other == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	if (this.check())
    	{
    		return other;
    	}
    	else if (other.check())
    	{
    		return this;
    	}
    	
    	int[] sum = new int[BigInt.SIZE];
    	boolean carry = false;
    	int size;
    	if (this.numSigDigits >= other.numSigDigits)
    	{
    		size = this.numSigDigits;
    	}
    	else
    	{
    		size = other.numSigDigits;
    	}
    	for (int i = BigInt.SIZE-1; i>BigInt.SIZE - size-2; i--)
    	{
    		int total;
    		if (i == -1)
    		{
    			if (carry == true)
    			{
    				throw new ArithmeticException();
    			}
    			else
    			{
    				break;
    			}
    		}
    		total = this.digits[i] + other.digits[i];
    		if (carry)
    		{
    			total += 1;
    		}
    		if (total >=10)
    		{
    			carry = true;
    		}
    		else
    		{
    			carry = false;
    		}
    		sum[i] = total%10;
    	}
    	BigInt n = new BigInt(sum);
    	return n;
    }
    
    public BigInt mul(BigInt other)
    {
    	if (other == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	if (this.check()||other.check())
    	{
    		return new BigInt(0);
    	}
    	
    	BigInt prorest = new BigInt();
    	BigInt answer = new BigInt();
    	int total =0;
    	int carry = 0;
    	int remainder = 0;
    	for (int i = BigInt.SIZE-1; i>BigInt.SIZE - this.numSigDigits-1; i--)
    	{
        	int[] product = new int[20];
    		//turn into bigint every loop
    		//be i +j
    		for (int j = BigInt.SIZE-1; j>BigInt.SIZE-other.numSigDigits-2;j--)
    		{

    			total = other.digits[j] *this.digits[i];
    			total += carry;
    			remainder = total%10;
    			carry = total/10;
    			product[i+j-19] = remainder;
    			if (i+j-20 <0)
    			{
    				if (carry ==0)
    				{
    					break;
    				}
    				throw new ArithmeticException();
    			}
    			else
    			{
    				product[i+j-20] = carry;
    			}
    		}
    		answer = new BigInt(product);
    		prorest = prorest.add(answer);
    	}
    	return prorest;
    }
    
    public int getNumSigDigits()
    {
    	return this.numSigDigits;
    }
    public String toString()
    {
    	String s = "";
    	for (int i = this.digits.length - this.numSigDigits; i<this.digits.length; i++)
    	{
    		s += this.digits[i];
    	}
    	return s;
    }
    
    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();

        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        

        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
 
        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
    }
    private void validate(int[] arr)
    {
    	if (arr == null||arr.length > BigInt.SIZE)
    	{
    		throw new IllegalArgumentException();
    	}
    	for (int i= 0; i<arr.length; i++)
    	{
    		if (arr[i]>10||arr[i]<0)
    		{
    			throw new IllegalArgumentException();
    		}
    	}
    }
    private int sigFig(int[] arr)
    {
    	int i = 0;
    	while(arr[i] == 0)
    	{
    		i++;
    		if (i == arr.length)
    		{
    			break;
    		}
    	}
    	int sigfig = arr.length -i;
    	if (sigfig ==0)
    	{
    		sigfig =1;
    	}
    	return sigfig;
    }
    private boolean check()
    {
    	if (this.numSigDigits ==0)
    	{
    		if (this.digits[this.digits.length-1] == 0)
    		{
    			return true;
    		}
    	}
    	return false;
    }
}
