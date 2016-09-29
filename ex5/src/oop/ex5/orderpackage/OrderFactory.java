package oop.ex5.orderpackage;



/**
 * This class creates order objects according to the factory design pattern.
 * @author orib
 */
public class OrderFactory {
	// The location of the order name in the command string.
		private final static int ORDER_NAME_INDEX = 0;
		// Constants for the names of the orders.
		private final static String REVERSE_ORDER_NAME = "REVERSE";
		private final static String ABS_ORDER_NAME = "abs";
		private final static String SIZE_ORDER_NAME = "size";
		private final static String TYPE_ORDER_NAME = "type";
		// The separator in the command string.                                 
		private final static String COMMAND_DELIMITER = "#";
		
		
		
		/**
		 * Create a filter according to a given command string.
		 * @param filterString - the command to create a filter.
		 * @return the filter that was created.
		 * @throws BadOrderNameWarning if the order type name was invalid.
		 */
		@SuppressWarnings("finally")
		public static Order createOrder(String orderString)
				throws BadOrderNameWarning{
			// Order by absolute value if the command string is null.
			if (orderString == null){
				return new AbsOrder();
			}
			// Separate the command to create an order to different parts.
			String[] commandParts = orderString.split(COMMAND_DELIMITER);
			// Keep the name of the order.
			String orderName = commandParts[ORDER_NAME_INDEX];
			Order order = null;
			// Try to create an order.
			try{
				if (orderName.equals(SIZE_ORDER_NAME)){
					order = new SizeOrder();
				}
				else if (orderName.equals(TYPE_ORDER_NAME)){
					order = new TypeOrder();
				}
				else if (orderName.equals(ABS_ORDER_NAME)){
					order = new AbsOrder();
				}
				else{
					throw new BadOrderNameWarning();
				}
				// We need to reverse a filter.
				if (commandParts[commandParts.length - 1].equals
						(REVERSE_ORDER_NAME)){
					order = new ReverseOrder(order);
				}
			}
			// The order could not be created as requested. Set warning on
			// and use default abs order.
			catch (BadOrderNameWarning warning){
				order = new AbsOrder();
				order.setWarning();
			}finally{
				return order;
			}
		}
}