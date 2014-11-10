import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener{
	// Creating instances of ImageIcon
	ImageIcon X,O;
	byte value = 0;
	
	// XObutton Constructor
	public XOButton() {
		// Initialize the ImageIcon variables
		X = new ImageIcon(this.getClass().getResource("x.png"));
		O = new ImageIcon(this.getClass().getResource("o.png"));
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		value++;
		value%=3;
		switch(value) {
			case 0:
				setIcon(null);
				break;
			case 1:
				setIcon(X);
				break;
			case 2:
				setIcon(O);
				break;
		}
	}
}
