package filescript.filterpackage;

import java.io.File;

/**
 * Filters a file by the negation of a given filter using decorator.
 * @author orib
 */
public class NegFilter implements Filter{

	
	private Filter filter;
	
	/**
	 * Constructor.
	 * @param filter - the filter we negate.
	 */
	public NegFilter(Filter filter){
		this.filter = filter;
	}
	
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		return !filter.isPass(file);
	}
	
	
}
