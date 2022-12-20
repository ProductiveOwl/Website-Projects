import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.lang.Thread;
import java.io.*;

Must click on a game and finish it*/

public class Main {
  static JFrame frame = new JFrame(); // create the JFrame
  static JButton op1 = new JButton("Tic Tac Toe"); // create a tictactoe button
  static JButton op2 = new JButton("RPS"); // create the rock, paper, scissors button
  static JButton op3 = new JButton("Hangman"); // create the hangman button
  static JButton op4 = new JButton("GTN"); // create the hangman button
  static JButton secret = new JButton("Secret"); // the button that will lead to the secret
  static JLabel title = new JLabel("Game Escape!"); // display the title of the game
  // greet the user and say the instructions
  static JTextArea greet = new JTextArea(
      "Win 4 Games to unlock a code letter. Use these letters to unlock the secret that can be accessed through the secret \n button. Try to beat all the games as quick as possible and your final time will be displayed at the end. The games: \n (1) Tic Tac Toe \n (2) Rock Paper Scissors \n (3) Hangman \n (4) Guess the Number \n Click and finish ONE GAME AT A TIME to recieve the proper code. To reset the timer, RESET the program. After entering a \n game, press the BACK button or EXIT the program to return to the menu. Press START to begin your time!");
  // static Instant i;
  /*static LocalDateTime i = LocalDateTime.now();
  // static Instant j;
  static LocalDateTime j = LocalDateTime.now();
  
  static Duration d = Duration.between(i, j);*/
  static JLabel time = new JLabel("0:00");
  static TimeTracker object = new TimeTracker(time);

  static boolean isStopped = false;
  static JLabel TicCode = new JLabel("?");
  static JLabel RCode = new JLabel("?");
  static JLabel HCode = new JLabel("?");
  static JLabel GNCode = new JLabel("?");

