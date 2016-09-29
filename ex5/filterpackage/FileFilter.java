package filescript.filterpackage;

import java.io.File;

import filescript.MyUtilities;
/**
 * Filters the file if it equals the given file name (excluding path).
 * @author orib
 */
public class FileFilter implements Filter {
	private String nameCriteria;
	/**
	 * Constructor.
	 * @param nameCriteria - the name of the file to filter with.
	 */
	public FileFilter(String nameCriteria){
		this.nameCriteria = nameCriteria;
	}
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		String fileName = MyUtilities.getFileName(file);
		if (fileName.equals(this.nameCriteria)){
			return true;
		}
		return false;
	}
}
