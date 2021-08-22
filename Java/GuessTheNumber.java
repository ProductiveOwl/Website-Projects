import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
  public static void main(String[] args) {
    Scanner input = new Scanner (System.in);
    Random rand = new Random();
    int user_guess, answer, attempts = 0, play_again = 0;
    int rounds = 1, total_attempts = 0;
    //Start game
    System.out.println("Welcome to Guess The Number!");
    System.out.println("An integer is chosen from 1 to 50.");
    while (play_again == 0) {
      System.out.println("\nRound " + rounds + ":");
      //Generate random number
      answer = rand.nextInt(50) + 1;
      do {
        System.out.print("What is your guess?: ");
        user_guess = input.nextInt();
        //Too high
        if (user_guess > answer) {
          System.out.println("Too high");
          attempts += 1;
          total_attempts += 1;
        }
        //Too low
        else if (user_guess < answer) {
          System.out.println("Too low");
          attempts += 1;
          total_attempts += 1;
        }
        else if (user_guess == answer) {
          //Add attempt that gave the winning answer
          attempts += 1;
          total_attempts += 1;
          System.out.println("You win!\n");
          System.out.println("You guessed the right number in " + attempts + " attempts this round.");
          System.out.println("You have " + total_attempts + " total attempts.");
          attempts = 0;
          break;
        }
      }
      while (user_guess != answer);
      //End
      System.out.print("Would you like to play again? Type '0' to continue or another number to leave: ");
      play_again = input.nextInt();
      if (play_again != 0) {
        System.out.println("Thanks for playing!");
        input.close();
      }
      rounds += 1;
    }
  }
}
