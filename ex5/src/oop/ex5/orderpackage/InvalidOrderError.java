package oop.ex5.orderpackage;

import oop.ex5.filescript.BadCommandFileError;

/**
 * This error will be thrown if 'ORDER' was misspelled.
 * @author OriB
 */
public class InvalidOrderError extends BadCommandFileError{
	private	static final long serialVersionUID	= 1L;
	
	/**
	 * Constructor.
	 */
	public InvalidOrderError(){
		super();
	}
}
