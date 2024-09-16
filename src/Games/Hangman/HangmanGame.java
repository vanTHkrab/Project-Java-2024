package Games.Hangman;

public class HangmanGame extends Game {
    private String hint;  // ฟิลด์สำหรับเก็บคำใบ้

    // Constructor ที่เพิ่มคำใบ้
    public HangmanGame(String wordToGuess, String hint, int attempts) {
        super(wordToGuess, attempts);
        this.hint = hint;
    }

    // แสดงคำใบ้เมื่อเริ่มเกม
    public void showHint() {
        System.out.println("Hint: " + hint);
    }

    @Override
    public void playTurn(String guess) {
        // ตรวจสอบว่าผู้เล่นป้อนคำทั้งคำหรือไม่
        if (guess.length() > 1) {
            // ตรวจสอบว่าคำที่ป้อนตรงกับคำตอบที่ถูกต้องหรือไม่
            if (guess.equalsIgnoreCase(wordToGuess)) {
                guessedWord = wordToGuess.toCharArray(); // แสดงคำทั้งหมดที่ถูกต้อง
                System.out.println("Correct! You've guessed the whole word: " + wordToGuess);
            } else {
                attempts--;
                System.out.println("Wrong guess! Attempts left: " + attempts);
                displayHangman();
            }
        } else {
            // หากผู้เล่นป้อนเพียงแค่ตัวอักษรเดียว (เล่นตามปกติ)
            char letterGuess = Character.toLowerCase(guess.charAt(0)); // แปลงตัวอักษรเป็นพิมพ์เล็ก
            boolean correct = false;

            for (int i = 0; i < wordToGuess.length(); i++) {
                if (Character.toLowerCase(wordToGuess.charAt(i)) == letterGuess) { // เปรียบเทียบโดยไม่สนใจพิมพ์เล็กพิมพ์ใหญ่
                    guessedWord[i] = wordToGuess.charAt(i); // เก็บตัวอักษรที่ทายถูกตามรูปแบบเดิม (พิมพ์เล็กหรือใหญ่)
                    correct = true;
                }
            }

            if (!correct) {
                attempts--;
                System.out.println("Wrong guess! Attempts left: " + attempts);
                displayHangman();
            } else {
                System.out.println("Correct! Current word: " + String.valueOf(guessedWord));
            }
        }
    }

    // ฟังก์ชันสำหรับแสดง Games.Hangman ตามจำนวนครั้งที่เหลือ
    public void displayHangman() {
        switch (attempts) {
            case 5:
                System.out.println(" O ");
                break;
            case 4:
                System.out.println(" O ");
                System.out.println(" | ");
                break;
            case 3:
                System.out.println(" O ");
                System.out.println(" | ");
                System.out.println("/  ");
                break;
            case 2:
                System.out.println(" O ");
                System.out.println(" | ");
                System.out.println("/ \\");
                break;
            case 1:
                System.out.println(" O ");
                System.out.println("\\| ");
                System.out.println("/ \\");
                break;
            case 0:
                System.out.println(" O ");
                System.out.println("\\|/");
                System.out.println("/ \\");
                System.out.println("Game over! The man is hanged.");
                break;
        }
    }
}

