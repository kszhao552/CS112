package ps8;
/*
 * HashTable.java
 *
 * Computer Science 112, Boston University
 */

public interface HashTable {
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    boolean insert(Object key, Object value);
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    Queue<Object> search(Object key);
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    Queue<Object> remove(Object key);
}