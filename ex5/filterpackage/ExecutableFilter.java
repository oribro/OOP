package filescript.filterpackage;

import java.io.File;

/**
 * Filters files by their presence of execute permission.
 * @author orib
 */
public class ExecutableFilter implements Filter {
	
	private boolean permissionCriteria;
	
	/**
	 * Constructor.
	 * @param permissionCriteria - 'YES' if the file is executable,
	 * 							 'NO' otherwise.
	 * * @throws BadFilterPropertyWarning if the given criteria is not YES/NO
	 */
	public ExecutableFilter(String permissionCriteria) throws
	BadFilterPropertyWarning{
		
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
		return this.permissionCriteria == file.canExecute();
	}
	
}
