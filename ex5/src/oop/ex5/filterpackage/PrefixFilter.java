package oop.ex5.filterpackage;

import java.io.File;

import oop.ex5.filescript.MyUtilities;


/**
 * Filters files by the prefix of the file name (excluding path).
 * @author orib
 */

public class PrefixFilter implements Filter {
	private String prefixCriteria;
	private boolean warning;
	
	/**
	 * Constructor.
	 * @param prefixCriteria - the prefix we filter by.
	 */
	public PrefixFilter(String prefixCriteria){
		this.warning = false;
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
