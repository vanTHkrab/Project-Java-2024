package Games.Hangman;
import java.util.Arrays;  // เพิ่มบรรทัดนี้เพื่อแก้ปัญหา

public abstract class Game {
    protected String wordToGuess;
    protected char[] guessedWord;
    protected int attempts;

    public Game(String wordToGuess, int attempts) {
        this.wordToGuess = wordToGuess;
        this.guessedWord = new char[wordToGuess.length()];
        Arrays.fill(guessedWord, '_');  // ใช้ Arrays.fill() เพื่อเติมตัวอักษร '_'
        this.attempts = attempts;
    }

    public boolean isGameOver() {
        return attempts <= 0 || String.valueOf(guessedWord).equals(wordToGuess);
    }

    public abstract void playTurn(String guess);
}
