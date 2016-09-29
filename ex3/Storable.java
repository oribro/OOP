/**
 * This interface helps in storing the table's keys in a temporary data
 * structure like an array.
 * @author OriB
 */
public interface Storable{
	/**
	 * Copy the keys (string values) of the table to an array for rehashing.
	 * @return an array with the table's keys.
	 */
	public abstract String[] fromTableToArray();
	/**
	 * Copy string values from an array to a table.
	 * @param values - the string values to copy to the table.
	 * @return a table with the copied string values.
	 */
	public abstract LinkedCell[] fromArrayToTable(String[] values);
}
