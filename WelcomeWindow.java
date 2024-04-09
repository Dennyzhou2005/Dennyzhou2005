import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow {
	public static void main(String[] args) {
 
        JFrame frame = new JFrame("Cat and Mouse");
        frame.setSize(300, 200); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true); 
        
        WelcomePanel panel = new WelcomePanel();
        frame.add(panel);
	}
}

// Custom JPanel
class WelcomePanel extends JPanel {
    public WelcomePanel() {
    	setLayout(new BorderLayout());
    	
        JLabel label = new JLabel("Welcome to the game!" + "\n" + "coauthor: Jenny L, Daiming Zhou");
        label.setHorizontalAlignment(JLabel.CENTER); 
        this.add(label);
        
        JButton button = new JButton("Press to start!");
        button.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed (ActionEvent e) {
        	// To activate the new Frame and Panel
        }
        });
        this.add(button, BorderLayout.SOUTH);
        
    }
}


