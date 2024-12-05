package main.java.monopoly;

public class Player {
    private String name;
    private int balance;
    private int position;  // Player's position on the board
    private Strategy strategy; // Strategy for the player

    // Constructor to initialize a player with a name, balance, and strategy
    public Player(String name, int initialBalance, Strategy strategy) {
        this.name = name;
        this.balance = initialBalance;
        this.position = 0;  // Start at the beginning of the board
        this.strategy = strategy; // Assign strategy
    }

    // Method for the player to take a turn using their strategy
    public void takeTurn(Board board) {
        strategy.makeMove(this, board);
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void move(int spaces, Board board) {
        position = (position + spaces) % board.getBoardSize();  // Wrap around the board
    }

    public int getPosition() {
        return position;
    }

    public void buyProperty(Property property) {
        if (!property.isOwned()) {
            if (balance >= property.getPrice()) {
                balance -= property.getPrice();
                property.setOwner(this);
            }
        }
    }

    public void payRent(Player owner, int rent) {
        if (balance >= rent) {
            balance -= rent;
            owner.setBalance(owner.getBalance() + rent);
        }
    }
}
