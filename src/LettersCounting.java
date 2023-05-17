package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LettersCounting {
    public static void main(String[] args) {
        // Replace "example.txt" with the path to your text file
        String filename = "src/input.txt"; // location of the file

        try {                                     // try catch block to handle the exception if any occurs while reading the file
            BufferedReader reader = new BufferedReader(new FileReader(filename));  // read the file
            String line = reader.readLine();    // read the line
            while (line != null) {                 // while loop to read the line until it is null
                // Count the appearance of letters in the current line
                int[] count = new int[26];       // array to store the count of each letter
                line = line.toLowerCase();       // convert the line to lower case to avoid case sensitivity
                for (int i = 0; i < line.length(); i++) {    // for loop to iterate through the line and count the letters
                    char c = line.charAt(i);        // store the character at the current index in the variable c
                    if (c >= 'a' && c <= 'z') {     // check if the character is a letter or not
                        count[c - 'a']++;         // increment the count of the letter by 1
                    }
                }
                // Print the letter counts for the current line
                for (int i = 0; i < 26; i++) {         // for loop to print the count of each letter in the line
                    System.out.print((char) (i + 'a') + ": " + count[i] + " ");      // print the letter and its count
                }
                System.out.println();                    // print a new line
                line = reader.readLine();                // read the next line
            }
            reader.close();                              // close the reader to avoid memory leaks
        } catch (IOException e) {                        // catch block to handle the exception if any occurs
            e.printStackTrace();                         // print the stack trace of the exception to the console
        }
    }
}
