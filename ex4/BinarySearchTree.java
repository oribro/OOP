package oop.ex4.data_structures;

import java.util.Iterator;

/**
 * This class implements a BST with actions to perform on it.
 * @author orib
 *
 */
public class BinarySearchTree implements Iterable<Integer>{
	// The root of the tree that represents the whole tree.
	private TreeNode root;
	private int numberOfNodes;
	protected static final int VALUE_NOT_FOUND = -1;
	protected static final int RIGHT_CHILD = -1;
	protected static final int LEFT_CHILD = 1;
	protected static final int LEAF_HEIGHT = 0;
	/**
	 * Constructor.
	 */
	public BinarySearchTree(){
		this.root = null;
		this.numberOfNodes = 0;
	}


	/**
	 * Add a new node with the given key to the tree.
	 * 
	 * @param newValue - the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was
	 * successfully added, false otherwise.
	 */
	
	public boolean add(int newValue){

		// The current node to check.
		TreeNode current = getRoot();
		
		if (current == null){
			this.root = new TreeNode(newValue, null);
			this.numberOfNodes++;
			return true;
		}
	
		// Go over the tree
		while(current != null){
			// Search the left sub-tree.
			if (newValue < current.getValue()){
				// The value can be added as the
				// left child of the current node.
				if (current.getLeft() == null){
					current.setLeft(new TreeNode(newValue, current));
					this.numberOfNodes++;
					return true;
				}
				// We need to go deeper into the left sub-tree.
				else{
					current = current.getLeft();
				}
			}
			// Search the right sub-tree.
			else if (newValue > current.getValue()){
				// The value can be added as the
				// right child of the current node.
				if (current.getRight() == null){
					current.setRight(new TreeNode(newValue, current));
					this.numberOfNodes++;
					return true;
				}
				// We need to go deeper into the right sub-tree.
				else{
					current = current.getRight();
				}
			}
			// The value is already in the tree and we don't allow duplicates.
			else{
				return false;
			}
		}
		// The value cannot be added to the tree.
		return false;
	}
	/**
	 * Searches for the given value in the tree.
	 * @param searchVal - the value to search.
	 * @return the node in which the value is stored. If not found 
	 * returns null.
	 */
	TreeNode treeSearch(int searchVal){                  ////PRIVATE
		// The current node to search.
		TreeNode current = getRoot();
		// The value might be found deeper into the tree.
		while (current != null && current.getValue() != searchVal){
			// Search the left sub-tree.
			if (searchVal < current.getValue()){
				current = current.getLeft();
			}
			// Search the right sub-tree.
			else{
				current = current.getRight();
			}	
		}
		// We searched the tree and didn't find the value.
		if (current == null){
			return null;
		}
		return current;
	}
	/**
	 * Check whether the tree contains the given input value.
	 * 
	 * @param searchVal- the value to search for.
	 * @return the depth of the node (0 for the root) with the given value
	 * if it was found in the tree, -1 otherwise.
	 */
	public int contains(int searchVal){
		// Find the wanted node in the tree.
		TreeNode desiredNode = treeSearch(searchVal);
		// The value is not in the tree.
		if (desiredNode == null)
			return VALUE_NOT_FOUND;
		// The value is in the tree.
		return desiredNode.getHeight();	
	}
	
	/**
	 * Removes the node with the given value from the tree, if it exists.
	 * 
	 * @param toDelete - the value to remove from the tree.
	 * @return true if the given value was found and deleted, false otherwise.
	 */

	public boolean delete(int toDelete){
		// Find the node to delete
		TreeNode nodeToDelete = treeSearch(toDelete);
		// The node cannot be deleted.
		if (nodeToDelete == null)
			return false;
		TreeNode nodeToDeleteFather = nodeToDelete.getFather();
		// Variables to assist with the deletion process.
		TreeNode firstDeletionHelper, secondDeletionHelper;
		// First 2 simple cases of deletion where the node has at most
		// one child.
		if(nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null)
			secondDeletionHelper = nodeToDelete;
		// The complex case where the node has 2 children.
		else
			secondDeletionHelper = findSuccessor(nodeToDelete);
		// Preserve the subtree of the node we switch.
		if(secondDeletionHelper.getLeft() != null)
			firstDeletionHelper = secondDeletionHelper.getLeft();
		else 
			firstDeletionHelper = secondDeletionHelper.getRight();
		// Connect the previous subtree with the new subtree.
		if(firstDeletionHelper != null){
			firstDeletionHelper.setFather(secondDeletionHelper.getFather());
		}
		// The successor is the maximal element in the left subtree.
		if(secondDeletionHelper.getFather() == null)
			this.setRoot(firstDeletionHelper); 
		// Update node relations before deleting the successor.
		else{
			if (secondDeletionHelper == secondDeletionHelper.getFather()
					.getLeft()){
				secondDeletionHelper.getFather()
				.setLeft(firstDeletionHelper);
			}
			else{
				secondDeletionHelper.getFather()
				.setRight(firstDeletionHelper);
			}	
		}
		// Switch between the successor and the node we delete and 
		// preserve the bst structure.
		if(secondDeletionHelper != nodeToDelete){
			nodeToDelete.setValue(secondDeletionHelper.getValue());
			if (secondDeletionHelper.getLeft() != null)
				nodeToDelete.setLeft(secondDeletionHelper.getLeft());
			if (secondDeletionHelper.getRight() != null)
				nodeToDelete.setRight(secondDeletionHelper.getRight());
		}
		if (nodeToDeleteFather != null)
			nodeToDeleteFather.updateHeight();
		this.numberOfNodes--;
		return true;
	}
	/*
	 * Find the node in the sub-tree that has the lowest value of all nodes.
	 * @param root - the root of the sub-tree to search in.
	 * @return the node with minimal value.
	 */
	TreeNode findMinNodeInSubTree(TreeNode root){
		TreeNode minNode = root;
		while (minNode.getLeft() != null)
			minNode = minNode.getLeft();
		return minNode;
	}
	
