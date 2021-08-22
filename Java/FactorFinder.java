import java.util.Scanner;
import java.lang.Math;

public class FactorFinder {
  public static void main(String[] args) {
    Scanner input = new Scanner (System.in);
    //Declare Variables
    int user_input, i = 2;
    String factors = "";
    //Title and User Input
    System.out.println("\nFactor Finder");
    System.out.print("Input an integer to find its factors: ");
    user_input = input.nextInt();
    input.close();

    //Finds factors (positive and negative)
    while (i <= Math.abs((user_input) / 2)) {
      if (user_input % i == 0) {
        factors += i + ", ";
        if (user_input < 0) {
          factors += (i * -1) + ", ";
        }
      }
      i++;
    }

    System.out.println("\nHere are the factors for " + user_input + ":");
    
    //Edge cases and answers
    if (user_input == 0) {
      System.out.print("The only factor for 0 is 0.");
    }
    else if (user_input == 1) {
      System.out.print("The only factor of 1 is 1.");
    }
    else if (user_input == -1) {
      System.out.print("The only factors of -1 are 1 and -1.");
    }
    else if (user_input < 0) {
      System.out.print("1, -1, " + factors + user_input + ", " + (user_input * -1));
    }
    else {
      System.out.print("1, " + factors + user_input);
    }
  }
}
