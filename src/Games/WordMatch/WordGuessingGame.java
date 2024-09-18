package Games.WordMatch;

import Games.Game;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class WordGuessingGame implements Game {
    public String getName() {
        return "Word Guessing Game";
    }

    @Override
    public void start() {
        String[] animals = {"cat", "dog", "elephant", "lion", "tiger", "giraffe", "zebra",
                "kangaroo", "panda", "whale", "dolphin", "shark", "eagle",
                "penguin", "rabbit", "bat", "octopus", "bear", "crocodile", "monkey"};

        String[] objects = {"table", "chair", "computer", "phone", "car", "clock", "lamp",
                "pencil", "notebook", "television", "keyboard", "mouse", "bed",
                "mirror", "couch", "bottle", "umbrella", "wallet", "watch", "camera"};

        String[] foods = {"pizza", "burger", "pasta", "apple", "banana", "grape", "cherry",
                "mango", "orange", "carrot", "broccoli", "rice", "sushi",
                "noodles", "sandwich", "steak", "icecream", "cake", "cookie", "salad"};

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String selectedWord;

        System.out.println("Choose a category:");
        System.out.println("1. Animals");
        System.out.println("2. Objects");
        System.out.println("3. Foods");
        System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                selectedWord = animals[random.nextInt(animals.length)];
                System.out.println("You selected the category: Animals");
                break;
            case 2:
                selectedWord = objects[random.nextInt(objects.length)];
                System.out.println("You selected the category: Objects");
                break;
            case 3:
                selectedWord = foods[random.nextInt(foods.length)];
                System.out.println("You selected the category: Foods");
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Animals.");
                selectedWord = animals[random.nextInt(animals.length)];
                break;
        }

        boolean wordGuessed = false;
        int attempts = 0;
        int wrongAttempts = 0;

        System.out.println("The word has " + selectedWord.length() + " letters.");

        char[] revealedLetters = new char[selectedWord.length()];
        Arrays.fill(revealedLetters, '_');

        while (!wordGuessed) {
            System.out.println("Current word: " + String.valueOf(revealedLetters));
            System.out.print("Guess the word: ");
            String guess = scanner.next().toLowerCase();

            if (guess.equals(selectedWord)) {
                wordGuessed = true;
                attempts++;
                System.out.println("Congratulations! You guessed the word '" + selectedWord + "' in " + attempts + " attempt(s).");
            } else {
                if (!String.valueOf(revealedLetters).equals(selectedWord)) {
                    System.out.println("Wrong guess! Try again.");
                    attempts++;
                    wrongAttempts++;

                    if (wrongAttempts == 3) {
                        revealLetter(selectedWord, revealedLetters);
                        wrongAttempts = 0;
                        System.out.println("Hint: One letter has been revealed!");
                    }
                }

                if (String.valueOf(revealedLetters).equals(selectedWord)) {
                    System.out.println("The correct word was: " + selectedWord);
                    wordGuessed = true;
                }
            }
        }



    }

    public static void revealLetter(String word, char[] revealedLetters) {
        Random random = new Random();
        boolean revealed = false;

        while (!revealed) {
            int index = random.nextInt(word.length());
            if (revealedLetters[index] == '_') {
                revealedLetters[index] = word.charAt(index);
                revealed = true;
            }
        }
    }
}
