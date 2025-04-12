package model;

public interface DataTable extends Table {
	public int capacity();

	// If table is full/at capacity, return true
	public default boolean isFull() {
		return size() == capacity();
	}

	/*
	 * Avoids integer division error by casting size and capacity to double before
	 * dividing
	 */
	public default double loadFactor() {
		return (double) size() / (double) capacity();
	}
}
