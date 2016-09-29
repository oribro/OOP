package oop.ex5.filterpackage;

import java.io.File;

/**
 * Filters files by their presence of writing permission.
 * @author orib
 *
 */
public class WritableFilter implements Filter {
	
	private boolean permissionCriteria;
	private boolean warning;
	
	/**
	 * Constructor.
	 * @param permissionCriteria - 'YES' if the file is writable,
	 * 							 'NO' otherwise.
	 * * @throws BadFilterPropertyWarning if the given criteria is not YES/NO
	 */
	public WritableFilter(String permissionCriteria) throws
	BadFilterPropertyWarning{
		this.warning = false;
		if (permissionCriteria.equals(HAS_PROPERTY)){
			this.permissionCriteria = true;
		}
		else if (permissionCriteria.equals(HAS_NOT_PROPERTY)){
			this.permissionCriteria = false;
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
		return this.permissionCriteria == file.canWrite();
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
