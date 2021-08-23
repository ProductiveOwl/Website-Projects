import java.util.Scanner;

public class Blackjack {
  public static void main(String[] args) {
    Game player = new Game();
    Game computer = new Game();
    Scanner input = new Scanner (System.in);
    //Variables
    String rules = "", another_card = "", another_round = "";
    //realPoints -> identifies the number of aces with the hundreds column
    //displayPoints -> actual points someone has
    int realPoints_play = 0, displayPoints_play = 0;
    int realPoints_comp = 0, displayPoints_comp = 0;
    int round = 1;

    //Welcome and explain rules
    System.out.println("\nWelcome to Blackjack!\n");
    System.out.print("Would you like to learn the rules? (press y for yes or any other key to skip): ");
    rules = input.nextLine();
    if (rules.equals("Y") || rules.equals("y")) {
      player.sayRules();
    }
    System.out.print("\n");

    while (!another_round.equals("n")) {
    //Clear cards for each new round
    player.cards.clear();
    computer.cards.clear();
   
    //Give out and display cards to start
    //Computer
    System.out.println("Round " + round + ":");
    realPoints_comp = computer.startingPoints("Computer's", "Computer");
    displayPoints_comp = computer.pointCalc(realPoints_comp);
    System.out.println("Computer's cards:");
    computer.displayCards();
    System.out.println("\nComputer has " + displayPoints_comp + " points.");

    System.out.print("-------------------------------");

    //Player
    System.out.print("\n");
    realPoints_play = player.startingPoints("Your", "You");
    displayPoints_play = player.pointCalc(realPoints_play);
    System.out.println("Here are your cards:");
    player.displayCards();
    System.out.println("\nYou have " + displayPoints_play + " points.");

    //Draw cards until player stops or someone reaches >=21
    while (displayPoints_play < 21 && displayPoints_comp <= 21){
      //Computer automatically wins
      if (displayPoints_comp == 21) {
        break;
      }
      System.out.print("\nWould you like to stand? (y for yes or any other key to draw another card): ");
      another_card = input.nextLine();

      //Player doesn't want cards
      if (another_card.equals("y") || another_card.equals("Y")) {
        //Computer will draw more cards if points <= 16
        while (displayPoints_comp <= 16) {
          //Display round # and computer draws cards
          System.out.println("-------------------------------");
          round++;
          System.out.println("Round " + round + ":");

          System.out.println("-------------------------------");
          System.out.println("The computer will draw a card.");
          realPoints_comp += computer.drawCard("Computer");
          displayPoints_comp = computer.pointCalc(realPoints_comp);
          System.out.println("Computer's cards");
          computer.displayCards();
          System.out.print("\nComputer total: " + displayPoints_comp + "\n");
        }
        break;
      }
      //Player wants cards
      else {
        //Display round # and draw cards
        System.out.println("-------------------------------");
        round++;
        System.out.println("Round " + round + ":");

        //Computer will draw more cards if points <= 16
        if (displayPoints_comp <= 16) {
          System.out.println("-------------------------------");
          System.out.println("The computer will also draw a card.");
          realPoints_comp += computer.drawCard("Computer");
          displayPoints_comp = computer.pointCalc(realPoints_comp);
          System.out.println("Computer's cards");
          computer.displayCards();
          System.out.print("\nComputer total: " + displayPoints_comp + "\n");
        }
        //Player Turn
        System.out.println("-------------------------------");
        realPoints_play += player.drawCard("You");
        displayPoints_play = player.pointCalc(realPoints_play);
        System.out.println("Here are your cards");
        player.displayCards();
        System.out.print("\nNew total: " + displayPoints_play + "\n");
      }
    }

    //Declare Winner
    System.out.println("\n");
    player.findWinner(displayPoints_play, displayPoints_comp);

    //Game ended and another round can start
    System.out.print("Another round? (n for no and any other key for yes): ");
    another_round = input.nextLine();
    System.out.println("-------------------------------");
    //reset round counter
    round = 1;
    }

    input.close();
    System.out.println("Bye!");
  }
}
