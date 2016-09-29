
public class Move {
	
	private int row;
	private int leftBound;
	private int rightBound;
	
	public Move(int inRow,int inLeft,int inRight) {
		row = inRow;
		leftBound = inLeft;
		rightBound = inRight;
		
	}
	
	public String toString() {
		return row + ":" + leftBound + "-" + rightBound;
	
	}
	
	public int getRow(){
		return row;
	}
	
	public int getLeftBound(){
		return leftBound;
	}
	public int getRightBound()
	{
		return rightBound;
	}
}
