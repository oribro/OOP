package oop.ex5.filterpackage;

import java.io.File;


/**
 * Filters a file by the negation of a given filter using decorator.
 * @author orib
 */
public class NegFilter implements Filter{

	private Filter filter;
	private boolean warning;
	
	/**
	 * Constructor.
	 * @param filter - the filter we negate.
	 */
	public NegFilter(Filter filter){
		this.filter = filter;
		this.warning = filter.getWarning();
		
	}
	
	/**
	 * @return true if the file matches the filter's criteria,
	 *         false otherwise.
	 * @param file - the file to filter.
	 */
	public boolean isPass(File file){
		return !filter.isPass(file);
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
