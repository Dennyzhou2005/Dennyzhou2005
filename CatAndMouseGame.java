package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


// You are the cat!!!!!

public class CatAndMouseGame extends JFrame implements KeyListener {

    private JPanel gamePanel;
    private JLabel catLabel, mouseLabel, cheeseLabel, cheeseLabel1, timerLabel, creditLabel;
    private int catX, catY, mouseX, mouseY, cheeseX, cheeseY, cheese1X, cheese1Y, timeLeft, credit;
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 800;
    private final int CAT_SIZE = 120;
    private final int MOUSE_SIZE = 110;
    private int MOVE_AMOUNT = 30;
    private final int CHEESE_SIZE = 80;
    private final int GAME_DURATION_SECONDS = 10; // 30 seconds
    private final int SPEED_INCREASE_AMOUNT = 15; // Amount to increase mouse speed after collision
    private Random random;
    private Timer timer, timerGame;
    private boolean gameEnd = false;
    private boolean isVictory;


    public CatAndMouseGame() {
        setTitle("Catch Jerry");
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.DARK_GRAY);
        add(gamePanel);

        catLabel = new JLabel(new ImageIcon("/Users/lenovo/Downloads/cat.jpg"));
        catLabel.setSize(CAT_SIZE, CAT_SIZE);
        gamePanel.add(catLabel);

        mouseLabel = new JLabel(new ImageIcon("/Users/lenovo/Downloads/mouse.png"));
        mouseLabel.setSize(MOUSE_SIZE, MOUSE_SIZE);
        gamePanel.add(mouseLabel);
        
        cheeseLabel = new JLabel(new ImageIcon("/Users/lenovo/Downloads/cheese.png"));
        cheeseLabel.setSize(CHEESE_SIZE, CHEESE_SIZE);
        gamePanel.add(cheeseLabel);
        
        cheeseLabel1 = new JLabel(new ImageIcon("/Users/lenovo/Downloads/cheese.png"));
        cheeseLabel1.setSize(CHEESE_SIZE, CHEESE_SIZE);
        gamePanel.add(cheeseLabel1);
        
        timerLabel = new JLabel("T-minus: " + GAME_DURATION_SECONDS);
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timerLabel.setBounds(10, 10, 100, 20);
        gamePanel.add(timerLabel);
        
        
        creditLabel = new JLabel("Credit: " + credit);
        creditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        creditLabel.setBounds(10, 40, 100, 20);
        gamePanel.add(creditLabel);
        
        Font labelFont = new Font("Arial", Font.BOLD, 17);
        Color a = new Color(255,255,255);
        timerLabel.setFont(labelFont);
        creditLabel.setFont(labelFont);
        timerLabel.setForeground(a);
        creditLabel.setForeground(a);

      


        random = new Random();

