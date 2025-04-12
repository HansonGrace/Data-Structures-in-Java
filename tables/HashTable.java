package tables;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.DataTable;
import model.Row;
import model.Table;

// Psuedocode for HashTable. Not yet implemented

/*
 * Class HashTable:
 * Constants:
 * SENTINEL = Row(null, null)
 * 
 * Properties:
 * name: String
 * columns: List<String>
 * rows: Array of Row
 * rowSize: Integer
 * fingerprint: Integer
 * 
 * Constructor(name: String, columns: List<String>):
 * Initialize name
 * Initialize columns
 * Initialize rows as an array of size 32
 * Initialize rowSize to 0
 * Initialize fingerprint to 0
 * 
 * Method clear():
 * Set all elements in rows to null
 * Reset rowSize to 0
 * Reset fingerprint to 0
 * 
 * Method hashFunction(key: String) -> Integer:
 * Append "Grace" to key
 * Initialize hash to 0
 * For each character in key (starting from index 1):
 * Update hash using polynomial rolling hash formula
 * Return hash mod capacity
 * 
 * Method doubleHash(key: String) -> Integer:
 * Append "Grace" to key
 * Try:
 * Get SHA-256 digest of key
 * Convert digest to an integer hash
 * Return (hash mod (capacity - 1)) + 1
 * Catch NoSuchAlgorithmException:
 * Throw runtime exception
 * 
 * Method rehash():
 * Backup current rows to a temporary array
 * Create a new rows array with double the capacity
 * Reset rowSize and fingerprint to 0
 * For each row in the temporary array:
 * If row is not null and not SENTINEL:
 * Reinsert row into the new rows array
 * 
 * Method put(key: String, fields: List<Object>) -> List<Object>:
 * If fields size is not equal to degree - 1:
 * Throw IllegalArgumentException
 * 
 * Initialize field to null
 * Compute base index (h) using hashFunction
 * Compute adjustment (c) using doubleHash
 * Initialize sentinel index to -1
 * 
 * For each j from 0 to capacity - 1:
 * Compute index i using (h + j * c) mod capacity
 * If rows[i] is SENTINEL and sentinel index is not set:
 * Set sentinel index to i
 * If rows[i] is null:
 * If sentinel index is set:
 * Replace sentinel with new row
 * Else:
 * Insert new row at rows[i]
 * Update fingerprint and rowSize
 * If load factor >= 75%:
 * Call rehash()
 * Return null
 * If rows[i] matches the key:
 * Update fingerprint by subtracting old row's hash
 * Set field to old row's fields
 * Replace old row with new row
 * Update fingerprint with new row's hash
 * Return field
 * Throw IllegalStateException
 * 
 * Method get(key: String) -> List<Object>:
 * Compute base index (h) using hashFunction
 * Compute adjustment (c) using doubleHash
 * 
 * For each j from 0 to capacity - 1:
 * Compute index i using (h + j * c) mod capacity
 * If rows[i] is null:
 * Return null
 * If rows[i] matches the key:
 * Return rows[i]'s fields
 * Throw IllegalStateException
 * 
 * Method remove(key: String) -> List<Object>:
 * Compute base index (h) using hashFunction
 * Compute adjustment (c) using doubleHash
 * 
 * For each j from 0 to capacity - 1:
 * Compute index i using (h + j * c) mod capacity
 * If rows[i] is null:
 * Return null
 * If rows[i] matches the key:
 * Update fingerprint by subtracting old row's hash
 * Set field to old row's fields
 * Replace row with SENTINEL
 * Decrease rowSize
 * Return field
 * Throw IllegalStateException
 * 
 * Method degree() -> Integer:
 * Return size of columns
 * 
 * Method size() -> Integer:
 * Return rowSize
 * 
 * Method capacity() -> Integer:
 * Return length of rows array
 * 
 * Method hashCode() -> Integer:
 * Return fingerprint
 * 
 * Method equals(obj: Object) -> Boolean:
 * Return true if obj is a Table and its hashCode matches this table's hashCode
 * 
 * Method iterator() -> Iterator<Row>:
 * Create an iterator with:
 * Property index initialized to 0
 * Method hasNext():
 * While index is within bounds and rows[index] is null or SENTINEL:
 * Increment index
 * Return true if index is within bounds
 * Method next():
 * If hasNext() is false:
 * Throw UnsupportedOperationException
 * Return rows[index] and increment index
 * 
 * Method name() -> String:
 * Return name
 * 
 * Method columns() -> List<String>:
 * Return columns
 * 
 * Method toString() -> String:
 * Return result of toPrettyString()
 * 
 * 
 * 
 */