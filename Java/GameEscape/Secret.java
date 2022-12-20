import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.*;

public class Secret extends JFrame{
    JTextField insert = new JTextField(""); // The text area the user will use to type in the password
    JLabel message = new JLabel("Enter the password shown in the menu (in order): "); //add a label to display the prompt
    JLabel title = new JLabel("??Secret??"); // display the title of the game
    JButton back = new JButton("Back"); //The back button
    JLabel win = new JLabel("You win!"); //Display the win message
    JLabel timeTaken = new JLabel ("You did not activate the timer"); //Only shown when the user did not use the timer
    //Thank the user for playing the game
    JTextArea thanks = new JTextArea ("Thanks for playing! You can now play the games seperately or beat your time! \n Resart the program to try again!");
    
    String password = "XJBD"; //the real password to win
    String formattedTime = ""; //The time the user took to complete the game

    /*default constructor
     * Set up the frame and the components on it. 
     */
    public Secret (String theTime) {
        setTitle("Secret"); //name the frame
        getContentPane().setLayout(null);
        setVisible(true); //make the frame visible to the user
        setBounds(0,0,600,400); //size the frame
        getContentPane().setBackground(new Color(100, 200, 150)); // set the background color of the frame
        formattedTime = theTime; //get the time the user took

        //Add a title
        title.setBounds(225, 0, 300, 50); // size and place the label
        title.setFont(new Font("Serif", Font.BOLD, 30)); // change the font to make it bigger
        title.setForeground(Color.WHITE); // change the text colour
        add(title); // add the title to the frame

        //Label to prompt for the password
        message.setBounds(25, 50, 290, 30); //size and place the message
        message.setBorder(BorderFactory.createLineBorder(Color.white)); //add a border to the message
        message.setForeground(Color.WHITE); // change the text colour
        add(message); //add the message to the frame

        //Set up the textbox
        insert.setBounds(325, 50, 100, 30); //size and place the textbox
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickevent) {
                String userInput = ""; //the user's input to guess the password
                userInput = insert.getText(); //get the text the user typed
                checkPassword(userInput); //check the password to see if it matches
            }
          });
        add(insert); //add the textbox to the frame

        win.setBounds(210, 100, 150, 50); //size and place the label
        win.setBorder(BorderFactory.createLineBorder(Color.white)); //add a border to the message
        win.setFont(new Font("Serif", Font.BOLD, 35)); // change the font to make it bigger
        win.setVisible(false); //do not let the user see the message
        win.setForeground(Color.WHITE); // change the text colour
        add(win); //add the label to the frame

        timeTaken.setBounds(170, 160, 260, 50); //size and place the label
        timeTaken.setBorder(BorderFactory.createLineBorder(Color.white)); //add a border to the message
        timeTaken.setFont(new Font("Serif", Font.BOLD, 15)); // change the font to make it bigger
        timeTaken.setVisible(false); //do not let the user see the message
        timeTaken.setForeground(Color.WHITE); // change the text colour
        add(timeTaken); //add the label to the frame

        thanks.setBounds(10, 230, 550, 50); //size and place the label
        thanks.setBorder(BorderFactory.createLineBorder(Color.black)); //add a border to the message
        //thanks.setFont(new Font("Serif", Font.BOLD, 20)); // change the font to make it bigger
        thanks.setEditable(false); //do not allow the user to edit
        thanks.setLineWrap(true); //allow the text to wrap
        thanks.setBackground(new Color (100, 200, 150)); //set the background colour of the frame
        thanks.setForeground(Color.WHITE); // change the text colour
        thanks.setBorder(BorderFactory.createLineBorder(Color.white)); // put a black border around the label
        thanks.setFont(new Font("Serif", Font.BOLD, 15)); //change the font size of the title
        thanks.setVisible(false); //do not let the user see the message
        add(thanks); //add the label to the frame

        back.setBounds(225, 290, 100, 50); //size and place the back button
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickevent) {
                dispose(); //close the frame
            }
          }); //add an action listener to the button when pressed
        add(back); //add the back button to the frame

        /*ImageIcon icon = new ImageIcon("testLand.png");
        add(new JLabel(icon));
        https://stackhowto.com/how-to-display-an-image-on-jframe-in-java-swing/ */
    }//end constructor

    /*checkPassword() method
     * Check if the user entered the correct password
     */
    public void checkPassword (String userInput) {
        /*Check if the user typed in the right password by checking the user's input
         * to the real password
         */
        //The user typed in the correct password
        if (userInput.equalsIgnoreCase(password)) {
            reveal(); //reveal the secret
            insert.setEnabled(false); //prevent the user from guessing
        }
        //The user typed in the wrong password
        else {
            message.setText("You entered the wrong password. Try again:"); //prompt the user to enter the right one
        }
    }//end method

    /*reveal() method
     * Reveal the winning message
     */
    public void reveal () {
        
        win.setVisible(true); //reveal the winning message
        Game end = new Game(); //instantiate the Game class to check if the user used the timer

        //If the user used the timer, display the time it took for them to complete the game
        if (end.timerOn == true) {
            timeTaken.setText("You took: " + formattedTime + " to complete the game!");
        }

        //Reveal the time taken (or that the timer was not activated) to the user
        timeTaken.setVisible(true);
        thanks.setVisible(true); //display the thank you message
    }//end method
}//end class
