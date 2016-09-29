package oop.ex5.filterpackage;

/**
 * This warning will be thrown if the parameters of one of the
 * hidden/executable/writable filters were invalid.
 * @author OriB
 */
public class BadFilterPropertyWarning extends BadFilterWarning{
	private static final long serialVersionUID	= 1L;
	
	/**
	 * Constructor.
	 */
	public BadFilterPropertyWarning() {
		super(); 
	}

}
