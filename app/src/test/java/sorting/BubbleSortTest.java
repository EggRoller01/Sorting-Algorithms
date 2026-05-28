package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;


public class BubbleSortTest {

    private SortingAlgorithm sorter;

    @BeforeEach
    public void setUp(){
        sorter = new BubbleSort();
    }

    @Test
    void testMixedArray() {
        // 1. Arrange 
        int[] input = {5, 1, 4, 2, 8};
        int[] expectedOutput = {1, 2, 4, 5, 8};

        // 2. Act 
        sorter.sort(input);

        // 3. Assert 
        assertArrayEquals(expectedOutput, input, "Array was not sorted correctly! \n\tExpected: " + Arrays.toString(expectedOutput) + " \n\tGot: " + Arrays.toString(input) + "\n");
    }

    @Test
    void testAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expectedOutput = {1, 2, 3, 4, 5};

        sorter.sort(input);

        assertArrayEquals(expectedOutput, input, "Code modified an already sorted array! \n\tExpected: " + Arrays.toString(expectedOutput) + " \n\tGot: " + Arrays.toString(input) + "\n");
    }

    @Test
    void testEmptyArray() {
        int[] input = {};
        int[] expectedOutput = {};

        sorter.sort(input);

        assertArrayEquals(expectedOutput, input, "Algorithm failed to handle an empty array!");
    }

    @Test
    void testSingleElementArray() {
        int[] input = {42};
        int[] expectedOutput = {42};

        sorter.sort(input);

        assertArrayEquals(expectedOutput, input, "Algorithm failed with only one element!");
    }

    @Test
    void testWithDuplicates() {
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expectedOutput = {1, 1, 2, 3, 4, 5, 5, 6, 9};

        sorter.sort(input);

        assertArrayEquals(expectedOutput, input, "Algorithm did not handle duplicates correctly! \n\tExpected: " + Arrays.toString(expectedOutput) + " \n\tGot: " + Arrays.toString(input) + "\n");
    }

    @Test
    void testWithNegativeNumbers() {
        int[] input = {-3, -1, -4, 0, 2};
        int[] expectedOutput = {-4, -3, -1, 0, 2};

        sorter.sort(input);

        assertArrayEquals(expectedOutput, input, "Algorithm failed to sort negative numbers! \n\tExpected: " + Arrays.toString(expectedOutput) + " \n\tGot: " + Arrays.toString(input) + "\n");
    }
}
