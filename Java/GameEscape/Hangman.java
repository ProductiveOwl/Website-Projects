import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File; //working with File
import java.io.FileNotFoundException;

public class Hangman {
    JFrame frame = new JFrame(); //create the frame
    JButton back = new JButton("Back"); //back button
    JTextArea inst = new JTextArea("Guess the hidden word below! Type in a letter and the program will tell you if the letter is present. Type in \n 7 wrong letters and you will lose the game. You will recieve your code once you complete the word."); //instructions
    
    JTextField insert = new JTextField(""); //text input for a letter
    JLabel strikes = new JLabel("0"); //Number of strikes
    JLabel inputMess = new JLabel ("Type a letter"); //message to check input
    JLabel wordToGuess = new JLabel (); //the unknown word the user has to guess
    JLabel secret = new JLabel("Secret Letter: B"); //the secret letter
    JLabel catLabel = new JLabel (); //This shows the user the category
    JLabel guessed = new JLabel("Guessed Letters"); //Shows the user the letters they guessed
    JLabel title = new JLabel("Hangman!"); //Display the title to the user
    
    String userLetter = ""; //the letter the user guessed
    String realWord = ""; //the real word the user needs to guess
    String allLettersGuessed = ""; //All the elements in the arrayList as a String
    int newStrikes = 0; //the number of strikes the user has (max 7)
    String[] realLetters; //create an array to hold the letters from the real word
    String[] letters; //create an array to hold letters from the unknown array

    String[][] allWords = new String[3][10]; //2D array that will hold all the categories and the words
    ArrayList<String> guessedLetters = new ArrayList<String>(); //an array list to track the letters guessed

