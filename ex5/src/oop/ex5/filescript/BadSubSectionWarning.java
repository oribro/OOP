package oop.ex5.filescript;

/**
 * This warning will be thrown when there is an invalid type or parameters 
 * of filter/order in a line of the command file.
 * @author OriB
 */
public class BadSubSectionWarning extends MyException{
private static final long serialVersionUID	= 1L;
	
	
	/**
	 * Constructor.
	 */
	public BadSubSectionWarning(){
		super();
	}
}
