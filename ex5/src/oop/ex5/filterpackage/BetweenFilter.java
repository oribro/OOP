package oop.ex5.filterpackage;

import java.io.File;

import oop.ex5.filescript.MyUtilities;


/**
 * Filters files with size between (inclusive) the given numbers (in k-bytes).
 * @author orib
 */
public class BetweenFilter implements Filter{

	private double upperSizeCriteria;
	private double lowerSizeCriteria;
	private boolean warning;
	
	/**
	 * Constructor
	 * @param upperSizeCriteria - the upper size to compare to.
	 * @param lowerSizeCriteria - the lower size to compare to.
	 * @throws BadFilterSizeWarning if the given input is invalid (negative
	 * or wrong order of criteria)
	*/
	public BetweenFilter(String lowerSizeCriteria, String upperSizeCriteria)
			throws BadFilterSizeWarning{
		this.warning = false;
		this.upperSizeCriteria = Double.parseDouble(upperSizeCriteria);
		this.lowerSizeCriteria = Double.parseDouble(lowerSizeCriteria);
		// Bad input.
		if (this.upperSizeCriteria < this.lowerSizeCriteria ||
				this.lowerSizeCriteria < 0){
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
		if (fileSize >= this.lowerSizeCriteria &&
				fileSize <= this.upperSizeCriteria){		
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
