package sorting;


public class BubbleSortTest extends SortingAlgorithmTest {
    
    @Override
    protected SortingAlgorithm specifySorter() {
        return new BubbleSort();
    }
}
