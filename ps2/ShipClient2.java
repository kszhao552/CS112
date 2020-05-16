package ps2;

public class ShipClient2 {
    public static void main(String[] args) {
        Ship s1 = new Ship("Cruiser", 3);
        System.out.println("Created a " + s1);   // toString() is called
        
        System.out.println();
        
        System.out.println("after creating ship:");
        System.out.println("    # hits = " + s1.getNumHits());
        System.out.println("   is sunk = " + s1.isSunk());
        
        System.out.println();
        s1.applyHit();
        System.out.println("after applying one hit:");
        System.out.println("    # hits = " + s1.getNumHits());
        System.out.println("   is sunk = " + s1.isSunk());

        System.out.println();
        s1.applyHit();
        System.out.println("after applying a second hit:");
        System.out.println("    # hits = " + s1.getNumHits());
        System.out.println("   is sunk = " + s1.isSunk());

        System.out.println();
        s1.applyHit();
        System.out.println("after applying a third hit:");
        System.out.println("    # hits = " + s1.getNumHits());
        System.out.println("   is sunk = " + s1.isSunk());
    }
}