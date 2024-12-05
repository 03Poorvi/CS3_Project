package main.java.monopoly;

public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;

    public Game(Player[] players) {
        this.players = players;
        this.board = new Board();  // Initialize the game board
        this.currentPlayerIndex = 0;  // Start with the first player
    }

    public void nextTurn() {
        // Move to the next player (circular)
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Board getBoard() {
        return board;  // Return the board
    }

    public void playGame() {
        // Logic for playing the game (e.g., turn handling, rolling dice, etc.)
        // For now, we can just print a message
        while (!isGameOver()) {
            System.out.println("Current Player: " + getCurrentPlayer().getName());
            getCurrentPlayer().takeTurn(board);  // Pass the board instead of the game
            nextTurn();
        }
        System.out.println("Game Over!");
    }

    public boolean isGameOver() {
        // Placeholder game over logic
        // You can add more specific conditions (e.g., bankruptcy or after a number of rounds)
        return false;  // Game never ends in this placeholder
    }
}
