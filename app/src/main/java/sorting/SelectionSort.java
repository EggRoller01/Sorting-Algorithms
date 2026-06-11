package sorting;
import sorting.utils.ArrayUtils;

public class SelectionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        int minIndex;

        //ArrayUtils.printArray(array);
        
        for (int i=0; i<n-1; i++) {
            minIndex = i;
            for (int j=i+1; j<n; j++) {
                
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            
            ArrayUtils.swap(array, i, minIndex);
            //ArrayUtils.printArray(array);
            
        }
    }
}

