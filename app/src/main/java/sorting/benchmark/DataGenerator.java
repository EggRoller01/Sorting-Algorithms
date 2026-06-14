package sorting.benchmark;

import sorting.utils.ConsoleUtils;

import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;




public class DataGenerator {
    
    /**
     * Generates an array of the specified size filled with random integers.
     */
    private static int[] generateRandomArray(int size) {
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


    /**
     * Helper method to quickly count the number of lines in a file.
     * Used to set the max value for our progress bar.
     */
    private static int countLines(String filePath) {
        try {
            // Files.lines() returns a Stream, and .count() forces it to process the file.
            return (int) Files.lines(Paths.get(filePath)).count();
        } catch (IOException e) {
            System.err.println("Could not count lines in file: " + e.getMessage());
            return 0;
        }
    }

    public static List<int[]> loadArraysFromFile(String filePath) {
        System.out.println("Loading benchmark data from " + filePath);

        List<int[]> arrays = new ArrayList<>();
        
        // Get the total number of lines for the progress bar
        int totalLines = countLines(filePath);
        int currentLine = 0;

        if (totalLines == 0) {
            System.out.println("File is empty or could not be read.");
            return arrays;
        }

        ConsoleUtils.printProgressBar(currentLine, totalLines);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    arrays.add(new int[0]); 
                } else {
                    String[] stringValues = line.split(",");
                    int[] intArray = new int[stringValues.length];

                    for (int i = 0; i < stringValues.length; i++) {
                        intArray[i] = Integer.parseInt(stringValues[i].trim());
                    }
                    arrays.add(intArray);
                }
                
                // Update progress bar after processing the line
                currentLine++;
                ConsoleUtils.printProgressBar(currentLine, totalLines);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("\nError parsing integer from file: " + e.getMessage());
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

        int current = 0;
        int total = sizes.length;
        ConsoleUtils.printProgressBar(current, total);

        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            appendArrayToFile(array, filePath);

            current++;
            ConsoleUtils.printProgressBar(current, total);
        }
        System.out.println("Finished creating " + sizes.length + " benchmark data in " + filePath);
    }


    public static int[] createLinearTestSizes(int start, int end, int step) {
        return IntStream.iterate(start, i -> i <= end, i -> i + step).toArray();
    }

    public static int[] createExponentialTestSizes(int start, int end, double multiplier) {
        // Security check to prevent infinite loops or invalid input
        if (start <= 0 || multiplier <= 1) {
            throw new IllegalArgumentException("Start must be > 0 and multiplier must be > 1 for exponential growth.");
        }
        return IntStream.iterate(
                start, 
                i -> i <= end, 
                // ensure we always get a larger number to avoid infinite loops, even if multiplier is close to 1
                i -> Math.max(i + 1, (int) Math.round(i * multiplier))
        ).toArray();
    }
}
