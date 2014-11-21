import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Button extends JPanel implements ActionListener{
	
	private JButton buttons[]=new JButton[30];
	private ImageIcon X,O;
	
	public Button() {

		// Set the layout
		setLayout(new GridBagLayout());
		
		// Dimension for the buttons
		Dimension dim = getPreferredSize();
		dim.width = 45;
		dim.height = 45;
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 0;
		gc.weighty = 0;
		
		// Create the buttons and store them in a array
		for(int k=0; k<15; k++) {
			gc.gridy = k;
			for(int i=0; i<15; i++) {
				gc.gridx = i;
				buttons[i] = new JButton(" ");
				buttons[i].addActionListener(this);
				buttons[i].setPreferredSize(dim);
				add(buttons[i], gc);
			}
		}
				
	}
	// This is where you would place the Server function to check the logic to change the button
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		switch(clicked.getText()) {
			case " ":
				clicked.setText("O");
				break;
			case "O":
				clicked.setText("X");
				break;
			case "X":
				clicked.setText("O");
				break;
		}
		
	}
	
}
