
/**
 * This interface will be implemented by hash sets that support rehashing.
 * the common property of rehashing is more of a specific behavior defined
 *  by one class and used by another.
 * @author OriB
 */
public interface Rehashable {
	/**
	 * When being called, will insert all the elements from the current table
	 * to a new one with different capacity, by calling the 'add' method.
	 * @param newHashSet - the new set object we will rehash to it's table. 
	 * Notice that I had to use casting in order to match the interface's
	 * requirements.
	 */
	public void rehash(SimpleHashSet newTable);
}
