/**
 * Generic interface for messages. All other messages should implement this.
 * Used to communicate between the client (ClientModel) and server (GameController).
 * @author tmcnulty14
 */
public interface Message {
    public int getSourceId();
    
    // Implement a constructor which assigns final values to data field(s).
    
    // Implement getter methods for data field(s).
}
