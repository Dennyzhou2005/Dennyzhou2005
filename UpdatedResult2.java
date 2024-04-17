package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatedResult2 extends JFrame {
    private JLabel titleLabel;
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JLabel gameOverReasonLabel;
    private JButton restartButton;

    public UpdatedResult2(boolean isVictory) {
        setTitle("Game Result");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CustomPanel panel = new CustomPanel();
        panel.setLayout(null);


        //meme
        PicPanel catVic = new PicPanel("/Users/lenovo/Downloads/Dennyzhou2005-Game-Screen/popCat.gif");
        catVic.setBounds(500, 300, 100, 100);

        PicPanel catLose = new PicPanel("/Users/lenovo/Downloads/Dennyzhou2005-Game-Screen/screamCat.gif");
        catLose.setBounds(500, 300, 100, 100);

        PicPanel mouVictory = new PicPanel("/Users/lenovo/Downloads/Dennyzhou2005-Game-Screen/jerryhi.gif");
        mouVictory.setBounds(80, 300, 100, 100);

        PicPanel mouLose = new PicPanel("/Users/lenovo/Downloads/Dennyzhou2005-Game-Screen/jerry-beg.gif");
        mouLose.setBounds(80, 300, 100, 100);

        if (isVictory){
            panel.add(catVic);
            panel.add(mouLose);

        }else{
            panel.add(catLose);
            panel.add(mouVictory);
        }

        //Title label: Game over page
        titleLabel = new JLabel("GAME RESULTS:");
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(200, 50, 300, 50);
        panel.add(titleLabel);

        //Player's status: WIN/LOSE
        statusLabel = new JLabel(isVictory ? "You Won!" : "You Lost...");
        
        statusLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBounds(200, 150, 300, 50);
        panel.add(statusLabel);


        //restart button
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial Black", Font.PLAIN, 20));
        restartButton.setBounds(300, 310, 100, 40);


        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeWindow.main(null);
            }
        });
        panel.add(restartButton);

        add(panel);
        setVisible(true);
    }



    // Custom Panel for color fill in
    class CustomPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(225, 202, 163));
            g.fillRect(0, 0, getWidth(), getHeight());

            Graphics2D g2d = (Graphics2D) g;


            g2d.setColor(new Color(210, 180, 140));
            g2d.fillRect(0, 0, getWidth(), getHeight());


            g2d.setColor(new Color(138, 86, 12));
            g2d.setStroke(new BasicStroke(10));
            g2d.drawRect(10, 10, getWidth() - 20, getHeight() - 20);
        }
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UpdatedResult2(false);
            }
        });
    }
}
