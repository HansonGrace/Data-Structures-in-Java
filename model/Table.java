package model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import tables.HashTable;

public interface Table extends Iterable<Row> {
	public void clear();

	public List<Object> put(String key, List<Object> fields);

	public List<Object> get(String key);

	public List<Object> remove(String key);

	//defines whether get() hits or misses
	public default boolean contains(String key) {
		return get(key) == null;
	}

	public int degree(); 

	public int size(); 

	//return true if table is empty
	public default boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int hashCode();

	@Override
	public boolean equals(Object obj);

	@Override
	public Iterator<Row> iterator();

	public String name();

	public List<String> columns();

	@Override
	public String toString();

	public default String toPrettyString() {
		int[] widths = new int[degree()];
		for (int i = 0; i < columns().size(); i++) {
			widths[i] = Math.max(widths[i], columns().get(i).length());
		}
		for (Row row: this) {
			widths[0] = Math.max(widths[0], row.key().length());
			for (int i = 0; i < row.fields().size(); i++) {
				widths[i+1] = Math.max(widths[i+1], Objects.requireNonNullElse(row.fields().get(i), "").toString().length());
			}
		}

		StringJoiner head2 = new StringJoiner(" | ", "| ", " |\n");
		for (int i = 0; i < columns().size(); i++) {
			head2.add(("%-"+widths[i]+"s").formatted(columns().get(i)));
		}

		String head1 = ("| %-"+(head2.length()-4)+"s|\n").formatted(name());

		StringBuilder body = new StringBuilder();
		for (Row row: this) {
			StringJoiner current = new StringJoiner(" | ", "| ", " |\n");
			current.add(("%-"+widths[0]+"s").formatted(row.key()));
			for (int i = 0; i < row.fields().size(); i++) {
				current.add(("%-"+widths[i+1]+"s").formatted(Objects.requireNonNullElse(row.fields().get(i), ""), widths[i+1]));
			}
			body.append(current);
		}

		String line = "+" + "-".repeat(head2.length()-3) + "+\n";

		return line + head1 + line + head2 + line + body + line;
	}
	
	//find the rows where a given column matches a given value
	//build and return a data table having only the matching rows
	public default Table filter (Object value, String column)
	{
		Table filteredTable = new HashTable(this.name(), this.columns());
			
		//set col as the list of columns in the table
		List<String> col = this.columns();
		//retrieve the index of the column that matches the inputed one
		int index = col.indexOf(column);
		var iterator = this.iterator();
		//var size = 0;
		//if the index is 0, iterate through keys
		//otherwise find the correct column within the fields, then iterate
		if (index == 0)
		{
			while(iterator.hasNext())
			{
				//get the current row
				Row r = iterator.next();
				//check if the key of the row equals the value
				if (r.key().equals(value))
				{
					//add it to the new data table
					filteredTable.put(r.key(), r.fields());
				}
			}
		}
		else
		{
			while(iterator.hasNext())
			{
				//get the current row
				Row r = iterator.next();
				//get the correct value from the fields at the index
				//subtract one from index because index includes the key
				var f = r.fields().get(index-1);
				//check if this value equals the inputted value
				if (f.equals(value))
				{
					//add the row to the new table
					filteredTable.put(r.key(), r.fields());
				} 
			}
		}
	
		//return the new table
		return filteredTable;
	}
}
