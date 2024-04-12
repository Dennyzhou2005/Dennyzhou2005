import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GamePanel extends JPanel {
    private Mouse mouse;
    private Cat cat1, cat2;
    private Cheese c1, c2, c3;
    public JLabel lifeLabel, scoreLabel, timerLabel;
    private Timer timer;
    private Timer updateTimer;
    private int remainingTime = 30;

    public GamePanel() {
        setLayout(null);
        initGameObjects();
        initStatusBar();
        initTimer();
        initUpdateTimer();
    }

    private void initGameObjects() {
        cat1 = new Cat("src/imageDemo/cat.jpg");
        cat1.setPosition(100, 100);

        cat2 = new Cat("src/imageDemo/cat2.jpeg");
        cat2.setPosition(300, 100);

        mouse = new Mouse();
        mouse.setPosition(200,300);

        c1 = new Cheese();
        c1.setPosition(700,700);
    }

    public JLabel getLifeLabel() {return lifeLabel;};
    public JLabel getScoreLabel() {return scoreLabel;};
    public JLabel getTimerLabel() {return timerLabel;};
    public void initStatusBar() {
        lifeLabel = new JLabel("Lives: " + mouse.getLives());
        scoreLabel = new JLabel("Score: " + mouse.getScore());
        timerLabel = new JLabel("Time: 100s");

        lifeLabel.setBounds(1000, 10, 100, 30);
        scoreLabel.setBounds(1000, 50, 100, 30);
        timerLabel.setBounds(1000, 90, 100, 30);

        lifeLabel.setForeground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);
        timerLabel.setForeground(Color.WHITE);

        Font labelFont = new Font("Arial", Font.BOLD, 17);
        lifeLabel.setFont(labelFont);
        scoreLabel.setFont(labelFont);
        timerLabel.setFont(labelFont);

        add(lifeLabel);
        add(scoreLabel);
        add(timerLabel);
    }

    private void initTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        timer.start();
    }

    private void initUpdateTimer() {
        updateTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatus();
            }
        });
        updateTimer.start();
    }

    private void updateTimer() {
        remainingTime--;
        timerLabel.setText("Time: " + remainingTime + "s");
        if (remainingTime <= 0) {
            timer.stop();
            // Call game result screen method
        }
    }

    private void updateStatus() {
        lifeLabel.setText("Lives: " + mouse.getLives());
        scoreLabel.setText("Score: " + mouse.getScore());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(13, 24, 65));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw game objects examples
        mouse.draw(g);
        cat1.draw(g);
        cat2.draw(g);
        c1.draw(g);
    }
}
