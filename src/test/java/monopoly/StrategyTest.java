package test.java.monopoly;

import main.java.monopoly.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {

    private Player player;
    private Board board;
    private Strategy simpleStrategy;
    private Strategy aggressiveStrategy;

    @BeforeEach
    public void setUp() {
        player = new Player("Test Player", 1500, new SimpleStrategy());
        board = new Board();
        simpleStrategy = new SimpleStrategy();
        aggressiveStrategy = new AggressiveStrategy();
    }

    @Test
    public void testSimpleStrategy() {
        simpleStrategy.makeMove(player, board);
        assertTrue(player.getPosition() > 0);  // Ensure player moved
    }

    @Test
    public void testAggressiveStrategy() {
        aggressiveStrategy.makeMove(player, board);
        assertTrue(player.getPosition() > 0);  // Ensure player moved
    }
}
