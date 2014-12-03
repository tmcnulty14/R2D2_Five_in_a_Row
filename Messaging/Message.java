package Messaging;

/**
 * Generic interface for messages. All other messages should implement this.
 * Used to communicate between the client (ClientModel) and server (GameController).
 * @author tmcnulty14
 */
public interface Message {
    /**
     * @return The id of the message's source.
     * 0 for server, 1 or 2 for clients 1 and 2.
     */
    public int getSourceId();
    
    public int getPriority();
    
    // Implement a constructor which assigns final values to data field(s).
    
    // Implement getter methods for data field(s).
}
