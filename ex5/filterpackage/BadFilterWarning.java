package filescript.filterpackage;

import filescript.MyException;



public class BadFilterWarning extends MyException{
	private static final long serialVersionUID	= 1L;
	
	
	/**
	 * Constructor for type 1 errors - warnings.
	 * @param lineOfWarning - The line where the warning was found.
	 */
	public BadFilterWarning(){
		super();
	}
}
