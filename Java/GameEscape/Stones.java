import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Stones {
    JFrame frame = new JFrame(); //set up the frame
    JButton back = new JButton("Back"); //back button
    JTextField insert = new JTextField(""); //text input for a letter
    JTextArea inst = new JTextArea("A number is chosen between 1 and 100. Guess the correct number with the help of the number line in 10 tries. As \n you type in numbers in the textbox and press enter, the number line will update automatically to find the number."); //The instructions to the user

    JLabel results = new JLabel ("The results here"); //tells the user the accuracy of their guess
    JLabel displayTries = new JLabel ("0"); //the number of tries the user has
    JLabel secret = new JLabel("Secret Letter: D"); //the secret letter to be revealed
    JPanel line = new JPanel (); //number line
    JLabel lower = new JLabel("?"); //lower number
    JLabel upper = new JLabel("?"); //higher number
    JLabel realLabel = new JLabel ("?");

    int numTries = 0; //the number of tries the user takes
    int realNum = 0; //the real number the computer chose
    int userNumInt = 0; //The user's number as an integer
    int previousUp = 0; //the previous higher number
    int previousDown = 0; //the previous lower number
    String userNum = " "; //The user's input as a String

    /*default constructor 
     * Set up the frame and all the components the user will interact with
    */
    public Stones () {
        //set up the frame
        frame.setTitle("Guess the Number"); //set the name of the frame to Rock Paper Scissors
        frame.getContentPane().setLayout(null);
        frame.setVisible(true); //let the user see the frame
        frame.setBounds(0,0,800,500); //size the frame
        frame.getContentPane().setBackground(new Color (100, 200, 150)); //set the background colour
        generateNum(); //generate a random number
        addLabels(); //add labels for clarity

        //Set up the number line
        line.setBounds(100, 175, 450, 10); //size and frame the number line
        line.setBackground(Color.WHITE); //fill in the line
        frame.add(line); //add the line to the frame

        //Lower number
        lower.setBounds(60, 150, 50, 50); //size and place the label
        lower.setFont(new Font("Serif", Font.BOLD, 25));
        lower.setForeground(Color.white);
        frame.add(lower); //add the label to the frame

        //real number
        realLabel.setBounds(320, 130, 50, 50); //size and place the label
        realLabel.setFont(new Font("Serif", Font.BOLD, 25));
        realLabel.setForeground(Color.white);
        frame.add(realLabel); //add the label to the frame

        //Higher number
        upper.setBounds(570, 150, 50, 50); //size and place the label
        upper.setFont(new Font("Serif", Font.BOLD, 25));
        upper.setForeground(Color.white);
        frame.add(upper); //add the label to the frame

        //Set up the textbox
        insert.setBounds(280, 320, 100, 30); //size and place the label
        //When the action is performed, record the user's number to check
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickevent) {
                checkUserInputInt(insert.getText()); //get the text the user entered
            }
          });
        frame.add(insert);//add the text box to the frame
        
        //The instructions of the game
        inst.setBounds(5, 40, 775, 50); //size and place the label
        inst.setEditable(false); //do not allow the user to edit
        inst.setLineWrap(true); //allow the text to wrap
        inst.setBackground(new Color (100, 200, 150)); //set the background colour of the frame
        inst.setForeground(Color.WHITE); // change the text colour
        inst.setBorder(BorderFactory.createLineBorder(Color.white)); // put a black border around the label
        inst.setFont(new Font("Serif", Font.BOLD, 15)); //change the font size of the title
        frame.add(inst); //add the instructions to the frame

        //Display the secret letter
        secret.setBounds(280, 430, 600, 20); //size and place the secret label
        frame.add(secret); //add the label to the frame
        secret.setVisible(false); //user cannot see the secret yet
        secret.setForeground(Color.WHITE); //change the text colour to white
        secret.setFont(new Font("Serif", Font.BOLD, 20)); //change the font

        //Set up message to user
        results.setBounds(150, 220, 380, 50); //size the results label
        results.setFont(new Font("Serif", Font.BOLD, 25));
        results.setBorder(BorderFactory.createLineBorder(Color.white)); //create a border around the label
        results.setForeground(Color.white);
        frame.add(results); //add the label to the frame

        //Number of tries
        displayTries.setBounds(80, 370, 100, 50); //size and place the displayTries label
        displayTries.setForeground(Color.WHITE); //change the text colour to white
        displayTries.setFont(new Font("Serif", Font.BOLD, 25));
        frame.add(displayTries); //add the label to the frame

        //Set up the back button
        back.setBounds(280, 370, 100, 50); //size and place the back button
        back.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent clickevent) {
                frame.dispose(); //close the frame which will lead the user back to the main frame
            }
          });
        frame.add(back); //add the back button to the frame
    }//end constructor

    /*addLabels() method
     * Add more labels to make the interface more friendly to users
     */
    public void addLabels() {
        JLabel guess = new JLabel("Number of guesses:"); //The guesses the user has
        guess.setBounds(25, 350, 300, 25); //size and place the label
        guess.setForeground(Color.WHITE); //change the text colour to white
        guess.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel numToGuess = new JLabel("Guess the Number!"); //the number the user will guess
        numToGuess.setBounds(265, 10, 300, 20); //size and place the label
        numToGuess.setFont(new Font("Serif", Font.BOLD, 25));
        numToGuess.setForeground(Color.white);
        JLabel enterNumber = new JLabel("Enter a number here:"); //tell user where to enter the number
        enterNumber.setBounds(135, 320, 150, 20); //place and size the number
        enterNumber.setForeground(Color.WHITE); //change the text colour to white
        enterNumber.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel high = new JLabel("High end"); //label large number
        high.setBounds(560, 130, 50, 20); //size and place the label
        high.setForeground(Color.white);
        JLabel low = new JLabel("Low end"); //label low number
        low.setBounds(50, 130, 50, 20); //size and place the label
        low.setForeground(Color.white);

        frame.add(guess); //add the label to the frame
        frame.add(numToGuess); //add the label to the frame
        frame.add(enterNumber); //add the label to the frame
        frame.add(high); //add the label to the frame
        frame.add(low); //add the label to the frame
    }//end method

    /*generateNum() method
     * Generate a random number that the user needs to guess between 1 and 100
    */
    public void generateNum () {
        int min = 1; //the minimum the number it can be
        int max = 100; //the maximum the number can be

        realNum = (int)(Math.random() * (max - min + 1) + min); //generate a random number
    }//end method

    /*checkUserInputInt() method
     * This method checks that the user's input is an integer. Otherwise, it will tell
     * the player to enter a proper integer
     */
    public void checkUserInputInt (String user_input_str) {
        int userNumInt = 0; //the user's number as an integer
        
        //Check if the user entered an integer
        try {
            //if user's choice can be converted to an Integer, it is valid
            userNumInt = Integer.parseInt(user_input_str); 
            compareNum(userNumInt); //if the user entered a valid integer, it will be passed to the compareNum() method
            
        }
        //The user did not enter a valid integer
        catch (NumberFormatException e) {
            results.setText("Enter a valid Integer"); //Print out exception if prompted
        }
    }//end method

    /*compareNum() method
     * Compare the user's number to the one chosen by the computer.
     */
    public void compareNum (int numUser) {
        //Determine how the user's number relates to the chosen number
        //The user guesses correctly
        if (numUser == realNum) {
            results.setText("You win!"); //tell the user they won
            insert.setEnabled(false); //prevent the user from guessing
            secret.setVisible(true); //reveal the secret code
            realLabel.setText(Integer.toString(realNum));
            Game g = new Game();
            g.setCode("D");
        }
        //The user's number is too big
        else if (numUser > realNum) {
            results.setText("Too big"); //The number is larger than the actual number
            //If the number is larger than the previous number, it is out of range 
            if (previousUp < numUser && previousUp != 0) {
                results.setText("Out of range"); //tell the user the number is out of range
            }
            else {
                upper.setText(Integer.toString(numUser)); //The guess is placed on the upper side
                previousUp = numUser; //the current number becomes the previous number
            }
        }
        //The user's number is too small
        else if (numUser < realNum) {
            results.setText("Too small"); //The number is larger than the actual number
            //If the number is smaller than the previous number, it is out of range 
            if (previousDown > numUser && previousDown != 0) {
                results.setText("Out of range"); //tell the user the number is out of range
            }
            else {
                lower.setText(Integer.toString(numUser)); //The guess is placed on the upper side
                previousDown = numUser; //the current number becomes the previous number
            }
        }

        numTries++; //increase the number of tries by one
        displayTries.setText(Integer.toString(numTries)); //update the number of tries the user has

        //If the user guessed 10 or more times, the user loses
        if (numTries >= 10 && !(results.getText().equals("You win!"))) {
            results.setText("You lost. Press BACK to try again."); //tell the user they lost
            insert.setEnabled(false); //prevent the user from guessing
        }
    }//end method
}//end class
