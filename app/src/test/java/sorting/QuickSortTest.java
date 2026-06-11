package sorting;

public class QuickSortTest extends SortingAlgorithmTest {
    
    @Override
    protected SortingAlgorithm specifySorter() {
        return new QuickSort();
    }
}
