public interface GameModel {
    /**
     * @param x The x coordinate of the target location.
     * @param y The y coordinate of the target location.
     * @param value The intended player value to set the target location to.
     * @return True if this was a valid move and was accepted.
     */
    public boolean setMarker(int x, int y, int value);
    
    /**
     * @return A Board object which represents the current board state.
     */
    public Board getBoard();
    
    /** 
     * @return The current winner (1 or 2) or 0 if no winner yet.
     */
    public int checkStatus(int x, int y);
}
