package main.java.monopoly;

public interface Strategy {
    // Each strategy decides how to make a move
    void makeMove(Player player, Board board);
}
