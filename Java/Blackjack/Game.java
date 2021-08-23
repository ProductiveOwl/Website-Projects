import java.util.ArrayList;
import java.util.Random;

/*Game Class*/
public class Game {
  public ArrayList<String> cards = new ArrayList<String>();

  public void sayRules() {
    System.out.println("This is a game played with a standard deck of cards. The goal of the game is to reach 21 points.                                \nHere are the points for each card.              \nAce = 1 or 11 points                        \nNumbers 2 - 10 = The same value of points \nKing/Queen/Jack = 10 points.                      \nAt the start of the game, the player is given two cards. the player then chooses whether they want another card or stand (stop drawing cards). The game ends when the player stands, reaches exactly 21 points or goes over 21 which is a loss. The computer is playing along too. The computer will draw cards until it has more than 16 points. If both the player and computer stand, whoever has the hightest points will win. Let's play!");
  }

  public int drawCard(String draw_name) {
    int allCards[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    int number, cardChosen;
    Random random = new Random();
    number = random.nextInt(13);
    cardChosen = allCards[number];
    if (cardChosen == 1) {
      System.out.println(draw_name + " drew an Ace.\n");
      cards.add("Ace");
      return 100;
    }
    else if (cardChosen == 11) {
      System.out.println(draw_name + " drew a Jack\n");
      cards.add("Jack");
      return 10;
    }
    else if (cardChosen == 12) {
      System.out.println(draw_name + " drew a Queen\n");
      cards.add("Queen");
      return 10;
    }
    else if (cardChosen == 13) {
      System.out.println(draw_name + " drew a King\n");
      cards.add("King");
      return 10;
    }
    else {
      System.out.println(draw_name + " drew a(n) " + cardChosen + "\n");
      cards.add(String.valueOf(cardChosen));
      return cardChosen;
    }
  }

  //Returns realPoints
  public int startingPoints(String name, String draw_name) {
    int card1, card2, startPoints;
    System.out.println(name + " cards to start:");
    card1 = drawCard(draw_name);
    card2 = drawCard(draw_name);
    startPoints = card1 + card2;
    return startPoints;
  }

  public int pointCalc (int points) {
    int totalAces = 0, totalAces_11, counter = 0, pointsLeft, newTotal = 50;
    pointsLeft = points;
    //If there are no aces, return points
    if (points < 100) {
      return points;
    }
    else {
      //Count the amount of aces
      for (int i = 1; i < 20; i++) {
        pointsLeft = pointsLeft - 100;
        totalAces++;
        if (pointsLeft < 100) {
          break;
        }
      }
      //Adjust points so that the aces are counted properly
      totalAces_11 = totalAces;
      while (newTotal > 21 && counter <= totalAces) {
        //Add non-ace cards, 11 point aces and 1 point aces
        newTotal = pointsLeft + (totalAces_11 - counter) * 11;
        newTotal += counter * 1;
        counter++;
      }
    }
    return newTotal;
  }

  public void displayCards() {
    ArrayList<String> mod_list = new ArrayList<String>(this.cards);
    //Prints all cards drawn from the player or computer (except last card)
    if (mod_list.size() > 0) {
    mod_list.remove(mod_list.size() - 1);
    }
      for (String card : mod_list) {
        System.out.print(card + ", ");
      }
    //Prints last card drawn from the player or computer
    System.out.print(this.cards.get(this.cards.size()- 1));
  }
}
