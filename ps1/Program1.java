package ps1;
import java.util.Scanner;

public class Program1 
{
    /*
     * This method should take an integer x and return:
     *    2x when x is odd
     *    the unchanged value of x when x is even
     */
    public static int double_if_odd(int x) 
    {
        if (x % 2 == 1)
        {
            x *= 2;
        }
      return x;
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter an integer x: ");
        int x = scan.nextInt();

        System.out.print("double_if_odd(x) = " + double_if_odd(x));
    }
}