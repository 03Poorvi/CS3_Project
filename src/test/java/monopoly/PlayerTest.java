package test.java.monopoly;

import main.java.monopoly.Board;
import main.java.monopoly.Player;
import main.java.monopoly.Property;
import main.java.monopoly.SimpleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Test Player", 1500, new SimpleStrategy());
    }

    @Test
    public void testInitialBalance() {
        assertEquals(1500, player.getBalance());
    }

    @Test
    public void testPlayerMove() {
        Board board = new Board();  // Assuming a Board object is needed
        player.move(3, board);  // Move 3 spaces
        assertEquals(3, player.getPosition());
    }

    @Test
    public void testBuyProperty() {
        Property property = new Property("Mediterranean Avenue", 60, 2);
        player.buyProperty(property);
        assertTrue(property.isOwned());
        assertEquals(player, property.getOwner());
        assertEquals(1440, player.getBalance());  // Initial balance of 1500 minus property price of 60
    }

    @Test
    public void testPayRent() {
        Player owner = new Player("Owner", 1500, new SimpleStrategy());
        Property property = new Property("Mediterranean Avenue", 60, 2);
        property.setOwner(owner);
        player.buyProperty(property);  // Player buys property
        player.payRent(owner, 2);  // Player pays rent
        assertEquals(1440, player.getBalance());  // Rent is 2
        assertEquals(1502, owner.getBalance());  // Owner gets rent
    }
}
