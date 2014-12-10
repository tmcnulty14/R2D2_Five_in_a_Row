package Client;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class R2D2ClientGUI extends JFrame implements ClientGUI {
    private final JButton btn;
    private final ButtonPanel board;
    private final JTextArea textArea;
    private final JTextField textField;
    private final JPanel chatPane;
    
    private final R2D2GameClient client;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    public R2D2ClientGUI(R2D2GameClient client, int player) {
        super("Five in a row - Player " + player);
        
        // Initialize things.
        this.client = client;
        btn = new JButton("Send");
        board = new ButtonPanel(client);
        textArea = new JTextArea();
        textField = new JTextField();
        chatPane = new JPanel();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        // Make the text field send the text when the user clicks Send or presses enter.
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendChatMessage(textField.getText());
                textField.setText("");
            }
        });
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendChatMessage(textField.getText());
                    textField.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        // Configure and add the board.
        board.setAlignmentX(CENTER_ALIGNMENT);
        add(board);
        
        // Configure and add the chat pane.
        chatPane.setLayout(new BoxLayout(chatPane, BoxLayout.X_AXIS));
        Dimension dimChat = new Dimension(900, 20);
        chatPane.setPreferredSize(dimChat);
        chatPane.setMaximumSize(dimChat);
        chatPane.setMinimumSize(dimChat);
        textField.setAlignmentX(LEFT_ALIGNMENT);
        chatPane.add(textField);
        btn.setAlignmentX(CENTER_ALIGNMENT);
        chatPane.add(btn);
        add(chatPane);
        
        // Configure and add the notification text area.
        Dimension dimScroll = new Dimension(900, 80);
        textArea.setPreferredSize(new Dimension(800, 1200));
        textArea.setMargin(new Insets(0, 10, 0, 0));
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(dimScroll);
        scroll.setMaximumSize(dimScroll);
        scroll.setMinimumSize(dimScroll);
        add(scroll);
        
        // Make frame visible
        setVisible(true);

        // This kills the background process when the user closes the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        setSize(900, 580);
    	
    }

    /**
     * Called by the game client to draw a piece on the board.
     * @param x
     * @param y
     * @param player 
     */
    @Override
    public void updateBoard(int x, int y, int player) {
        board.updateBoard(x, y, player);
    }

    /**
     * Called by the game client to display a String in the text area.
     * @param message 
     */
    @Override
    public void displayMessage(String message) {
        Calendar cal = Calendar.getInstance();
    	String time = sdf.format(cal.getTime());
        textArea.insert(time + ": " + message + "\n", 0);
    }
    
    /**
     * Private helper method for sending a chat message.
     * @param chat 
     */
    private void sendChatMessage(String chat) {
        client.sendChatMessage(chat);
    }
}
