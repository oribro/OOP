package oop.ex5.filterpackage;

import java.io.File;

/**
 * All files are matched.
 * @author orib
 *
 */
public class AllFilter implements Filter {
	// True if there was a warning while creating this filter
	// False otherwise.
	private boolean warning;
	
	/**
	 * Constructor.
	 */
	public AllFilter(){
		this.warning = false;
	}
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		return true;
	}
	
	/**
	 * @return this filter warning.
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
