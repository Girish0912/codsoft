


import java.util.Scanner;
import java.util.Random;

public class NumberGame 
{
    public static void main(String[] args) 
	{
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        while (playAgain) 
	  {
            System.out.println("Welcome to Guess the Number!");
            int lowerRange = 1;
            int upperRange = 100;
            int secretNumber = random.nextInt(upperRange - lowerRange + 1) + lowerRange;
            int attempts = 0;
            int maxAttempts = 5; // You can adjust this number to change the limit of attempts

            while (true) 
		{
                System.out.print("Guess the number between " + lowerRange + " and " + upperRange + ": ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == secretNumber) 
		    {
                    System.out.println("Congratulations! You guessed the number " + secretNumber + " in " + attempts + " attempts.");
                    int roundScore = maxAttempts - attempts + 1; // Calculate round score based on attempts
                    totalScore += roundScore; // Update total score
                    System.out.println("Round Score: " + roundScore);
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                if (attempts >= maxAttempts) {
                    System.out.println("Sorry, you've reached the maximum number of attempts. The number was " + secretNumber + ".");
                    break;
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        }

        System.out.println("Total Score: " + totalScore);
        System.out.println("Thanks for playing!");
    }
}