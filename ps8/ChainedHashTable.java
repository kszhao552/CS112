package ps8;
/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name:
 *     email:
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
    
    public ChainedHashTable(int s) {
    	//constructor that sets the size of the Hash table

    	if (s <=0) {
    		throw new IllegalArgumentException("size must be positive");
    	}
    	else
    	{
    		table = new Node[s];
    	}
    }
    
    
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
    	if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
    	
    	int index = h1(key)% table.length;
    	if (table[index] == null) {
    		numKeys+=1;
    		table[index] = new Node(key, value);
    		return true;
    	}else {
    		Node trail = null;
    		Node trav = table[index];
    		while (trav!= null) {
    			if (trav.key.equals(key)) {
    				trav.values.insert(value);
    				return true;
    			}
    			trail = trav;
    			trav = trav.next;
    		}
    		Node toInsert = new Node(key, value);
    		trail.next = toInsert;
    		numKeys+=1;
    		return true;
    	}
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
    	if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
    	
    	int index = h1(key);

    	Node trav = table[index];
    	while(trav != null) {
    		if (trav.key == key) {
    			return trav.values;
    		}else {
    			trav = trav.next;
    		}
    	}
    	return null;
    	
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
    	if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
    	
    	int index = h1(key);

    	Node trav = table[index];
    	Node trail = null;
    	while(trav != null) {
    		if (trav.key == key) {
        		numKeys-=1;
    			Queue<Object> queue =  trav.values;
    			
    			if (trail == null) {
    				table[index] = trav.next;
    				
    			}else {
    				trail.next = trav.next;
    			}
    			return queue;
    		}else {
    			trail = trav;
    			trav = trav.next;
    		}
    	}
    	return null;
    }
    
    
    /*** Add the other required methods here ***/
    public int getNumKeys() {
    	return numKeys;
    }
    
    public double load() {
    	double load = (double)(numKeys)/table.length;
    	return load;
    }
    
    public Object[] getAllKeys() {
    	Object[] keyList = new Object[numKeys];
    	int count = 0;
    	
    	for (int i = 0; i<table.length; i++) {
    		Node trav = table[i];
    		while (trav != null) {
    			keyList[count] = trav.key;
    			trav = trav.next;
    			count++;
    		}
    		
    		if (count == numKeys) {
    			break;
    		}
    	}
    	
    	return keyList;
    }
    
    public void resize(int s) {
    	if (s<table.length) {
    		throw new IllegalArgumentException("size must be larger than current size.");
    	}
    	if (s != table.length) {
        	Node[] oldTable = table;
        	table = new Node[s];
    		numKeys = 0;
        	for (int i = 0; i < oldTable.length; i++) {
        		Node trav = oldTable[i];
        		while (trav != null) {
        			insert(trav.key, trav.values);
        			trav= trav.next;
        		}
        	}
    	}
    }
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
    	try {
    	System.out.println("Test 1:");
    	ChainedHashTable table = new ChainedHashTable(5);
    	table.insert("howdy", 15);
    	table.insert("goodbye", 10);
    	System.out.println(table.insert("apple", 5));
    	System.out.println(table);
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    	System.out.println();
    	
    	try {
        	System.out.println("Test 2:");
        	ChainedHashTable table = new ChainedHashTable(2);
        	table.insert("test", 15);
        	table.insert("hello", 10);
        	System.out.println(table.insert("another", 5));
        	System.out.println(table);
        	}catch(Exception e) {
        		System.out.println("Error: " +e);
        	}
    	
    	System.out.println();
    	System.out.println("Test 3:");
    	try {
    	ChainedHashTable table1 = new ChainedHashTable(5);
    	table1.insert("howdy", 15);
    	table1.insert("goodbye", 10);
    	table1.insert("apple", 5);
    	System.out.println(table1.getNumKeys());
    	table1.insert("howdy", 25);     // insert a duplicate
    	System.out.println(table1.getNumKeys());
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    	
    	System.out.println();
    	System.out.println("Test 4:");
    	try {
    	ChainedHashTable table1 = new ChainedHashTable(5);
    	table1.insert("one", 15);
    	table1.insert("one", 10);
    	table1.insert("one", 5);
    	System.out.println(table1.getNumKeys());
    	table1.insert("two", 25);     // insert a duplicate
    	System.out.println(table1.getNumKeys());
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    	
    	System.out.println();
    	System.out.println("Test 5:");
    	try {
    	ChainedHashTable table2 = new ChainedHashTable(5);
    	table2.insert("howdy", 15);
    	table2.insert("goodbye", 10);
    	table2.insert("apple", 5);
    	System.out.println(table2.load());
    	table2.insert("pear", 6);
    	System.out.println(table2.load());
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    	
    	System.out.println();
    	System.out.println("Test 6:");
    	try {
    	ChainedHashTable table2 = new ChainedHashTable(1);
    	table2.insert("test", 15);
    	table2.insert("this", 10);
    	table2.insert("is", 5);
    	System.out.println(table2.load());
    	table2.insert("one", 6);
    	table2.insert("one", 4);
    	System.out.println(table2.load());
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    	System.out.println();
    	System.out.println("Test 7");
    	try {
    	ChainedHashTable table3 = new ChainedHashTable(5);
    	table3.insert("howdy", 15);
    	table3.insert("goodbye", 10);
    	table3.insert("apple", 5);
    	table3.insert("howdy", 25);    // insert a duplicate
    	Object[] keys = table3.getAllKeys();
    	System.out.println(Arrays.toString(keys));
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    	
    	System.out.println();
    	System.out.println("Test 8");
    	try {
    	ChainedHashTable table3 = new ChainedHashTable(3);
    	table3.insert("this", 15);
    	table3.insert("test", 10);
    	table3.insert("this", 5);
    	table3.insert("this", 25);   
    	table3.insert("example", 4);
    	Object[] keys = table3.getAllKeys();
    	System.out.println(Arrays.toString(keys));
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    	
    	System.out.println();
    	System.out.println("Test 9:");
    	try {
    		ChainedHashTable table5 = new ChainedHashTable(2);
    		table5.insert("test", 15);
    		table5.insert("example", 10);
    		table5.insert("score", 5);
    		table5.insert("hello", 3);
    		System.out.println(table5);
    		table5.resize(5);
    		System.out.println(table5);
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	System.out.println();
    	System.out.println("Test 10:");
		System.out.println("Illegal argument exception expected");
    	try {
    		ChainedHashTable table5 = new ChainedHashTable(5);
    		table5.insert("This", 15);
    		table5.insert("is", 10);
    		table5.insert("test", 5);
    		System.out.println(table5);
    		table5.resize(4);
    		System.out.println(table5);
    	}catch(Exception e) {
    		System.out.println("Error: " +e);
    	}
    }
}
