package oop.ex5.filterpackage;

import java.io.File;

import oop.ex5.filescript.MyUtilities;


/**
 * Filters files with file size strictly greater than the given number
 *  of k-bytes
 * @author orib
 */
public class GreaterThanFilter implements Filter {
	private double sizeCriteria;
	private boolean warning;
	/**
	 * Constructor
	 * @param sizeCriteria - the size to compare to.
	 * @throws BadFilterSizeWarning if the given criteria is negative.
	 */
	public GreaterThanFilter(String sizeCriteria) throws BadFilterSizeWarning{
		this.warning = false;
		this.sizeCriteria = Double.parseDouble(sizeCriteria);
		// Bad parameter.
		if (this.sizeCriteria < 0){
			throw new BadFilterSizeWarning();
		}
	}
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		// Get the size of the file.
		double fileSize = MyUtilities.getFileSize(file);
		if (fileSize > this.sizeCriteria){
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
