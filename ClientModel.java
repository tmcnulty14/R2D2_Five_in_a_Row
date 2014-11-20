public interface ClientModel {
    /**
     * Called by the GUI when the player clicks a board location.
     * @param x The x-coordinate of the clicked location.
     * @param y The y-coordinate of the clicked location.
     */
    public void requestMove(int x, int y);
    
    /**
     * Sends a chat message.
     * @param chat The string to send.
     */
    public void sendChatMessage(String chat);
}
