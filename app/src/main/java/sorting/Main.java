package sorting;

import sorting.benchmark.DataGenerator;
import sorting.benchmark.PerformanceRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        // File paths for our data and results
        String dataFile = "benchmark_data.csv";
        String resultsFile = "benchmark_results.csv";
        // int[] testSizes = {1000, 5000, 10000, 20000, 50000}; 
        // int[] testSizes = {10, 100, 1000, 10000, 100_000}; 
        int[] testSizes = IntStream.iterate(1000, i -> i <= 100_000, i -> i + 1000).toArray();

        // 1. Generate and load test data
        DataGenerator.createBenchmarkData(testSizes, dataFile);
        List<int[]> testSuite = DataGenerator.loadArraysFromFile(dataFile);

        // 2. Prepare the list of algorithms to test
        List<SortingAlgorithm> algorithmsToTest = new ArrayList<>();
        
        // Add BubbleSort (Later you can easily add QuickSort, MergeSort here)
        algorithmsToTest.add(new QuickSort());
        algorithmsToTest.add(new BubbleSort());
        algorithmsToTest.add(new SelectionSort());
        

        // 3. Run the complete benchmark suite!
        PerformanceRunner.runBenchmark(algorithmsToTest, testSuite, resultsFile);

        System.out.println("--- All Benchmarks Complete! ---");
        System.out.println("Results saved to: " + resultsFile);;
    }
}