	/*
	 * Find the successor of the given node in the tree.
	 * @param node - the node to find the successor to.
	 * @return - the node of the successor.
	 */
	TreeNode findSuccessor(TreeNode node){
		if (node == null)
			throw new NullPointerException("Invalid node");
		// The node of the successor.
		TreeNode successor;
		// The given node has a right child and therfore the successor will
		// be the lowest number in the right child's sub-tree.
		if (node.getRight() != null){
			successor = findMinNodeInSubTree(node.getRight());
			return successor;
		}
		// The node has no right sub-tree and the successor will be in
		// the left sub-tree.
		successor = node.getFather();
		// The node has the highest value in the tree and therfore has no
		// successor OR the successor is a root of the node's sub-tree
		// and can be found in a lower height.
		while (successor != null && successor.right == node){
			node = successor;
			successor = node.getFather();
		}
		return successor;
		
	}
	 int getBalanceFactor(TreeNode root) {
		 if (root.getLeft() != null) {
			 getBalanceFactor(root.getLeft());
		 }
		 System.out.println(root.getHeight());
		 if (root.getRight() != null) {
			 getBalanceFactor(root.getRight());
		 }
		 System.out.println(root.getHeight());
		 //return greaterHeight - lesserHeight;
		 return 0;
	 	}

	/*
	 * @return the root of the tree.
	 */
	TreeNode getRoot(){                                                //// CHANGE TO PRIVATE
		return this.root;
	}
	
	
	/*
	 * @param root the root of the tree to set.
	 */
	void setRoot(TreeNode root) {
		this.root = root;
	}
	
	int getNumberOfNodes(){
		return this.numberOfNodes;
	}
	
	void setNumberOfNodes(int numberOfNodes){
		this.numberOfNodes = numberOfNodes;
	}

	
	
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * This nested class represents a node in the binary tree.
	 * @author orib
	 *
	 */
										//README DESIGN!!!								// CHANGE TO PRIVATE!!!!!!!!!!!!!!!!!! 
																					// METHODS TOO!!!!!!!!
	public static class TreeNode {
		private Integer value;
		private int height;
		private TreeNode left;
		private TreeNode right;
		private TreeNode father;
		
		/**
		 * Constructor
		 * @param value - The value that the node stores.
		 * @param height - The height of the node in the tree.
		 * @param left - The left child of the node.
		 * @param right - The right child of the node.
		 * @param father - The parent of the node.
		 */
		
		
		/**
		 * Constructs the node with it's value and parent.
		 * @param value - the node's value.
		 * @param father - the node's parent.
		 */
		public TreeNode(int value, TreeNode father){
			this.value  = value;
			this.father = father;
			this.right = null;
			this.left = null;
			this.height = LEAF_HEIGHT;
		}

		/*
		 * Update the height of a node according to its children.
		 */
		void updateHeight(){		
			// 2 children
			if (this.left != null && this.right != null){
				this.height = Math.max(this.left.height,
				this.right.height) + 1;
			}
			// No children
			else if (this.left == null && this.right == null){
				this.height = LEAF_HEIGHT;
			}
			// Only right child
			else if (this.left == null && this.right != null){
				this.height = (this.right.height + 1);
			}
			// Only left child
			else{
				this.height = (this.left.height + 1);
			}
			if (this.father != null){
				this.father.updateHeight();
			}
				
		}
		/**
		 * @return the node's value.
		 */
		public int getValue() {
			return value;
		}
		
		/**
		 * @param value - value to set for the node.
		 */
		public void setValue(int value) {
			this.value = value;
		}
		/**
		 * @return the node's height.
		 */
		public int getHeight() {
			return height;
		}
		/**
		 * @param height - height to set for the node.
		 */
		public void setHeight(int height) {
			this.height = height;
		}
		/**
		 * @return the left child of the node.
		 */
		public TreeNode getLeft() {
			return left;
		}
		/**
		 * @param left - left child to set for the node.
		 */
		public void setLeft(TreeNode left) {
			this.left = left;
		}
		/**
		 * @return the right child of the node.
		 */
		public TreeNode getRight() {
			return right;
		}
		/**
		 * @param right - right child to set for the node.
		 */
		public void setRight(TreeNode right) {
			this.right = right;
		}
		/**
		 * @return the node's parent.
		 */
		public TreeNode getFather() {
			return father;
		}
		/**
		 * @param father - parent to set for the node.
		 */
		public void setFather(TreeNode father) {
			this.father = father;
		}
	}
	



}
