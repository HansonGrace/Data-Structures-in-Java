package tables;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.DataTable;
import model.Row;
import model.Table;


public class HashTable implements DataTable {
	private static final Row SENTINEL = new Row(null,null);
	private String name;
	private List<String> columns;
	private Row[] rows;
	private int rowSize;
	private int fingerprint;
	
	//store table name and column names
	//initialize data structure
	public HashTable(String name, List<String> columns) {
		this.name = name;
		this.columns = columns;
		this.rows = new Row[32];
		this.rowSize = 0;
		this.fingerprint= 0;	}

	//reinitialize data structure
	@Override
	public void clear() {
		Arrays.fill(this.rows, null);
		this.rowSize = 0;
		this.fingerprint = 0;
	}

	//salt the given key with your own name
	//use polynomial rolling hash function with range 1 to capacity -1
	private int hashFunction(String key) {
		int hash = 0;
		key += "Grace";
		for (int i = 1; i < key.length(); i++)
		{
			hash = 31 * hash + key.charAt(i);
		}
		//hash mod capacity
		//remember % doesn't work right when the hash before modding is negative
		//use Math.floorMod method instead
		return Math.floorMod(hash, this.capacity());
	}
	
	//salt given key with your own name
	//use SHA-256 function with range 1 to capacity - 1
	//must not return 0 (or anything that would mod to 0)
	private int doubleHash(String key){
		key += "Grace";
		try {
			//get the SHA-256 method from MessageDigest
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			//turn key into a byte array
			byte[] encodedHash = digest.digest(key.getBytes());
			
			//convert the byte array into an integer
			int hash = 0;
			for (byte b : encodedHash)
			{
				hash = (hash << 8) + (b & 0xFF);
			}
			
			//return the modded hash 
			return Math.floorMod(hash,this.capacity()-1) +1;
		}
		//if SHA-256 is not available throw runtime exception
		catch (NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}
	
	//Expand the capacity to approximately double.
	//Rehash each row.
	private void rehash()
	{
		//let backup/copy reference = old array reference
		Row[] temp = this.rows;
		//reassign table array ref = new larger empty array
		this.rows = new Row[this.capacity() * 2];
		//reinitialize size/fingerprint
		rowSize = 0;
		fingerprint = 0;
		//for each index in the backup/copy, check for hit or miss and update
		for (Row r : temp)
		{
			//on a hit
			if (r != null && r != SENTINEL)
			{
				//put in new array of rows
				put(r.key(), r.fields());
			}
		}
	}

	//Define a guard condition for an invalid key.
	//Define a guard condition for fields which are too wide or narrow.
	//On a hit, update the old row, then return the old list of fields.
	//On a miss, create the new row by replacing a sentinel (if possible) and check for rehash, then return null.
	//When the load factor reaches 75%, call the rehash method.
	@Override
	public List<Object> put(String key, List<Object> fields) {
		//guard condition for fields
		if (fields.size() != this.degree()-1)
		{
			throw new IllegalArgumentException();
		}
		
		List<Object> field = null;
		//let h be the result of hash(key), the base index
		int h = this.hashFunction(key);
		//let c be the result of hash2(key), the adjustment
		int c = this.doubleHash(key);
		//index of sentinels
		int index = -1;
		
		//for each j value from 0 to capacity - 1:
		for (int j = 0; j < this.capacity(); j++)
		{
			int i = Math.floorMod((h + j * c), this.capacity());
			
			//note down sentinel index
			if (rows[i] == SENTINEL && index != -1)
			{
				index = i;
			}
			
			//check for miss
			if (rows[i] == null)
			{
				//if there was a sentinel found, go to that index
				if (index != -1)
				{
					//replace sentinel with new row
					rows[index] = new Row(key,fields);
				}
				else
				{
					//create new row on miss
					rows[i] = new Row(key, fields);
					
				}
				//increase fingerprint
				fingerprint += rows[i].hashCode();
				//increase row size
				rowSize++;
				
				//rehash at 75% load factor
				if (rowSize >= this.capacity() * 0.75)
				{
					rehash();
				}
				
				//return null;
				return null;
			}
			//hit
			if (rows[i].key() == key)
			{
				//decrease fingerprint by OLD row's hash
				fingerprint -= rows[i].hashCode();
				//set field equal to OLD row's fields to return
				field = rows[i].fields();
				//update key's fields
				rows[i] = new Row(key, fields);
				//increase fingerprint by NEW row's hash
				fingerprint += rows[i].hashCode();
				//return old row's fields
				return field;
			}
		}
		//unexpected fall-through
		throw new IllegalStateException();
	}
	
	//on a hit, return the old list of fields
	//on a miss, return null
	@Override
	public List<Object> get(String key) {
		int h = this.hashFunction(key);
		int c = this.doubleHash(key);
		
		//Searches for the key
		for (int j = 0; j < this.capacity(); j++)
		{
			int i = Math.floorMod((h + j * c), this.capacity());
			//check for miss
			if (rows[i] == null)
			{
				return null;
			}
			
			//hit
			if (rows[i].key() == key)
			{
				//return row's fields
				return rows[i].fields();
			}
		}
		//unexpected fall-through
		throw new IllegalStateException();
	}

	/*On a hit, delete the old row by replacing it with a sentinel (like a
	row with null components), then return the old list of fields */
	//On a miss return null
	@Override
	public List<Object> remove(String key) {
		List<Object> field = null;
		int h = this.hashFunction(key);
		int c = this.doubleHash(key);
		
		//search for key
		for (int j = 0; j < this.capacity(); j++)
		{
			int i = Math.floorMod((h + j * c),this.capacity());
			
			//miss
			if (rows[i] == null)
			{
				return null;
			}
			
			//hit
			if (rows[i].key() == key)
			{
				//decrease fingerprint by OLD row's hash
				fingerprint -= rows[i].hashCode();
				//set field equal to the old rows fields
				field = rows[i].fields();
				//set row as a sentinel
				rows[i] = SENTINEL;
				//decrease row size
				rowSize--;

				//return old rows fields
				return field;
			}
		}
		//unexpected fall-through
		throw new IllegalStateException();
	}

	//total number of columns (key + fields)
	@Override
	public int degree() {
		return this.columns.size();
	}

	//current number of rows amortized
	@Override
	public int size() {
		return this.rowSize;
	}

	//max number of rows
	@Override
	public int capacity() {
		return this.rows.length;
	}

	@Override
	public int hashCode() {
		return this.fingerprint;
	}

	//defines whether the given object is a table of any type (not only the same type as this table)
	//with the same fingerprint as this table
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Table && obj.hashCode() == this.hashCode());
	}

	//Iterates over all rows in the table in arbitrary order
	@Override
	public Iterator<Row> iterator() {
		return new Iterator<>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				//maintenance condition, accounts for the gaps
				while (index < capacity() && (rows[index] == null || rows[index] == SENTINEL))
				{
					index++;
				}
				return index < capacity();
			}

			@Override
			public Row next() {
				
				if (!hasNext())
				{
					throw new UnsupportedOperationException();
				}
				Row next = rows[index];
				index++;
				return next;
			}
		};
	}

	// table name
	@Override
	public String name() {
		return this.name;
	}

	//list of column names
	@Override
	public List<String> columns() {
		return this.columns;
	}

	// represent in Table.toPrettyString format
	@Override
	public String toString() {
		return toPrettyString();
	}
}
