import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameResultScreen extends JFrame {
    private JLabel titleLabel;
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JLabel gameOverReasonLabel;
    private JButton restartButton;

    public GameResultScreen(boolean isVictory, int score, String gameOverReason) {
        setTitle("Game Result");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CustomPanel panel = new CustomPanel();
        panel.setLayout(null);


        //meme
        PicPanel catVic = new PicPanel("src/resources/popCat.gif");
        catVic.setBounds(500, 300, 100, 100);

        PicPanel catLose = new PicPanel("src/resources/screamCat.gif");
        catLose.setBounds(500, 300, 100, 100);

        PicPanel mouVictory = new PicPanel("src/resources/jerryhi.gif");
        mouVictory.setBounds(80, 300, 100, 100);

        PicPanel mouLose = new PicPanel("src/resources/jerry-beg.gif");
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
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 30)); // Arial Black font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);//文本在label里居中
        titleLabel.setBounds(200, 30, 300, 50);
        panel.add(titleLabel);

        //Player's status: WIN/LOSE
        statusLabel = new JLabel(isVictory ? "You Won!" : "You Lost...");
        statusLabel.setFont(new Font("Arial Black", Font.PLAIN, 25));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBounds(200, 100, 300, 50);
        panel.add(statusLabel);

        //Player's Score
        scoreLabel = new JLabel("Your Final Score: " + score+"/1000");//score threshold
        scoreLabel.setFont(new Font("Cancun", Font.PLAIN, 18));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBounds(200, 185, 300, 20);
        panel.add(scoreLabel);

        //Game Over Reason
        gameOverReasonLabel = new JLabel(gameOverReason);
        gameOverReasonLabel.setFont(new Font("Cancun", Font.PLAIN, 16));
        gameOverReasonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverReasonLabel.setBounds(50, 210, 600, 40);
        panel.add(gameOverReasonLabel);

        //restart button
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        restartButton.setBounds(300, 310, 100, 40);


        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加重新开始游戏的逻辑
                dispose(); // 关闭当前窗口
                // 添加重新开始游戏的代码
            }
        });
        panel.add(restartButton);

        add(panel);
        setVisible(true);
    }

    // Custom Panel for color fill in
    class CustomPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // paint the background image and scale it to fill the entire space
            g.setColor(new Color(225, 202, 163)); // a yellow color for the fill
            g.fillRect(0, 0, getWidth(), getHeight()); // fill the entire panel

            Graphics2D g2d = (Graphics2D) g; // 将 Graphics 转换为 Graphics2D

            // 设置填充色并填充矩形
            g2d.setColor(new Color(210, 180, 140)); //
            g2d.fillRect(0, 0, getWidth(), getHeight()); //

            // 设置线条宽度并绘制矩形边框
            g2d.setColor(new Color(138, 86, 12)); //
            g2d.setStroke(new BasicStroke(10)); //
            g2d.drawRect(10, 10, getWidth() - 20, getHeight() - 20); // 边框
        }
    }


    public static void main(String[] args) {
        // example output
        boolean isVictory = false;
        //boolean isVictory = true;
        int score = 1000;
        //String gameOverReason = "Your life is depleted.";
        String gameOverReason = "Congratulations! You reached the score threshold!";

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameResultScreen(isVictory, score, gameOverReason);
            }
        });
    }
}
