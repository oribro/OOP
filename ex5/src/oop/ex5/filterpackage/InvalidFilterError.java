package oop.ex5.filterpackage;

import oop.ex5.filescript.BadCommandFileError;

/**
 * This error will be thrown if 'FILTER' was misspelled.
 * @author OriB
 */
public class InvalidFilterError extends BadCommandFileError{
	private	static final long serialVersionUID	= 1L;
	
	/**
	 * Constructor.
	 */
	public InvalidFilterError(){
		super();
	}
}
