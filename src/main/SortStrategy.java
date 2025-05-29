package main;

public interface SortStrategy {
    /**
     * Sorts the array using the specific algorithm
     *
     * @param array the array to sort
     * @param repaintCallback a Runnable to call whenever a visual update is needed
     * @throws InterruptedException if the sorting is interrupted (example by thread stop)
     */
	void sort(int[] array, Runnable repaintCallback, java.util.function.IntConsumer playNote) throws InterruptedException;
}
