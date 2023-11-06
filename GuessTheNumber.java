import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int attemptsLimit = 10;
        int rounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number!");

        for (int round = 1; round <= rounds; round++) {
            int generatedNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            int score = 0;

            System.out.println("\nRound " + round + ": Guess a number between " + lowerBound + " and " + upperBound);

            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score = attemptsLimit - attempts + 1; // Score calculation
                    totalScore += score;
                    break;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Try again! The number is higher.");
                    System.out.println("You guessed the incorrect number...The actual number was: " + generatedNumber);
                } else {
                    System.out.println("Try again! The number is lower.");
                    System.out.println("You guessed the incorrect number...The actual number was: " + generatedNumber);
                }
            }

            if (attempts == attemptsLimit) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + generatedNumber);
            }

            System.out.println("Round " + round + " Score: " + score);
        }

        System.out.println("\nGame Over. Total Score: " + totalScore);

        scanner.close();
    }
}
