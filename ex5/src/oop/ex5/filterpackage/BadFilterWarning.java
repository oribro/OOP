package oop.ex5.filterpackage;

import oop.ex5.filescript.BadSubSectionWarning;



/**
 * This warning will be thrown if the name or parameters for a filter
 * were invalid.
 * @author OriB
 */
public class BadFilterWarning extends BadSubSectionWarning{
	private static final long serialVersionUID	= 1L;
	
	
	/**
	 * Constructor.
	 */
	public BadFilterWarning(){
		super();
	}
}
