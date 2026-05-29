package sorting.benchmark;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DataGenerator {
    
    /**
     * Generates an array of the specified size filled with random integers.
     */
    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size); // Random numbers between 0 and size-1
        }
        return array;
    }

    private static void appendArrayToFile(int[] array, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            if (array.length == 0) { writer.println(); return; }

            for (int i = 0; i < array.length - 1; i++) {
                writer.print(array[i] + ",");
            }
            writer.println(array[array.length - 1]); // Print the last element without a comma
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<int[]> loadArraysFromFile(String filePath) {
        System.out.println("Loading benchmark data from  " + filePath);

        List<int[]> arrays = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read the file line by line until we reach the end (null)
            while ((line = reader.readLine()) != null) {
                
                // Add an empty array for empty lines
                if (line.trim().isEmpty()) {
                    arrays.add(new int[0]); 
                    continue;
                }

                // Split the comma-separated string into an array of strings
                String[] stringValues = line.split(",");
                
                // Prepare an integer array of the exact same length
                int[] intArray = new int[stringValues.length];

                // Parse each string value back into an integer
                for (int i = 0; i < stringValues.length; i++) {
                    intArray[i] = Integer.parseInt(stringValues[i].trim());
                }

                // Add the finished array to our list of arrays
                arrays.add(intArray);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer from file: " + e.getMessage());
        }
        System.out.println("Loaded " + arrays.size() + " arrays from " + filePath);
        return arrays;
    }

    public static void createBenchmarkData(int[] sizes, String filePath) {
        System.out.println("Creating " + sizes.length + " benchmark data in " + filePath);
        try {
            new PrintWriter(filePath).close(); // Clear the file before writing
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            appendArrayToFile(array, filePath);
        }
        System.out.println("Finished creating " + sizes.length + " benchmark data in " + filePath);
    }
}
