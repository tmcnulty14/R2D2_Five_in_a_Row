package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class ButtonPanel extends JPanel implements ActionListener{
	
	private final ArrayList<JButton> buttons=new ArrayList(30);
	private ImageIcon X,O;
        private final R2D2GameClient client;
	
	public ButtonPanel(R2D2GameClient client) {
                this.client = client;
                
		// Set the layout
		setLayout(new GridBagLayout());
                Dimension gridbagDim = new Dimension(460, 460);
                this.setMaximumSize(gridbagDim);
                this.setPreferredSize(gridbagDim);
                this.setMinimumSize(gridbagDim);
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 10;
		gc.weighty = 10;
                gc.fill = GridBagConstraints.BOTH;
		
		// Create the buttons, store them in a array, and add them to the panel
		for(gc.gridy=0; gc.gridy<15; gc.gridy++) {
			for(gc.gridx=0; gc.gridx<15; gc.gridx++) {
                                JButton b = new JButton("   ");
				b.addActionListener(this);
                                b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                                b.setBackground(Color.lightGray);
                                b.setOpaque(true);
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
            Color color;
            switch(player) {
                case 1:
                    text = "X";
                    color = Color.PINK;
                    break;
                case 2:
                    text = "O";
                    color = Color.CYAN;
                    break;
                default:
                    text = "";
                    color = Color.GRAY;
            }
            JButton b = buttons.get(z);
            b.setText(text);
            b.setBackground(color);
        }
}
