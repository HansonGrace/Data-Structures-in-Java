package main;

public class BubbleSort implements SortStrategy {

	@Override
	public void sort(int[] array, Runnable repaintCallback) throws InterruptedException {
		
		int n = array.length;
		boolean swapped;
		
		
		for (int i = 0; i< n -1; i++) {
			swapped = false;
		
			//for loop to check elements and swap them
		for(int j = 0; j < n - i - 1; j++) {
			
			if(array[j] > array[j + 1]) {
				//swaps the elements using a temp var
				int temp = array[j];
				//move current index to the next
				array[j] = array[j + 1];
				array[j + 1] = temp;
				
				swapped = true;
				repaintCallback.run(); //update visually
				Thread.sleep(10); //slow down animation thread
				
			}
		}
		//optimization for best time complexity
		//O(n) early exit check
		//if !swapped array is sorted		
		if(!swapped) break;
		}
	}
}
