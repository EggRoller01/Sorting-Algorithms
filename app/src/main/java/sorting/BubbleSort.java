package sorting;

public class BubbleSort extends SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        
        for (int i=0; i<n; i++) {
            // System.out.println(i);
            // printArray(array);
            swapped = false;
            for (int j=0; j<n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    swap(array, j, j+1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}

