package Games.Simon;
import Games.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.net.URL;

public class SimonGame implements Game {
    public String getName() {
        return "Simon Game";
    }

    // ฟังก์ชันสำหรับการเล่นไฟล์เสียง
    public static void playSound(String soundFile) {
        try {
            // เข้าถึงไฟล์เสียงที่อยู่ในแพ็กเกจ sound
            URL soundURL = SimonGame.class.getResource("/Games/Simon/sound/" + soundFile);
            if (soundURL == null) {
                System.out.println("Sound file not found: " + soundFile);
                return;
            }
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            Thread.sleep(1000); // เล่นเสียงเป็นเวลา 1 วินาที
            clip.close(); // ปิดเสียงเมื่อเล่นเสร็จ
        } catch (Exception e) {
            System.out.println("Error playing sound.");
            e.printStackTrace();  // พิมพ์ stack trace เพื่อช่วยในการดีบัก
        }
    }

    // ฟังก์ชันสำหรับเคลียร์หน้าจอ
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // ฟังก์ชันสำหรับการจัดอันดับ
    public static String getRank(int score) {
        return "Beginner";
    }

    public void start() {
        // ตัวเลือกสีที่ใช้ในเกม
        String[] colors = {"Red", "Green", "Blue", "Yellow"};

        // ใช้ไฟล์เสียงสำหรับสีต่างๆ
        String[] soundFiles = {
                "Red-Ahhho-Adventure.wav",                // สีแดง
                "Embracing-the-Color-Green.wav",          // สีเขียว
                "The-Beauty-of-Blue.wav",                 // สีฟ้า
                "The-Color-Yellow-Explained.wav"          // สีเหลือง
        };

        // สร้างออบเจ็กต์สุ่มและ Scanner
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // ลำดับที่คอมพิวเตอร์สุ่มขึ้น
        List<String> sequence = new ArrayList<>();

        // เริ่มเกม
        boolean gameOver = false;
        System.out.println("Welcome to Simon Game! Remember the sequence of colors.");

        // วนลูปการเล่น
        while (!gameOver) {
            // เคลียร์หน้าจอทุกครั้งที่เริ่มรอบใหม่
            clearScreen();

            // เพิ่มสีใหม่ในลำดับ
            int nextColorIndex = random.nextInt(colors.length);
            String nextColor = colors[nextColorIndex];
            sequence.add(nextColor);

            // แสดงลำดับ
            System.out.println("Simon says: ");
            for (String color : sequence) {
                int colorIndex = -1;
                for (int i = 0; i < colors.length; i++) {
                    if (colors[i].equalsIgnoreCase(color)) {
                        colorIndex = i;
                        break;
                    }
                }
                // เล่นเสียงของสีที่ตรงกัน
                playSound(soundFiles[colorIndex]);  // เล่นเสียงของสีแดง, สีเขียว, สีฟ้า, หรือสีเหลือง
            }

            // ให้ผู้เล่นป้อนลำดับ (ไม่แสดงผลการป้อนลำดับ)
            System.out.println("Now it's your turn! Enter the sequence of colors:");
            List<String> playerSequence = new ArrayList<>();
            for (int i = 0; i < sequence.size(); i++) {
                String playerColor = scanner.nextLine().trim();
                playerSequence.add(playerColor);
            }

            // ตรวจสอบว่าผู้เล่นป้อนถูกต้องหรือไม่
            for (int i = 0; i < sequence.size(); i++) {
                if (!sequence.get(i).equalsIgnoreCase(playerSequence.get(i))) {
                    gameOver = true;
                    break;
                }
            }

            // ถ้าผู้เล่นป้อนลำดับผิด เกมจะจบ
            if (gameOver) {
                System.out.println("Game Over!");
                System.out.println("You remembered a sequence length of: " + sequence.size());
                // แสดงอันดับตามจำนวนลำดับที่ผู้เล่นจำได้
                System.out.println("Your Rank: " + getRank(sequence.size()));
                // เล่นเสียงเมื่อเกมจบ
                playSound("Roblox-Death-Sound-OOF-Sound-Effect-HD-HomeMadeSoundEffects.wav");  // เล่นเสียงจบเกม
            } else {
                System.out.println("Correct! Get ready for the next round.");
            }
        }
    }

//    public static void main(String[] args) {
//        SimonGame simonGame = new SimonGame();
//        simonGame.start();
//    }
}
