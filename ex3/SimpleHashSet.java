/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 * @author orib
 *
 */

public abstract class SimpleHashSet implements SimpleSet {
	
	protected final int INITIAL_TABLE_SIZE = 16;
	protected final float INITIAL_UPPER_LOAD_FACTOR = 0.75f;
	protected final float INITIAL_LOWER_LOAD_FACTOR = 0.25f;
	// We change the capacity of the table by division/multiplication of 2.
	protected final int CAPACITY_MODIFIER = 2;
	
	// The current amount of values in the table.
	protected int currentNumberOfElements;
	// The upper bound of the load factor for this set.
	protected float upperLoadFactor;
	// The lower bound of the load factor for this set.
	protected float lowerLoadFactor;
	// The current the load factor for this set.
	protected float currentLoadFactor;
	// A variable to assist in resizing the table.
	protected int tableSizeMinusOne;
	// A flag for updating the hash table. True means we want to add a value,
	// False means we want to delete a value.
	protected boolean updateFlag;
	
	/**
	 * Constructor with the default upper load factor (0.75) and lower load factor (0.25).
	 */
	public SimpleHashSet(){
		this.upperLoadFactor = INITIAL_UPPER_LOAD_FACTOR;
		this.lowerLoadFactor = INITIAL_LOWER_LOAD_FACTOR;
		this.currentNumberOfElements = 0;
		this.currentLoadFactor = 0;
	}
	/**
	 * Constructor with the specified load factors.
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
		this.lowerLoadFactor = lowerLoadFactor;
		this.upperLoadFactor = upperLoadFactor;
		this.currentNumberOfElements = 0;
		this.currentLoadFactor = 0;
	}
	/**
	 *  Data constructor - builds the hash set by adding the elements one
	 *  by one. Duplicate values should be ignored. The new table has the
	 *  default values of initial capacity (16), upper load factor (0.75),
	 *  and lower load factor (0.25).
	 * @param data
	 */
	public SimpleHashSet(java.lang.String[] data){}
	
	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public abstract boolean add(String newValue);
	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public abstract boolean contains(String searchVal);
	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public abstract boolean delete(String toDelete);
	/**
	 * @return The number of elements currently in the set
	 */
	public int size(){
		return this.currentNumberOfElements;
	}
	/**
	 * @return  The current capacity (number of cells) of the table.
	 */
	public abstract int capacity();
	/**
	 * builds the hash set by adding the elements one by one
	 * @param data - Values to add to the set.
	 */
	protected void fillTable(java.lang.String[] data){
		this.currentNumberOfElements = 0;
		for (int i = 0; i < data.length; i++){
			this.add(data[i]);
		}
	}
	
	/**
	 * a method to calculate the hash value for a given string according
	 * to the size of the table.
	 * 
	 * @param str - the string that needs a hash value
	 * @return the hash value for the current table
	 */
	protected int getHashValue(String str){
		this.tableSizeMinusOne = this.capacity() - 1;
		return (Math.abs(str.hashCode())) & this.tableSizeMinusOne;
	}
	
	/**
	 * Update table properties upon successfully adding/deleting an element.
	 * @param updateFlag - true for adding an element and increasing load.
	 * 					false for deleting an element and decreasing load.
	 */
	protected void updateTable(boolean updateFlag){
		// An element has been added to the table.
		if (updateFlag == true){
			this.currentNumberOfElements++;
			this.currentLoadFactor = (float) this.size() / this.capacity();
		}
		// An element has been removed from the table.
		else{
			this.currentNumberOfElements--;
			this.currentLoadFactor = (float) this.size() / this.capacity();
		}
	}
	/**
	 * Gets the new size of the table after a decision has been made to resize 
	 * @param resizeFlag - true for increasing the size by 2 times.
	 * 					false for decreasing the size by 2 times.
	 * @return The new size of the table.
	 */
	protected int resize(boolean resizeFlag){
		// Save the old capacity.
		int arrayCapacity = this.capacity();
		// The size to be returned.
		int newSize;
		if (resizeFlag == true)
			newSize = (arrayCapacity <<= 1);
		else
			newSize = (arrayCapacity >>= 1);
		return newSize;
	}
	
	
}
