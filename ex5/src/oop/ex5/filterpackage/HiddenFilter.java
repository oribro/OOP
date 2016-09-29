package oop.ex5.filterpackage;

import java.io.File;

/**
 * Filters files by their property of being hidden.
 * @author orib
 *
 */
public class HiddenFilter implements Filter {
	private boolean hiddenCriteria;
	private boolean warning;
	
	/**
	 * Constructor.
	 * @param hiddenCriteria - 'YES' if the file is hidden,
	 * 							 'NO' otherwise.
	 * * @throws BadFilterPropertyWarning if the given criteria is not YES/NO
	 */
	public HiddenFilter(String hiddenCriteria) throws BadFilterPropertyWarning
	{
		this.warning = false;
		if (hiddenCriteria.equals(HAS_PROPERTY)){
			this.hiddenCriteria = true;
		}
		else if (hiddenCriteria.equals(HAS_NOT_PROPERTY)){
			this.hiddenCriteria = false;
		}
		// Bad parameter warning.
		else{
			throw new BadFilterPropertyWarning();
		}
	}
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file) {
		return this.hiddenCriteria == file.isHidden();
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
