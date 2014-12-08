package Client;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class R2D2ClientGUI extends JFrame implements ClientGUI {

    private final JButton btn;
    private final Button button;
    private final JTextArea textArea;
    private final R2D2GameClient client;
    private final SimpleDateFormat sdf;
    
    public R2D2ClientGUI(R2D2GameClient client, int player) {
        super("Five in a row - Player " + player);

        this.client = client;
        
        btn = new JButton("Send");
        button = new Button(client);
        textArea = new JTextArea();

        Dimension dim = getPreferredSize();
        dim.width = 50;
        dim.height = 80;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        btn.setAlignmentX(CENTER_ALIGNMENT);

        button.setAlignmentX(CENTER_ALIGNMENT);
        add(button);

        textArea.setPreferredSize(dim);
        add(new JScrollPane(textArea));
        add(btn);
        
        // Action to send messgaes back and forth
        
        // Make frame visible
        setVisible(true);

        // This kills the background process when
        //  the users hits the red X box
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the size
        setSize(1000, 600);
        
        // Set up timestamp formatting
        
    	sdf = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void updateBoard(int x, int y, int player) {
        button.updateBoard(x, y, player);
    }

    @Override
    public void displayMessage(String message) {
        Calendar cal = Calendar.getInstance();
    	String time = sdf.format(cal.getTime());
        textArea.insert(time + ": " + message + "\n", 0);
    }
}
