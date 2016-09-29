package filescript.filterpackage;

import java.io.File;

import filescript.MyUtilities;

/**
 * Filters files by the prefix of the file name (excluding path).
 * @author orib
 */

public class PrefixFilter implements Filter {
	private String prefixCriteria;
	
	/**
	 * Constructor.
	 * @param prefixCriteria - the prefix we filter by.
	 */
	public PrefixFilter(String prefixCriteria){
		this.prefixCriteria = prefixCriteria;
	}
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		String fileName = MyUtilities.getFileName(file);
		if (fileName.startsWith(this.prefixCriteria)){
			return true;
		}
		return false;
	}
}
