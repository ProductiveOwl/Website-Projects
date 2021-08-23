import java.util.Scanner;
import java.text.DecimalFormat;

public class TemperatureConverter {
  public static int checkUserInput(int userChoice) {
    Scanner input = new Scanner (System.in);
    while (userChoice != 1 && userChoice != 2) {
      System.out.print("Please enter 1 or 2: ");
      userChoice = input.nextInt();
    }
    return userChoice;
  }
  //Converting Celsius to Fahrenheit
  public static void CtoF () {
    Scanner input = new Scanner(System.in);
    DecimalFormat temp = new DecimalFormat("######0.0#°F");
    double fTemp = 0, cTemp = 0;
    System.out.print("\n");
    System.out.print("Input a number for Celsius: ");
    cTemp = input.nextDouble();
    input.close();
    fTemp = ((9.0/5.0) * cTemp) + 32.0;
    System.out.print("The temperature in Fahrenheit is " + temp.format(fTemp));
  }
  //Converting Fahrenheit to Celsius
  public static void FtoC () {
    Scanner input = new Scanner(System.in);
    DecimalFormat temp = new DecimalFormat("######0.0#°C");
    double fTemp, cTemp;
    System.out.print("\n");
    System.out.print("Input a number for Fahrenheit: ");
    fTemp = input.nextDouble();
    input.close();
    cTemp = (5.0/9.0) * (fTemp - 32.0);
    System.out.print("The temperature in Celsius is " + temp.format(cTemp));
  }
  //Main
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int userChoice = 0;
    System.out.println("\nWelcome to Temperature Converter");
    System.out.println("What would you like to convert?");
    System.out.println("1. Celsius to Fahrenheit");
    System.out.println("2. Fahrenheit to Celsius");
    System.out.print("Select your option by typing in the number: ");
    userChoice = input.nextInt();
    userChoice = checkUserInput(userChoice);

    if (userChoice == 1) {
      CtoF();
    }
    else {
      FtoC();
    }
    input.close();
  }
}
