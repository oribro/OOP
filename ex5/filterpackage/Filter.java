package filescript.filterpackage;

import java.io.File;
/**
 * This interface represents a general filter for files.
 * @author orib
 */

public interface Filter {
	final static String HAS_PROPERTY = "YES";
	static String HAS_NOT_PROPERTY = "NO";
	
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file);
	
}
