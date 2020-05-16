package ps7;

/*
 * ArrayQueue.java
 *
 * Computer Science 112, Boston University
 */
/*
 * A generic class that implements our Queue interface using an array.
 * We use a circular queue so that we don't need to shift items.
 */
public class ArrayQueue<T> implements Queue<T> {
    private T[] items;          // the items on the queue
    private int front;          // the index of the item at the front
    private int rear;           // the index of the item at the rear
    private int numItems;       // the number of items in the queue
    
    /*
     * Constructs an ArrayQueue object with the specified maximum size
     * for a queue that is initially empty.
     */
    public ArrayQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("max size must be positive");
        }
        items = (T[])new Object[maxSize];
        front = 0;
        rear = -1;
        numItems = 0;
    }
    
    /* 
     * isEmpty - returns true if the queue is empty, and false otherwise
     */
    public boolean isEmpty() {
        return (numItems == 0);
    }
    
    /*
     * isFull - returns true if the queue is full, and false otherwise 
     */
    public boolean isFull() {
        return (numItems == items.length);
    }

    /* 
     * insert - adds the specified item at the rear of the queue.
     * Returns false if the queue is full, and true otherwise.
     */
    public boolean insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else if (isFull()) {
            return false;
        }
        rear = (rear + 1) % items.length;
        items[rear] = item;
        numItems++;
        return true;
    }
    
    /* 
     * remove - removes the item at the front of the queue and returns a
     * reference to the removed object.  Returns null if the queue is
     * empty.
     */
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        numItems--;
        return removed;
    }
    
    /* 
     * peek - returns a reference to the item at the front of the queue
     * without removing it. Returns null if the queue is empty.
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return items[front];
    }
    
    /*
     * toString - converts the queue into a String of the form 
     * {front, one-after-front, two-after-front, ...}
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < numItems; i++) {
            int j = (front + i) % items.length;
            str = str + items[j];
            if (i < numItems - 1) {
                str = str + ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
}
