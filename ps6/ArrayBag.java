package ps6;
import java.util.*;

/*
 * An implementation of a bag data structure using an array.
 */
public class ArrayBag implements Bag {
    /* 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /* 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /*
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        items = new Object[DEFAULT_MAX_SIZE];
        numItems = 0;
    }
    
    /* 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        items = new Object[maxSize];
        numItems = 0;
    }
    
    /*
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return numItems;
    }
    
    /* 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (numItems == items.length) {
            return false;    // no more room!
        } else {
            items[numItems] = item;
            numItems++;
            return true;
        }
    }
    
    /* 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < numItems - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[numItems - 1] = null;
                
                numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /*
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /*
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * numItems);
        return items[whichOne];
    }
    
    /*
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[numItems];
        
        for (int i = 0; i < numItems; i++) {
            copy[i] = items[i];
        }
        
        return copy;
    }
    
    /*
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < numItems; i++) {
            str = str + items[i];
            if (i != numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("number of items in bag 1: ");
        int numItems = scan.nextInt();
        Bag bag1 = new ArrayBag(numItems);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < numItems; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
    }
}
