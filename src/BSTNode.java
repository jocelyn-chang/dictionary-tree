/*
 * BSTNode represents a binary search tree node.
 *
 * @author jocelynchang
 */
public class BSTNode {
	
	/*
	 * This node's record.
	 */
	private Record item;
	
	/*
	 * This node's relatives.
	 */
	private BSTNode rightChild, leftChild, parent;
	
	/*
	 * A constructor that initializes a new node with a record.
	 * 
	 * @param item  record item to be stored
	 */
	public BSTNode(Record item) {
		this.item = item;
		this.rightChild = null;
		this.leftChild = null;
	}
	
	/*
	 * A method that returns the record of the node.
	 * 
	 * @return  node's record
	 */
	public Record getRecord() {
		return this.item;
	}
	
	/*
	 * A method that stores the given record in this node.
	 * 
	 * @param d  record item to be set to this node
	 */
	public void setRecord(Record d) {
		this.item = d;
	}
	
	/*
	 * A method that returns the left child of the node.
	 * 
	 * @return  node's left child
	 */
	public BSTNode getLeftChild() {
		return this.leftChild;
	}
	
	/*
	 * A method that returns the right child of the node.
	 * 
	 * @return  node's right child
	 */
	public BSTNode getRightChild() {
		return this.rightChild; 
	}
	
	/*
	 * A method that returns the parent of the node.
	 * 
	 * @return  node's parent
	 */
	public BSTNode getParent() {
		return this.parent;
	}
	
	/*
	 * A method that sets the given node as the left child of this node.
	 * 
	 * @param u  node to be set as the this node's left child
	 */
	public void setLeftChild(BSTNode u) {
		this.leftChild = u;
	}
	
	/*
	 * A method that sets the given node as the right child of this node.
	 * 
	 * @param u  node to be set as the this node's right child
	 */
	public void setRightChild(BSTNode u) {
		this.rightChild = u;
	}
	
	/*
	 * A method that sets the given node as the parent of this node.
	 * 
	 * @param u  node to be set as the this node's parent
	 */
	public void setParent(BSTNode u) {
		this.parent = u;
	}
	
	/*
	 * A method that returns the a boolean value regarding if the node is a leaf.
	 * 
	 * @return  true if is a leaf and false otherwise
	 */
	public boolean isLeaf() {
		if ((this.leftChild == null) && (this.rightChild == null)) {
			return true;
		} else {
			return false;
		}
	}
}
