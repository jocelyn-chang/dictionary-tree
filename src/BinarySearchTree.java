/*
 * BinarySearchTree represents a binary search tree.
 *
 * @author jocelynchang
 */
public class BinarySearchTree {
	
	/*
	 * This node's root.
	 */
	private BSTNode root;
	
	/*
	 * A constructor that initializes a binary search tree by creating a leaf node as the tree's root.
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/*
	 * A method that returns the root node of this tree.
	 * 
	 * @return  root node
	 */
	public BSTNode getRoot() {
		return this.root;
	}
	
	/*
	 * A method that returns the node storing the given key or null if the key is not in the tree.
	 * 
	 * @param r  the root node of the tree
	 * @param k  the key of the node to be found
	 * @return  node storing the specified key
	 */
	public BSTNode get(BSTNode r, Key k) {
		
		// base case, checks if the node is a leaf
		if (r == null || r.isLeaf()) {

			// compares the key's and returns null if they don't match, and the node if they do match
			if(r != null && r.getRecord().getKey().compareTo(k) == 0) {
				return r;
			} else {
				return null;
			}
		} else {
			
			int cmp = r.getRecord().getKey().compareTo(k);
			
			// returning the node if the keys match, or method is recursively called
			if (cmp == 0) {
				return r;
			} else if (cmp == -1) {
				return get(r.getRightChild(), k);
			} else {
				return get(r.getLeftChild(), k);
			}
		}
	}
	
	/*
	 * A method that adds the given record to the binary search tree, throws exception if it's already in the tree.
	 * 
	 * @param r  root of the binary search tree to be added to
	 * @param d  the record to be added
	 */
	public void insert(BSTNode r, Record d) throws DictionaryException {
		
		// a node initialized to hold d
		BSTNode newNode = new BSTNode(d);
		
		// assigns root with new node if r is null
		if (r == null) {
			this.root = newNode;
			return;
		}
		
		// create variable holding the value from comparing d and r's key
		int cmp = d.getKey().compareTo(r.getRecord().getKey());
		
		// throws exception if r and d's records have the same key
		if (cmp == 0) {
			throw new DictionaryException("Record's key is already in the tree!");
			
		// base case, checks if r is a leaf and compares d's key with r's key to assign d to left or right child
		} else if (r.isLeaf()) {
			if (cmp == -1) {
				r.setLeftChild(newNode);
			} else {
				r.setRightChild(newNode);
			} 
			newNode.setParent(r);
			
		// when d's key is smaller than r's key
		} else if (cmp == -1) {
			if (r.getLeftChild() == null) {
				r.setLeftChild(newNode);
				newNode.setParent(r);
			} else {
				// recursive call to left child
				insert(r.getLeftChild(), d);
			}
		
		// when d's key is bigger than r's key
		} else {
			if (r.getRightChild() == null) {
				r.setRightChild(newNode);
				newNode.setParent(r);
			} else {
				// recursive call to right child
				insert(r.getRightChild(), d);
			}
		}
	}
	
