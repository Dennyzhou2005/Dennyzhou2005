package Objects;

import javax.swing.*;
import java.awt.*;

public class PicPanel extends JPanel{
    private Image image; // Declare Image as a class member

    public PicPanel(String imagePath) {
        // Load the image when the frameExample.DemoPlane is initialized
        this.image  = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image onto the JPanel
        g.drawImage(image, 0, 0, 100, 100, this);
    }

    @Override
    public Dimension getPreferredSize() {
        // Specify the preferred size of the JPanel based on the image size
        return new Dimension(image.getWidth(this), image.getHeight(this));
    }
}
