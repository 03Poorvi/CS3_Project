package main.java.monopoly;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Property> properties;

    public Board() {
        properties = new ArrayList<>();
        initializeBoard();
    }

    // Initializes the board with the standard properties of Monopoly (40 spaces)
    private void initializeBoard() {
        properties.add(new Property("Go", 0, 0)); // Special space: Go
        properties.add(new Property("Mediterranean Avenue", 60, 2));
        properties.add(new Property("Community Chest", 0, 0)); // Special space
        properties.add(new Property("Baltic Avenue", 60, 4));
        properties.add(new Property("Income Tax", 200, 0));  // Special space
        properties.add(new Property("Reading Railroad", 200, 25));
        properties.add(new Property("Oriental Avenue", 100, 6));
        properties.add(new Property("Chance", 0, 0)); // Special space
        properties.add(new Property("Vermont Avenue", 100, 6));
        properties.add(new Property("Connecticut Avenue", 120, 8));
        properties.add(new Property("Jail", 0, 0));  // Special space
        properties.add(new Property("St. Charles Place", 140, 10));
        properties.add(new Property("Electric Company", 150, 0));  // Utility
        properties.add(new Property("States Avenue", 140, 10));
        properties.add(new Property("Virginia Avenue", 160, 12));
        properties.add(new Property("Community Chest", 0, 0)); // Special space
        properties.add(new Property("St. James Place", 180, 14));
        properties.add(new Property("Tennessee Avenue", 180, 14));
        properties.add(new Property("New York Avenue", 200, 16));
        properties.add(new Property("Free Parking", 0, 0));  // Special space
        properties.add(new Property("Kentucky Avenue", 220, 18));
        properties.add(new Property("Chance", 0, 0)); // Special space
        properties.add(new Property("Indiana Avenue", 220, 18));
        properties.add(new Property("Illinois Avenue", 240, 20));
        properties.add(new Property("B&O Railroad", 200, 25));
        properties.add(new Property("Atlantic Avenue", 260, 22));
        properties.add(new Property("Ventnor Avenue", 260, 22));
        properties.add(new Property("Water Works", 150, 0));  // Utility
        properties.add(new Property("Marvin Gardens", 280, 24));
        properties.add(new Property("Go to Jail", 0, 0));  // Special space
        properties.add(new Property("Pacific Avenue", 300, 26));
        properties.add(new Property("North Carolina Avenue", 300, 26));
        properties.add(new Property("Community Chest", 0, 0)); // Special space
        properties.add(new Property("Pennsylvania Avenue", 320, 28));
        properties.add(new Property("Short Line Railroad", 200, 25));
        properties.add(new Property("Chance", 0, 0)); // Special space
        properties.add(new Property("Park Place", 350, 35));
        properties.add(new Property("Luxury Tax", 100, 0));  // Special space
        properties.add(new Property("Boardwalk", 400, 50)); // High-value property
    }

    // Returns a list of all properties on the board
    public List<Property> getProperties() {
        return properties;
    }

    // Returns the property at the specified index
    public Property getPropertyAt(int index) {
        if (index >= 0 && index < properties.size()) {
            return properties.get(index);
        }
        return null;
    }

    // Returns the total number of spaces on the board
    public int getBoardSize() {
        return properties.size();  // Should return 40 now
    }

    // Displays the board for debugging/printing purposes
    public void displayBoard() {
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            System.out.print(i + ": " + property.getName() + " - ");
            if (property.isOwned()) {
                System.out.println("Owned by: " + property.getOwner().getName() + " | Rent: $" + property.getRent());
            } else {
                System.out.println("Available | Rent: $" + property.getRent());
            }
        }
    }
}
