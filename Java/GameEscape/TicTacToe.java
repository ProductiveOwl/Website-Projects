import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TicTacToe extends JFrame implements ActionListener {
    JButton back = new JButton("Back"); //The back button
    //The instructions to the user 
    JTextArea inst = new JTextArea("Match 3 boxes in a row, colomun or in a diagonal. Win against the computer to get your \n code. Click on a box to start!");
    JLabel secret = new JLabel ("Secret Letter: X"); //the secret letter
    JLabel whoWins = new JLabel("Click on a box!"); //display the winner
    JLabel title = new JLabel ("Tic Tac Toe!"); //the title of the game
    
    ArrayList<Integer> buttonsLi = new ArrayList<Integer>(); //an array list to track the buttons pressed
    JButton buttons[] = new JButton[9]; //an array of all the button objects
    int btnNum = 0; //the number on the buttons
    int playerTurns = 0; //the number of turns the player had
    boolean playerTurn = true; //check if it is the player's turn
    
    /*default constructor */
    TicTacToe() {
        prepareGUI(); //set up the GUI frame and components
        ticTacToeButtons(); //create the board
    }//end constructon
 
    /*prepareGUI() method
     * Set up the frame and the components on it (labels)
     */
    public void prepareGUI(){
        setTitle("Tic Tac Toe"); //set the name of the title to Tic Tac Toe
        getContentPane().setLayout(null); 
        setVisible(true); //Allow the user to see the frame
        setBounds(0,0,600,450); //size and place the frame
        getContentPane().setBackground(new Color (100, 200, 150)); //set the background colour of the frame

        //Add the title to the game
        title.setBounds(200, 0, 800, 50);
        title.setFont(new Font("Serif", Font.BOLD, 30)); //change the font size of the title
        title.setForeground(Color.WHITE); //change the title colour to white
        add(title); //add the title to the frame

        //The instructions of the game
        inst.setBounds(5, 50, 575, 40); //size and place the label
        inst.setEditable(false); //do not allow the user to edit
        inst.setLineWrap(true); //allow the text to wrap
        inst.setBackground(new Color (100, 200, 150)); //set the background colour of the frame
        inst.setForeground(Color.WHITE); // change the text colour
        inst.setBorder(BorderFactory.createLineBorder(Color.white)); // put a black border around the label
        inst.setFont(new Font("Serif", Font.BOLD, 15)); //change the font size of the title
        add(inst); //add the instructions to the frame

        secret.setBounds(200, 370, 600, 20); //size and place the secret letter
        secret.setForeground(Color.WHITE); //change the text colour to white
        secret.setFont(new Font("Serif", Font.BOLD, 20)); //change the font
        add(secret); //add the secret word to the frame
        secret.setVisible(false); //do not allow the user to see the letter

        whoWins.setBounds(25, 270, 575, 20); //size and place the winning message
        whoWins.setFont(new Font("Serif", Font.BOLD, 16));
        whoWins.setForeground(Color.white);
        add(whoWins); //add the label to the frame
    }//end method

    /*ticTacToeButtons() method
     * Create and activate all the buttons on the frame
     */
    public void ticTacToeButtons (){
        //create 9 buttons on the ticTacToe board and place them appropriately
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(Integer.toString(i)); //add the button object to the array
            
            buttonsLi.add(i); //add the number i to the arrayList

            //Place each button accordingly
            //For the first three buttons
            if (i < 3) {
                buttons[i].setBounds(50 + i*150, 100, 150, 50); //x, y, width, height
            }
            //For the middle three buttons
            else if (i < 6){
                buttons[i].setBounds(50 + (i - 3)*150, 150, 150, 50); //x, y, width, height
            }
            //for the last three buttons
            else {
                buttons[i].setBounds(50 + (i - 6)*150, 200, 150, 50); //x, y, width, height
            }
            
            add(buttons[i]); //add the button to the frame
            buttons[i].addActionListener(this); //add an action listener to the button when pressed
        }//end for-loop

        back.setBounds(225, 320, 100, 50); //size and place the back button
        back.addActionListener(this); //add an action listener to the button when pressed
        add(back); //add the back button to the frame
    }//end method
    
    /*computerTurn() method
     * The computer will randomely choose a button to push to simulate a second player
     */
    public void computerTurn() {
        int randIndex = 0; //the random index generated
        int randListNum = 0; //the randome number on the list of available buttons
        int arrayInd = 0; //the index of the randListNum in the buttons array
        int max = buttonsLi.size() - 1; //finding the maximum index number is one less than its size
        int min = 0; //the minimum index of an array

        randIndex = (int)(Math.random() * (max - min + 1) + min); //generate a random index number
        randListNum = buttonsLi.get(randIndex); //get the number found from the random index

        //loop through the button list to find where the randListNum matches
        for (int j = 0; j < buttons.length; j++) {
            //When a match is found
            if (buttons[j].getText().equals(Integer.toString(randListNum))) {
                arrayInd = j; //index found in the buttons array
            }
        }
        buttons[arrayInd].doClick(); //find index num, access the object to click it
    }//end method
 
    /*actionPerformed() method
     * Override the method to find the button pressed and determine the player that clicked it
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Changing Background Color
        //frame.getContentPane().setBackground(Color.pink);
        //btnNum = Arrays.binarySearch(buttons, e.getSource());

        //If the user clicks the back button
        if (e.getSource() == back) {
            dispose(); //close the game
        }
        //Clicked button "0"
        else if (e.getSource() == buttons[0]) {
            btnNum = 0;
        }
        //Clicked button "1"
        else if (e.getSource() == buttons[1]){
            btnNum = 1;
        }
        //Clicked button "2"
        else if (e.getSource() == buttons[2]){
            btnNum = 2;
        }
        //Clicked button "3"
        else if (e.getSource() == buttons[3]){
            btnNum = 3;
        }
        //Clicked button "4"
        else if (e.getSource() == buttons[4]){
            btnNum = 4;
        }
        //Clicked button "5"
        else if (e.getSource() == buttons[5]){
            btnNum = 5;
        }
        //Clicked button "6"
        else if (e.getSource() == buttons[6]){
            btnNum = 6;
        }
        //Clicked button "7"
        else if (e.getSource() == buttons[7]){
            btnNum = 7;
        }
        //Clicked button "8"
        else if (e.getSource() == buttons[8]){
            btnNum = 8;
        }
        buttonsLi.remove(new Integer(btnNum)); //remove the button clicked from the list of possible buttons to press
        //btnNum = buttonsLi.indexOf(e.getSource());
        
        //If the player clicked the button
        if (playerTurn) {
            buttons[btnNum].setBackground(Color.BLUE); //change the background color of the button to blue
            buttons[btnNum].setForeground(Color.WHITE); //change the text color to white
            buttons[btnNum].setText("X"); //change the text on the button from a number to the letter "X"
            buttons[btnNum].setEnabled(false); //disable the button after clicking on it
            playerTurns++; //add one to the number of player turns
            playerTurn = false; //it is the computer's turn
        }
        
        //If the computer pressed the button
        else {
            buttons[btnNum].setBackground(Color.RED); //change the background colour of the button to red
            buttons[btnNum].setForeground(Color.WHITE); //change the text color to white
            buttons[btnNum].setText("O"); //change the text on the button from a number to the letter "O"
            buttons[btnNum].setEnabled(false); //disable the button after clicking on it
            playerTurn = true; // it is the player's turn
        }
        checkBoard(); //check the board to check if there is a winner

        //If it is not the player's turn and the player did not have 5 turns, allow the computer to play
        if (playerTurn == false && playerTurns != 5) {
            computerTurn(); //Allow the computer to press a random button
        }
 
    }//end method
    
    //Check the board to see if there is a winner by checking if the symbols on the buttons match
    public void checkBoard () {
        //Check Rows
        //First row
        if (buttons[0].getText().equals(buttons[1].getText()) && buttons[2].getText().equals(buttons[1].getText())) {
            findWinner(buttons[0].getText()); //find who won
        }
        //Second row
        else if (buttons[3].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[5].getText())) {
            findWinner(buttons[3].getText()); //find who won
        }
        //Third row
        else if (buttons[6].getText().equals(buttons[7].getText()) && buttons[7].getText().equals(buttons[8].getText())) {
            findWinner(buttons[6].getText()); //find who won
        }
        //Check Coloumns
        //First coloumn
        else if (buttons[0].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[6].getText())) {
            findWinner(buttons[0].getText()); //find who won
        }
        //Second coloumn
        else if (buttons[1].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[7].getText())) {
            findWinner(buttons[1].getText()); //find who won
        }
        //Third coloumn
        else if (buttons[2].getText().equals(buttons[5].getText()) && buttons[5].getText().equals(buttons[8].getText())) {
            findWinner(buttons[2].getText()); //find who won
        }
        //Check Diagonals
        //Top right to bottom left
        else if (buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText())) {
            findWinner(buttons[0].getText()); //find who won
        }
        //Top left to bottom right
        else if (buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText())) {
            findWinner(buttons[2].getText()); //find who won
        }
        //The player has gone 5 times and there is no winner, announce a tie
        else if (playerTurns == 5) {
            whoWins.setText("It is a tie!"); //Tell the user it is a tie
        }
    }//end method
    
    //Determines if the winner is the player or the computer
    public void findWinner (String mark) {
        if (mark.equals("X")) { //If the mark is an "X", it is the player's win
            whoWins.setText("The player wins!"); //announce the player has won
            secret.setVisible(true); //reveal the secret letter
            Game g = new Game();
            g.setCode("X");
        }
        //If the mark is an "O", the computer wins
        else {
            whoWins.setText("The computer wins. Press the back button to try again or play another game");  //announce the computer has won
        }
        //Iterate through the buttons array to disable all the buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }
    }//end method

    /*@Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }*/
}//end class
