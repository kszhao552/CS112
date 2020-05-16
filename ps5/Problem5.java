package ps5;

import java.util.Arrays;

public class Problem5 
{
	public static void main(String[] args)
	{
		int[] arr = {2, 5, 5, 5, 10, 12, 12};
		int ret = removeDups(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(ret);
	}
	public static int removeDups(int[] arr)
	{
		int curNum = arr[0];
		int curNumIndex = 1;
		for (int i =1;i<arr.length; i++)
		{
			if (arr[i] == curNum)
			{
				arr[i] = 0;
			}
			else
			{
				curNum = arr[i];
				arr[curNumIndex] = arr[i];
				if (i != curNumIndex)
				{
					arr[i] = 0;
				}
				curNumIndex += 1;
			}
		}
		return curNumIndex;
	}
}
