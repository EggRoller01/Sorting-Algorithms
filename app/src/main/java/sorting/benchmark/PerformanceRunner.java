package sorting.benchmark;

import sorting.SortingAlgorithm;
import sorting.utils.ArrayUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class PerformanceRunner {

    /**
     * Runs a full benchmark suite for multiple algorithms against multiple datasets.
     * Initializes the CSV file, writes the header, and runs the tests.
     */
    public static void runBenchmark(List<SortingAlgorithm> algorithms, List<int[]> testData, String resultsFilePath) {
        System.out.println("\n--- Starting Benchmark Suite ---");

        try (PrintWriter writer = new PrintWriter(new FileWriter(resultsFilePath, false))) {
            writer.println("Algorithm,ArraySize,Time_ms");
        } catch (IOException e) {
            System.err.println("Failed to initialize results file: " + e.getMessage());
            return; // Abort if we can't write to the file
        }

        for (SortingAlgorithm algorithm : algorithms) {
            String algorithmName = algorithm.getClass().getSimpleName();
            System.out.println("Running benchmark for " + algorithmName);

            for (int[] data : testData) {
                long timeInMs = measureSortingTime(algorithm, data);
                exportResultToCSV(algorithmName, data.length, timeInMs, resultsFilePath);
                // System.out.println("  Sorted array of size " + data.length + " in " + timeInMs + " ms");
            }
            System.out.println("Finished benchmark for " + algorithmName + "!\n");
        }
    }
    /**
     * Runs a sorting algorithm on a given array, verifies the result, 
     * and returns the execution time in milliseconds.
     */
    private static long measureSortingTime(SortingAlgorithm algorithm, int[] arrayToSort) {
        // Create a copy of the array so we don't modify the original test data
        int[] copy = Arrays.copyOf(arrayToSort, arrayToSort.length);

        // Measure running time of the sorting algorithm
        long startTime = System.nanoTime();
        algorithm.sort(copy);
        long endTime = System.nanoTime();

        // Verify that the array is actually sorted
        if (!ArrayUtils.isSorted(copy)) {
            throw new IllegalStateException("Algorithm failed! Array is not sorted.");
        }

        // Return result in ms
        return (endTime - startTime) / 1_000_000;
    }

    /**
     * Appends a benchmark result to a CSV file.
     * Assumes the file and header are already created.
     */
    private static void exportResultToCSV(String algorithmName, int arraySize, long timeInMs, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            // Write the data row
            writer.println(algorithmName + "," + arraySize + "," + timeInMs);
            
        } catch (IOException e) {
            System.err.println("Failed to write to CSV: " + e.getMessage());
        }
    }

}

