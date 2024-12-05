package test.java.monopoly;

import main.java.monopoly.Board;
import main.java.monopoly.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();  // Ensure board is properly initialized with properties
    }

    @Test
    public void testBoardSize() {
        assertEquals(40, board.getBoardSize(), "The board should have exactly 40 spaces.");
    }

    @Test
    public void testGetPropertyAtValidIndex() {
        Property property = board.getPropertyAt(1);  // Get the property at index 1
        assertNotNull(property, "Property at index 1 should not be null.");
        assertEquals("Mediterranean Avenue", property.getName(), "The property at index 1 should be Mediterranean Avenue.");
    }

    @Test
    public void testGetPropertyAtFirstIndex() {
        Property property = board.getPropertyAt(0);  // Get the property at index 0 (Go)
        assertNotNull(property, "Property at index 0 should not be null.");
        assertEquals("Go", property.getName(), "The property at index 0 should be Go.");
    }

    @Test
    public void testGetPropertyAtLastIndex() {
        Property property = board.getPropertyAt(39);  // Get the property at index 39 (Boardwalk)
        assertNotNull(property, "Property at index 39 should not be null.");
        assertEquals("Boardwalk", property.getName(), "The property at index 39 should be Boardwalk.");
    }

    @Test
    public void testGetPropertyAtInvalidIndex() {
        Property property = board.getPropertyAt(-1);  // Invalid index
        assertNull(property, "Property at invalid index -1 should be null.");

        property = board.getPropertyAt(40);  // Invalid index (out of bounds)
        assertNull(property, "Property at index 40 should be null (out of bounds).");
    }

    @Test
    public void testSpecialSpacesInitialization() {
        // Test that special spaces are correctly initialized
        Property goProperty = board.getPropertyAt(0);  // "Go"
        assertNotNull(goProperty);
        assertEquals("Go", goProperty.getName());

        Property jailProperty = board.getPropertyAt(10);  // "Jail"
        assertNotNull(jailProperty);
        assertEquals("Jail", jailProperty.getName());

        Property freeParkingProperty = board.getPropertyAt(20);  // "Free Parking"
        assertNotNull(freeParkingProperty);
        assertEquals("Free Parking", freeParkingProperty.getName());

        Property goToJailProperty = board.getPropertyAt(30);  // "Go to Jail"
        assertNotNull(goToJailProperty);
        assertEquals("Go to Jail", goToJailProperty.getName());
    }

    @Test
    public void testRentValue() {
        Property property = board.getPropertyAt(1);  // Mediterranean Avenue
        assertNotNull(property, "Mediterranean Avenue should not be null.");
        assertEquals(2, property.getRent(), "Rent for Mediterranean Avenue should be 2 initially.");

        property = board.getPropertyAt(39);  // Boardwalk
        assertNotNull(property, "Boardwalk should not be null.");
        assertEquals(50, property.getRent(), "Rent for Boardwalk should be 50 initially.");
    }
}
