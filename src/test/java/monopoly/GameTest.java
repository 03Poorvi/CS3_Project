package test.java.monopoly;

import main.java.monopoly.Game;
import main.java.monopoly.Player;
import main.java.monopoly.SimpleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Player 1", 1500, new SimpleStrategy());
        player2 = new Player("Player 2", 1500, new SimpleStrategy());
        game = new Game(new Player[]{player1, player2});
    }

    @Test
    public void testNextTurn() {
        game.nextTurn();  // Move to the next player
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    public void testGameOver() {
        // Assuming a placeholder check for game over (you can enhance this logic)
        assertFalse(game.isGameOver());
    }

    @Test
    public void testPlayGame() {
        // Placeholder test for game play simulation
        game.playGame();
        assertNotNull(game.getBoard());
    }
}
