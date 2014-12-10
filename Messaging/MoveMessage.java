package Messaging;

import java.io.Serializable;

/**
 * A message containing information about a move (player, x, y).
 * @author tmcnulty
 */
public class MoveMessage implements Message, Serializable {
    private static final int PRIORITY = 2;
    
    private final int playerId;
    
    private final int moveX;
    private final int moveY;
    
    public MoveMessage(int playerId, int x, int y) {
        this.playerId = playerId;
        this.moveX = x;
        this.moveY = y;
    }
    
    @Override
    public int getSourceId() {
        return playerId;
    }
    
    public int getX() {
        return moveX;
    }
    
    public int getY() {
        return moveY;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
    
    @Override
    public String toString() {
        return "Messaging.MoveMessage" + playerId + ", " + moveX + ", " + moveY;
    }
}
