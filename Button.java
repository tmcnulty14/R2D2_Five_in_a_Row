import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Button extends JPanel implements ActionListener{
	
	private final ArrayList<JButton> buttons=new ArrayList(30);
	private ImageIcon X,O;
        private final R2D2GameClient client;
	
	public Button(R2D2GameClient client) {
            
                this.client = client;
                
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
				
                                JButton b = new JButton(" ");
				b.addActionListener(this);
				b.setPreferredSize(dim);
				buttons.add(b);
                                
                                add(b, gc);
			}
		}
				
	}
	// This is where you would place the Server function to check the logic to change the button
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		int i = buttons.indexOf(clicked);
                int x = i%15;
                int y = i/15;
                //System.out.println("Clicked:" + i + "x: " + x + "y:" + y);
                client.requestMove(x, y);
	}
        
        public void updateBoard(int x, int y, int player) {
            int z = x+(15*y);
            String text;
            switch(player) {
                case 1:
                    text = "X";
                    break;
                case 2:
                    text = "O";
                    break;
                default:
                    text = "";
            }
            buttons.get(z).setText(text);
        }
}
