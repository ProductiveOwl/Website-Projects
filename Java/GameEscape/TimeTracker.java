import javax.swing.*;
/*import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class*/
import java.lang.Thread;

public class TimeTracker extends Thread {
  /*static LocalDateTime i = LocalDateTime.now();
  // static Instant j;
  static LocalDateTime j = LocalDateTime.now();
  //static JLabel time = new JLabel("Its a label");
  static Duration d = Duration.between(i, j);*/
  static JLabel secondsPassed;
  static int iter = 1;
  static int totalSeconds = 0;
  static boolean continueTimer = true;
  static String formatted;

    /*default constructor*/
    public TimeTracker (JLabel displayTime) {
        secondsPassed = displayTime;
    }//end constructor
  
    public void run() {
        try {
            // Displaying the thread that is running
            //System.out.println("Thread " + Thread.currentThread().getId() + " is running");
            while (continueTimer) {
                // it will sleep the main thread for 1 sec
                // ,each time the for loop runs
                //Thread.sleep(1000);

                /*System.out.println(iter);
                j = LocalDateTime.now();
                d = Duration.between(i, j);
                
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedDate2 = j.format(myFormatObj);
                System.out.println("After formatting: " + formattedDate2);
                System.out.println("Inbetween: " + d.getSeconds());
                // printing the value of the variable
                //secondsPassed.setText("Inbetween " + d.getSeconds());
                formatTime();
                iter++;*/

                formatTime(iter);
                iter++;
                Thread.sleep(1000);
            }
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }//end run() method

    /*Properly format the time into minutes and seconds based on the seconds that have passed */
    public void formatTime (int i) {
        int numMin = 0;
        int numSec = 0;
        //int totalSeconds = (int)(d.getSeconds());
        totalSeconds = i;
        formatted = " ";

        if (totalSeconds % 60 != 0) { //There are seconds
            numMin = totalSeconds / 60;
            numSec = totalSeconds % 60;

            if (numSec % 10 == 0) { //zero is the last digit
                formatted = Integer.toString(numMin) + ":" + Integer.toString(numSec) + "";
            }
            else if (numSec < 10) {
                formatted = Integer.toString(numMin) + ":0" + Integer.toString(numSec);
            }
            else { //a number is the last digit
                formatted = Integer.toString(numMin) + ":" + Integer.toString(numSec);
            }
        }
        else { //Only minutes
            numMin = totalSeconds / 60;
            formatted = Integer.toString(numMin) + ":00";
        }
        secondsPassed.setText(formatted);
    }//end method

    public void stopTimer () {
        continueTimer = false;
    }

    public void reactivateTimer (JLabel theTime) {
        continueTimer = true;
        TimeTracker x = new TimeTracker(theTime);
        x.start();
    }

    public String getTotalTime () {
        return formatted;
    }
}//end class
