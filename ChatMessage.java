/**
 * A message containing a single line of chat message.
 * @author tmcnulty14
 */
public class ChatMessage implements Message {
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
}