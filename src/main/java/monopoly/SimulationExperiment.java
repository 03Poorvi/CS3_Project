package main.java.monopoly;

public class SimulationExperiment {
    public static void main(String[] args) {
        // Create the game components
        Board board = new Board();

        // Create players with names, initial balances, and strategies
        Player player1 = new Player("Player 1", 1500, new SimpleStrategy());
        Player player2 = new Player("Player 2", 1500, new AggressiveStrategy());

        // Simulate games between the two strategies
        int games = 20;
        for (int i = 0; i < games; i++) {
            System.out.println("Game " + (i + 1) + " - Start:");

            // Simulate a round of gameplay
            player1.takeTurn(board);  // Player 1 makes a move based on their strategy
            player2.takeTurn(board);  // Player 2 makes a move based on their strategy

            // Print player balances
            System.out.println(player1.getName() + " Balance: $" + player1.getBalance());
            System.out.println(player2.getName() + " Balance: $" + player2.getBalance());
            System.out.println("----------");
        }
    }
}
