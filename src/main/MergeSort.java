package main;

public class MergeSort implements SortStrategy {

    @Override
    public void sort(int[] array, Runnable repaintCallback) throws InterruptedException {
        mergeSort(array, 0, array.length - 1, repaintCallback);
    }

    private void mergeSort(int[] array, int left, int right, Runnable repaintCallback) throws InterruptedException {
        if (left < right) {
            int middle = (left + right) / 2;

            // Sort left half
            mergeSort(array, left, middle, repaintCallback);

            // Sort right half
            mergeSort(array, middle + 1, right, repaintCallback);

            // Merge the two halves
            merge(array, left, middle, right, repaintCallback);
        }
    }

    private void merge(int[] array, int left, int middle, int right, Runnable repaintCallback) throws InterruptedException {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data into temp arrays
        for (int i = 0; i < n1; i++) leftArr[i] = array[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = array[middle + 1 + j];

        int i = 0, j = 0;
        int k = left;

        // Merge the arrays back into array[left...right]
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i++];
            } else {
                array[k] = rightArr[j++];
            }
            repaintCallback.run();
            Thread.sleep(10);
            k++;
        }

        // Copy remaining elements of leftArr[]
        while (i < n1) {
            array[k++] = leftArr[i++];
            repaintCallback.run();
            Thread.sleep(10);
        }

        // Copy remaining elements of rightArr[]
        while (j < n2) {
            array[k++] = rightArr[j++];
            repaintCallback.run();
            Thread.sleep(10);
        }
    }
}

