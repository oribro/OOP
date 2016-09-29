import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashSet;

/**
 * The main method of the program. It creates tests to perform on the
 * data structures.
 * @author orib
 *
 */
public class SimpleSetPerformanceAnalyzer {
	// Array to store the sets we use.
	private SimpleSet[] setArray;
	private static final String DATA1_FILE = "/cs/stud/orib/workspace/ex3/data1.txt";
	private static final String DATA2_FILE = "/cs/stud/orib/workspace/ex3/data2.txt";
	private static final int NANO_TO_MILI_SECONDS_CONVERTOR = 1000000;
	// The number of the test we perform.
	private static final int TEST_ONE_MARKER = 1;
	private static final int TEST_TWO_MARKER = 2;
	private static final int TEST_THREE_MARKER = 3;
	private static final int TEST_FOUR_MARKER = 4;
	private static final int TEST_FIVE_MARKER = 5;
	private static final int TEST_SIX_MARKER = 6;
	private static final int NUMBER_OF_SETS = 5;
	private static final int CHAINED_HASH_INDEX = 0;
	private static final int OPEN_HASH_INDEX = 1;
	private static final int TREE_SET_INDEX = 2;
	private static final int LINKED_LIST_INDEX = 3;
	private static final int HASH_SET_INDEX = 4;
	// Values to test contains.
	private static final String TEST_THREE_KEY = "hi";
	private static final String TEST_FOUR_KEY = "13170890158";
	private static final String TEST_FIVE_KEY = "23";
	
	
	// The data file we are working with. The number is either 1 or 2.
	private static final int DATA_ONE_MARKER = 1;
	private static final int DATA_TWO_MARKER = 2;
	
	// The words in each file.
	private String[] dataOneValues; 
	private String[] dataTwoValues;
	
	
	
	/**
	 * Constructor. Load the desired structures and the data files.
	 */
	
	public SimpleSetPerformanceAnalyzer(){
		this.setArray = new SimpleSet[NUMBER_OF_SETS];
		this.setArray[CHAINED_HASH_INDEX] = new ChainedHashSet();
		this.setArray[OPEN_HASH_INDEX] = new OpenHashSet();
		this.setArray[TREE_SET_INDEX] = new CollectionFacadeSet
				(new TreeSet<String>());
		this.setArray[LINKED_LIST_INDEX] = new CollectionFacadeSet
				(new LinkedList<String>());
		this.setArray[HASH_SET_INDEX] = new CollectionFacadeSet
				(new HashSet<String>());
		this.dataOneValues = Ex3Utils.file2array(DATA1_FILE);
		this.dataTwoValues = Ex3Utils.file2array(DATA2_FILE);
	}
	/**
	 * This method runs tests on sets and checks how much time it takes
	 * to perform certain actions. 
	 * @param test - a test object initialized with one of the data files.
	 * @param timesForTest - an array to store the results for the current
	 * test.
	 * @param testNumber - the number of the current test.
	 * @param markerOfDataFile - the number of data file we test with.
	 */
	public static void performTest(SimpleSetPerformanceAnalyzer test,
			long[] timesForTest, int testNumber, int markerOfDataFile){

		// Announcement
		System.out.println("the results for data" + markerOfDataFile +
				" will be printed in the " +
				"following order: ChainedHash ; OpenHash ; TreeSet ; " +
				"LinkedList ; HashSet" + "\n");
		
		
		// variable to save best result
		long bestTime = 0;
		
		// Index for storing the time for each set.
		int timesIndex = 0;
				
		for (SimpleSet currentSet : test.setArray){
			// The time before we added the values.
			long timeBefore = System.nanoTime();
			
			// Add values to the current set.
			if (testNumber == TEST_ONE_MARKER){
				for (String currentValue : test.dataOneValues){
					currentSet.add(currentValue);
				}
			}
			else if (testNumber == TEST_TWO_MARKER){
				for (String currentValue : test.dataTwoValues)
					currentSet.add(currentValue);
			}
			
			else if (testNumber == TEST_THREE_MARKER){
				currentSet.contains(TEST_THREE_KEY);
			}
			else if (testNumber == TEST_FOUR_MARKER){
				currentSet.contains(TEST_FOUR_KEY);
			}
			else if (testNumber == TEST_FIVE_MARKER){
				currentSet.contains(TEST_FIVE_KEY);
			}
			else if (testNumber == TEST_SIX_MARKER){
				currentSet.contains(TEST_THREE_KEY);
			}
			
			// time after we entered the values.
			long difference;
			if (testNumber <= TEST_TWO_MARKER)
				difference = (System.nanoTime() - timeBefore)
				/ NANO_TO_MILI_SECONDS_CONVERTOR;
			else  
				difference = System.nanoTime() - timeBefore;
			timesForTest[timesIndex] = difference;
			timesIndex++;
			
			// Printing the time it took to test.
			if (testNumber <= TEST_TWO_MARKER)
				System.out.println("It took " + difference +
						"ms to add to " + currentSet.toString() + "\n");
			else if (testNumber > TEST_TWO_MARKER)
				System.out.println("It took " + difference +
						"ns to check contains in " + currentSet.toString()
						+ "\n");
			
			// updating best result
			if((difference == 0) || (difference < bestTime)){
				bestTime = difference;
			}
		}
		System.out.println("best time is "+ bestTime );
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	// This test uses data1 file.
	SimpleSetPerformanceAnalyzer test1 = new SimpleSetPerformanceAnalyzer();
	// This test uses data2 file.
	SimpleSetPerformanceAnalyzer test2 = new SimpleSetPerformanceAnalyzer();
	
	
	
	// array to hold test results for the different tests.
	long[] timesForTest1 = new long[NUMBER_OF_SETS];
	long[] timesForTest2 = new long[NUMBER_OF_SETS];
	long[] timesForTest3 = new long[NUMBER_OF_SETS];
	long[] timesForTest4 = new long[NUMBER_OF_SETS];
	long[] timesForTest5 = new long[NUMBER_OF_SETS];
	long[] timesForTest6 = new long[NUMBER_OF_SETS];
	
	// Perform the tests.
	System.out.println("test" + TEST_ONE_MARKER +"\n");
	performTest(test1, timesForTest1, TEST_ONE_MARKER, DATA_ONE_MARKER);
	System.out.println("test" + TEST_TWO_MARKER +"\n");
	performTest(test2, timesForTest2, TEST_TWO_MARKER, DATA_TWO_MARKER);
	System.out.println("test" + TEST_THREE_MARKER +"\n");
	performTest(test1, timesForTest3, TEST_THREE_MARKER, DATA_ONE_MARKER);
	System.out.println("test" + TEST_FOUR_MARKER +"\n");
	performTest(test1, timesForTest4, TEST_FOUR_MARKER, DATA_ONE_MARKER);
	System.out.println("test" + TEST_FIVE_MARKER +"\n");
	performTest(test2, timesForTest5, TEST_FIVE_MARKER, DATA_TWO_MARKER);
	System.out.println("test" + TEST_SIX_MARKER +"\n");
	performTest(test2, timesForTest6, TEST_SIX_MARKER, DATA_TWO_MARKER);
	}
	
	
	

}
