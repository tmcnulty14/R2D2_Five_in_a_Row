import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		
		// Used for muilthreading apps
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				// Calls new Class
                            try {
				new Gomoku(new R2D2GameClient());
                            } catch(IOException e) {
                                
                            }
				
			}
		});
		
	}

}
