package filescript.filterpackage;

import java.io.File;

import filescript.MyUtilities;

/**
 * Filters files with size between (inclusive) the given numbers (in k-bytes).
 * @author orib
 */
public class BetweenFilter implements Filter{

	private double upperSizeCriteria;
	private double lowerSizeCriteria;
	
	/**
	 * Constructor
	 * @param upperSizeCriteria - the upper size to compare to.
	 * @param lowerSizeCriteria - the lower size to compare to.
	 * @throws BadFilterSizeWarning if the given input is invalid (negative
	 * or wrong order of criteria)
	*/
	public BetweenFilter(String lowerSizeCriteria, String upperSizeCriteria)
			throws BadFilterSizeWarning{
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
		double fileSize = MyUtilities.getFileSize(file);
		if (fileSize >= this.lowerSizeCriteria &&
				fileSize <= this.upperSizeCriteria){		
			return true;
		}
		return false;
	}
	
	/**
	 * Get a warning regarding the filter.
	 * @return - filter warning.
	 */
	public String getWarning(){
		return null;
	}
}
