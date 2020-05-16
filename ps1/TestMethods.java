package ps1;
/*
 * Test program for PS 1, Methods class
 *
 * Put this program in the same folder as your Methods.java.
 *
 * If it doesn't compile, that means that one or more of your methods
 * does not have the correct header -- i.e., either the name, the 
 * return type, or the parameters are incorrect.
 *
 * The correct results to these method calls are given in the assignment.
 * 
 * We encourage you to add additional test cases.
 */

public class TestMethods {
    public static void main(String[] args) {
        System.out.println("** part 1 **");
        Methods.printWithSpaces("method");
        System.out.println();
        
        System.out.println("** part 2, example 1 **");
        char mid1 = Methods.middleChar("clock");
        System.out.println(mid1);
        System.out.println();      
                
        System.out.println("** part 2, example 2 **");
        char mid2 = Methods.middleChar("Boston");
        System.out.println(mid2);
        System.out.println();
        
        System.out.println("** part 3, example 1 **");
        String str1 = Methods.moveToEnd("Boston", 4);
        System.out.println(str1);
        System.out.println();
        
        System.out.println("** part 3, example 2 **");
        String str2 = Methods.moveToEnd("Terriers", 2);
        System.out.println(str2);
        System.out.println();

        System.out.println("** part 3, example 3 **");
        String str3 = Methods.moveToEnd("Boston", 8);
        System.out.println(str3);
        System.out.println();
    }
}
