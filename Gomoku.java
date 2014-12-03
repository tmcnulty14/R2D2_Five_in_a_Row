import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

public class Gomoku extends JFrame implements ClientGUI {

    private JButton btn;
    private Button button;
    private JTextArea textArea;

    public Gomoku() {
        super("Gomoku");

        btn = new JButton("Send");
        button = new Button();
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

        // Make frame visible
        setVisible(true);

        // This kills the background process when
        //  the users hits the red X box
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the size
        setSize(1000, 600);
    }

    @Override
    public void updateBoard(int x, int y, int player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
