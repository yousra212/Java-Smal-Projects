package src;

import java.util.Random;

public class ArrayNonPrimitiveInteger {

    public static void main(String[] args) { // Main method
        int maxSize = 10; // Maximum size of array
        Integer[] randomArray = new Integer[maxSize]; // Create new array

        // Fill array with random integers
        Random random = new Random(); // Create a new random number generator
        for (int i = 0; i < randomArray.length; i++) { //   Loop over array
            randomArray[i] = random.nextInt(10); // Random integer between 0 and 10
            System.out.println(randomArray[i] + " "); // Print out the element
        }
        System.out.println(); // Print new line

        // Loop over array and divide each integer by the previous integer
        for (int i = 0; i < randomArray.length; i++) { //  Loop over array
            try {
                if (i == 0) {// If first integer
                    System.out.println(randomArray[i]); // Print first integer
                } else {
                    Integer result = randomArray[i] / randomArray[i - 1]; // Divide current integer by previous integer
                    System.out.println(result + " "); // Print result
                }
            } catch (ArithmeticException e) { // Catch division by zero
                System.out.println("Error: Division by zero"); // Print error message
            }
        }
    }
}