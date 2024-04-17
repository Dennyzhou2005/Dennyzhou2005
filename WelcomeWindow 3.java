package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class WelcomeWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cat and Mouse");
        WelcomePanel panel = new WelcomePanel(frame);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.setSize(1080,1920);
        frame.pack(); 
        frame.setVisible(true); 
    }
}

// Custom JPanel to display "Welcome", images of cats and rats, and a button
class WelcomePanel extends JPanel {
    private Image catImage;
    private ImageIcon ratImage;
    private JFrame parentFrame;

    public WelcomePanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        // Load the images of the cat and the rat
        try {
            catImage = ImageIO.read(new File("/Users/lenovo/eclipse-workspace/CSC171 Project/src/resource/huh_cat.jpg"));
            ratImage = new ImageIcon("/Users/lenovo/eclipse-workspace/CSC171 Project/src/resource/spinning-cat.gif");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a button
        JButton button = new JButton("Press to see the rules");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show game rules page
                showGameRules();
            }
        });
        add(button); // Add the button to the panel
    }

    private void showGameRules() {
        JFrame rulesFrame = new JFrame("Game Rules");
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setText("Game Rules:\n" +
                "1. Try to get more cheese and arrest the mouse!\n" +
                "2. You'll lose if you don't catch the mouse in 10s.\n" +
                "3. You'll win if you catch the mouse or catch 5 cheese in 10s.\n" +
                "4. The mouse will move faster as you catch the chheese\n" +
                "GOOD LUCK!"); 
        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        rulesPanel.add(scrollPane, BorderLayout.CENTER);
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rulesFrame.dispose();
                CatAndMouseGame.main(null);
            }
        });
        // Add the start button to the panel
        rulesPanel.add(startButton, BorderLayout.SOUTH);

        // Add the panel to the frame
        rulesFrame.add(rulesPanel);

        // Set frame properties
        rulesFrame.setSize(400, 300); // Set size
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close operation
        rulesFrame.setLocationRelativeTo(parentFrame); // Center the frame relative to the parent frame
        rulesFrame.setVisible(true); // Make the frame visible
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the resized images of the cat and the rat
        if (catImage != null && ratImage != null) {
            int width = getWidth();
            int height = getHeight();
            int catWidth = catImage.getWidth(null);
            int catHeight = catImage.getHeight(null);
            int ratWidth = ratImage.getIconWidth();
            int ratHeight = ratImage.getIconHeight();

            // Draw the cat image at the center of the panel
            g.drawImage(catImage, (width - catWidth) / 2, (height - catHeight) / 2, catWidth, catHeight, null);

            // Draw the rat icon at the center of the panel
            ratImage.paintIcon(this, g, (width - ratWidth) / 2, (height - ratHeight) / 2);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 700); // Set preferred size for the panel
    }
}
