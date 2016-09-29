package oop.ex4.data_structures;

import java.util.Iterator;



/**
 * This class represents the AVL tree data structure from DAST.
 * This tree is a BST that preserves the height difference property:
 * the difference between each left sub-tree and right sub-tree of a node
 *  is  at most 1.
 * @author orib
 *
 */

public class AvlTree extends BinarySearchTree implements Iterable<Integer>{
	
	// The left subtree is higher by 2 units than the right subtree.
	private static final int VIOLATION_FROM_THE_LEFT = -2;
	// The right subtree is higher by 2 units than the left subtree.
	private static final int VIOLATION_FROM_THE_RIGHT = 2;
	// The right subtree is higher by 1 unit than the left subtree.
	private static final int BALANCED_FROM_THE_RIGHT = 1;
	// The left subtree is higher by 1 unit than the right subtree.
	private static final int BALANCED_FROM_THE_LEFT = -1;
	// Both subtrees are with equal heights.
	private static final int BALANCED_FROM_BOTH_SIDES = 0;
	// If a given node has no child, we refer to the height of the
	// corresponding non-existent subtree by -1. 
	private static final int NO_SUBTREE_HEIGHT = -1;
	// Constants for the fibonacci-like formula.
	private static final int TREE_WITH_ROOT_ONLY_HEIGHT = 0;
	private static final int TREE_WITH_ROOT_ONLY_NUM_OF_NODES = 1;
	private static final int TREE_WITH_TWO_NODES_HEIGHT = 1;
	private static final int TREE_WITH_TWO_NODES_NUM_OF_NODES = 2;
	private static final int REDUCE_HEIGHT_BY_ONE = 1;
	private static final int REDUCE_HEIGHT_BY_TWO = 2;
	
	
	/**
	 * The default constructor.
	 */
	
	public AvlTree(){
		super();
	}
	/**
	 * A constructor that builds the tree by adding the elements in the input
	 * array one by one. If a value appears more than once in the list, only
	 * the first appearance is added.
	 * 
	 * @param data - the values to add to the tree.
	 * @throws NullPointerException if the given data array is empty.
	 */
	public AvlTree(int[] data){
		// null check.
		if (data == null)
			throw new NullPointerException("Null data reference");
		this.setNumberOfNodes(0);
		for (int value : data){
			this.add(value);
		}
	}
	/**
	 * A copy constructor that creates a deep copy of the given AvlTree.
	 * This means that for every node or any other internal object of the 
	 * given tree, a new, identical object is instantiated for the new tree
	 * (the internal object is not simply referenced from it). The new 
	 * tree must contain all the values of the given tree, but not 
	 * necessarily in the same structure.
	 * 
	 * @param avlTree - an AVL tree.
	 * @throws NullPointerException if the given tree is empty.
	 */
	
	public AvlTree(AvlTree avlTree){
		//null check.
		if (avlTree == null)
			throw new NullPointerException("Null tree reference");
		
		// Create a new tree for deep copy.
		this.setNumberOfNodes(0);
		
		// Use the iterator of the AvlTree to insert values.
		for (int value : avlTree)
		{
			this.add(value);
		}
	}
	/**
	 * Add a new node with the given key to the tree.
	 * 
	 * @param newValue - the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was
	 * successfully added, false otherwise.
	 */
	public boolean add(int newValue){
		// Variable to check if the node was added.
		boolean isAdded = super.add(newValue);
		// The value cannot be added to the tree.
		if (!isAdded)
			return false;
		// Add the value and then update the heights of it's ancestors
		// and balance the tree if necessary.
		super.treeSearch(newValue).updateHeight();
		treeBalance(super.treeSearch(newValue));
		return true;
	}
	
