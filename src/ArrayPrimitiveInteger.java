package src;
import java.util.Random;
public class ArrayPrimitiveInteger {
    public static void main(String[] args) {

        Random random = new Random(); // Create a new random number generator

        int[] array = new int[10]; // Create a new array of size 10

        for (int i = 0; i < array.length; i++) { // Fill the array with random numbers
            array[i] = random.nextInt(10); // Random integer between 0 and 10
        }

        for (int integer : array) { // Loop over the array and print out each element
            System.out.print(integer + " "); // Print out the element
        }
    }
}

