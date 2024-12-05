package main.java.monopoly;

public class AggressiveStrategy implements Strategy {

    @Override
    public void makeMove(Player player, Board board) {

        // Roll the dice
        int diceRoll = (int) (Math.random() * 6) + 1;  // Random roll between 1 and 6
        player.move(diceRoll, board);  // Pass diceRoll to the move method (only need the spaces)

        // Get the property the player landed on
        Property property = board.getPropertyAt(player.getPosition());

        if (property != null) {
            System.out.println(player.getName() + " rolled " + diceRoll + " and landed on " + property.getName());

            // If the property is not owned, buy it aggressively
            if (!property.isOwned()) {
                System.out.println(player.getName() + " is buying " + property.getName());
                player.buyProperty(property);  // Aggressive player buys the property
            } else {
                // If the property is already owned, pay rent to the owner
                Player owner = property.getOwner();
                int rent = property.calculateRent();  // Calculate rent
                System.out.println(player.getName() + " paid rent of $" + rent + " to " + owner.getName());
                player.payRent(owner, rent);  // Player pays rent
            }
        } else {
            System.out.println("No property at this position.");
        }

        // Print the player's balance after the turn
        System.out.println(player.getName() + " Balance: $" + player.getBalance());
    }
}
