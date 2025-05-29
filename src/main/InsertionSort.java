package main;

import java.util.function.IntConsumer;

public class InsertionSort implements SortStrategy {

    @Override
    public void sort(int[] array, Runnable repaintCallback, IntConsumer playNote) throws InterruptedException {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;

                repaintCallback.run();
                playNote.accept(array[j + 1]);
                Thread.sleep(10);
            }

            array[j + 1] = key;
            repaintCallback.run();
            playNote.accept(key);
            Thread.sleep(10);
        }
    }
}
