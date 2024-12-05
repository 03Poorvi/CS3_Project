package main.java.monopoly;

public class Property {
    private String name;
    private int price;
    private int rent;
    private boolean owned;
    private Player owner;

    // Constructor for the property
    public Property(String name, int price, int rent) {
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.owned = false;
        this.owner = null;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public boolean isOwned() {
        return owned;
    }

    public Player getOwner() {
        return owner;
    }

    // Sets the owner of the property
    public void setOwner(Player owner) {
        this.owner = owner;
        this.owned = true;
    }

    // Method to calculate rent based on the current rent value
    public int calculateRent() {
        return rent;  // This can be expanded if the rent varies based on different conditions
    }

    // Method to handle paying rent to the owner of the property
    public void payRent(Player player) {
        if (this.isOwned() && owner != player) {
            int rentAmount = calculateRent();  // Calculate the rent to pay
            System.out.println(player.getName() + " is paying rent of $" + rentAmount + " to " + owner.getName());
            player.payRent(owner, rentAmount);  // Player pays rent to the owner
        }
    }
}
