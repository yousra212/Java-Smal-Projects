package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LettersCountingPious {

    public static void main(String[] args) {
        String filePath = "src/input.txt";
        Map<Character, Integer> charCountMap = new HashMap<>();
        char mostFrequentCharacter = ' ';
        int maxCharCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char character : line.toCharArray()) {
                    if (Character.isLetter(character)) {
                        character = Character.toLowerCase(character);
                        if (charCountMap.containsKey(character)) {
                            int count = charCountMap.get(character);
                            charCountMap.put(character, count + 1);
                        } else {
                            charCountMap.put(character, 1);
                        }

                        if (charCountMap.get(character) > maxCharCount) {
                            mostFrequentCharacter = character;
                            maxCharCount = charCountMap.get(character);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Letter count overview:");
        for (char character : charCountMap.keySet()) {
            int count = charCountMap.get(character);
            System.out.println("'" + character + "': " + count);
        }

        // Print out most used letter
        System.out.println("Most used letter: '" + mostFrequentCharacter + "' (" + maxCharCount + " times)");
    }
}