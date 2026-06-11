package sorting;
import sorting.utils.ArrayUtils;

public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        //ArrayUtils.printArray(array);
        
        quickSort(array, 0, n-1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {

            int mid = partition(array, left, right);

            quickSort(array, left, mid-1);
            quickSort(array, mid+1, right);

        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;

        for (int j=left; j<=right-1; j++) {
            if (array[j] < pivot) {
                i++;
                ArrayUtils.swap(array, i, j);
                // ArrayUtils.printArray(array);
            }
        }
        ArrayUtils.swap(array, i+1, right);
        //ArrayUtils.printArray(array);
        return i+1;
    }
}

