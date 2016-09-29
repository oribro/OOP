package oop.ex5.filterpackage;

/**
 * This warning will be thrown if the parameters of one of the
 * GreaterThan/SmallerThan/Between filters were invalid.
 * @author OriB
 */
public class BadFilterSizeWarning extends BadFilterWarning{
	private static final long serialVersionUID	= 1L;
	
	/**
	 * Constructor.
	 */
	public BadFilterSizeWarning() {
		super(); 
	}
}
