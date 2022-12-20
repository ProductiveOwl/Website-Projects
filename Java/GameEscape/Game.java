import javax.swing.*;
/*import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.*;*/

public class Game {
    //static boolean hasWon = false; 
    static boolean timerOn = false; //check if the timer was used
    static String codeLetter = ""; //the code letter from the game that the user is playing
    static JLabel cText = new JLabel("?"); //the label that is shown on the main screen

    /*Constructor to get the label*/
    public Game (JLabel theLabel) {
        cText = theLabel;
    }//end constructor

    /*Default constructor */
    public Game () {
        codeLetter = "?"; //Set the code letter to "?"
    }//end constructor

    /*setCode() method
     * Once the user has won a game, the code will be updated 
     * in the Game class which the main method can access
     */
    public static void setCode(String theCode) {
        codeLetter = theCode;
        revealCode();
    }//end method

    /*revealCode() method
     * This method will set the text of the label to the main method. It will then reset the codeLetter
     */
    public static void revealCode() {
        cText.setText(codeLetter);
        codeLetter = "?";
    }//end method

    /*usedTimer() method
     * This method will be provoked by the main method 
     * to indicate that the user has started the timer
     */

    public static void usedTimer () {
        timerOn = true; //the user used the timer
    }//end method


}//end class
