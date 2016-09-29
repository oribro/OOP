package oop.ex5.orderpackage;

import java.io.File;

import oop.ex5.filescript.MyUtilities;


/**
 * Order files by their type.
 * @author orib
 */
public class TypeOrder implements Order {
	private boolean warning;
	
	public TypeOrder(){
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
		String fileType1 = MyUtilities.getFileType(file1);
		String fileType2 = MyUtilities.getFileType(file2);
		if (fileType1.compareTo(fileType2) > 0){
			return FILE1_BIGGER;
		}
		else if (fileType1.compareTo(fileType2) < 0){
			return FILE2_BIGGER;
		}
		// Order by absolue path (files were equal).
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
