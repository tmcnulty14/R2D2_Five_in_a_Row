package Client;

public interface ClientGUI {
    /**
     * Adds a board piece to the GUI display.
     * @param x
     * @param y
     * 1 for player 1, 2 for player 2, 0 for empty.
     * abstract class so other classes can inherit the methods
     */
    public void updateBoard(int x, int y, int player);
    
    /**
     * Changes the message the GUI is displaying.
     * @param message A message to be displayed on the GUI.
     */
    public void displayMessage(String message);
}
