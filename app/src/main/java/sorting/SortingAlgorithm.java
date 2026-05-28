package sorting;

public abstract class SortingAlgorithm {
 
    /**
     * Den abstrakte metode, som alle sorteringsalgoritmer skal implementere.
     * @param array Det array af heltal, der skal sorteres på stedet (in-place).
     */
    public abstract void sort(int[] array);

    /**
     * En fælles hjælpe-metode til at bytte om på to elementer i et array.
     * Da den er 'protected', kan alle underklasser genbruge den direkte.
     */
    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * En hjælpe-metode til nemt at udskrive et array (god til manuel visuel test).
     */
    public void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
