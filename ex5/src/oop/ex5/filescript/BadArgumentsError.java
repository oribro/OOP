package oop.ex5.filescript;

/**
 * This error will be thrown when the program's arguments are
 * not in the right format ([sourcedir, commandfile]).
 * @author OriB
 */
public class BadArgumentsError extends MyError{
	private	static final long serialVersionUID	= 1L;
	
	/**
	 * Constructor.
	 */
	public BadArgumentsError(){
		super(ERROR_MESSAGE);
	}
}
