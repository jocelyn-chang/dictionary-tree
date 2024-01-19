/*
 * Record represents the records to be stored in the internal 
 * nodes of the binary search tree.
 *
 * @author jocelynchang
 */
public class Record {
	
	/*
	 * This record's key.
	 */
	private Key theKey;
	
	/*
	 * This record's data.
	 */
	private String data;
	
	/*
	 * A constructor that initializes a new record with the specified key and data.
	 * 
	 * @param k  stored in record's key
	 * @param theData  stored in record
	 */
	public Record(Key k, String theData) {
		this.theKey = k;
		this.data = theData;
	}
	
	/*
	 * A method that returns the key of the record.
	 * 
	 * @return  record's key
	 */
	public Key getKey() {
		return this.theKey;
	}
	
	/*
	 * A method that returns the data of the record.
	 * 
	 * @return  record's data
	 */
	public String getDataItem() {
		return this.data;
	}
	
}
