package ps2;
import java.util.Arrays;


/*Problem Set 2 CS 112
 * Kradon Zhao
 * 
 */
public class ArrayMethods {
	public static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	public static void main(String[] args) 
	{
		System.out.println(getDayIndex("monday"));
		
		int[] a1 = {0, 2, 4, 6, 8, 10};
		swapAdjacent(a1);
		System.out.println(Arrays.toString(a1));
		
		int[] a2  = null;
		System.out.println(Arrays.toString(a2));
		
		int[] a3 = {2, 5, 6, 3, 7, 4, 1};
		int[] a4 = ArrayMethods.copyCapped(a3, 4);
		System.out.println(Arrays.toString(a4));
		
		int[] list1 = {1, 3, 6};
		int[] list2 = {1, 3, 5, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6};
		System.out.println(indexOf(list1, list2));
	}

	public static int getDayIndex(String day)
	//returns the index of the day entered by the user from the DAYS array.
	{
		for (int i =0; i<7; i++)
		{
			if (day.toLowerCase().equals(DAYS[i].toLowerCase()))
			{
				return i;
			}
		}
		return -1;
	}
	
	public static void swapAdjacent(int[] values)
	//swaps every pair of values in an inputed array
	{
		if (values == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			for (int i =0; i<values.length/2;i++)
			{
				int temp = values[2*i];
				values[2*i] = values[2*i+1];
				values[2*i+1] =temp;
			}
		}
	}
	
	public static int[] copyCapped(int[] values, int cap)
	//turns any value above the cap to the cap in an array of ints
	{
		int[] vals  = new int[values.length];
		if (values == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			for (int i=0; i<values.length; i++)
			{
				if (values[i] > cap)
				{
					vals[i] = cap;
				}
				else
				{
					vals[i] = values[i];
				}
			}
			return vals;
		}
	}
	
	public static int mostFrequentValue(int[] arr)
	//returns the most frequent number in an array of ints that is ordered
	{
		if (arr == null||arr.length ==0)
		{
			throw new IllegalArgumentException();
		}
		int maxCount = 0;
		int maxValue = arr[0];
		int count=0;
		int value = arr[0];
		for (int i =0; i<arr.length;i++)
		{
			if (arr[i] == value)
			{
				count +=1;
				if (count > maxCount)
				{
					maxValue = arr[i];
					maxCount = count;
				}
			}
			else
			{
				value = arr[i];
				count = 1;
			}
		}
		return maxValue;
	}
	
	public static int indexOf(int[] arr1, int[] arr2)
	//returns the index of when the sequence of the first array appears in the second array.
	{
		if (arr1 == null||arr2==null||arr1.length == 0|| arr2.length ==0)
		{
			throw new IllegalArgumentException();
		}
		int cur = arr1[0];
		int curIndex = 0;
		for (int i =0;i<arr2.length;i++)
		{
			if (cur == arr2[i])
			{
				curIndex+=1;
				if (curIndex == arr1.length)
				{
					return i-(arr1.length-1);
				}
				cur= arr1[curIndex];
			}
			else if (curIndex!=0)
			{
				curIndex=0;
				cur =arr1[0];
			}
		}
		return -1;
	}
}
