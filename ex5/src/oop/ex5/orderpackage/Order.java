package oop.ex5.orderpackage;
import java.io.File;
import java.util.Comparator;

/**
 * This interface represents a general ordering of files.
 * @author orib
 */
public interface Order extends Comparator<File>{
	final static int FILE1_BIGGER = 1;
	final static int FILE2_BIGGER = -1;
	final static int EQUAL_FILES = 0;
	
	
	/**
	 * Create an ordering between two files.
	 * @param file1 - the first file to be compared.
	 * @param file2 - the second file to be compared.
	 * @return -1 if file2 is bigger,
	 * 			1 if file1 is bigger,
	 * 			0 if the files are equal.
	 */
	public int compare(File file1, File file2);
	
	/**
	 * @return True if there was a warning while creating the order,
	 * 			false otherwise.
	 */
	public boolean getWarning();
	
	/**
	 * Set the order warning to be true.
	 */
	public void setWarning();
}
