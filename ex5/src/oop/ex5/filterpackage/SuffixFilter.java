package oop.ex5.filterpackage;

import java.io.File;

import oop.ex5.filescript.MyUtilities;

/**
 * Filters files by the suffix of the file name (excluding path).
 * @author OriB
 */
public class SuffixFilter implements Filter {
	private String suffixCriteria;
	private boolean warning;
	
	/**
	 * Constructor.
	 * @param suffixCriteria - the suffix we filter by.
	 */
	public SuffixFilter(String suffixCriteria){
		this.warning = false;
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
	/**
	 * @return True if there was a warning while creating the filter,
	 * 			false otherwise.
	 */
	public boolean getWarning(){
		return this.warning;
	}
	
	/**
	 * Set the filter warning to be true.
	 */
	public void setWarning(){
		this.warning = true;
	}
}
