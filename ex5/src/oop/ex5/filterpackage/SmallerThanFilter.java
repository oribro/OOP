package oop.ex5.filterpackage;

import java.io.File;

import oop.ex5.filescript.MyUtilities;


/**
 * Filters files with size strictly less than the given numbber of k-bytes.
 * @author orib
 */
public class SmallerThanFilter implements Filter{
	private double sizeCriteria;
	private boolean warning;
	/**
	 * Constructor
	 * @param sizeCriteria - the size to compare to.
	 * @throws BadFilterSizeWarning if the given criteria is negative.
	*/
	public SmallerThanFilter(String sizeCriteria) throws BadFilterSizeWarning{
		this.warning = false;
		this.sizeCriteria = Double.parseDouble(sizeCriteria);
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
		// Get file size.
		double fileSize = MyUtilities.getFileSize(file);
		if (fileSize < this.sizeCriteria){
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

