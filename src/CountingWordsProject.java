package src;

import java.io.BufferedReader; // you need to import this to use BufferedReader class, the bufferedReader class is used to read text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CountingWordsProject {

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // File to read
        Map<String, Integer> wordCount = new HashMap<>(); // Map to store word counts

        String mostUsedWord = null; // Most used word
        int maxCount = 0; // Number of times most used word is used

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // Current line
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split line into words
                for (String word : words) { // Loop over words
                    if (wordCount.containsKey(word)) { // If word is already in map
                        int count = wordCount.get(word); // Get current count
                        wordCount.put(word, count + 1); // Update count
                    } else { // If word is not in map
                        wordCount.put(word, 1); // Add word to map
                    }
                    // Update most used word
                    if (wordCount.get(word) > maxCount) { // If word is used more times than current most used word
                        mostUsedWord = word; // Update most used word
                        maxCount = wordCount.get(word); // Update number of times most used word is used
                    }
                }
            }
        } catch (IOException e) { // Catch file reading errors
            System.out.println("Error reading file: " + e.getMessage()); // Print error message
        }

        // Print out word count overview
        System.out.println("Word count overview:"); // Print header

        for (String word : wordCount.keySet()) { // Loop over words
            int count = wordCount.get(word); // Get word count
            System.out.println(word + ": " + count); // Print word and count

        }

        // Print out most used word
        System.out.println("Most used word: " + mostUsedWord + " (" + maxCount + " times)"); // Print most used word
    }
}

