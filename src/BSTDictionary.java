/*
 * BSTDictionary implements an ordered dictionary using a binary search tree.
 *
 * @author jocelynchang
 */
public class BSTDictionary implements BSTDictionaryADT {
	
	/*
	 * The tree of BSTDictionary.
	 */
	private BinarySearchTree tree;
	
	/*
	 * A constructor that initializes a BSTDictionary by creating a tree.
	 */
	public BSTDictionary() {
		tree = new BinarySearchTree();
	}
	
	/*
	 * A method that returns the record with key k, or null if the record is not in the dictionary.
	 * 
	 * @param k  the key of the record to be found
	 * @return  the record with k
	 */
	public Record get(Key k) {
		
		// returns null if the root of getRoot will be null
		if (tree.getRoot() == null) {
			return null;
		}
		
		BSTNode node = tree.get(tree.getRoot(), k);
		
		if (node == null) {
			return null; 
		} else {
			return node.getRecord();
		}
	}
	
	
	/*
	 * A method that inserts the given record in the ordered dictionary and throws an exception if a record with the same key already exists.
	 * 
	 * @param d  record to be put in the ordered dictionary
	 */
	public void put(Record d) throws DictionaryException {
		tree.insert(tree.getRoot(), d);
	}
	
	/*
	 * A method that removes the record with the specified key from the dictionary and throws an exception if it doesn't exist.
	 * 
	 * @param k  key of the record to be removed from the dictionary
	 */
	public void remove(Key k) throws DictionaryException {
		tree.remove(tree.getRoot(), k);
	}
	
	/*
	 * A method that returns the record of the successor of k or null if there is no successor.
	 * 
	 * @param k  key to find the successor of
	 * @return  record with a key that is the successor of k
	 */
	public Record successor(Key k) {
		if (tree.successor(tree.getRoot(), k) != null) {
			return (tree.successor(tree.getRoot(), k)).getRecord();
		} else {
			return null;
		}
	}

	/*
	 * A method that returns the record of the predecessor of k or null if there is no predecessor.
	 * 
	 * @param k  key to find the predecessor of
	 * @return  record with a key that is the predecessor of k
	 */
	public Record predecessor(Key k) {
		if (tree.predecessor(tree.getRoot(), k) != null) {
			return (tree.predecessor(tree.getRoot(), k)).getRecord();
		} else {
			return null;
		}
	}
	
	/*
	 * A method that returns the record with the smallest key in the dictionary or null if the dictionary is empty.
	 * 
	 * @return  the record with the smallest key in the dictionary.
	 */
	public Record smallest() {
		if (tree.smallest(tree.getRoot()) != null) {
			return (tree.smallest(tree.getRoot())).getRecord();
		} else {
			return null;
		}
	}
	
	/*
	 * A method that returns the record with the largest key in the dictionary or null if the dictionary is empty.
	 * 
	 * @return  the record with the smallest key in the dictionary.
	 */
	public Record largest() {
		if (tree.largest(tree.getRoot()) != null) {
			return (tree.largest(tree.getRoot())).getRecord();
		} else {
			return null;
		}
	}

}
