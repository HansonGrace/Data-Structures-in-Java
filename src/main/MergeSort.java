package main;

import java.util.function.IntConsumer;

public class MergeSort implements SortStrategy {

    @Override
    public void sort(int[] array, Runnable repaintCallback, IntConsumer playNote) throws InterruptedException {
        mergeSort(array, 0, array.length - 1, repaintCallback, playNote);
    }

    private void mergeSort(int[] array, int left, int right, Runnable repaintCallback, IntConsumer playNote) throws InterruptedException {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid, repaintCallback, playNote);
            mergeSort(array, mid + 1, right, repaintCallback, playNote);
            merge(array, left, mid, right, repaintCallback, playNote);
        }
    }

    private void merge(int[] array, int left, int mid, int right, Runnable repaintCallback, IntConsumer playNote) throws InterruptedException {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data
        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Merge the temp arrays
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i++];
            } else {
                array[k] = R[j++];
            }
            repaintCallback.run();
            playNote.accept(array[k]);
            Thread.sleep(10);
            k++;
        }

        // Copy remaining elements of L[]
        while (i < n1) {
            array[k] = L[i++];
            repaintCallback.run();
            playNote.accept(array[k]);
            Thread.sleep(10);
            k++;
        }

        // Copy remaining elements of R[]
        while (j < n2) {
            array[k] = R[j++];
            repaintCallback.run();
            playNote.accept(array[k]);
            Thread.sleep(10);
            k++;
        }
    }
}
