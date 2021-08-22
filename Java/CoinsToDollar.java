import java.util.Scanner;
import java.text.DecimalFormat;

public class CoinsToDollar {
  //Does the calculations
  public static void getDollarAmount(int quarters, int dimes, int nickels, int pennies) {
    DecimalFormat money = new DecimalFormat("$######0.00");
    double total = 0;
    total = quarters * 0.25;
    total += dimes * 0.1;
    total += nickels * 0.05;
    total += pennies * 0.01;
    System.out.print("Your total is: " + money.format(total));
  }

  public static void main (String [] args) {
    Scanner input = new Scanner (System.in);
    int quarters, dimes, nickels, pennies;
    //Start
    System.out.println("Welcome to the Coin to Dollar calculator!");
    System.out.println("Input the amount of coins and the program will calculate the dollar amount!");
    System.out.println("Enter your coins:");
    System.out.print("Quarters: ");
    quarters = input.nextInt();
    System.out.print("Dimes: ");
    dimes = input.nextInt();
    System.out.print("Nickels: ");
    nickels = input.nextInt();
    System.out.print("Pennies: ");
    pennies = input.nextInt();
    input.close();
    getDollarAmount(quarters, dimes, nickels, pennies);
  }
}
