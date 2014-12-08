import Messaging.*;
import java.io.*;
import java.net.*;

/**
 * The client class for the 5 in a row game.  This class sets up to 2 clients
 * and listens for updates from the GUI, sends to GameController.
 * @author RyanAhearn
 */

public class R2D2ClientModel implements ClientModel{
    private ClientGUI gui;
    private int player;
    private R2D2Connection server;
    private boolean gameOver = false;
    
    /*
    *Main method calls constructor
    *and initializes server connection
    */
    public static void main(String[] args) throws IOException 
    {
        R2D2ClientModel client = new R2D2ClientModel();
        
        Socket s = new Socket("localhost", 18242);
        client.setServer(s);
        
        while(!client.isGameOver())
        {
            client.checkMessages();
        }
    }
    
    /*
    *Sets up new R2D2 serve rconnection
    *@param  Socket s server socket
    */
    public void setServer(Socket s) throws IOException {
        server = new R2D2Connection(s);
        new Thread(server).start();
    }

    /*
    * Calls handle message method if server has message waiting
    */
    public void checkMessages()
    {
        if(server.hasMessage())
        {
            handleMessage(server.readMessage());
        }
    }

    /*
    * Handles message based on type
    */
    private void handleMessage(Message message)
    {
        if(message instanceof MoveMessage) {
            handleMoveMessage((MoveMessage) message);
        } else if(message instanceof ChatMessage) {
            handleChatMessage((ChatMessage) message);
        } else if(message instanceof InfoMessage) {
            handleInfoMessage((InfoMessage) message);
        } else if(message instanceof HelloMessage) {
            handleHelloMessage((HelloMessage) message);
        }
    }
    /*
    * Calls updateBoard method on gui to make a movie
    */
    private void handleMoveMessage(MoveMessage message)
    {
        gui.updateBoard(message.getX(), message.getY(), message.getSourceId());
    }
    /*
    * Calls displayMessage method for a chat message between players
    */
    private void handleChatMessage(ChatMessage message)
    {
        gui.displayMessage("Player "+message.getSourceId()+": " +message.getChatMessage());

    }
    /*
     * Calls displayMessage with either the winning player
    *  or which Player's turn it is
     */
    private void handleInfoMessage(InfoMessage message)
    {
        System.out.println("INFO: " + message.isGameOver());
        if(message.isGameOver())
        {
            gui.displayMessage("Player " + message.getWinnerPlayer() + " is the winner!");
            gameOver = true;
        } else {
            gui.displayMessage("It's Player " + message.getActivePlayer() + "'s turn");
        }
    }

    /*
    * Displays greeting to player based on ID
    */
    private void handleHelloMessage(HelloMessage message)
    {
        player=message.getClientId();
        System.out.println("Client initalized with player id:" + player);
        
        // Initialize GUI
        gui = new R2D2ClientGUI(this, player);
        gui.displayMessage("Welcome to five in a row! You are player " + player + ".");
    }


    @Override
    public void requestMove(int x, int y) {
        if(!isGameOver()) {
            MoveMessage GuiMove = new MoveMessage(player, x, y);
            server.sendMessage(GuiMove);
        }
    }

    @Override
    public void sendChatMessage(String chat)
    {
        if(!isGameOver()) {
            ChatMessage message = new ChatMessage(player, chat);
            server.sendMessage(message);
        }
    }

    private boolean isGameOver() {
        return gameOver;
    }
}
