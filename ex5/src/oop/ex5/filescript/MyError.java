package oop.ex5.filescript;

/**
 * This class is for type 2 errors of the program. 
 * These errors will result in printing "ERROR" to STDERR and exiting
 * the program.
 * @author OriB
 */
public class MyError extends Error{
	
	private static final long serialVersionUID	= 1L;
	
	public final static String ERROR_MESSAGE = "ERROR";
	
	protected String message;
	
	/**
	 * Default constructor.
	 */
	public MyError(){
	}
	
	/**
	 * Constructor for type 2 errors.
	 * @param message - The error message thrown.
	 */
	public MyError(String message){
		this.message = message;
	}
	
}
