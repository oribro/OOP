package filescript.filterpackage;

import java.io.File;

import filescript.MyUtilities;

/**
 * Filters according to the string contained in the file name (excluding path)
 * @author orib
 */
public class ContainsFilter implements Filter {
	private String nameCriteria;
	/**
	 * Constructor.
	 * @param nameCriteria - the name of the file to filter with.
	 */
	public ContainsFilter(String nameCriteria){
		this.nameCriteria = nameCriteria;
	}
	
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		String fileName = MyUtilities.getFileName(file);
		if (fileName.contains(this.nameCriteria)){
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
