package oop.ex5.filescript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import oop.ex5.filterpackage.FilterFactory;
import oop.ex5.filterpackage.Filter;
import oop.ex5.filterpackage.InvalidFilterError;
import oop.ex5.orderpackage.InvalidOrderError;
import oop.ex5.orderpackage.OrderFactory;
import oop.ex5.orderpackage.Order;

/**
 * This class represents a parser for the command file.
 * The parser scans the command file and creates sections of it.
 * Each section holds instructions for how to handle the files of the dir.
 * @author orib
 */                 
public class Parser {                                    
	private final static String FILTER_INDICATOR = "FILTER";
	private final static String ORDER_INDICATOR = "ORDER";
	private final static String ABS_ORDER_NAME = "abs";
	private final static int FIRST_LINE_IN_FILE = 1;
	// Constant for indicating that no warning was found.
	private final static int NO_WARNING = 0;
	
	
	private BufferedReader fileReader;
	private ArrayList<Section> sections;
	
	/**
	 * Constructor.
	 * @param commandFilePath - the path of the file to parse.
	 * @throws FileNotFoundException - Signals that an attempt to open the
	 *  file denoted by the specified pathname has failed. 
	 */
	public Parser(String commandFilePath) throws FileNotFoundException{
		// Create a buffered reader using decorator design pattern.
		Reader tempFileReader = new FileReader(commandFilePath);
		this.fileReader = new BufferedReader(tempFileReader); 
		this.sections = new ArrayList<Section>();
		
	}
	
	/**
	 * Creates sections of the command file.
	 * @return an ArrayList of sections, altogether comprise the command file.
	 * @throws IOException if line of the file cannot be read.
	 * @throws BadCommandFileError if there was no FILTER/ORDER at some point.
	 * @throws BadSubSectionWarning if there was invalid type or parameters
	 * of filter/order.
	 */
	public ArrayList<Section> createSections() throws IOException,
	BadCommandFileError, BadSubSectionWarning{
		// Variables for saving the lines in which there were warnings.
		int filterWarningLineNum = NO_WARNING;
		int orderWarningLineNum = NO_WARNING;
			
		// Attempt to read a line from the file.
		String line = fileReader.readLine();
		// Save the current line number.
		int lineNumber = FIRST_LINE_IN_FILE;
		// There are sections to be created.
		while (line != null){
			// New section - reset warnings.
			filterWarningLineNum = NO_WARNING;
			orderWarningLineNum = NO_WARNING;
			// Misspelled 'FILTER'
			if (!line.equals(FILTER_INDICATOR)){
				throw new InvalidFilterError();
			}
			
			line = fileReader.readLine();
			lineNumber++;
			// No filter type.
			if (line == null){
				throw new BadCommandFileError();
			}
			// Create a filter according to the command line.
			Filter filter = FilterFactory.createFilter(line);
			// Check if there was a warning while creating the filter.
			if (filter.getWarning()){
				filterWarningLineNum = lineNumber;
			}
			// Try for an order.
			line = fileReader.readLine();
			lineNumber++;
			// No order indicator - error.
			if (line == null){
				throw new BadCommandFileError();
			}
			// Misspelled 'ORDER'
			if (!line.equals(ORDER_INDICATOR)){
				throw new InvalidOrderError();
			}
			// Read the order type.
			line = fileReader.readLine();
			lineNumber++;
			Order order;
			//No explicit order. Use abs order.
			if (line == null || line.equals(FILTER_INDICATOR)){
				order = OrderFactory.createOrder(ABS_ORDER_NAME);
			}
			// Create order according to the given parameter.
			else{
				order = OrderFactory.createOrder(line);
			}
			// Check if there was a warning while creating the order.
			if (order.getWarning()){
				orderWarningLineNum = lineNumber;
			}
			
			// Add a new section to the array of sections.
			Section section = new Section(filter, order);
			sections.add(section);
			// Add warnings to the section.
			section.getWarnings().add(filterWarningLineNum);
			section.getWarnings().add(orderWarningLineNum);
			
			// Bad line check.
			if (line != null){
				if (!line.equals(FILTER_INDICATOR)){
					line = fileReader.readLine();
					lineNumber++;
				}
			}
		}
		// We finished reading the command file, close it.
		fileReader.close();
		return sections;
		
	}
}

