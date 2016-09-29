import java.util.LinkedList;
/**
 * This class represents a cell in the hash table using linked list.
 * @author orib
 *
 */
public class LinkedCell {
	// The linked list in the cell.
	public LinkedList <String> chainedList;
	
	public LinkedCell(){
		this.chainedList = new LinkedList<String>(); 
	}
}

