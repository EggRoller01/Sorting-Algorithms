package sorting;


public class SelectionSortTest extends SortingAlgorithmTest {
    
    @Override
    protected SortingAlgorithm specifySorter() {
        return new SelectionSort();
    }
}
