/**
 * This class represents a hash table with quadratic
 * hash function in open addressing to solve collisions issues.
 * @author orib
 *
 */
public class OpenHashSet extends SimpleHashSet implements Rehashable{
	// A flag to mark deleted values from the table. See README for more.
	private static final String DELETION_FLAG = new String("DELETED");
	// This constant will be used whenever we didn't find the wanted cell.
	private static final int CELL_NOT_FOUND = -1;
	// The table of the set.
	private String[] openHashTable;
	// The cell containing the value we wish to find in the table.
	private int cellToFind;
	// The first cell we can add the newValue in the table.
	private int cellToAdd;
	
	/**
	 * Constructs a new, empty table with the specified load factors,
	 *  and the default initial capacity (16).
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
		this.openHashTable = new String[INITIAL_TABLE_SIZE];
		this.currentNumberOfElements = 0;
	}
	/**
	 * A default constructor. Constructs a new, empty table with default
	 *  initial capacity (16), upper load factor (0.75) and lower load
	 *   factor (0.25).
	 */
	public OpenHashSet(){
		super();
		this.openHashTable = new String[INITIAL_TABLE_SIZE];
		this.currentNumberOfElements = 0;
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements one
	 *  by one. Duplicate values should be ignored. The new table has the
	 *  default values of initial capacity (16), upper load factor (0.75),
	 *  and lower load factor (0.25).
	 * @param data - Values to add to the set.
	 */
	public OpenHashSet(java.lang.String[] data){
		super();
		this.openHashTable =  new String[INITIAL_TABLE_SIZE];
		this.fillTable(data);
	}
	/**
	 * A constructor to create a new table with a new size.
	 * @param newSize - the new size of the new table.
	 */
	public OpenHashSet(int newSize){
		super();
		this.openHashTable = new String[newSize];
		this.currentNumberOfElements = 0;
	}
	
	
	
	 // The function that calculates the index of cell in the table we wish
	 // to hash to. We use quadratic probing here.
	// index - The current try to probe the table.
	// newValue - The value to add.
	// returns the desired cell in the table.
	
	private int hashFunction(int index, String newValue){
		int hashValue = this.getHashValue(newValue); 
		return (int) (hashValue + (index + Math.pow(index, 2))/
				CAPACITY_MODIFIER)
				& this.tableSizeMinusOne;
	}
	
	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	
	public boolean add(String newValue){
		// Attempt to find the cell containing the newValue.
		this.cellFinder(newValue, false);
		// Value is in the table, no duplicates allowed.
		if (this.cellToFind != CELL_NOT_FOUND)
			return false;
		
		// We are adding.
		this.updateFlag = true;
		// Attempt to find a cell to insert the newValue to.
		this.cellFinder(newValue, true);
		// We found a cell to add. Add the value here.
		if (this.cellToAdd != CELL_NOT_FOUND)
			this.openHashTable[cellToAdd] = newValue;
		this.updateTable(updateFlag);
		//Checking for resizing.
		if (this.currentLoadFactor > this.upperLoadFactor){
			this.resizeAndRehash(this.updateFlag);
		}
		return true;
	}
	
	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	
	public boolean contains(String searchVal) {
		// Attempt to find the wanted value's cell.
		this.cellFinder(searchVal, false);
		// The value is not in the table.
		if (this.cellToFind == CELL_NOT_FOUND)
			return false;
		// The value is found in the table.
		return true;	
		
	}
	/**
	 * When being called, will insert all the elements from the current table
	 * to a new one with different capacity, by calling the 'add' method.
	 * @param newHashSet - the new set object we will rehash to it's table. 
	 * Notice that I had to use casting in order to match the interface's
	 * requirements.
	 */
	
	public void rehash(SimpleHashSet newHashSet){
		// Down-casting to complete the rehashing for this specific type of
		// hash set.
		OpenHashSet newOpenHashSet = (OpenHashSet) newHashSet;
		// The old table's capacity
		int tableSize = this.capacity();
		// Rehash to the new table only non-null and non-"DELETED" values
		for (int i = 0; i < tableSize; i++){
			if (this.openHashTable[i] != null && this.openHashTable[i]
					!= DELETION_FLAG){
				newOpenHashSet.add(this.openHashTable[i]);
			}
		}
		// Update the set's table with the new sized rehashed table.
		this.openHashTable = newOpenHashSet.openHashTable;
	}
	
	
		
		

	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */

	public boolean delete(String toDelete){
		// Attempt to find the cell of value to delete.
		this.cellFinder(toDelete, false);
		// The value is not in the table and cannot be deleted.
		if (this.cellToFind == CELL_NOT_FOUND){
			return false;
		}
		// We are deleting.
		this.updateFlag = false;
		// Mark the deleted cell for future reference. See README.
		this.openHashTable[this.cellToFind] = DELETION_FLAG;
		this.updateTable(this.updateFlag);
		// Checking for resize.
		if (this.currentLoadFactor < this.lowerLoadFactor){
			this.resizeAndRehash(this.updateFlag);
		}
		return true;		
	}

	/**
 	* @return  The current capacity (number of cells) of the table.
 	*/
	public int capacity(){
		return this.openHashTable.length;
	}
	
	
	 //This method will create a new table with different size and perform
	 // rehashing onto that table.
	 //  @param updateFlag - true to increase the size by 2 times.
	 //                false to decrease by 2 times.
	private void resizeAndRehash(boolean updateFlag){
		int newSize = resize(updateFlag);
		// Up-casting for the rehash method.
		SimpleHashSet newHashSet = new OpenHashSet(newSize);
		this.rehash(newHashSet);
	}
	
	// This method tries to find cells in the hash table-array using the
	// hash probing function. It is used by the methods:
	// add, contain, delete
	// searchVal - the value to search in the table, or to add to.
	// actionFlag - true means we want to add to the table.
	//            false means we want to search the value in
	//            the table for delete and contains.
	
	private void cellFinder (String searchVal, boolean actionFlag){
		// The index of the cell the value should be in, advancing by
		// the hash function.
		int cellToSearch = this.getHashValue(searchVal);
		// The index to probe the table according to the hash function.
		int probingIndex = 0;
		// We want to find the value in the table for delete or contains. 
		if (actionFlag == false){
			this.cellToFind = CELL_NOT_FOUND;
			// Keep searching until we found the value in the table and then
			// the relevant data member will save the cell, or we searched
			// in all the cells of the table and didn't find the value - in
			// that case the data member will not be updated.
			while (this.cellToFind == -1 && probingIndex < this.capacity()){
				// The value was found.
				if(searchVal.equals(this.openHashTable[cellToSearch])){
					this.cellToFind = cellToSearch;
				}
			
				probingIndex++;
			
				// The value might be found deeper into the table. Probe it.
				cellToSearch = this.hashFunction(probingIndex, searchVal);
								
			
			}
		}
		// We want to add the value to the table.
		else{
			this.cellToAdd = CELL_NOT_FOUND;
			// Keep searching until we found a null cell, or a cell that 
			// a value was inside before and it was deleted. In both cases
			// we can insert our value there if found.
			while (this.cellToAdd == CELL_NOT_FOUND && probingIndex <
					this.capacity()){	
				if((this.openHashTable[cellToSearch] == null)
						|| this.openHashTable[cellToSearch] == DELETION_FLAG ){
					this.cellToAdd = cellToSearch;
				}
				probingIndex++;
				
				// The cell might be found deeper into the table. Probe it.
				cellToSearch = this.hashFunction(probingIndex, searchVal);
			}
		}
	}
}
