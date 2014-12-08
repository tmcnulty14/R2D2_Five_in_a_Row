package Server;

import Messaging.*;
import java.io.*;
import java.net.*;

/**
 * This class handles game flow and communications on the server side.
 * It uses a GameModel to take care of game logic.
 * 
 * @author tmcnulty14
 */
public class R2D2GameController implements Runnable {
    private final GameModel model;
    private final R2D2Connection[] clients = new R2D2Connection[2];
    private boolean doneTurn = false;
    private int winner = 0;
    
    /**
     * Constructor. Initializes the model.
     */
    public R2D2GameController() {
        model = new R2D2GameModel();
        System.out.println("GameController: Initialized.");
    }
    
    /**
     * Sets up the client connections.
     * @param c1 Socket for client 1
     * @param c2 Socket for client 2
     */
    public void setSockets(Socket c1, Socket c2) {
        R2D2Connection client1;
        R2D2Connection client2;
        try {
            client1 = new R2D2Connection(c1);
            client2 = new R2D2Connection(c2);
            
            new Thread(client1).start();
            new Thread(client2).start();
            
            clients[0] = client1;
            clients[1] = client2;
        } catch (IOException e) {
            System.out.println("Error when setting client sockets.");
        }
        System.out.println("GameController: Sockets assigned.");
    }
    
    @Override
    public void run() {
        System.out.println("GameController: Running game.");
        // Send HelloMessages to clients
        for(int i=0; i<2; i++) {
            clients[i].sendMessage(new HelloMessage(i+1));
        }
        
        // Wait for a second while clients start up.
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Loop as long as nobody has won the game.
        // Each iteration of the loop is a new turn.
        while(winner == 0) {
            doneTurn = false;
            int activePlayer = model.getActivePlayer();
            // Tell the clients whose turn it is.
            for(R2D2Connection client : clients) {
                client.sendMessage(InfoMessage.newTurn(activePlayer));
            }
            
            // Handle messages in a loop until the turn is over.
            while(!doneTurn) {
                // Checks to see if each client has sent a message.
                for(R2D2Connection client : clients) {
                    if(client != null && client.hasMessage()) {
                        handleMessage(client.readMessage());
                    }
                }
            }
            
            // Update the status
            winner = model.checkStatus();
        }
        
        for(R2D2Connection client : clients) {
            client.sendMessage(InfoMessage.gameOver(winner));
            client.close();
        }
        
        System.out.println("GameController: Game is over.");
    }
    
    /**
     * Handles a newly received message.
     * @param message A Message object.
     */
    private void handleMessage(Message message) {
        if(message instanceof MoveMessage) {
            handleMoveMessage((MoveMessage) message);
        } else if(message instanceof ChatMessage) {
            handleChatMessage((ChatMessage) message);
        } else {
            // else this isn't a message we care about.
            System.out.println("Error: Server received unknown or improper message.");
        }
    }
    
    /**
     * Handles Move messages.
     * @param message A MoveMessage object.
     */
    private void handleMoveMessage(MoveMessage message) {
        // Attempt to mark the move on the model. If it works, the turn is done.
        if(model.setMarker(message.getX(), message.getY(), message.getSourceId())) {
            doneTurn = true;
            // Send the recorded move on to the clients to be drawn.
            for(R2D2Connection client : clients) {
                client.sendMessage(message);
            }
        }
    }
    
    /**
     * Handles Chat messages.
     * @param message A ChatMessage object.
     */
    private void handleChatMessage(ChatMessage message) {
        // Set the target player to the client id which is not the source.
        int target = message.getSourceId() == 1 ? 2 : 1;
        
        // Send the chat message on to the target client.
        clients[target - 1].sendMessage(message);
    }
}
