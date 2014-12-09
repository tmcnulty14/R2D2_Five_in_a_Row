package Client;

import Messaging.*;
import java.io.*;
import java.net.*;

/**
 * The client class for the 5 in a row game.  This class sets up to 2 clients
 * and listens for updates from the GUI, sends to GameController.
 * @author RyanAhearn
 */

public class R2D2GameClient implements ClientModel{
    private ClientGUI gui;
    private int player;
    private R2D2Connection server;
    private boolean gameOver = false;
    
    /**
     * Calls constructor, sets up server connection, loops to check for messages
     * until the game is over, and then closes the server connection.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException 
    {
        R2D2GameClient client = new R2D2GameClient();
        
        String address = args.length > 0 ? args[0] : "localhost";
        int port = 18242;
        Socket s = new Socket(address, port);
        client.setServer(s);
        
        while(!client.isGameOver())
        {
            client.checkMessages();
        }
        
        client.close();
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
            gui.displayMessage("PLAYER " + message.getWinnerPlayer() + " IS THE WINNER!");
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

    /**
     * Method called by the GUI to request a move from the server.
     * @param x X coordinate of move
     * @param y Y coordinate of move
     */
    @Override
    public void requestMove(int x, int y) {
        if(!isGameOver()) {
            MoveMessage GuiMove = new MoveMessage(player, x, y);
            server.sendMessage(GuiMove);
        }
    }

    /**
     * Method called by the GUI to send a chat message.
     * @param chat The string to be sent.
     */
    @Override
    public void sendChatMessage(String chat)
    {
        if(!isGameOver()) {
            ChatMessage message = new ChatMessage(player, chat);
            server.sendMessage(message);
        }
    }

    /**
     * @return True if the client believes the game is over.
     */
    private boolean isGameOver() {
        return gameOver;
    }

    /**
     * Closes the server connection.
     * @throws IOException 
     */
    private void close() throws IOException {
        server.close();
    }
}