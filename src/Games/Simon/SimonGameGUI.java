package Games.Simon;

import Games.Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonGameGUI extends JFrame implements Game {
    private static final String[] COLORS = {"Red", "Green", "Blue", "Yellow"};
    private static final String[] SOUND_FILES = {
            "Red-Ahhho-Adventure.wav",
            "Embracing-the-Color-Green.wav",
            "The-Beauty-of-Blue.wav",
            "The-Color-Yellow-Explained.wav"
    };

    private final List<String> sequence = new ArrayList<>();
    private final Random random = new Random();
    private final JTextArea gameStatus;
    private boolean waitingForPlayerInput = false;
    private int playerIndex = 0;

    public SimonGameGUI() {
        setTitle("Simon Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        JButton[] colorButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            colorButtons[i] = new JButton(COLORS[i]);
            colorButtons[i].setBackground(getColor(COLORS[i]));
            colorButtons[i].setActionCommand(COLORS[i]);
            colorButtons[i].addActionListener(new ColorButtonListener());
            buttonPanel.add(colorButtons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);

        gameStatus = new JTextArea();
        gameStatus.setEditable(false);
        add(new JScrollPane(gameStatus), BorderLayout.SOUTH);

        setVisible(true);
    }

    private Color getColor(String colorName) {
        return switch (colorName) {
            case "Red" -> Color.RED;
            case "Green" -> Color.GREEN;
            case "Blue" -> Color.BLUE;
            case "Yellow" -> Color.YELLOW;
            default -> Color.GRAY;
        };
    }

    private void playSound(String soundFile, String text) {
        SwingUtilities.invokeLater(() -> {
            try {
                URL soundURL = SimonGameGUI.class.getResource("/Games/Simon/sound/" + soundFile);
                if (soundURL == null) {
                    gameStatus.append("Sound file not found: " + soundFile + "\n");
                    return;
                }
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundURL);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                gameStatus.append(text + "\n"); // Display the text corresponding to the sound
                Thread.sleep(1000); // Play sound for 1 second
                clip.close();
            } catch (Exception e) {
                gameStatus.append("Error playing sound: " + e.getMessage() + "\n");
                e.printStackTrace();
            }
        });
    }

    private void waitForPlayerInput() {
        SwingUtilities.invokeLater(() -> {
            waitingForPlayerInput = true;
            playerIndex = 0;
            gameStatus.append("It's your turn! Please select a color.\n");
        });
    }

    private void handlePlayerChoice(String color) {
        if (!waitingForPlayerInput) return;

        SwingUtilities.invokeLater(() -> {
            gameStatus.append("You pressed: " + color + "\n");

            // Check if player's choice matches the sequence
            String expectedColor = sequence.get(playerIndex);
            if (!color.equalsIgnoreCase(expectedColor)) {
                gameStatus.append("Game Over! Incorrect choice.\n");
                gameStatus.append("You remembered a sequence length of: " + sequence.size() + "\n");
                gameStatus.append("Your Rank: " + getRank(sequence.size()) + "\n");
                playSound("Roblox-Death-Sound-OOF-Sound-Effect-HD-HomeMadeSoundEffects.wav", "Game Over");
                waitingForPlayerInput = false;
            } else {
                playerIndex++;
                if (playerIndex >= sequence.size()) {
                    gameStatus.append("Correct! Get ready for the next round.\n");
                    waitingForPlayerInput = false;
                    newRound(); // Start the next round
                }
            }
        });
    }

    public void start() {
        gameStatus.setText("Welcome to Simon Game! Remember the sequence of colors.\n");
        newRound();
    }

    private void newRound() {
        // Add a new color to the sequence
        int nextColorIndex = random.nextInt(COLORS.length);
        String nextColor = COLORS[nextColorIndex];
        sequence.add(nextColor);

        // Show the sequence to the player
        showSequence();
    }

    private void showSequence() {
        new Thread(() -> {
            for (String color : sequence) {
                int colorIndex = -1;
                for (int i = 0; i < COLORS.length; i++) {
                    if (COLORS[i].equalsIgnoreCase(color)) {
                        colorIndex = i;
                        break;
                    }
                }
                playSound(SOUND_FILES[colorIndex], "Simon says: ?");
                try {
                    Thread.sleep(1000); // Delay between showing each color
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            waitForPlayerInput(); // Once the sequence is shown, wait for player's input
        }).start();
    }

    private String getRank(int score) {
        return getString(score);
    }

    static String getString(int score) {
        if (score < 5) {
            return "Rank F";
        } else if (score >= 6 && score <= 15) {
            return "Rank E";
        } else if (score >= 16 && score <= 24) {
            return "Rank D";
        } else if (score >= 25 && score <= 34) {
            return "Rank C";
        } else if (score >= 35 && score <= 44) {
            return "Rank B";
        } else if (score >= 45 && score <= 80) {
            return "Rank A";
        } else {
            return "Rank S";
        }
    }

    private class ColorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String colorName = e.getActionCommand();
            handlePlayerChoice(colorName);
        }
    }

    @Override
    public String getName() {
        return "Simon Game";
    }
}
