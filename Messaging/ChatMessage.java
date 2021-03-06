package Messaging;

import java.io.Serializable;

/**
 * A message containing a single line of chat message.
 * @author tmcnulty
 */
public class ChatMessage implements Message, Serializable {
    private static final int PRIORITY = 1;
    
    private final int playerId;
    
    private final String chatMessage;
    
    public ChatMessage(int playerId, String message) {
        this.playerId = playerId;
        this.chatMessage = message;
    }
    
    @Override
    public int getSourceId() {
        return playerId;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
}
