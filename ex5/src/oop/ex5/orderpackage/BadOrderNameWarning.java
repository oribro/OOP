package oop.ex5.orderpackage;

import oop.ex5.filescript.BadSubSectionWarning;

/**
 * This warning will be thrown if the name of the order type is invalid.
 * @author OriB
 */
public class BadOrderNameWarning extends BadSubSectionWarning{
	private static final long serialVersionUID	= 1L;
	
	
	/**
	 * Constructor.
	 */
	public BadOrderNameWarning(){
		super();
	}
}