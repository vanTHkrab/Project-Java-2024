package Games.RockPaperScissors;
import java.util.Scanner;
import java.util.Random;

public class ROck {
    public void start() {
        // สร้าง Scanner สำหรับรับการป้อนข้อมูลจากผู้เล่น
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // ตัวเลือกที่คอมพิวเตอร์จะใช้
        String[] rps = { "Rock", "Paper", "Scissors" };

        // วนลูปเพื่อให้เกมเล่นต่อจนกว่าผู้เล่นจะพิมพ์ "exit"
        while (true) {
            System.out.println("Enter Rock, Paper, or Scissors. To quit the game, type 'exit': ");
            String playerMove = scanner.nextLine();

            // ตรวจสอบว่าผู้เล่นพิมพ์ "exit" เพื่อออกจากเกมหรือไม่
            if (playerMove.equalsIgnoreCase("exit")) {
                System.out.println("Game ended.");
                break;
            }

            // ตรวจสอบว่าผู้เล่นเลือกตัวเลือกที่ถูกต้องหรือไม่
            if (!playerMove.equalsIgnoreCase("Rock") &&
                    !playerMove.equalsIgnoreCase("Paper") &&
                    !playerMove.equalsIgnoreCase("Scissors")) {
                System.out.println("Invalid move, please try again.");
                continue;
            }

            // คอมพิวเตอร์สุ่มเลือกหนึ่งตัวเลือกจาก rps
            int computerMoveIndex = random.nextInt(3);
            String computerMove = rps[computerMoveIndex];
            System.out.println("Computer chose: " + computerMove);

            // ตรวจสอบผลลัพธ์ว่าผู้เล่นชนะ, แพ้ หรือเสมอ
            if (playerMove.equalsIgnoreCase(computerMove)) {
                System.out.println("It's a tie!");
            } else if (playerMove.equalsIgnoreCase("Rock")) {
                if (computerMove.equalsIgnoreCase("Scissors")) {
                    System.out.println("You win! Rock crushes Scissors.");
                } else {
                    System.out.println("You lose! Paper covers Rock.");
                }
            } else if (playerMove.equalsIgnoreCase("Paper")) {
                if (computerMove.equalsIgnoreCase("Rock")) {
                    System.out.println("You win! Paper covers Rock.");
                } else {
                    System.out.println("You lose! Scissors cuts Paper.");
                }
            } else if (playerMove.equalsIgnoreCase("Scissors")) {
                if (computerMove.equalsIgnoreCase("Paper")) {
                    System.out.println("You win! Scissors cuts Paper.");
                } else {
                    System.out.println("You lose! Rock crushes Scissors.");
                }
            }
        }


    }
}