    //Set up the frame with all of the buttons and text box
    public Hangman () {
        //Frame setup
        frame.setTitle("Hangman"); //set the name of the frame to "Hangman"
        frame.getContentPane().setLayout(null);
        frame.setVisible(true); //allow the user to see the frame
        frame.setBounds(0,0,600,500); //size the frame
        frame.getContentPane().setBackground(new Color (100, 200, 150)); //set the background color
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the title to the game
        title.setBounds(200, 0, 800, 50);
        title.setFont(new Font("Serif", Font.BOLD, 30)); //change the font size of the title
        title.setForeground(Color.WHITE); //change the title colour to white
        frame.add(title); //add the title to the frame

        //Set up the instructions
        inst.setBounds(5, 50, 575, 50); //size and place the instructions
        inst.setBackground(new Color (100, 200, 150)); //set the background colour of the frame
        inst.setEditable(false); //the user cannot edit the instructions
        inst.setLineWrap(true); //allow the text to wrap in the textfield
        inst.setForeground(Color.WHITE); // change the text colour
        inst.setBorder(BorderFactory.createLineBorder(Color.white)); // put a black border around the label
        inst.setFont(new Font("Serif", Font.BOLD, 13)); //change the font size of the instructions
        frame.add(inst); //add the instructions to the frame

        //Set up the panel for the word
        wordToGuess.setBounds(50, 200, 500, 100); //size and place the instructions
        wordToGuess.setBorder(BorderFactory.createLineBorder(Color.white)); //put a black border around the label
        wordToGuess.setFont(new Font("Serif", Font.BOLD, 25)); //change the font to make it bigger
        wordToGuess.setForeground(Color.white);
        frame.add(wordToGuess); //add the word to the frame

        //Display the category of the unknown word
        catLabel.setBounds(350, 120, 200, 50); //size and place the label
        catLabel.setBorder(BorderFactory.createLineBorder(Color.white)); //put a black border around the label
        catLabel.setFont(new Font("Serif", Font.BOLD, 20)); //change the font to make it bigger
        catLabel.setForeground(Color.white);
        frame.add(catLabel); //add the label to the frame

        //Set up the spot for incorrect letters and strikes
        strikes.setBounds(55, 390, 50, 50); //size and place the instructions
        strikes.setForeground(Color.white);
        strikes.setFont(new Font("Serif", Font.BOLD, 25)); //change the font to make it bigger
        frame.add(strikes); //add the strikes to the frame

        //Set up a spot to say correct, incorrect and an error message
        inputMess.setBounds(25, 120, 300, 50); //size and place the label
        inputMess.setBorder(BorderFactory.createLineBorder(Color.white)); //put a black border around the label
        inputMess.setFont(new Font("Serif", Font.BOLD, 20)); //change the font to make it bigger
        inputMess.setForeground(Color.white);
        frame.add(inputMess); //add the label to the frame

        //Set up an area to show the user the letters they guessed
        guessed.setBounds(380, 350, 200, 50); //size and place the label
        guessed.setBorder(BorderFactory.createLineBorder(Color.white)); //put a black border around the label
        guessed.setForeground(Color.white);
        guessed.setFont(new Font("Serif", Font.BOLD, 20)); //change the font to make it bigger
        frame.add(guessed); //add the label to the frame

        //Set up the textbox
        insert.setBounds(225, 320, 100, 30); //size and place the textbox
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickevent) {
                userLetter = insert.getText(); //get the letter the user types in
                checkInput(); //check if the user entered one letter
            }
          });
        frame.add(insert); //add the textbox to the frame
        
        //Set up the back button
        back.setBounds(225, 370, 100, 50); //size and place the back button
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickevent) {
                frame.dispose(); //close the game frame to allow the user to go back to the menu
            }
          });
        frame.add(back); //add the back button to the frame

        //Display the secret word
        secret.setBounds(225, 430, 600, 20); //size and place the secret label
        secret.setForeground(Color.white);
        secret.setFont(new Font("Serif", Font.BOLD, 20)); //change the font
        frame.add(secret); //add the label to the frame
        secret.setVisible(false); //user cannot see the letter yet

        displayUnkownWord(); //display the word the user needs to guess
        addLabels(); //add additional labels for clarity
    }//end constructor

    /*addLabels method
     * Add more labels to the frame to make the game clearer for the player
     */
    public void addLabels() {
        JLabel enterLetter = new JLabel("Enter a letter here: "); //Show the user where to enter a letter
        enterLetter.setBounds(110, 320, 150, 20); //size and place the label
        enterLetter.setForeground(Color.white);
        JLabel alreadyGuessed = new JLabel("Wrong Letters: "); //Show the user where the wrong letters are placed
        alreadyGuessed.setBounds(380, 320, 150, 20); //size and place the label
        alreadyGuessed.setFont(new Font("Serif", Font.BOLD, 20)); //change the font to make it bigger
        alreadyGuessed.setForeground(Color.white);
        JLabel numOfStrikes = new JLabel ("Number of Strikes: "); //Show the user where the number of strikes are displayed
        numOfStrikes.setBounds(25, 370, 150, 20); //size and place the label
        numOfStrikes.setFont(new Font("Serif", Font.BOLD, 15)); //change the font to make it bigger
        numOfStrikes.setForeground(Color.white);

        frame.add(enterLetter); //add the label to the frame
        frame.add(alreadyGuessed); //add the label to the frame
        frame.add(numOfStrikes); //add the label to the frame
    }

    /*checkInput() method
     * This method is used to check if the user entered a single character
    */
    public void checkInput () {
        //Check to see if the user entered more than one character
        //the user entered one character 
        if (userLetter.length() == 1) {
            //if the user entered a valid letter
            if (Character.isAlphabetic(userLetter.charAt(0))) {
                checkLetter(userLetter); //check if the letter is present in the word
            }
            //User did not enter a valid letter
            else {
                inputMess.setText("Enter a valid English letter");
            }
        }
        //The user entered more than one character
        else {
            inputMess.setText("Please enter one letter."); //tell the user to enter one letter
        }
    }//end method

    /*getWord()
     * The method will take a random word from the text file for the user to guess
     */
    public String getWord (String category) {
        String word = " "; //the word to find
        int min = 1; //the minimum index number
        int max = allWords[0].length - 1; //the length of the the fruits array minus 1 -> max index number
        int randIndex = 0; //the random index
        
        //Depending on the category chosen, it will choose a random word from the category
        //If the category is "Fruits"
        if (category.equals("Fruits")) {
            //get random fruit -> row 0
            randIndex = (int)(Math.random() * (max - min + 1) + min); //generate a random index number
            word = allWords[0][randIndex]; //get the random fruit

        }
        //If the category is "Cities"
        else if (category.equals("Cities")) {
            //get random city -> row 1
            randIndex = (int)(Math.random() * (max - min + 1) + min); //generate a random index number
            word = allWords[1][randIndex]; //get the random fruit
        }
        //If the category is "Clothing"
        else {
            //get random clothing item -> row 2
            randIndex = (int)(Math.random() * (max - min + 1) + min); //generate a random index number
            word = allWords[2][randIndex]; //get the random fruit
        }

        return word; //return the word the user will guess
    }//end method

    /*getCategory() method
     * Put all of the text files into a 2D array. Then choose a random category and return it.
     */
    public String getCategory() {
        String randCat = " "; //the randomely chosen category
        int row = 0; //the row number
        int iter = 0; //the iterator for the columns
        int min = 0; //the minimum row number
        int max = 2; //the maximum row number
        int randIndex = 0; //the randomly generated row index

        //Get all the information from the text files and add them to the allWords array
        try {
            File fruits = new File ("Fruits.txt"); //create an object
            Scanner s = new Scanner(fruits); //instantiate scanner
                
            //loop through every line in the text file and add it to the allWords array
            while (s.hasNextLine()) {
                String currWord = s.nextLine(); //get the line
                allWords[row][iter] = currWord; //get the current fruit and put it into the array
                iter++; //add one to the iterator
            }
            s.close(); //close the scanner

            iter = 0; //reset the iterator
            row = 1; //change row
            File cities = new File ("Cities.txt"); //create an object
            Scanner q = new Scanner(cities); //instantiate scanner
                
            //loop through every line in the text file and add it to the allWords array
            while (q.hasNextLine()) {
                String currWord = q.nextLine(); //get the line
                allWords[row][iter] = currWord; //get the current fruit and put it into the array
                iter++; //add one to the iterator
            }
            q.close(); //close the scanner

            iter = 0; //reset the iterator
            row = 2; //change the row
            File clothing = new File ("Clothing.txt"); //create an object
            Scanner t = new Scanner(clothing); //instantiate scanner
                
            //loop through every line in the text file and add it to the allWords array
            while (t.hasNextLine()) {
                String currWord = t.nextLine(); //get the line
                allWords[row][iter] = currWord; //get the current fruit and put it into the array
                iter++; //add one to the iterator
            }
            t.close(); //close the scanner
        }
        //If the file cannot be found
        catch (FileNotFoundException e) {
            System.out.println("Could not find the file."); //print out the exception
        }

        randIndex = (int)(Math.random() * (max - min + 1) + min); //generate a random index number
        randCat = allWords[randIndex][0]; //get the random category name from the first column
        catLabel.setText("Category: " + randCat); //Display the category to the user

        return randCat; //return the random category
    }//end method

    /*displayUnknownWord() method
     * This method will display the word with the appropriate number of dashes at the start of the game
     */
    public void displayUnkownWord() {
        String category = " "; //a random category that is chosen
        category = getCategory(); //Get a category from the text files
        realWord = getWord(category); //select a word from the category chosen
        String unknownDisplay = ""; //find the unkown word

        realLetters = new String [realWord.length()]; //size the realLetters array with the correct word

        //for loop to put the actual letters in the array
        for (int j = 0; j < realWord.length(); j++) {
            realLetters[j] = realWord.substring(j,j+1); //each character is an element added to the array
        }

        //Size the letters array
        letters = new String[realWord.length()];
        //for loop to put each individual character in an unknown array
        for (int i = 0; i < realWord.length(); i++) {

            //print a _ for every letter and " " for every space
            //if there is a space in the word to guess, add a space in the letters array
            if (realLetters[i].equals(" ")) {
                letters[i] = " ";
                unknownDisplay += " ";
            }
            //For every letter in the real word, add a "_" in the letters array
            else {
                letters[i] = "_";
                unknownDisplay += "_";
            }

            unknownDisplay += " "; //add as space inbetween each character to make it easier to read
        }

        wordToGuess.setText(unknownDisplay); //display the unknown word
        wordToGuess.setFont(new Font("Serif", Font.BOLD, 25)); //adjust the font size
    }//end method

    /*checkLetter() method
     * This method will check if the character the user entered is in the chosen word.
     * If not, the user gets a strike.
     */
    public void checkLetter(String letter) {
        boolean letterFound = false; //check if the letter the user guessed is found
        boolean alreadyGuessed = false; //check if the user already guessed the letter
        //check if the letter matches any from the array of the actual word
        for (int s = 0; s < realLetters.length; s++) {
            //If the user's letter is found in the word 
            if (realLetters[s].equalsIgnoreCase(letter)) {
                letterFound = true; //the letter is found
                break; //break out of the loop
            }
        }

        //check if the letter has been guessed already
        for (int k = 0; k < guessedLetters.size(); k++) {
            //If the user's letter matches a letter guessed already
            if (guessedLetters.get(k).equals(letter)) {
                alreadyGuessed = true; //user already guessed the letter
                break; //break out of the loop
            }
        }

        //Display the right message to the user if the letter is found
        //The letter is found
        if (letterFound && !alreadyGuessed) {
            letterFound(letter); //find where the letter appears in the word and display it to the user
        }
        //The letter is not found in the word
        else {
            //The user already guessed this letter
            if (alreadyGuessed) {
                inputMess.setText("You already guessed this letter"); //Tell the user they already guessed the letter
            }
            //The user did not guess this letter before
            else {
                guessedLetters.add(letter); //add the missed letter to the array
                allLettersGuessed = ""; //reset the string

                /*Display the letters the user guessed to the user by taking the elements in the guessedLetters
                 * array and adding them to a string. 
                */
                for (int x = 0; x < guessedLetters.size() - 1; x++) {
                    allLettersGuessed += guessedLetters.get(x) + ", "; //add all the missed letters (except the last one) to the string
                }
                allLettersGuessed += guessedLetters.get(guessedLetters.size() - 1); //add the last element of the array without a comma

                guessed.setText(allLettersGuessed); //display the updated missed letters

                inputMess.setText("Letter is not in the word."); //change text to say the letter is not found
                newStrikes++; //add a strike
                strikes.setText(Integer.toString(newStrikes)); //display the number of strikes to the user
            }

            //If the user has 7 strikes, the user loses the game
            if (newStrikes >= 7) {
                inputMess.setText("You lost. Press BACK to try again."); //Tell user to quit the program
                insert.setEnabled(false); //disable the text box so the user cannot type in another letter
                wordToGuess.setText(realWord); //reveal the real word
            }
        }
    }//end method

    //the letter the user found is in the actual word
    public void letterFound(String letter) {
        inputMess.setText(letter + " is in the word!"); //Tell the user the letter is in the word
        //Start a for-loop with:
        //search for the letter in the array
        for (int s = 0; s < realLetters.length; s++) {
            //If the user's letter matches a letter in the real word, add that letter to the letters array
            if (realLetters[s].equalsIgnoreCase(letter)) {
                letters[s] = realLetters[s]; //add the real letter to the letters array at the same index
            }
        }

        String updatedWord = ""; //the updated word with the guessed letters

        //display the new unknown array
        for (int g = 0; g < realLetters.length; g++) {
            //print a _ for every letter and " " for every space
            //If there is a space in the letters array, add a space to the updated word
            if (letters[g].equals(" ")) {
                updatedWord += " ";
            }
            //else, copy the existing array
            else {
                updatedWord += letters[g];
            }

            updatedWord += " "; //add a space to make the word easy to read
        }
        wordToGuess.setText(updatedWord); //display the new text with the guess letters added

        //If the letters revealed are the same as the real word and the user has less than 7 strikes, the user guessed the word
        if (Arrays.equals(letters, realLetters) && newStrikes < 7) {
            inputMess.setText("You win!"); //Tell user they won
            secret.setVisible(true); //reveal the secret letter
            insert.setEnabled(false); //disable the text box so the user cannot type in another letter
            Game g = new Game();
            g.setCode("B");
        }
    }//end method
}//end class
