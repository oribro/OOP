/**
 * This class represents a hash table with chaining
 * to solve collisions issues.
 * @author orib
 *
 */
public class ChainedHashSet extends SimpleHashSet implements Rehashable{
	// The table of the set. See README for explanation about implementation.
	public LinkedCell[] chainedHashTable;

	/**
	 * Constructs a new, empty table with the specified load factors,
	 *  and the default initial capacity (16).
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
		this.chainedHashTable = new LinkedCell[this.INITIAL_TABLE_SIZE];
	}
	/**
	 * A default constructor. Constructs a new, empty table with default
	 *  initial capacity (16), upper load factor (0.75) and lower load
	 *   factor (0.25).
	 */
	public ChainedHashSet(){
		super();
		this.chainedHashTable = new LinkedCell[this.INITIAL_TABLE_SIZE];
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements one
	 *  by one. Duplicate values should be ignored. The new table has the
	 *  default values of initial capacity (16), upper load factor (0.75),
	 *  and lower load factor (0.25).
	 * @param data - Values to add to the set.
	 */
	public ChainedHashSet(java.lang.String[] data){
		super();
		this.chainedHashTable = new LinkedCell[this.INITIAL_TABLE_SIZE];
		this.fillTable(data);
	}
	
	/**
	 * A constructor to create a new table with a new size.
	 * @param newSize - the new size of the new table.
	 */
	public ChainedHashSet(int newSize){
		super();
		this.chainedHashTable = new LinkedCell[newSize];
	}
	
	
	/**
	 * When being called, will insert all the elements from the current table
	 * to a new one with different capacity, by calling the 'add' method.
	 * @param newHashSet - the new set object we will rehash to it's table. 
	 * Notice that I had to use casting in order to match the interface's
	 * requirements.
	 */
	public void rehash(SimpleHashSet newHashSet){
		// Down-casting to complete the rehashing for this specific kind
		// of hash set.
		ChainedHashSet newChainedHashSet = (ChainedHashSet) newHashSet;
		// Save the capacity of the old table.
		int tableSize = this.capacity();
		// Rehash the elements only for non-empty cells.
		for(int i = 0; i < tableSize; i++){
			if(this.chainedHashTable[i] != null){
				// Use linked-list methods to retrieve the elements.
				while(this.chainedHashTable[i].chainedList.isEmpty()
						== false){
					newChainedHashSet.add(this.chainedHashTable[i]
							.chainedList.pop());
				}		
			}
		}
		// The new table is now rehashed and ready to go.
		this.chainedHashTable = newChainedHashSet.chainedHashTable;
	}
	
	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public boolean add(String newValue){
		// Checking for duplicate value. We don't like duplicates in the set.
		if (this.contains(newValue) == true)
			return false;
		// The value can be added.
		else{
			// We are adding.
			this.updateFlag = true;
			// The index of cell in the table to be hashed to.
			int cellOfValueToAdd = this.getHashValue(newValue);
			// If we add a value to this cell for the first time, we will
			// need to create a new linked list in this cell to start chaining
			if(this.chainedHashTable[cellOfValueToAdd] == null){
				this.chainedHashTable[cellOfValueToAdd] = new LinkedCell();
			}
			this.chainedHashTable[cellOfValueToAdd].chainedList
			.add(newValue);
			this.updateTable(updateFlag);
			// Rehashing check.
			if (this.currentLoadFactor > this.upperLoadFactor){
				this.resizeAndRehash(this.updateFlag);
			}
			return true;
		}
	}

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal){
		// Getting the hash value for the input 
		int cellOfValueToSearch = this.getHashValue(searchVal);

		// Checking if the table in this index is already a Cell 
		// or still a null
		if(this.chainedHashTable[cellOfValueToSearch] != null){
			// Returning the results of the search in the relevant cell
			return this.chainedHashTable[cellOfValueToSearch].chainedList
					.contains(searchVal);
		}
		// This cell has no chaining in it, meaning the value is not there.
		else{
			return false;
		}
	}
	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public boolean delete(String toDelete){
		// Checking if the input is in - if not return false
		if(this.contains(toDelete) == false){
			return false;
		}
		// We are deleting.
		this.updateFlag = false;
		//Getting the hash value for the input, removing it and updating size
		int cellOfValueToDelete = this.getHashValue(toDelete);
		this.chainedHashTable[cellOfValueToDelete].chainedList.remove(toDelete);
		this.updateTable(this.updateFlag);
		
				
		// Checking for underloading and a need to resize, rehash.
		if(this.currentLoadFactor < this.lowerLoadFactor){
			this.resizeAndRehash(this.updateFlag);
		}	
		return true;
	}
	
	 //This method will create a new table with different size and perform
	 // rehashing onto that table.
	 //  @param updateFlag - true to increase the size by 2 times.
	 //                false to decrease by 2 times.
	private void resizeAndRehash(boolean updateFlag){
		int newSize = resize(updateFlag);
		// Up-casting for the rehash method.
		SimpleHashSet newHashSet = new ChainedHashSet(newSize);
		this.rehash(newHashSet);
	}

	/**
 	* @return  The current capacity (number of cells) of the table.
 	*/
	public int capacity(){
		return this.chainedHashTable.length;
	}
}
