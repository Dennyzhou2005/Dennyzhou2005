public class GameResult {
    private boolean isVictory;
    private String gameOverReason;

    public GameResult(boolean isVictory, String gameOverReason) {
        this.isVictory = isVictory;
        this.gameOverReason = gameOverReason;
    }

    public boolean getIsVictory() {
        return isVictory;
    }

    public String getGameOverReason() {
        return gameOverReason;
    }
}

class GameUtilities {
    public static GameResult determineGameResult(int score, int life, boolean hitWall, boolean timeOut) {
        final int threshold = 1500; // Example threshold
        boolean isVictory = false;
        String gameOverReason = " ";

        if (timeOut && score < threshold) {
            gameOverReason = "Time's up and score didn't reach the threshold.";
        } else if (life == 0) {
            gameOverReason = "Your life is depleted.";
        } else if (hitWall) {
            gameOverReason = "You hit the wall.";
        } else if (score >= threshold) {
            gameOverReason = "Congratulations! You reached the score threshold!";
            isVictory = true;
        }

        return new GameResult(isVictory, gameOverReason);
    }
}
