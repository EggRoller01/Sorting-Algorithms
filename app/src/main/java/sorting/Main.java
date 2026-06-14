package sorting;

import sorting.benchmark.DataGenerator;
import sorting.benchmark.PerformanceRunner;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // File paths for our data and results
        String dataFile = "benchmark_data.csv";
        String resultsFile = "benchmark_results.csv";

        // Generate and load test data
        // int[] testSizes = DataGenerator.createLinearTestSizes(100, 100_000, 100);
        int[] testSizes = DataGenerator.createExponentialTestSizes(1000, 100_000_000, 1.1);
        DataGenerator.createBenchmarkData(testSizes, dataFile);
        List<int[]> testSuite = DataGenerator.loadArraysFromFile(dataFile);

        // Prepare the list of algorithms to test
        List<SortingAlgorithm> algorithmsToTest = new ArrayList<SortingAlgorithm>();

        algorithmsToTest.add(new QuickSort());
        // algorithmsToTest.add(new BubbleSort());
        // algorithmsToTest.add(new SelectionSort());
        

        // Run the complete benchmark suite!
        PerformanceRunner.runBenchmark(algorithmsToTest, testSuite, resultsFile);

        System.out.println("--- All Benchmarks Complete! ---");
        System.out.println("Results saved to: " + resultsFile);;

        
    }
}