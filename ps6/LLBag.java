package ps6;

import java.util.Scanner;

public class LLBag implements Bag{

	 private class Node 
	 {
	        private Object item;
	        private Node next;
	        
	        private Node(Object i, Node n) 
	        {
	            item = i;
	            next = n;
	        }
	 }
	 private Node head;
	 private int length;
	 public LLBag()
	 {
		 head = new Node(null, null);
		 length = 0;
	 }
	//add(Object item) will add a new item into the bag. If item is null, it throws an error
	public boolean add(Object item) 
	{
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		Node insert = new Node(item, null);
		insert.next = head.next;
		head.next = insert;
		length+=1;
		return true;
	}

	/*
	 * remove - will remove one occurance of the object provided. If the object is removed
	 * it will return true. If the object is not found within the bag, then it will return false.
	 */
	public boolean remove(Object item) {
		Node trav = head.next;
		Node trail = head;
		while (trav != null)
		{
			if (item.equals(trav.item))
			{
				trail.next = trav.next;
				length -= 1;
				return true;
			}
			else
			{
				trail = trav;
				trav = trav.next;
			}
		}
		return false;
	}
	
	public String toString()
	{
		String s = "{";
		Node trav = head.next;
		while (trav!=null)
		{
			s+= trav.item;
			if (trav.next == null)
			{
				s+="}";
			}
			else
			{
				s+=", ";
			}
			trav = trav.next;
		}
		return s;
		
	}
	/*
	 * contains - searches the bag for the item. If item is in the bag,
	 * the method returns true. If the item is not in the bag, then it returns false.
	 */
	public boolean contains(Object item) {
		Node trav = head.next;
		while (trav != null)
		{
			if (item.equals(trav.item))
			{
				return true;
			}
			trav = trav.next;
		}
		return false;
	}

	/*
	 * numItems - returns the amount of items in Bag
	 */
	public int numItems() {
		// TODO Auto-generated method stub
		return length;
	}

	/*
	 * grab - returns a random item within the bag
	 */
	public Object grab() {
        if (length == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * length);
        Node trav = head.next;
        for (int i=0; i<whichOne;i++)
        {
        	trav = trav.next;
        }
        return trav.item;
	}

    /*
     * toArray - return an array containing the current contents of the bag
     */
	public Object[] toArray() {
		Object[] array = new Object[length];
		Node trav = head.next;
		for (int i=0;i<length;i++)
		{
			array[i] = trav.item;
			trav = trav.next;
		}
		return array;
	}
	
	 public static void main(String[] args) {
	        // Create a Scanner object for user input.
	        Scanner scan = new Scanner(System.in);
	        
	        // Create an ArrayBag named bag1.
	        System.out.print("number of items in bag 1: ");
	        int numItems = scan.nextInt();
	        Bag bag1 = new LLBag();
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
