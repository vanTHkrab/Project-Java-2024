package Games.Hangman;

import java.util.Scanner;
import java.util.Random;

public class HangmanGameMain {
    public void start() {
        // สร้างผู้เล่น
        Player player = new Player("Player 1");
        System.out.println("Welcome " + player.getName() + "!");

        // อาร์เรย์ของคำที่ใช้ทายและคำใบ้
        String[] words = { "JAVA", "PYTHON", "COMPUTER", "HANGMAN", "DEVELOPER" };
        String[] hints = {
                "A popular programming language.",
                "A programming language named after a snake.",
                "A machine that processes data.",
                "A word guessing game.",
                "A person who writes computer programs."
        };

        // สุ่มคำและคำใบ้
        Random random = new Random();
        int randomIndex = random.nextInt(words.length); // สุ่ม index ของคำและคำใบ้

        String word = words[randomIndex];
        String hint = hints[randomIndex];

        // สร้างเกม Games.Hangman และตั้งค่าคำใบ้ที่สุ่มมา
        HangmanGame game = new HangmanGame(word, hint, 6); // ผู้เล่นมีโอกาสทาย 6 ครั้ง

        // แสดงคำใบ้
        game.showHint();

        Scanner scanner = new Scanner(System.in);
        while (!game.isGameOver()) {
            System.out.print("Guess a letter or the whole word: ");
            String guess = scanner.next(); // รับคำทายจากผู้เล่น (รับเป็น String แทน char)
            game.playTurn(guess);
        }

        // แสดงผลลัพธ์เมื่อเกมจบ
        if (String.valueOf(game.guessedWord).equals(game.wordToGuess)) {
            System.out.println("Congratulations! You've guessed the word: " + game.wordToGuess);
        } else {
            System.out.println("Game over! The word was: " + game.wordToGuess);
        }
    }
}
