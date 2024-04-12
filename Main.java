import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Jerry's Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 800);
        frame.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 1000));

        GamePanel gamePanel = new GamePanel();
        gamePanel.setBounds(0, 0, 1080, 1000);
        layeredPane.add(gamePanel, Integer.valueOf(0));  // 将游戏面板添加到底层

        // 初始化状态栏并将其添加到高层，确保它们显示在游戏元素之上
        gamePanel.initStatusBar();  // 确保状态栏已初始化
        JLabel lifeLabel = gamePanel.getLifeLabel();
        JLabel scoreLabel = gamePanel.getScoreLabel();
        JLabel timerLabel = gamePanel.getTimerLabel();

        lifeLabel.setBounds(950, 10, 200, 30);
        scoreLabel.setBounds(950, 50, 200, 30);
        timerLabel.setBounds(950, 90, 200, 30);

        layeredPane.add(lifeLabel, Integer.valueOf(1));  // 将生命值标签添加到高层
        layeredPane.add(scoreLabel, Integer.valueOf(1));  // 将分数标签添加到高层
        layeredPane.add(timerLabel, Integer.valueOf(1));  // 将时间标签添加到高层

        frame.add(layeredPane);
        frame.setVisible(true);
    }

}
