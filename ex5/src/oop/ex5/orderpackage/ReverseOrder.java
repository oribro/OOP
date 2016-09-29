package oop.ex5.orderpackage;

import java.io.File;


/**
 * Orders files in a reverse order of a given order using decorator.
 * @author orib
 */
public class ReverseOrder implements Order {
	
	private Order order;
	private boolean warning;
	
	/**
	 * Constructor.
	 * @param order - the order we reverse.
	 */
	public ReverseOrder(Order order){
		this.order = order;
		this.warning = this.order.getWarning();
	}
	
	/**
	 * Create an ordering between two files.
	 * @param file1 - the first file to be compared.
	 * @param file2 - the second file to be compared.
	 * @return -1 if file2 is bigger,
	 * 			1 if file1 is bigger,
	 * 			0 if the files are equal.
	 */
	
	public int compare(File file1, File file2){
		if (order.compare(file1, file2) == FILE1_BIGGER){
			return FILE2_BIGGER;
		}
		else if (order.compare(file1, file2) == FILE2_BIGGER){
			return FILE1_BIGGER;
		}
		else
			return EQUAL_FILES;
	}
	
	/**
	 * @return True if there was a warning while creating the order,
	 * 			false otherwise.
	 */
	public boolean getWarning(){
		return this.warning;
	}
	
	/**
	 * Set the order warning to be true.
	 */
	public void setWarning(){
		this.warning = true;
	}

}
