import java.util.*;

public class NumberGuessingGameStarterCode {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        // Generate a number from 1 to 100
        int secretNumber = rand.nextInt(100) + 1;

        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100...");
        System.out.println("Try to guess it!");

        int guess = 0;
        int attempts = 0;

        // Loop until the user guesses correctly
        while (guess != secretNumber) {
            System.out.print("Enter your guess: ");

            // Validate numeric input
            while (!input.hasNextInt()) {
                System.out.print("Invalid input! Enter a number: ");
                input.next();
            }

            guess = input.nextInt();
            attempts++;

            // Give hints
            if (guess < secretNumber) {
                System.out.println("Too low! Try again ⬆️");
            } else if (guess > secretNumber) {
                System.out.println("Too high! Try again ⬇️");
            } else {
                System.out.println("\n🎉 Correct! Great job!");
            }
        }

        // Display results
        System.out.println("---------------------------");
        System.out.println("The number was: " + secretNumber);
        System.out.println("Total attempts: " + attempts);
        System.out.println("Thanks for playing!");
    }
}
