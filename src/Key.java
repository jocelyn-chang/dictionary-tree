/*
 * Key represents the key of the data items in the internal nodes
 * of the binary search tree implementing the ordered dictionary.
 *
 * @author jocelynchang
 */
public class Key {
	
	/*
	 * Type of the key.
	 */
	private int type;
	
	/*
	 * Label of the key.
	 */
	private String label;
	
	/*
	 * A constructor that initializes a new key with specified label and type.
	 * 
	 * @param theLabel  stored in key's label
	 * @param theType  stored in key's type
	 */
	public Key(String theLabel, int theType) {
		// turn theLabel to lower case letters before storing
		this.label = theLabel.toLowerCase();
		
		this.type = theType;
	}
	
	/*
	 * A method that returns the String stored in the key's label.
	 * 
	 * @return  label String
	 */
	public String getLabel() {
		return this.label;
	}
	

	/*
	 * A method that returns the value of the key's type.
	 * 
	 * @return  type value
	 */
	public int getType() {
		return this.type;
	}
	

	/*
	 * A method that returns a value respective of if this Key object is equal to Key k.
	 * 
	 * @return  0 if it's equal to k, -1 if it's smaller than k, and 1 otherwise
	 */
	public int compareTo(Key k) {
		int size;
		
		// checks type if the keys have the same labels
		if ((this.label).equals(k.label)) {
			if (this.type == k.type) {
				return 0;
			} else if (this.type < k.type) {
				return -1;
			} else {
				return 1;
			}
		}
		
		// assigning size with the shorter key's label length
		if ((this.label).length() <= (k.label).length()) {
			size = (this.label).length();
		} else {
			size = (k.label).length();
		}
		
		// compares each character until the size is reached or the characters are different
		for (int i = 0; i < size; i++) {
			if ((int)this.label.charAt(i) == (int)k.label.charAt(i)) {
				continue;
			} else if ((int)this.label.charAt(i) < (int)k.label.charAt(i)) {
				return -1;
			} else {
				return 1;
			}
		}
		
		// compares the length of the keys' labels if they are the same up to the shorter key's label length
		if ((this.label).length() < (k.label).length()) {
			return -1;
		} else {
			return 1;
		}
	}
}


