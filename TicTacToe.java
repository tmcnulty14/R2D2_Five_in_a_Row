import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridLayout;

public class TicTacToe extends JFrame {
	
	// Create the instance of the JPanel Object
	JPanel p = new JPanel();
	
	// Create the XObutton array
	XOButton buttons[]=new XOButton[25];
	
	public static void main(String args[]) {
		new TicTacToe();
	}
	
	// TicTacToe Constructor 
	public TicTacToe() {
		super("TicTacToe");
		
		// Set the size of the screen
		setSize(400,400);
		
		// Make the screen resizable 
		setResizable(true);
		
		// If user clicks the red X, it will close the 
		//  screen and the program 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Sets the layout  to position and size its components
		p.setLayout(new GridLayout(5,5));
		
		// Create the buttons in grid format
		for(int i=0; i<25; i++) {
			buttons[i] = new XOButton();
			p.add(buttons[i]);
		}
		add(p);
		
		setVisible(true);
	}

}
