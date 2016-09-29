package filescript.filterpackage;

/**
 * This class creates filter objects according to the factory design pattern.
 * @author orib
 */
public class FilterFactory {
	// The location of the filter name in the command string.
	private final static int FILTER_NAME_INDEX = 0;
	// The location of the filter value in the command string.
	private final static int FILTER_FIRST_VALUE = 1;
	private final static int FILTER_SECOND_VALUE = 2;
	// The separator in the command string.                                   ///CHANGE TO MAIN CLASS
	private final static String COMMAND_DELIMITER = "#";
	// Constants for the names of the filters.
	private final static String GREATER_THAN_FILTER_NAME = "greater_than";
	private final static String SMALLER_THAN_FILTER_NAME = "smaller_than";
	private final static String BETWEEN_FILTER_NAME = "between";
	private final static String FILE_FILTER_NAME = "file";
	private final static String CONTAINS_FILTER_NAME = "contains";
	private final static String PREFIX_FILTER_NAME = "prefix";
	private final static String SUFFIX_FILTER_NAME = "suffix";
	private final static String WRITABLE_FILTER_NAME = "writable";
	private final static String EXECUTABLE_FILTER_NAME = "executable";
	private final static String HIDDEN_FILTER_NAME = "hidden";
	private final static String ALL_FILTER_NAME = "all";
	private final static String NEGATION_FILTER_NAME = "NOT";
	private final static String NO_WARNING = null;
	
	
	/**
	 * Create a filter according to a given command string.
	 * @param filterString - the command to create a filter.
	 * @return the filter that was created.
	 */
	public static Filter createFilter(String filterString, int lineOfWarning)
			throws BadFilterWarning{
		// Separate the command to create a filter to different parts.
		String[] commandParts = filterString.split(COMMAND_DELIMITER);
		// Keep the name of the filter.
		String filterName = commandParts[FILTER_NAME_INDEX];
		
		try{
			// We need to negate a filter.
			if (commandParts[commandParts.length - 1].equals
					(NEGATION_FILTER_NAME)){
				// The string without the "NOT".
				String positiveFilterString = filterString.substring(
						FILTER_NAME_INDEX, filterString.lastIndexOf(
								COMMAND_DELIMITER));
				// Recursively create a positive filter to negate.
				return new NegFilter(createFilter(positiveFilterString,
						lineOfWarning));
			}
			// No need to negate.
			else{
				if (filterName.equals(GREATER_THAN_FILTER_NAME)){
					return new GreaterThanFilter(commandParts[FILTER_FIRST_VALUE]
							);
				}
				else if (filterName.equals(SMALLER_THAN_FILTER_NAME)){
					return new SmallerThanFilter(commandParts[FILTER_FIRST_VALUE]
							);
				}
				else if (filterName.equals(BETWEEN_FILTER_NAME)){
					return new BetweenFilter(commandParts[FILTER_FIRST_VALUE],
							commandParts[FILTER_SECOND_VALUE]);
				}
				else if (filterName.equals(FILE_FILTER_NAME)){
					return new FileFilter(commandParts[FILTER_FIRST_VALUE]);
				}
				else if (filterName.equals(CONTAINS_FILTER_NAME)){
					return new ContainsFilter(commandParts[FILTER_FIRST_VALUE]);
				}
				else if (filterName.equals(PREFIX_FILTER_NAME)){
					return new PrefixFilter(commandParts[FILTER_FIRST_VALUE]);
				}
				else if (filterName.equals(SUFFIX_FILTER_NAME)){
					return new SuffixFilter(commandParts[FILTER_FIRST_VALUE]);
				}
				else if (filterName.equals(WRITABLE_FILTER_NAME)){
					return new WritableFilter(commandParts[FILTER_FIRST_VALUE]);
				}
				else if (filterName.equals(EXECUTABLE_FILTER_NAME)){
					return new ExecutableFilter(commandParts[FILTER_FIRST_VALUE]);
				}
				else if (filterName.equals(HIDDEN_FILTER_NAME)){
					return new HiddenFilter(commandParts[FILTER_FIRST_VALUE]);
				}
				else if (filterName.equals(ALL_FILTER_NAME)){
					return new AllFilter(NO_WARNING);
				}
				else{
					throw new BadFilterNameWarning();
				}
			}
		}	
		catch (BadFilterWarning warning){
			return new AllFilter("Warning in line " + lineOfWarning);
		}
	}
}
