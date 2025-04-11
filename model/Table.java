package model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

//import tables.HashTable;

public interface Table extends Iterable<Row> {
	public void clear();

	public List<Object> put(String key, List<Object> fields);

	public List<Object> get(String key);

	public List<Object> remove(String key);

	public int degree(); 

	public int size(); 

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
	//to implement
		}
		}

	

