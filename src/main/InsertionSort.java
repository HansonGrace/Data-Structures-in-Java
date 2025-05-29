package main;

public class InsertionSort implements SortStrategy {
	
	@Override
	public void sort(int[] array, Runnable repaintCallback) throws InterruptedException {
		
		int n = array.length;
		
		for (int i = 1; i < n; i++) {
			
			//current element to be sorted
			int key = array[i]; 
			int j = i - 1;
			
			//move elements of array that are greater than key by 1 position
			while (j >= 0 && array[j] > key) {
				array[j+ 1] = array[j];
				j--;
				
				//calls to visualizer
				//repaint and pause for the animations
				repaintCallback.run();
				Thread.sleep(10);
			}
			
			array[j+1] = key;
			
			//call repaint to show inserted key
			repaintCallback.run();
			Thread.sleep(10);
		}
		
		
		
	}

}
