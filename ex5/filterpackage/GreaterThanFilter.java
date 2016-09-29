package filescript.filterpackage;

import java.io.File;

import filescript.MyUtilities;

/**
 * Filters files with file size strictly greater than the given number
 *  of k-bytes
 * @author orib
 */
public class GreaterThanFilter implements Filter {
	private double sizeCriteria;
	/**
	 * Constructor
	 * @param sizeCriteria - the size to compare to.
	 * @throws BadFilterSizeWarning if the given criteria is negative.
	 */
	public GreaterThanFilter(String sizeCriteria) throws BadFilterSizeWarning{
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
		double fileSize = MyUtilities.getFileSize(file);
		if (fileSize > this.sizeCriteria){
			return true;
		}
		return false;
	}
	
}
