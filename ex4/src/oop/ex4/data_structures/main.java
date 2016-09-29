package oop.ex4.data_structures;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[]Bargs){
		int [] ar = {10,8,5,2,3,1,9,7,4,6};
		AvlTree tree = new AvlTree();
		BTreePrinter.printNode(tree.getRoot());
		
		
		

	
		/**
		tree.add(10);
		BTreePrinter.printNode(tree.getRoot());
		tree.add(6);
		BTreePrinter.printNode(tree.getRoot());
		tree.add(12);
		BTreePrinter.printNode(tree.getRoot());
		tree.add(13);
		BTreePrinter.printNode(tree.getRoot());
		tree.add(3);
		BTreePrinter.printNode(tree.getRoot());
		System.out.println(tree.contains(5));
		//tree.delete(6);
		//BTreePrinter.printNode(tree.getRoot());
		System.out.println(tree.getRoot().getHeight());
		//System.out.println(tree.getBalanceFactor(tree.treeSearch(6)));
		*/
	}

}
