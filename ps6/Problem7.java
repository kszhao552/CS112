/*
 * Problem7.java
 * 
 * Computer Science 112, Boston Univerity
 */
package ps6;
public class Problem7 {
    /*
     * getAllOdds (ArrayList version) - takes the ArrayList vals (which is 
     * assumed to contain only integers) and creates and returns a new 
     * ArrayList containing all of the odd integers in vals.
     */
    public static ArrayList getAllOdds(ArrayList vals) {
        /* Replace the line below with your implementation of this method. */
        ArrayList newList = new ArrayList(vals.length());
        for (int i =0; i<vals.length(); i++)
        {
        	int val = (Integer)vals.getItem(i);
        	if (val%2 == 1)
        	{
        		newList.addItem(val, newList.length());
        	}
        }
        return newList;
    }
    
    /*
     * getAllOdds (LLList version) - takes the LLList vals (which is 
     * assumed to contain only integers) and creates and returns a new 
     * LLList containing all of the odd integers in vals.
     */
    public static LLList getAllOdds(LLList vals) {
    	LLList newList = new LLList();
    	for (int i=0;i<vals.length();i++)
    	{
        	int val = (Integer)vals.getItem(i);
    		if (val%2 == 1)
    		{
    			newList.addItem(val, 0);
    		}
    	}
    	return newList;
    }
    
    public static void main(String[] args) {
        Integer[] vals = {2, 5, 14, 6, 5, 8, 3};  
        ArrayList list1 = new ArrayList(vals);
        ArrayList odds1 = Problem7.getAllOdds(list1);
        System.out.println(odds1);
        
        LLList list2 = new LLList(vals);
        LLList odds2 = Problem7.getAllOdds(list2);
        System.out.println(odds2);
        
        /* We encourage you to add additional test code below. */
    }
}