package oop.ex4.data_structures;

import java.util.Iterator;

/**
 * This class implements a BST with actions to perform on it.
 * @author orib
 *
 */
public class BinarySearchTree implements Iterable<Integer>{
	protected static final int VALUE_NOT_FOUND = -1;
	// The height of nodes without children.
	private static final int LEAF_HEIGHT = 0;
	// The node of the tree that represents the subtree rooted at root.
	private TreeNode root;
	// The number of nodes currently in the tree.
	private int numberOfNodes;
	
	/**
	 * Default constructor.
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
		// Construct the root of the tree.
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
	/*
	 * Searches for the given value in the tree.
	 * @param searchVal - the value to search.
	 * @return the node in which the value is stored. If not found 
	 * returns null.
	 */
	TreeNode treeSearch(int searchVal){                  
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
		// The depth of a node is simply the difference between the heights
		// of the root and the node.
		return getNodeDepth(desiredNode);	
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
		// Keep the father of the node we want to delete.
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
		// Update the height of the node's father since it was changed.
		if (nodeToDeleteFather != null)
			nodeToDeleteFather.updateHeight();
		// Deletion process has been completed.
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
		// Null check. I did not throw an exception here because other
		// methods use the null value that is returned (I could catch the
		// exception but i'm not experienced with it yet).
		if (node == null)
			return null;
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
	/* 
	 * @param node - the node to get the depth of.
	 * @return the depth of the node in the tree counting from the root.
	 */
	int getNodeDepth(TreeNode node){
		if (node == this.getRoot()){
			return 0;
		}
		return (getNodeDepth(node.father) + 1);
	}
	/*
	 * @return the root of the tree.
	 */
	TreeNode getRoot(){                                             
		return this.root;
	}
	
	
	/*
	 * @param root the root of the tree to set.
	 */
	void setRoot(TreeNode root) {
		this.root = root;
	}
	
	/*
	 * @return the number of nodes in the tree.
	 */
	int getNumberOfNodes(){
		return this.numberOfNodes;
	}
	
	/*
	 * @param numberOfNodes - the number of nodes to set.
	 */
	void setNumberOfNodes(int numberOfNodes){
		this.numberOfNodes = numberOfNodes;
	}

	
	/**
	 * @return an iterator on the Avl Tree. The returned iterator iterates
	 * over the tree nodes in an ascending order, and does NOT implement
	 * the remove() method.
	 */
	
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

	/*
	 * This nested class represents a node in the binary tree.
	 * @author orib
	 *
	 */
																											
	static class TreeNode {
		// The value of the node.
		private Integer value;
		// The height of the node.
		private int height;
		// The left child of the node.
		private TreeNode left;
		// The right child of the node.
		private TreeNode right;
		// The parent of the node.
		private TreeNode father;
		
		
		
		/*
		 * Constructs the node with it's value and parent.
		 * @param value - the node's value.
		 * @param father - the node's parent.
		 */
		TreeNode(int value, TreeNode father){
			this.value  = value;
			this.father = father;
			this.right = null;
			this.left = null;
			this.height = LEAF_HEIGHT;
		}

		/*
		 * Update the height of a node and it's ancestors in the tree.
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
			// Update the height of the ancestors of the node.
			if (this.father != null){
				this.father.updateHeight();
			}
				
		}
		
		/*
		 * @return the node's value.
		 */
		int getValue() {
			return value;
		}
		
		/*
		 * @param value - value to set for the node.
		 */
		void setValue(int value) {
			this.value = value;
		}
		/*
		 * @return the node's height.
		 */
		int getHeight() {
			return height;
		}
		/*
		 * @param height - height to set for the node.
		 */
		void setHeight(int height) {
			this.height = height;
		}
		/*
		 * @return the left child of the node.
		 */
		TreeNode getLeft() {
			return left;
		}
		/*
		 * @param left - left child to set for the node.
		 */
		void setLeft(TreeNode left) {
			this.left = left;
		}
		/*
		 * @return the right child of the node.
		 */
		TreeNode getRight() {
			return right;
		}
		/*
		 * @param right - right child to set for the node.
		 */
		void setRight(TreeNode right) {
			this.right = right;
		}
		/*
		 * @return the node's parent.
		 */
		TreeNode getFather() {
			return father;
		}
		/*
		 * @param father - parent to set for the node.
		 */
		void setFather(TreeNode father) {
			this.father = father;
		}
	}
	



}
