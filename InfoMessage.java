/**
 * A message containing information about the current game state.
 * @author tmcnulty14
 */
public class InfoMessage implements Message {
    private static final int PRIORITY = 3;
    
    private final int activePlayer; // 0, 1, 2
    
    private final int winnerPlayer; // 0, 1, 2
    
    /**
     * Constructor - Not used publicly, only used by static methods to 
     * create specific types of InfoMessages
     * @param activePlayer
     * @param winnerPlayer 
     */
    private InfoMessage(int activePlayer, int winnerPlayer) {
        this.activePlayer = activePlayer;
        this.winnerPlayer = winnerPlayer;
    }
    
    @Override
    public int getSourceId() {
        return 0; // Source is always from Server
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public int getWinnerPlayer() {
        return winnerPlayer;
    }
    
    /**
     * Returns an end of game info message specifying no active player, and
     * a specific winner player.
     * @param winner The id of the winner (1 or 2, or 0 for a tie).
     * @return A new InfoMessage object.
     */
    public static InfoMessage gameOver(int winner) {
        return new InfoMessage(0, winner);
    }
    
    /**
     * Returns a new turn info message specifying the active player and no winner.
     * @param activePlayer The id of the player whose turn it is (1 or 2, 0 for pause).
     * @return A new InfoMessage object.
     */
    public static InfoMessage newTurn(int activePlayer) {
        return new InfoMessage(activePlayer, 0);
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
}
