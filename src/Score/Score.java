package Score;

import Games.Game;

public abstract class Score {
    protected int score;

    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    public abstract void updateScore(Game game);
}