	/*
	 * Check for violations in the given root of a subtree, and balance 
	 * the subtree if needed: if the height difference between the root's
	 * subtrees is not in the range [-1,1], balancing will be made.
	 * Will check recursively for the balance of the root's ancestors in 
	 * order to balance the appropriate section of the whole tree.
	 * 
	 * @param root - the root of a given SUBTREE (not necessarily the 
	 * main root of the tree).
	 */
	private void treeBalance(TreeNode root) {
		  
		// The balance factor of the current subtree root.
		int balanceFactor = this.getBalanceFactor(root);

		// The tree is unbalanced from the left.
		if (balanceFactor == VIOLATION_FROM_THE_LEFT) {
			// The balance factor of the left child of the root.
			int leftBalanceFactor = this.getBalanceFactor(root.getLeft());
			// LL violation.
			if(leftBalanceFactor == BALANCED_FROM_THE_LEFT ||
					leftBalanceFactor == BALANCED_FROM_BOTH_SIDES) {
				this.rotationRight(root);
		    } 
			// LR violation.
			else{
				this.rotationLeft(root.getLeft());
				this.rotationRight(root);
		    }
		}
		// The tree is unbalanced from the right.
		else if (balanceFactor == VIOLATION_FROM_THE_RIGHT) {
			// The balance factor of the right child of the root.
			int rightBalanceFactor = this.getBalanceFactor(root.getRight());
			// RR violation.
		    if (rightBalanceFactor == BALANCED_FROM_THE_RIGHT ||
		    		rightBalanceFactor == BALANCED_FROM_BOTH_SIDES) {
		    	this.rotationLeft(root);
		    } 
		    // RL violation.
		    else {
		    	this.rotationRight(root.getRight());
		    	this.rotationLeft(root);
		    }
		}
		  
		// The subtree is balanced at this point. Try finding violations in
		// the upper levels.
		if(root.getFather() != null) {
		   treeBalance(root.getFather());
		}
		// The given root is the root of the whole tree.
		else{
		    super.setRoot(root);
		}
	}
    /*
     * Get the balance factor for the given subtree rooted at root.
     * 
     * @param root - the root of the SUBTREE to get the factor of.
     * @return An integer representing the balance factor for this node.
     * The balance factor is defined to be the difference between the left
     * and the right subtrees of the tree rooted at root.
     * If the left subtree is longer than the right subtree, the factor
     * will be negative.
     * @throws NullPointerException if the given tree is empty.
     */
    private int getBalanceFactor(TreeNode root) {
    	if (root == null)
    		throw new NullPointerException("Empty tree");
    	int leftSubTreeHeight = 0;
    	int rightSubTreeHeight = 0;
    	if (root.getLeft() == null){
    		leftSubTreeHeight = NO_SUBTREE_HEIGHT;
    	}
    	else{
    		root.getLeft().updateHeight();
    		leftSubTreeHeight = root.getLeft().getHeight();
    	}
    	if (root.getRight() == null){
    		rightSubTreeHeight = NO_SUBTREE_HEIGHT;
    	}
    	else{
    		root.getRight().updateHeight();
    		rightSubTreeHeight = root.getRight().getHeight();
    	}
    	return rightSubTreeHeight - leftSubTreeHeight;
    }
    
    /*
	 * This method performs a right rotation in the tree at a certain node.
	 * @param node - the node we perform the rotation on.
	 */
    
    private void rotationRight(TreeNode node){ 
    	// Node to help with the rotation.
    	TreeNode tempNode;
    	// If there is a father, attach it to a new child,
    	// consider the side which the node came from.
    	if (node.getFather() != null){
    		if (node == node.getFather().getLeft()){
    			node.getFather().setLeft(node.getLeft());
    		}
    		else{
    			node.getFather().setRight(node.getLeft());
    		}
    	}
    	// Update the relations between the nodes.
    	node.getLeft().setFather(node.getFather());
    	tempNode = node.getLeft().getRight();
    	node.getLeft().setRight(node);
    	node.setFather(node.getLeft());
    	node.setLeft(tempNode);
    	// If there is a right child of the left child of this node.
    	if (tempNode != null){
    		tempNode.setFather(node);
    	}
    	// Update height of this node and its father after the rotation.
    	node.updateHeight();
    	node.getFather().updateHeight();
    	// The root of the tree is now the rotated node.
    	if (super.getRoot() == node){
    		super.setRoot(node.getFather());
    	}
    }
    /*
    * This method performs a left rotation in the tree at a certain node.
    * @param node - the node we perform the rotation on.
    */
    private void rotationLeft(TreeNode node){
    	// Node to help with the rotation.
    	TreeNode tempNode;
    	// If there is a father, attach it to a new child,
    	// consider the side which the node came from.
    	if (node.getFather() != null){
    		if (node == node.getFather().getLeft()){
    			node.getFather().setLeft(node.getRight());
    		}

    		else{
    			node.getFather().setRight(node.getRight());
    		}
    	}
    	// Update the relations between the nodes.
    	node.getRight().setFather(node.getFather());
    	tempNode = node.getRight().getLeft();
    	node.getRight().setLeft(node); 
    	node.setFather(node.getRight());
    	node.setRight(tempNode); 
    	// If there is a left child of the right child of this node.
    	if (tempNode != null){
    		tempNode.setFather(node);
    	}
    	// Update height of this node and its father after the rotation.
    	node.updateHeight();
    	node.getFather().updateHeight();
    	// The root of the tree is now the rotated node.
    	if (super.getRoot() == node){
    		super.setRoot(node.getFather());
        }
    }
    
