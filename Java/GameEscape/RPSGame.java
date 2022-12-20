import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RPSGame implements ActionListener{
    JFrame frame = new JFrame(); //create the JFrame where everything will be placed
    String playerChoice = ""; //the player's choice 
    String compChoice = ""; //the computer's choice
    int numWins = 0; //the number of wins the player has

    //Greet the user and explain the game
    JTextArea inst = new JTextArea (" Welcome to Rock, Paper, Scissors! Win 10 Games against the computer to recieve your secret word. Both you and the computer will choose \n either rock, paper, or scissors. Depending on the following outcomes: \n Rock > Scissors \n Scissors > Paper \n Paper > Rock \n a point will be given. Click on a button to begin!");
    JLabel compReveal = new JLabel ("Computer Choice"); //display the computer's choice
    JLabel results = new JLabel ("Results"); //display the results of the game
    JLabel wins = new JLabel ("0"); //display the amount of wins
    JLabel secret = new JLabel ("Secret Letter: J"); //display the secret letter
    JLabel title = new JLabel("Rock Paper Scissors!"); //display the title

    JButton rock = new JButton ("Rock"); //create the rock button
    JButton paper = new JButton ("Paper"); //create the paper button
    JButton scissors = new JButton ("Scissors"); //create the scissors button
    JButton back = new JButton("Back"); //create the back button

    /* default constructor
     * This will set up the frame and all the buttons and labels the user will see
     */
    public RPSGame() {
        frame.setTitle("Rock Paper Scissors"); //Set the title of the frame
        frame.getContentPane().setLayout(null);
        frame.setVisible(true); //allow the user to see the frame
        frame.setBounds(0,0,800,500); //size and place the frame
        frame.getContentPane().setBackground(new Color (100, 200, 150)); //set the background colour

        //Add the title to the game
        title.setBounds(200, 0, 800, 50);
        title.setFont(new Font("Serif", Font.BOLD, 30)); //change the font size of the title
        title.setForeground(Color.WHITE); //change the title colour to white
        frame.add(title); //add the title to the frame

        //The instructions of the game
        inst.setBounds(5, 50, 775, 110); //size and place the label
        inst.setBackground(new Color (100, 200, 150)); //set the background colour of the frame
        inst.setEditable(false); //do not allow the user to edit
        inst.setLineWrap(true); //allow the text to wrap
        inst.setForeground(Color.WHITE); // change the text colour
        inst.setBorder(BorderFactory.createLineBorder(Color.white)); // put a black border around the label
        inst.setFont(new Font("Serif", Font.BOLD, 13)); //change the font size of the title
        frame.add(inst); //add the instructions to the frame

        //Declare winner
        results.setBounds(360, 190, 300, 50); //size and place the label
        results.setBorder(BorderFactory.createLineBorder(Color.white)); 
        results.setFont(new Font("Serif", Font.BOLD, 25)); //change the font
        results.setForeground(Color.WHITE); //change the title colour to white
        frame.add(results); //add the results label to the frame

        //Display the number of wins
        wins.setBounds(410, 400, 50, 30); //size and place the label
        wins.setForeground(Color.WHITE); //change the text colour to white
        wins.setFont(new Font("Serif", Font.BOLD, 25)); //change the font
        frame.add(wins); //add the label to the frame

        //Reveal the computer's choice
        compReveal.setBounds(30, 200, 290, 30);
        compReveal.setBorder(BorderFactory.createLineBorder(Color.white));
        compReveal.setFont(new Font("Serif", Font.BOLD, 20)); //change the font
        compReveal.setForeground(Color.WHITE); //change the text colour to white
        frame.add(compReveal); //add the label to the frame

        //Display the rock button
        rock.setBounds(80, 250, 100, 100); //size and place the button
        rock.addActionListener(this);
        frame.add(rock); //add the button to the frame

        //Display the paper button
        paper.setBounds(230, 250, 100, 100); //size and place the button
        paper.addActionListener(this);
        frame.add(paper); //add the button to the frame

        //Display the scissors button
        scissors.setBounds(380, 250, 100, 100); //size and place the button
        scissors.addActionListener(this);
        frame.add(scissors); //add the button to the frame

        //Display the back button
        back.setBounds(230, 370, 100, 50); //size and place the button
        back.addActionListener(this);
        frame.add(back); //add the button to the frame

        //Display the secret letter
        secret.setBounds(220, 430, 600, 20); //size and place the label
        frame.add(secret); //add the label to the frame
        secret.setVisible(false); //user cannot see the letter
        secret.setForeground(Color.WHITE); //change the text colour to white
        secret.setFont(new Font("Serif", Font.BOLD, 20)); //change the font

        addLabels(); //add more labels to the frame
    }//end constructor

    /*addLabels() method
     * Adds labels to the frame to make the interface clearer to the reader
    */
    public void addLabels() {
        JLabel numWins = new JLabel("Your wins: "); //label to tell the user the number of wins
        numWins.setBounds(380, 370, 100, 20); //size and place the label
        numWins.setForeground(Color.WHITE); //change the text colour to white
        numWins.setFont(new Font("Serif", Font.BOLD, 20)); //change the font
        frame.add(numWins); //add the label to the frame
    }//end method

    /*actionPerformed() method
     * Overide the actionPerformed() method to activate the buttons 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose(); //close the frame
        }
        else if (e.getSource() == rock) {
            playerChoice = "r"; //the player chose rock
        }
        else if (e.getSource() == paper) {
            playerChoice = "p"; //the player chose scissors
        }
        else if (e.getSource() == scissors) {
            playerChoice = "s"; //the player chose scissors
        }
        findWinner(); //check to see if there is a winner
    }//end method

    /*findWinner() method
     * Check the board to see if there is a winner. 
     */
    public void findWinner() {
        compChoice = compPlay(); //get the computer's choice 
        //Display the results of the game
        //All the ties
        if (playerChoice.equals("s") && compChoice.equals("s")) { //Both choose scissors
            results.setText("It's a tie!"); 
        }
        else if (playerChoice.equals("r") && compChoice.equals("r")) { //Both choose rocks
            results.setText("It's a tie!");
        }
        else if (playerChoice.equals("p") && compChoice.equals("p")) { //Both choose paper
            results.setText("It's a tie!");
        }
        //The player plays rock
        else if (playerChoice.equals("r") && compChoice.equals("p")) { //Rock vs paper
            results.setText("You lost.");
        }
        else if (playerChoice.equals("r") && compChoice.equals("s")) { //Rock vs scissors
            results.setText("You won!");
            numWins++; //add to the number of wins
        }
        //The player plays paper
        else if (playerChoice.equals("p") && compChoice.equals("r")) { //Paper vs rock
            results.setText("You won!");
            numWins++; //add to the number of wins
        }
        else if (playerChoice.equals("p") && compChoice.equals("s")) { //Paper vs scissors
            results.setText("You lost.");
        }
        //The player plays scissors
        else if (playerChoice.equals("s") && compChoice.equals("r")) { //Scissors vs rock
            results.setText("You lost.");
        }
        else if (playerChoice.equals("s") && compChoice.equals("p")) { //Scissors vs paper
            results.setText("You won!");
            numWins++; //add to the number of wins
        }

        wins.setText(Integer.toString(numWins)); //Display/Update the number of wins to the user

        //Once the user wins 10 times, reveal the secret word
        if (numWins >= 10) {
            secret.setVisible(true); //let the user see the secret letter
            Game g = new Game();
            g.setCode("J");
            results.setText("You have won 10 times!"); //display the winning message
        }
    }//end method

    /*compPlay() method
     * This method allows the computer to pick an option and return that value
     */
    public String compPlay() {
        String[] choices = {"r", "p", "s"}; //the choices the computer can pick
        int randIndex = 0; //the randomly generated index
        int max = 2; //the maximum index 
        int min = 0; //the minimum index
        String chosen = ""; //the element that is chosen randomly

        randIndex = (int)(Math.random() * (max - min + 1) + min); //randomly choose an index
        chosen = choices[randIndex]; //get the random element using the randome index

        //Display the computer's choice to the GUI
        //If the computer chose rock
        if (chosen == "r") {
            compReveal.setText("The computer chose ROCK"); //Display the computer's choice
        }
        //If the computer chose paper
        else if (chosen == "p") {
            compReveal.setText("The computer chose PAPER"); //Display the computer's choice
        }
        //If the computer chose scissors
        else {
            compReveal.setText("The computer chose SCISSORS"); //Display the computer's choice
        }
        return chosen; //return the computer's choice
    }//end method
}//end class
