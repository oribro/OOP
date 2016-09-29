package filescript.filterpackage;

import java.io.File;

import filescript.MyUtilities;

public class SuffixFilter implements Filter {
	private String suffixCriteria;
	
	/**
	 * Constructor.
	 * @param suffixCriteria - the suffix we filter by.
	 */
	public SuffixFilter(String suffixCriteria){
		this.suffixCriteria = suffixCriteria;
	}
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		String fileName = MyUtilities.getFileName(file);
		if (fileName.endsWith(this.suffixCriteria)){
			return true;
		}
		return false;
	}
}
