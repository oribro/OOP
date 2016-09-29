package filescript.filterpackage;

import java.io.File;

/**
 * All files are matched.
 * @author orib
 *
 */
public class AllFilter implements Filter {
	
	private String warning;
	
	/**
	 * Constructor.
	 * @param warning - the warning that caused us to use this filter.
	 */
	public AllFilter(String warning){
		this.warning = warning;
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
	public String getWarning(){
		return this.warning;
	}
}
