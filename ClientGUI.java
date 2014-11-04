public interface ClientGUI {
    /**
     * Updates the GUI's displayed board pieces.
     * @param board A 2d array of (0, 1, 2), 0 = empty, 1/2 = player 1/2
     */
    public void updateBoard(int[][] board);
    
    /**
     * Changes the message the GUI is displaying.
     * @param message A message to be displayed on the GUI.
     */
    public void displayMessage(String message);
}
