package Messaging;

/**
 * This message is sent to the client when the game starts up to tell the client
 * what its client id number is (1 or 2).
 * @author mcnultyt
 */
public class HelloMessage implements Message {
    // Very high priority - must be read before game begins.
    private static final int PRIORITY = 4;
    
    // 1 or 2, lets the client know what its id number is.
    private final int clientId;
    
    public HelloMessage(int clientId) {
        this.clientId = clientId;
    }
    
    @Override
    public int getSourceId() {
        return 0; // Always from server
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    public int getClientId() {
        return clientId;
    }
}
