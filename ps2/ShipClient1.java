package ps2;

public class ShipClient1 {
    public static void main(String[] args) {
        Ship s1 = new Ship("Cruiser", 3);
        System.out.println("first ship:");
        String type = s1.getType();
        System.out.println("    type = " + type);   
        int len = s1.getLength();
        System.out.println("  length = " + len);   
        int hits = s1.getNumHits();
        System.out.println("  # hits = " + hits);
        char symbol = s1.getSymbol();
        System.out.println("  symbol = " + symbol);
        
        System.out.println();
        
        Ship s2 = new Ship("Battleship", 5);
        System.out.println("second ship:");
        type = s2.getType();
        System.out.println("    type = " + type);   
        len = s2.getLength();
        System.out.println("  length = " + len);   
        hits = s2.getNumHits();
        System.out.println("  # hits = " + hits);
        symbol = s2.getSymbol();
        System.out.println("  symbol = " + symbol);
        
        System.out.println();
        
        // Try to create invalid Ships. You may want to add additional tests     
        // for invalid values.                                                   
        System.out.print("Trying to create a Ship with a negative length..."); 
        try {
            Ship s3 = new Ship("Negative", -1);
            System.out.println("failed to throw exception");
        } catch (Exception e) {
            System.out.println("correctly threw exception");
        }
        
        System.out.print("Trying to create a Ship with a null name..."); 
        try {
            Ship s3 = new Ship(null, 10);
            System.out.println("failed to throw exception");
        } catch (Exception e) {
            System.out.println("correctly threw exception");
        }       
    }
}