	/*
	 * A method that deletes the node with the given key from the tree, throws exception if node doesn't exist.
	 * 
	 * @param r  root of the binary search tree to be removed from
	 * @param k  the key of the node to be removed
	 */
	public void remove(BSTNode r, Key k) throws DictionaryException {
		BSTNode child, parent;
		
		// DictionaryException thrown if node is null
		if (r == null) {
			throw new DictionaryException("Record with key does not exist!");
		} 
		
		int cmp = k.compareTo(r.getRecord().getKey());
		
		if (cmp == -1) {
			
			// recursively calls the left child of r if r's key is smaller than k
			remove(r.getLeftChild(), k);
			
		} else if (cmp == 1) {
			
			// recursively calls the right child of r if r's key is bigger than k
			remove(r.getRightChild(), k);
			
		} else {
			// removing r when r's key matches key k
			
			// removing r when r only has one child
			if (((r.getRightChild() != null) && (r.getLeftChild() == null)) || ((r.getRightChild() == null) && (r.getLeftChild() != null))){
				
				// assigns child to be the child node that's not null
				if ((r.getRightChild() != null) && (r.getLeftChild() == null)) {
					child = r.getRightChild();
					r.setRightChild(null);
					child.setLeftChild(null);
				} else {
					child = r.getLeftChild();
					r.setLeftChild(null);
					child.setRightChild(null);
				}
				
				// assigns child as root if r doesn't have a parent
				if (r.getParent() == null) {
					this.root = child;
					return;
				} else {
					
					// switch r's parent to be child's parent
					parent = r.getParent();
					child.setParent(parent);
					if (r == parent.getLeftChild()) {
						parent.setLeftChild(child);
					} else if (r == parent.getRightChild()) {
						parent.setRightChild(child);
					}
					
					r.setParent(null);
					
					return;
				}
				
			} else if ((r.getParent() == null) && r.isLeaf()) {

				// removing r if r is the only node of the tree
				this.root = null;
				return;
				
			} else if (r.isLeaf()) {

				// removing r if r is a leaf
				parent = r.getParent();
				
				if (parent.getRightChild() == r) {
					parent.setRightChild(null);
				} else if (parent.getLeftChild() == r) {
					parent.setLeftChild(null);
				}
				
			} else {

				// removing r if r has a parent and two children, sets to smallest node in right subtree and removes that node
				BSTNode smallest = smallest(r.getRightChild());
				r.setRecord(smallest.getRecord());
				remove(smallest, smallest.getRecord().getKey());
			}
		}
	}
	
	/*
	 * A method that returns the node storing the successor of the given key in the tree, or null if the successor does not exist.
	 * 
	 * @param r  root of the binary search tree to search from
	 * @param k  the key of the node to find the successor of
	 */
	public BSTNode successor(BSTNode r, Key k) {
		BSTNode curr = get(r, k);
		
		// return null if node with k is not in tree
		if (curr == null) {
			return null;
		} else {
			
			// return smallest node in right subtree if curr has a right child
			if (curr.getRightChild() != null) {
				return smallest(curr.getRightChild());
			} else {
				
				BSTNode parent = curr.getParent();
				
				// traverse to find out if we are in a left subtree or a right subtree
				while ((curr != r) && (parent.getRightChild() == curr)) {
					curr = parent;
					parent = curr.getParent();
				}
				return parent;
			}
		}
	}
	
	/*
	 * A method that returns the node storing the predecessor of the given key in the tree, or null if it does not exist.
	 * 
	 * @param r  root of the binary search tree to be searched from
	 * @param k  the key of the node to find the predecessor of
	 */
	public BSTNode predecessor(BSTNode r, Key k) {
		BSTNode curr = get(r, k);
		
		// return null if node with k is not in tree
		if (curr == null) {
			return null;
		} else {
			
			// return largest node in left subtree if curr has a left child
			if (curr.getLeftChild() != null) {
				return largest(curr.getLeftChild());
			} else {
				BSTNode parent = curr.getParent();
				
				// traverse to find out if we are in a left subtree or a right subtree
				while ((curr != r) && (parent.getLeftChild() == curr)) {
					curr = parent;
					parent = curr.getParent();
				}
				
				return parent;
			}
		}
	}
	
	/*
	 * A method that returns the node with the smallest key in tree with root r.
	 * 
	 * @param r  root of the binary search tree to be searched from
	 */
	public BSTNode smallest(BSTNode r) {
		// return root if root is null
		if (r == null) {
			return r;
		} else {
			BSTNode curr = r;
			
			// iterate through the left side until a leaf is reached and return that node
			while (curr.getLeftChild() != null) {
				curr = curr.getLeftChild();
			}
			return curr;
		}
	}
	
	/*
	 * A method that returns the node with the largest key in tree with root r.
	 * 
	 * @param r  root of the binary search tree to be searched from
	 */
	public BSTNode largest(BSTNode r) {
		
		// return root if root is null
		if (r == null) {
			return r;
		} else {
			
			BSTNode curr = r;
			
			// iterate through the left side until a leaf is reached and return that node
			while (curr.getRightChild() != null) {
				curr = curr.getRightChild();
			}
			return curr;
		}
	}
}
