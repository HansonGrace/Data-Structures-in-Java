package main;

import java.util.function.IntConsumer;

public class BubbleSort implements SortStrategy {

    @Override
    public void sort(int[] array, Runnable repaintCallback, IntConsumer playNote) throws InterruptedException {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) { // outer loop
            for (int j = 0; j < n - i - 1; j++) { // inner loop
                if (array[j] > array[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    repaintCallback.run(); // update screen
                    playNote.accept(array[j + 1]); // play sound (if not muted)
                    Thread.sleep(10); // animation delay
                }
            }
        }
    }
}

