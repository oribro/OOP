import java.util.Collection;
public class CollectionFacadeSet implements SimpleSet{
	
	private Collection <String> collection;
	/**
	 * Constructor.
	 * @param newCollection the collection to initialize with.
	 */
	public CollectionFacadeSet(Collection <String> newCollection){
		this.collection = newCollection;
	}
	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	
	public boolean add(String newValue){
		// Value already in the collection. Set doesn't allow duplicates.
		if (this.collection.contains(newValue) == true)
			return false;
		this.collection.add(newValue);
		return true;
	}
	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal){
		return this.collection.contains(searchVal);
	}
	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public boolean delete(String toDelete){
		if (contains(toDelete) == true){
			this.collection.remove(toDelete);
			return true;
		}
		return false;
	}
	/**
	 * @return The number of elements currently in the set
	 */
	public int size(){
		return this.collection.size();
	}
}
		

