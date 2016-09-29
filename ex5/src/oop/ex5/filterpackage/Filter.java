package oop.ex5.filterpackage;

import java.io.File;
/**
 * This interface represents a general filter for files.
 * @author orib
 */

public interface Filter {
	// Constants for parameters of the executable/writable/hidden filters.
	final static String HAS_PROPERTY = "YES";
	static String HAS_NOT_PROPERTY = "NO";
	
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file);
	
	/**
	 * @return True if there was a warning while creating the filter,
	 * 			false otherwise.
	 */
	public boolean getWarning();
	
	/**
	 * Set the filter warning to be true.
	 */
	public void setWarning();
}
