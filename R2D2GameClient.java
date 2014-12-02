import java.io.*;
import java.net.*;

/**
 * The client class for the 5 in a row game.  This class sets up to 2 clients
 * and listens for updates from the GUI, sends to GameController.
 * @author RyanAhearn
 */

public class R2D2GameClient implements ClientModel{
	
	private ClientGUI GUI;
	int player;
	R2D2Connection R2D2con;
	
	public R2D2GameClient()throws IOException
	{
		//need to implement GUI object later
		Socket s = new Socket("localhost", 18242);
		 R2D2con= new R2D2Connection(s);
		 
		new Thread(R2D2con).start();
		
	}
	public static void main(String[] args) throws IOException 
	{
		
		//Calls constructor
		R2D2GameClient client = new R2D2GameClient();
		
		
		while(true)
		{
			
			client.checkMessages();
		}
		
	}

	public void checkMessages()
	{
		if(R2D2con.hasMessage())
		{
			
			HandleMessage(R2D2con.readMessage());
		}
			
		
	}
	
	public void HandleMessage(Message message)
	{
		if(message instanceof MoveMessage) {
            handleMoveMessage((MoveMessage) message);
        } else if(message instanceof ChatMessage) {
            handleChatMessage((ChatMessage) message);
        }
	}
	
	public void handleMoveMessage(MoveMessage message)
	{
		/*  Implement call to GUI based on Wesley's code  */
		GUI.updateBoard( message.getX(), message.getY(), message.getSourceId());
	}
	
	public void handleChatMessage(ChatMessage message)
	{
		/*  Implement call to GUI based on Wesley's code  */
		GUI.displayMessage("Player "+message.getSourceId()+": " +message.getChatMessage());
		
	}
	/*
	 * HandleInfoMessage 
	 */
	public void handleInfoMessage(InfoMessage message)
	{
		/*  Implement call to GUI based on Wesley's code  */
		if(message.isGameOver())
			{GUI.displayMessage("Player " + message.getWinnerPlayer() + " is the winner!");
			}else{
				GUI.displayMessage("It's Player " + message.getActivePlayer() + "'s turn");
			}
	}
	
	public void handleHelloMessage(HelloMessage message)
	{
		/*  Implement call to GUI based on Wesley's code  , used to determine
		 * client's ID number
		 * */
		player=message.getClientId();
	}
	
	
	@Override
	public void requestMove(int x, int y) {
		
		MoveMessage GuiMove = new MoveMessage(  x, y, player);
		R2D2con.sendMessage(GuiMove);
	}
	
	public void sendChatMessage(String chat)
	{
		ChatMessage message = new ChatMessage(player, chat);
		R2D2con.sendMessage(message);
		
	}
}
