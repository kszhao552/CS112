package ps2;

/*
 * BattleshipGameFinal.java - the main class for a program for playing the
 * game of Battleship.
 * 
 * Computer Science 112, Boston University
 * 
 * This file includes all of the changes that we've asked
 * you to make to the original version of BattleshipGame.java
 * 
 * NOTE: After completing all of the tasks, this file
 * should compile and run correctly. If it doesn't, there is 
 * something wrong with one of your other classes. 
 */

import java.util.*;

public class BattleshipGameFinal {
    public static final int DIMENSION = 8;
    public static final int NUM_PLAYERS = 2;
    
    
    /*** Fields for a BattleshipGame object ***/
    
    // An array containing an object for each player.
    private Player[] players;
    
    // An array containing an object for each board. 
    private Board[] boards;
    
    // Indicates whether the program should pause after each
    // pair of moves.
    private boolean shouldPause;
    
    /*
     * constructor 
     */
    public BattleshipGameFinal() {
        this.players = new Player[NUM_PLAYERS];
        this.boards = new Board[NUM_PLAYERS];
        this.shouldPause = false;
    }
    
    public static void main(String[] args) {
        // Create a Scanner for reading from the keyboard.
        // *** YOU SHOULD USE THIS SCANNER AND YOU SHOULD **NOT** ***
        // *** CREATE ANY OTHER SCANNER OBJECTS. ***
        Scanner console = new Scanner(System.in);
        
        // If the user supplied a seed for the random-number generator
        // on the command line, use it.
        if (args.length > 0) {
            int seed = Integer.parseInt(args[0]);
            Board.RAND.setSeed(seed);
        }
        
        System.out.println("Welcome to the game of Battleship!");
        BattleshipGameFinal game = new BattleshipGameFinal();
        game.initialize(console);
        game.play(console);
    }
    
    /*
     * initialize - configures the state of the game, 
     * based on the type of game selected by the user.
     */
    public void initialize(Scanner console) {
        System.out.println("Types of games:");
        System.out.println("  1. human vs. computer");
        System.out.println("  2. computer vs. computer");
        
        boolean validChoice = false;
        do {
            System.out.print("Enter your choice: ");
            int choice = console.nextInt();
            console.nextLine();
            
            if (choice == 1) {
                // human player
                this.boards[0] = new Board(DIMENSION);
                this.players[0] = new HumanPlayer("you");
                
                // computer player
                this.boards[1] = new HiddenShipsBoard(DIMENSION);
                this.players[1] = new ComputerPlayer("the computer");
                
                validChoice = true;
                this.shouldPause = false;
            } else if (choice == 2) {
                // first computer player
                this.boards[0] = new Board(DIMENSION);
                this.players[0] = new ComputerPlayer("player 0");
                
                // second computer player
                this.boards[1] = new Board(DIMENSION);
                this.players[1] = new ComputerPlayer("player 1");
                
                validChoice = true;
                this.shouldPause = true;
            } else {
                System.out.println("That type of game is not supported.");
            }
        } while (!validChoice);
        
        addShipsTo(this.players[0], this.boards[0]);
        addShipsTo(this.players[1], this.boards[1]);
    }
    
    /*
     * addShipsTo - add a fleet of ships to the specified player 
     * and board. 
     * Note that this method can be static, because it doesn't need to
     * access any of the fields of the BattleshipGame object.
     */
    public static void addShipsTo(Player player, Board board) {
        addShipTo(new Ship("Battleship", 4), player, board);
        addShipTo(new Ship("Cruiser", 3), player, board);
        addShipTo(new Tanker(), player, board);
        addShipTo(new Ship("Patrol Boat", 2), player, board);
        addShipTo(new Ship("Patrol Boat", 2), player, board);
    }
    
    /*
     * addShipTo - add a single ship to the specified player 
     * and board. 
     * Note that this method can be static, because it doesn't need to
     * access any of the fields of the BattleshipGame object.
     */
    public static void addShipTo(Ship ship, Player player, Board board) {
        player.addShip(ship);
        board.addShip(ship);
    }
    
    /*
     * play - guides the playing of the game.
     */
    public void play(Scanner console) {
        boolean gameOver = false;
        
        while (!gameOver) {
            this.displayBoards(console);                            
            
            // first player's move
            gameOver = this.processOneGuess(console, 0, 1);
            
            // second player's move
            if (!gameOver) {
                gameOver = this.processOneGuess(console, 1, 0);
            }
        }
    }
    
    /*
     * processOneGuess - processes a single guess from the player whose index
     * is specified by the second parameter.  The index of the other
     * player is given by the third parameter.
     * 
     * Returns true if the player's guess ends the game, and false otherwise.
     */
    public boolean processOneGuess(Scanner console, int currentIndex, int otherIndex) {
        Player currentPlayer = this.players[currentIndex];
        Player otherPlayer = this.players[otherIndex];
        Board otherBoard = this.boards[otherIndex];
        
        // Get the current player's next guess. 
        Guess guess = currentPlayer.nextGuess(console, otherBoard);
        System.out.println(currentPlayer + " guessed: " + guess);
        
        // Apply the guess, and print a message if a ship is hit.
        Ship ship = otherBoard.applyGuess(guess);
        if (ship != null) {
            System.out.print("*** " + currentPlayer);
            if (ship.isSunk()) {
                System.out.println(" sank a " + ship + "!!");
                otherPlayer.removeShip(ship);
            } else {
                System.out.println(" got a hit!");
            }
        }
        
        if (otherPlayer.hasLost()) {
            System.out.println("*** Game over! ***");
            System.out.println("The winner is " + currentPlayer + ".");
            this.displayBoards(console);
            return true;
        } else {
            return false;
        }
    }        
    
    /*
     * displayBoards - display each player's board, and pause if appropriate.
     */
    public void displayBoards(Scanner console) {
        System.out.println();
        
        // Print the actual boards.
        System.out.println(this.players[0] + ":");
        this.boards[0].display();
        System.out.println(this.players[1] + ":");
        this.boards[1].display();
        
        // Pause if appropriate.
        if (this.shouldPause) {
            System.out.print("Press <ENTER> to continue (enter S to stop pausing): ");
            String entry = console.nextLine();
            if (entry.equalsIgnoreCase("S")) {
                this.shouldPause = false;
            }
        }
        
        // Print a horizontal line with the same width as the boards.
        for (int i = 0; i <= DIMENSION; i++) {
            System.out.print("---");
        }    
        System.out.println();
    }
}