  public static void main(String[] args) {
    // set up the frame
    frame.setTitle("Main"); // name the frame
    frame.getContentPane().setLayout(null);
    frame.setVisible(true); // make the frame visible
    frame.setBounds(0, 0, 700, 550); // size the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make sure the programe ends when the frame closes
    frame.getContentPane().setBackground(new Color(100, 200, 150)); // set the background color of the frame

    //Add a title
    title.setBounds(210, 0, 300, 50); // size and place the label
    title.setFont(new Font("Serif", Font.BOLD, 35)); // change the font to make it bigger
    title.setForeground(Color.WHITE); // change the text colour
    frame.add(title); // add the title to the frame

    //Greet the user and display the instructions
    greet.setBounds(0, 360, 700, 150); // place and size the greeting
    greet.setEditable(false); // the user cannot edit the instructions
    greet.setLineWrap(true); // allow the text to wrap in the textfield
    frame.add(greet); // add the greeting the frame

    op1.setBounds(120, 70, 100, 100); // place and size the first button
    op1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent clickevent) {
        new Game(TicCode); //launch the Game class with the label for the code as an argument
        new TicTacToe(); // launch the TicTacToe game
      }
    }); // When the tic tac toe button is pressed, bring user to the game window
    frame.add(op1); // add the first button to the frame
    op1.setEnabled(false); //Not enabled until the user presses start

    op2.setBounds(250, 70, 100, 100); // place and size the second button
    op2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent clickevent) {
        new Game(RCode); //launch the Game class with the label for the code as an argument
        new RPSGame(); // launch the Rock Paper Scissors game
      }
    }); // When the rock paper scissors button is pressed, bring user to the game window
    frame.add(op2); // add the second button to the frame
    op2.setEnabled(false); //Not enabled until the user presses start

    op3.setBounds(380, 70, 100, 100); // place and size the third button
    op3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent clickevent) {
        new Game(HCode); //launch the Game class with the label for the code as an argument
        new Hangman(); // Launch hangman
      }
    }); // When the hangman button is pressed, bring user to the game window
    frame.add(op3); // add the third button to the frame
    op3.setEnabled(false); //Not enabled until the user presses start

    op4.setBounds(510, 70, 100, 100); // place and size the third button
    op4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent clickevent) {
        new Game(GNCode); //launch the Game class with the label for the code as an argument
        new Stones(); // launch the guess the number game
      }
    }); // When the stones button is pressed, bring user to the game window
    frame.add(op4); // add the third button to the frame
    op4.setEnabled(false); //Not enabled until the user presses start

    secret.setBounds(245, 230, 100, 100); // place and size the secret button
    secret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent clickevent) {
        new Secret(object.getTotalTime()); // launch the secret frame and get the time taken
      }
    }); // When the secret button is pressed, bring user to the secret window
    frame.add(secret); // add the secret button to the frame

    JButton start = new JButton("Start");
    start.setBounds(410, 230, 100, 50); // place and size the secret button
    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent clickevent) {
        object.start(); //start the timer
       
        Game t = new Game(); //launch the Game class to use the time
        t.usedTimer(); //The user has started the timer 

        start.setEnabled(false); //user must restart the program to reset the timer

        //Enable the game buttons
        op1.setEnabled(true);
        op2.setEnabled(true);
        op3.setEnabled(true);
        op4.setEnabled(true);
      }
    }); // When the start button is pressed, start the timer
    frame.add(start); // add the secret button to the frame

    JButton stop = new JButton("Stop");
    stop.setBounds(530, 230, 100, 50); // place and size the secret button
    stop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent clickevent) {
        //Determine if the timer needs to stop or resume
        //If the timer is already paused
        if (isStopped) {
          object.reactivateTimer(time); //reactivate the timer
          stop.setText("Stop"); //Reset the text on the button
          isStopped = false; //the timer is currently running

          //Enable the game buttons
          op1.setEnabled(true);
          op2.setEnabled(true);
          op3.setEnabled(true);
          op4.setEnabled(true);
        }
        //If the timer is running
        else {
          object.stopTimer(); //stop the timer
          stop.setText("Resume"); //change the text to ask the user if they want to resume the timer
          isStopped = true; //the timer has stopped

          //Disable all the game buttons
          op1.setEnabled(false);
          op2.setEnabled(false);
          op3.setEnabled(false);
          op4.setEnabled(false);
        }
      }
    }
    ); // When the stop/resume button is pressed, stop/resume the timer
    frame.add(stop); // add the secret button to the frame

    //Display the timer
    time.setBounds(480, 280, 100, 50); //size and place the time label
    time.setFont(new Font("Serif", Font.BOLD, 50)); // change the font to make it bigger
    time.setForeground(Color.WHITE); // change the text colour
    frame.add(time); //add the time label to the frame

    addLabels(); //add more labels to the frame

  }// end main

  /*Add additional labels to make the interface more user friendly*/
  public static void addLabels() {
    JLabel codes = new JLabel("Revealed Letters: "); //Label that will show where the codes are displayed once revealed
    codes.setFont(new Font("Serif", Font.BOLD, 14)); // change the font to make it bigger
    codes.setBounds(10, 180, 150, 20); //size and place the label
    codes.setForeground(Color.WHITE); // change the text colour
    
    TicCode.setBounds(165, 175, 100, 25); //size and place the label
    TicCode.setForeground(Color.WHITE); // change the text colour
    TicCode.setFont(new Font("Serif", Font.BOLD, 28)); // change the font to make it bigger
    
    RCode.setBounds(295, 175, 100, 25); //size and place the label
    RCode.setForeground(Color.WHITE); // change the text colour
    RCode.setFont(new Font("Serif", Font.BOLD, 28)); // change the font to make it bigger

    HCode.setBounds(425, 175, 100, 25); //size and place the label
    HCode.setForeground(Color.WHITE); // change the text colour
    HCode.setFont(new Font("Serif", Font.BOLD, 28)); // change the font to make it bigger

    GNCode.setBounds(555, 175, 100, 25); //size and place the label
    GNCode.setForeground(Color.WHITE); // change the text colour
    GNCode.setFont(new Font("Serif", Font.BOLD, 28)); // change the font to make it bigger

    frame.add(codes); //add label to the frame
    frame.add(TicCode); //add label to the frame
    frame.add(RCode); //add label to the frame
    frame.add(HCode); //add label to the frame
    frame.add(GNCode); //add label to the frame
  } //end method
}// end class
