package main.java.monopoly;

public class SimpleStrategy implements Strategy {

    @Override
    public void makeMove(Player player, Board board) {
        int roll = (int) (Math.random() * 6) + 1;  // Roll a die between 1 and 6
        player.move(roll, board);  // Pass the roll (only spaces) to move

        Property property = board.getPropertyAt(player.getPosition());
        System.out.println(player.getName() + " rolled " + roll + " and landed on " + property.getName());

        if (!property.isOwned()) {
            // Use the correct method to allow the player to buy the property
            player.buyProperty(property);
            System.out.println(player.getName() + " bought " + property.getName());
        } else {
            property.payRent(player);
            System.out.println(player.getName() + " paid rent to " + property.getOwner().getName());
        }
    }
}
