package oop.ex5.filterpackage;

/**
 * This warning will be thrown if the input name of filter was misspelled.
 * @author OriB
 */
public class BadFilterNameWarning extends BadFilterWarning{
	private static final long serialVersionUID	= 1L;
	
	/**
	 * Constructor.
	 */
	public BadFilterNameWarning() {
		super(); 
	}
}
