package oop.ex5.filescript;

import oop.ex5.filterpackage.Filter;
import oop.ex5.orderpackage.Order;
import java.util.ArrayList;

/**
 * This class represents a section in the command file, composed of
 * order and filter objects.
 * @author orib
 */
public class Section {
	private Filter filter;
	private Order order;
	// Save the lines in the section that resulted in warnings.
	private ArrayList<Integer> linesOfWarnings;
	
	/**
	 * Constructor.
	 * @param filter - the filter object in the section.
	 * @param order - the order object in the section.
	 */
	public Section(Filter filter, Order order){
		this.filter = filter;
		this.order = order;
		this.linesOfWarnings = new ArrayList<Integer>();
	}
	
	/**
	 * Filter getter.
	 * @return the section's filter.
	 */
	Filter getFilter() {
		return filter;
	}
	
	/**
	 * Order getter.
	 * @return the section's order.
	 */
	Order getOrder() {
		return order;
	}
	
	/**
	 * @return the lines in the section with warnings.
	 */
	public ArrayList<Integer> getWarnings(){
		return this.linesOfWarnings;
	}
	
}
