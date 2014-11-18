import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * The server class for the Five in a row game. This class handles
 * connecting to clients and spawning game instances for each pair of clients.
 * @author mcnultyt
 */
public class R2D2GameServer {
    private static Random r;
     /**
     * The main method is used to multithread the program, spawning new
     * instances of R2D2GameController for each pair of clients which connect.
     * @param args
     * @throws IOException 
     */
    public static void main(String args[]) throws IOException {
        // Set up a ServerSocket.
        ServerSocket se = new ServerSocket(18242);
        
        // Set up a random number generator.
        r = new Random();
        
        // Accept new client connections in a loop.
        int i=0;
        while(true) {
            try {
                // Accept client connections and start a new game instance
                Socket so1 = se.accept();
                Socket so2 = se.accept();
                runGameInstance(so1, so2);
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    
    /**
     * Runs a single instance of the game using two sockets as client connections.
     * @param so1 The first client connection.
     * @param so2 The second client connection.
     */
    public static void runGameInstance(Socket so1, Socket so2) {
        // Create a new GameController and assign it 2 clients.
        R2D2GameController game = new R2D2GameController();
        
        // Randomize players 1 and 2.
        if(r.nextBoolean()) {
            game.setSockets(so1, so2);
        } else {
            game.setSockets(so2, so1);
        }

        // Create a new thread for each new game instance.
        new Thread(game).start();
        System.out.println("Two clients connected. Creating new game thread.");
    }
}
