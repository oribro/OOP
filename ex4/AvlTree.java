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
	 */
	public AvlTree(int[] data){
		// null check.
		if (data == null)
			throw new NullPointerException("Null data reference");
		this.setNumberOfNodes(0);
		for (int value : data){
			this.add(value);
			this.setNumberOfNodes(this.getNumberOfNodes() + 1);
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
	 */
	
	public AvlTree(AvlTree avlTree){
		//null check.
		if (avlTree == null)
			throw new NullPointerException("Null tree reference");
		// Create a new tree for deep copy.
		AvlTree newTree = new AvlTree();
		newTree.setNumberOfNodes(0);
		// Iterator to iterate over the given tree nodes.
		Iterator<Integer> avlTreeIterator = avlTree.iterator();
		while (avlTreeIterator.hasNext()){
			newTree.add(avlTreeIterator.next());
			newTree.setNumberOfNodes(newTree.getNumberOfNodes() + 1);
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
		boolean isAdded = super.add(newValue);
		// The value cannot be added to the tree.
		if (!isAdded)
			return false;
		super.treeSearch(newValue).updateHeight();
		treeBalance(super.treeSearch(newValue));
		return true;
	}
	
	public void treeBalance(TreeNode root) {
		  
		// we do not use the balance in this class, but the store it anyway
		int balanceFactor = this.getBalanceFactor(root);

		System.out.println(balanceFactor);
		// check the balance
		if (balanceFactor == -2) {
			int leftBalanceFactor = this.getBalanceFactor(root.getLeft());
			if(leftBalanceFactor == -1 || leftBalanceFactor == 0) {
				this.rotationRight(root);
		    } 
			else{
				this.rotationLeft(root.getLeft());
				this.rotationRight(root);
		    }
		}
		
		else if (balanceFactor == 2) {
			int rightBalanceFactor = this.getBalanceFactor(root.getRight());
		    if (rightBalanceFactor == 1 || rightBalanceFactor == 0) {
		    	this.rotationLeft(root);
		    } 
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
		else{
		    super.setRoot(root);
		    System.out.println("------------ Balancing finished ----------------");
		}
	}
    /**
     * Get the balance factor for the given subtree rooted at root.
     * 
     * @param root - the root of the subtree to get the factor of.
     * @return An integer representing the balance factor for this node.
     * The balance factor is defined to be the difference between the left
     * and the right subtrees of the tree rooted at root.
     * If the left subtree is longer than the right subtree, the factor
     * will be negative 
     */
    int getBalanceFactor(TreeNode root) {
    	int leftSubTreeHeight = 0;
    	int rightSubTreeHeight = 0;
    	if (root.getLeft() == null){
    		leftSubTreeHeight = -1;
    	}
    	else{
    		root.getLeft().updateHeight();
    		leftSubTreeHeight = root.getLeft().getHeight();
    	}
    	if (root.getRight() == null){
    		rightSubTreeHeight = -1;
    	}
    	else{
    		root.getRight().updateHeight();
    		rightSubTreeHeight = root.getRight().getHeight();
    	}
    	return rightSubTreeHeight - leftSubTreeHeight;
    }
    
    void rotationRight(TreeNode node){     // PRIVATE
    	TreeNode tempNode;
    	// if there is a father, attach it to a new child,
    	//with consideration of the node�s side from it.
    	if (node.getFather() != null){
    		if (node == node.getFather().getLeft()){
    			node.getFather().setLeft(node.getLeft());
    		}
    		else{
    			node.getFather().setRight(node.getLeft());
    		}
    	}
    	// rearrange the nodes� fields for the rotation
    	node.getLeft().setFather(node.getFather());
    	tempNode = node.getLeft().getRight();
    	node.getLeft().setRight(node);
    	node.setFather(node.getLeft());
    	node.setLeft(tempNode);
    	// if there is a right child of the left child of this node
    	if (tempNode != null){
    		tempNode.setFather(node);
    	}
    	// update height of this node and its father after the rotation
    	node.updateHeight();
    	node.getFather().updateHeight();
    	// update new root if the node was the old root
    	if (super.getRoot() == node){
    		super.setRoot(node.getFather());
    	}
    }
    	 /**
    	 * this method perform a left rotation in the tree at a certain node
    	 * @param aNode a node in the tree that we need to rotate in
    	 */
    void rotationLeft(TreeNode node){
    	TreeNode tempNode;
    	/// if there is a father, attach it to a new child,
    	 //with consideration of the node�s side from it.
    	if (node.getFather() != null){
    		if (node == node.getFather().getLeft()){
    			node.getFather().setLeft(node.getRight());
    		}

    		else{
    			node.getFather().setRight(node.getRight());
    		}
    	}
    	// rearrange the nodes� fields for the rotation
    	node.getRight().setFather(node.getFather());
    	tempNode = node.getRight().getLeft();
    	node.getRight().setLeft(node); 
    	node.setFather(node.getRight());
    	node.setRight(tempNode); 
    	// if there is a left child of the right child of this node
    	if (tempNode != null){
    		tempNode.setFather(node);
    	}
    	// update height of this node and its father after the rotation
    	node.updateHeight();
    	node.getFather().updateHeight();
    	// update new root if the node was the old root
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
		int nodeDepth = ((BinarySearchTree) this).contains(searchVal);
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
		TreeNode toDeleteNode = super.treeSearch(toDelete);
		if (toDeleteNode == null)
			return false;
		TreeNode toDeleteNodeFather = toDeleteNode.getFather();
		super.delete(toDelete);
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
	 * @param h - the height of the tree (a non-negative number) in question.
	 * @return the number of minimum number of nodes in an AVL tree of the
	 * given height.
	 */
	public static int findMinNodes(int h){
		// Base of the recursion.
		if (h == 0)
			return 1;
		if (h == 1)
			return 2;
		// Calculate the min number of nodes of avl tree with height
		// greater than 1 according to DAST formula we proved there.
		return findMinNodes(h-1) + findMinNodes(h-2) + 1;
	}

	//@Override
	
	 // * @return an iterator on the Avl Tree. The returned iterator iterates
	 //* over the tree nodes in an ascending order, and does NOT implement
	 //* the remove() method.
	 
	public Iterator<Integer> iterator() {
		return new AvlTreeIterator(this);
	}
	
	
	// This class will be used as a nested class in 
	private class AvlTreeIterator implements Iterator<Integer>{
		
		// fields
		TreeNode currentNode;
		AvlTree tree;
		
		 //*  Iterator constructor.
		
		public AvlTreeIterator(AvlTree tree){
			this.tree = tree;
			this.currentNode = tree.findMinNodeInSubTree(tree.getRoot());
		}
		
		 
		public boolean hasNext(){
			TreeNode currentNodeSuccessor = this.tree.findSuccessor(this.currentNode);
			if (currentNodeSuccessor == null){
				return false;
			}
			return true;	
		}
		
		 
		 
		public Integer next(){
			TreeNode currentNodeSuccessor = this.tree.findSuccessor(this.currentNode);
			if (hasNext()){
				return currentNodeSuccessor.getValue();
			}
			return null;
		}
		
		 
		 
		public void remove(){
			throw new UnsupportedOperationException();
		}

	}
	
	
}
