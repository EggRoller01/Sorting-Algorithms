package sorting;
import sorting.utils.ArrayUtils;

public class BubbleSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        // ArrayUtils.printArray(array);

        for (int i=0; i<n; i++) {
            swapped = false;
            for (int j=0; j<n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    ArrayUtils.swap(array, j, j+1);
                    swapped = true;
                    // ArrayUtils.printArray(array);
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}

