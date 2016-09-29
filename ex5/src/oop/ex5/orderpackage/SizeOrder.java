package oop.ex5.orderpackage;

import java.io.File;



/**
 * Order files by their size.
 * @author orib
 */
public class SizeOrder implements Order {
	private boolean warning;
	
	public SizeOrder(){
		this.warning = false;
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
		double fileSize1 = file1.length();
		double fileSize2 = file2.length();
		if (fileSize1 > fileSize2){
			return FILE1_BIGGER;
		}
		else if (fileSize1 < fileSize2){
			return FILE2_BIGGER;
		}
		// Order by absolute path (files were equal).
		else{
			AbsOrder absOrder = new AbsOrder();
			return absOrder.compare(file1, file2);
		}
			
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
