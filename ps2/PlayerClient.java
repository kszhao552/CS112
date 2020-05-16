package ps2;

public class PlayerClient {
    public static void main(String[] args) {
        Ship s1 = new Ship("Battleship", 4);
        Ship s2 = new Ship("Cruiser", 3);
        Ship s3 = new Ship("Tanker", 3);
        Ship s4 = new Ship("Patrol Boat", 2);
        
        Player p = new Player("Admiral Perry");
        p.addShip(s1);
        p.addShip(s2);
        p.addShip(s3);
        p.addShip(s4);
        
        System.out.println("After adding all four ships:");
        p.printShips();
        System.out.println();
        
        p.removeShip(s2);
        System.out.println("After removing the cruiser:");
        p.printShips();
        System.out.println();
            
        p.removeShip(s1);
        System.out.println("After removing the battleship:");
        p.printShips();
    }
}