	/**
	 * Check whether the tree contains the given input value.
	 * 
	 * @param searchVal- the value to search for.
	 * @return the depth of the node (0 for the root) with the given value
	 * if it was found in the tree, -1 otherwise.
	 */
	public int contains(int searchVal){
		// Get the depth of the node.
		int nodeDepth = super.contains(searchVal);
		if (nodeDepth == VALUE_NOT_FOUND)
			return VALUE_NOT_FOUND;
		else
			return nodeDepth;
	}
	
	/**
	 * Removes the node with the given value from the tree, if it exists.
	 * 
	 * @param toDelete - the value to remove from the tree.
	 * @return true if the given value was found and deleted, false otherwise.
	 */
	public boolean delete(int toDelete){
		// The node in the tree we wish to delete.
		TreeNode toDeleteNode = super.treeSearch(toDelete);
		// The node cannot be deleted.
		if (toDeleteNode == null)
			return false;
		// The father of the node to be deleted.
		TreeNode toDeleteNodeFather = toDeleteNode.getFather();
		// Delete the node.
		super.delete(toDelete);
		// Check if the deletion resulted in the tree being unbalanced.
		if (toDeleteNodeFather != null)
			treeBalance(toDeleteNodeFather);
		return true;
	}
	
	/**
	 * @return the number of nodes in the tree.
	 */
	public int size(){
		return this.getNumberOfNodes();
	}
	
	
	
	/**
	 * Calculates the minimum number of nodes in an AVL tree of height h.
	 * 
	 * @param h - the height of the tree (a non-negative number).
	 * @return the number of minimum number of nodes in an AVL tree of the
	 * given height.
	 */
	public static int findMinNodes(int h){
		// Base of the recursion.
		if (h == TREE_WITH_ROOT_ONLY_HEIGHT)
			return TREE_WITH_ROOT_ONLY_NUM_OF_NODES;
		if (h == TREE_WITH_TWO_NODES_HEIGHT)
			return TREE_WITH_TWO_NODES_NUM_OF_NODES;
		// Calculate the min number of nodes of avl tree with height
		// greater than 1 according to DAST formula we proved there.
		return findMinNodes(h-REDUCE_HEIGHT_BY_ONE) +
				findMinNodes(h-REDUCE_HEIGHT_BY_TWO) + 1;
	}

	/**
	 * @return an iterator on the Avl Tree. The returned iterator iterates
	 * over the tree nodes in an ascending order, and does NOT implement
	 * the remove() method.
	 */
	 
	public Iterator<Integer> iterator() {
		return new AvlTreeIterator(this);
	}
	
	
	// This class will be used as a nested inner class and will define an
	// iterator object.
	private class AvlTreeIterator implements Iterator<Integer>{
		
		// The next node the iterator checks in the tree.
		private TreeNode nextNode;
		// The current node the iterator checks. 
		private TreeNode currentNode;
		// The tree we iterate on.
		private AvlTree tree;
		
		
		/*
		 * Constructs an iterator to go over the tree in an ascending order.
		 * We start from the minimal value and each time the next value will
		 * be the successor value. Since no duplicates are allowed, by the
		 * definition of successor we will be given an ascending order.
		 * @param tree - the avl tree we iterate on.
		 */
		public AvlTreeIterator(AvlTree tree){
			this.tree = tree; 
			this.currentNode = this.tree.findMinNodeInSubTree(tree.getRoot());
			this.nextNode = this.tree.findSuccessor(this.currentNode);
		}
		
		/*
		 * Checks if there is a next node to iterate on.
		 * @return true if there is a next node and false otherwise.
		 */
		public boolean hasNext(){
			if (this.currentNode == null){
				return false;
			}
			return true;	
		}
		
		 
		/*
		* Go to the next node in the tree.
		* @return the value of the next node.
		* @throws NullPointerException if there is no next node.
		 */
		public Integer next(){
			if (!hasNext())
				throw new NullPointerException("Out of nodes");
			int value = this.currentNode.getValue();
			this.currentNode = this.nextNode;
			this.nextNode = this.tree.findSuccessor(this.currentNode);
			return value;
			
		}

		
		 /*
		  * Remove is not supported by this iterator.
		  * @see java.util.Iterator#remove()
		  */
		public void remove(){
			throw new UnsupportedOperationException();
		}

	}
	
	
}
