package Objects;

import Objects.GameObject;

public class Mouse extends GameObject {
    private static final String IMAGE_PATH = "src/imageDemo/m2.png";
    private static final int DIAMETER = 75;
    private int lives;
    private int score;

    public Mouse() {
        super(IMAGE_PATH, DIAMETER);
        this.lives = 3;
        this.score = 0;
    }

    public int getLives() {return lives;}
    public void setLives(int lives) {this.lives = lives;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}






    public void loseLife() {
    }

    public void gainScore(){
    }

    public void move() {
    }

}