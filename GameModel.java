public interface GameModel {
    /**
     * @param x The x coordinate of the target location.
     * @param y The y coordinate of the target location.
     * @param player The intended player value to set the target location to.
     * @return True if this was a valid move and was accepted.
     * 
     * If the move is valid, it should be recorded and true should be returned.
     * If the move is not valid, it should not be recorded and false should be returned.
     */
    public boolean setMarker(int x, int y, int player);
    
    /** 
     * @return The current winner (1 or 2) or 0 if no winner yet.
     */
    public int checkStatus();
    
    /**
     * @return The player whose turn it is right now. 1 or 2, or 0 if game is 
     * not active.
     */
    public int getActivePlayer();
}
