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
    private final R2D2Connection server;

    public R2D2GameClient(R2D2Connection server) throws IOException
    {
        // Initialize GUI
        gui = new Gomoku();
        this.server = server;
    }

    public static void main(String[] args) throws IOException 
    {
        // Initialize the server connection
        Socket s = new Socket("localhost", 18242);
        R2D2Connection server = new R2D2Connection(s);
        new Thread(server).start();
        
        //Calls constructor
        R2D2GameClient client = new R2D2GameClient(server);
        
        while(true)
        {
            client.checkMessages();
        }	
    }

    public void checkMessages()
    {
        if(server.hasMessage())
        {
            handleMessage(server.readMessage());
        }
    }

    public void handleMessage(Message message)
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

    public void handleMoveMessage(MoveMessage message)
    {
        gui.updateBoard(message.getX(), message.getY(), message.getSourceId());
    }

    public void handleChatMessage(ChatMessage message)
    {
        gui.displayMessage("Player "+message.getSourceId()+": " +message.getChatMessage());

    }
    /*
     * HandleInfoMessage 
     */
    public void handleInfoMessage(InfoMessage message)
    {
        if(message.isGameOver())
        {
            gui.displayMessage("Player " + message.getWinnerPlayer() + " is the winner!");
        } else {
            gui.displayMessage("It's Player " + message.getActivePlayer() + "'s turn");
        }
    }

    public void handleHelloMessage(HelloMessage message)
    {
        player=message.getClientId();
    }


    @Override
    public void requestMove(int x, int y) {
        MoveMessage GuiMove = new MoveMessage(  x, y, player);
        server.sendMessage(GuiMove);
    }

    @Override
    public void sendChatMessage(String chat)
    {
        ChatMessage message = new ChatMessage(player, chat);
        server.sendMessage(message);

    }
}