        resetGame();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
         Timer timerGame = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!gameEnd) {
                updateTimer();
            	}
            }
        });
        timerGame.start();
    
         timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!gameEnd) {
                moveMouseRandomly();
            	}
            }
        });
        timer.start();
        Timer timerCheese = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!gameEnd) {
                moveCheeseRandomly();
                moveCheese1Randomly();

            	}
            }
        });
        timerCheese.start();
        
        
        
    } 
    
    

    private void resetGame() {
        catX = PANEL_WIDTH / 2 - CAT_SIZE / 2;
        catY = PANEL_HEIGHT / 2 - CAT_SIZE / 2;
        catLabel.setLocation(catX, catY);

        mouseX = random.nextInt(PANEL_WIDTH - MOUSE_SIZE);
        mouseY = random.nextInt(PANEL_HEIGHT - MOUSE_SIZE);
        mouseLabel.setLocation(mouseX, mouseY);
        
        cheeseX = random.nextInt(PANEL_WIDTH - CHEESE_SIZE);
        cheeseY = random.nextInt(PANEL_HEIGHT - CHEESE_SIZE);
        cheeseLabel.setLocation(cheeseX, cheeseY);

        cheese1X = random.nextInt(PANEL_WIDTH - CHEESE_SIZE);
        cheese1Y = random.nextInt(PANEL_HEIGHT - CHEESE_SIZE);
        cheeseLabel.setLocation(cheese1X, cheese1Y);
        
        timeLeft = GAME_DURATION_SECONDS;
        credit = 0;
        updateTimerLabel();
        updateCreditLabel();
        gameEnd = false;

    }
    
    private void updateTimer() {
        timeLeft--;
        if (timeLeft <= 0) {
        	gameEnd = true;
        	setVictory(false);
        	UpdatedResult2.main(null);																	// LOSE
        	
        }
        else {
        updateTimerLabel();
        }
    }
    
    private void updateTimerLabel() {
        timerLabel.setText("T-minus: " + timeLeft);
    }
    private void finalTimerLabel() {
    	timerLabel.setText("Game ends!");
    }
    private void moveMouseRandomly() {
        int deltaX = random.nextInt(3) - 1; // Random movement between -1, 0, 1
        int deltaY = random.nextInt(3) - 1;
        int newX = mouseLabel.getX() + deltaX * MOVE_AMOUNT;
        int newY = mouseLabel.getY() + deltaY * MOVE_AMOUNT;
        newX = Math.max(0, Math.min(PANEL_WIDTH - MOUSE_SIZE, newX)); // Ensure newX is within panel bounds
        newY = Math.max(0, Math.min(PANEL_HEIGHT - MOUSE_SIZE, newY)); // Ensure newY is within panel bounds
        mouseLabel.setLocation(newX, newY);
        checkCollision();
    }
    private void stopMouse() {
    	mouseLabel.setLocation(0,0);
    }
    
    
    private void moveCheeseRandomly() {
        int newX = random.nextInt(PANEL_WIDTH - MOUSE_SIZE);
        int newY = random.nextInt(PANEL_HEIGHT - MOUSE_SIZE);
        cheeseLabel.setLocation(newX, newY);
        checkCollision();
    }
    private void stopCheese() {
    	cheeseLabel.setLocation(0,0);
    }
    private void moveCheese1Randomly() {
        int newX = random.nextInt(PANEL_WIDTH - MOUSE_SIZE);
        int newY = random.nextInt(PANEL_HEIGHT - MOUSE_SIZE);
        cheeseLabel1.setLocation(newX, newY);
        checkCollision();
    }
    private void stopCheese1() {
    	cheeseLabel1.setLocation(0,0);
    }
    
    private void checkCollision() {
        Rectangle catBounds = catLabel.getBounds();
        Rectangle mouseBounds = mouseLabel.getBounds();
        Rectangle cheeseBounds = cheeseLabel.getBounds();
        Rectangle cheese1Bounds = cheeseLabel1.getBounds();



        if (catBounds.intersects(mouseBounds)) {
        	gameEnd = true;
        	setVictory(true);
        	UpdatedResult.main(null);				                                   // WIN
        	timerGame.stop();
            timer.stop();
            stopMouse();
            stopCheese();
            resetGame();
        }
        else if (cheeseBounds.intersects(catBounds)) {
        	if(credit <= 5) {
        		credit ++;
        		moveCheeseRandomly();
        		moveCheese1Randomly();
        		updateCreditLabel();
                increaseMouseSpeed();
                cheeseX = random.nextInt(PANEL_WIDTH - CHEESE_SIZE);
                cheeseY = random.nextInt(PANEL_HEIGHT - CHEESE_SIZE);
                cheeseLabel.setLocation(cheeseX, cheeseY);
                cheese1X = random.nextInt(PANEL_WIDTH - CHEESE_SIZE);
                cheese1Y = random.nextInt(PANEL_HEIGHT - CHEESE_SIZE);
                cheeseLabel1.setLocation(cheese1X, cheese1Y);
        	}
        	else{
        		gameEnd = true;
        		setVictory(true);
        		timerGame.stop();
        		stopMouse();
        		stopCheese();
        		stopCheese1();
        		setVictory(true);
        		UpdatedResult.main(null);													//WIN
        	}
        }
    }
    
    private void updateCreditLabel() {
        creditLabel.setText("Credit: " + credit);
    }
    
    private void increaseMouseSpeed() {
        MOVE_AMOUNT += SPEED_INCREASE_AMOUNT;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
                catY -= MOVE_AMOUNT;
                break;
            case KeyEvent.VK_A:
                catX -= MOVE_AMOUNT;
                break;
            case KeyEvent.VK_S:
                catY += MOVE_AMOUNT;
                break;
            case KeyEvent.VK_D:
                catX += MOVE_AMOUNT;
                break;
        }

        catLabel.setLocation(catX, catY);
        checkCollision();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    public void setVictory(boolean a) {
    	isVictory = a;
    }
    	
    public boolean getVictory() {
    	return isVictory;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
                new CatAndMouseGame().setVisible(true);
            }
        });
    }
}
