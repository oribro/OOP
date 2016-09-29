package oop.ex5.orderpackage;

import java.io.File;


/**
 * Order files by their absolute path.
 * @author orib
 */
public class AbsOrder implements Order {
	private boolean warning;
	
	
	/**
	 * Constructor.
	 */
	public AbsOrder(){
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
		// Get the paths of the files.
		String filePath1 = file1.getAbsolutePath();
		String filePath2 = file2.getAbsolutePath();
		if (filePath1.compareTo(filePath2) > 0){
			return FILE1_BIGGER;
		}
		else if (filePath1.compareTo(filePath2) < 0){
			return FILE2_BIGGER;
		}
		else{
			return EQUAL_FILES;
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
