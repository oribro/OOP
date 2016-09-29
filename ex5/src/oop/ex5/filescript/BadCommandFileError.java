package oop.ex5.filescript;

/**
 * This error will be throws when there is an invalid sub section
 * of the command file - no FILTER/ORDER or no filter type.
 * @author OriB
 */
public class BadCommandFileError extends MyError{
	private	static final long serialVersionUID	= 1L;
	
	/**
	 * Constructor.
	 */
	public BadCommandFileError() {
		super(ERROR_MESSAGE);
	}

}
