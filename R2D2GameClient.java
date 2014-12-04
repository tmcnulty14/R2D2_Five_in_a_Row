import Messaging.*;
import java.io.*;
import java.net.*;

/**
 * The client class for the 5 in a row game.  This class sets up to 2 clients
 * and listens for updates from the GUI, sends to GameController.
 * @author RyanAhearn
 */

public class R2D2GameClient implements ClientModel{
    private final ClientGUI gui;
    private int player;
    private R2D2Connection server;
    
    /*
    * Constructor initializes new GUI object
    */
    public R2D2GameClient() throws IOException
    {
        // Initialize GUI
        gui = new Gomoku(this);
    }

    /*
    *Main method calls constructor
    *and initializes server connection
    */
    public static void main(String[] args) throws IOException 
    {
        
        R2D2GameClient client = new R2D2GameClient();
        
        
        Socket s = new Socket("localhost", 18242);
        client.setServer(s);
        
        while(true)
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
        if(message.isGameOver())
        {
            gui.displayMessage("Player " + message.getWinnerPlayer() + " is the winner!");
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
        System.out.println("Player id:" + player);
    }


    @Override
    public void requestMove(int x, int y) {
        MoveMessage GuiMove = new MoveMessage(player, x, y);
        server.sendMessage(GuiMove);
    }

    @Override
    public void sendChatMessage(String chat)
    {
        ChatMessage message = new ChatMessage(player, chat);
        server.sendMessage(message);
    }
}
