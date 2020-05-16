package ps8;
/*
 * OpenHashTable.java
 *
 * Computer Science 112, Boston University
 */

/*
 * A class that implements a hash table that employs open addressing
 * using either linear probing, quadratic probing, or double hashing.
 */
public class OpenHashTable implements HashTable {
    /* Private inner class for an entry in the hash table */
    private class Entry {
        private Object key;
        private LLQueue<Object> values;    // all of the values with this key
        
        private Entry(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
        }
    }
    
    // possible types of probing
    public static final int LINEAR = 0;
    public static final int QUADRATIC = 1;
    public static final int DOUBLE_HASHING = 2;
    public static final int NUM_PROBE_TYPES = 3;
    
    private Entry[] table;             // the hash table itself
    private int probeType = LINEAR;    // the type of probing
    
    public OpenHashTable(int size, int probeType) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        if (probeType < 0 || probeType >= NUM_PROBE_TYPES) {
            throw new IllegalArgumentException("invalid probeType: " + probeType);
        }
        
        table = new Entry[size];
        this.probeType = probeType;
    }
    
    /*
     * Constructor for a hash table of the specified size that uses double hashing
     */ 
    public OpenHashTable(int size) {
        // call the other constructor to do the work
        this(size, DOUBLE_HASHING);
    }
    
    /* first hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /* second hash function */
    public int h2(Object key) {
        int h2 = key.hashCode() % 5;
        if (h2 < 0) {
            h2 += 11;
        }
        h2 += 5;
        return h2;
    }
    
    /* 
     * probeIncr - returns the amount by which the current index
     * should be incremented to obtain the next element in the probe
     * sequence if we have already checked numChecked positions
     * and h2 is the value of the second hash function
     */
    private int probeIncr(int numChecked, int h2) {
       if (numChecked <= 0) {
          return 0;
       } else if (probeType == LINEAR) {
           return 1;
       } else if (probeType == QUADRATIC) {
           return (2*numChecked - 1);
       } else {   //  DOUBLE_HASHING:
           return h2;
       }
    }
    
    /*
     * probe - attempt to find a slot in the hash table for the specified key.
     *
     * If key is currently in the table, it returns the index of the entry.
     * If key isn't in the table, it returns the index of the first empty cell
     * in the table.
     * If overflow occurs, it returns -1.
     */
    private int probe(Object key) {
        int i = h1(key);    // first hash function
        int h2 = h2(key);   // second hash function
        int numChecked = 1;
        
        // keep probing until we get an empty position or a match
        while (table[i] != null && !key.equals(table[i].key)) {
            if (numChecked == table.length) {
                return -1;
            }
            
            i = (i + probeIncr(numChecked, h2)) % table.length;
            numChecked++;
        }
        
        return i;
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        
        int i = h1(key); 
        int h2 = h2(key);
        int numChecked = 1;
        int firstRemoved = -1;
        
        while (table[i] != null && !key.equals(table[i].key)) {
            // record the index of the first removed cell we see
            if (table[i].key == null && firstRemoved == -1) {
                firstRemoved = i;
            }
            
            if (numChecked == table.length) {
                break;
            }
            
            i = (i + probeIncr(numChecked, h2)) % table.length;
            numChecked++;
        }
        
        if (table[i] != null && key.equals(table[i].key)) {
            table[i].values.insert(value);
        } else if (firstRemoved != -1) {
            table[firstRemoved] = new Entry(key, value);
        } else if (table[i] == null) {
            table[i] = new Entry(key, value);
        } else {
            return false;
        }
        
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        
        int i = probe(key);
        
        if (i == -1 || table[i] == null) {
            return null;
        } else {
            return table[i].values;
        }
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
            
        int i = probe(key);
        if (i == -1 || table[i] == null) {
            return null;
        }
        
        LLQueue<Object> removedVals = table[i].values;
        table[i].key = null;
        table[i].values = null;
        return removedVals;
    }
}