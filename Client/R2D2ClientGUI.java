package Client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class R2D2ClientGUI extends JFrame implements ClientGUI {

    private final JButton btn;
    private final Button button;
    private final JTextArea textArea;
    private final JTextField textField;
    private final R2D2GameClient client;
    private final SimpleDateFormat sdf;
    
    public R2D2ClientGUI(R2D2GameClient client, int player) {
        super("Five in a row - Player " + player);

        this.client = client;
        
        btn = new JButton("Send");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                client.sendChatMessage(textField.getText());
                textField.setText("");
            }
        });
        button = new Button(client);
        textArea = new JTextArea();
        textField = new JTextField();

        Dimension dim1 = getPreferredSize();
        dim1.setSize(50, 120);
        
        Dimension dim2 = getPreferredSize();
        dim2.setSize(50, 80);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        btn.setAlignmentX(CENTER_ALIGNMENT);

        button.setAlignmentX(CENTER_ALIGNMENT);
        add(button);

        textField.setPreferredSize(dim2);
        add(textField);
        add(btn);
        
        textArea.setPreferredSize(dim1);
        add(new JScrollPane(textArea));
        
        
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
