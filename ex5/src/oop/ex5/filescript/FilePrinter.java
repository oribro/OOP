package oop.ex5.filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import oop.ex5.filterpackage.Filter;
import oop.ex5.orderpackage.Order;


/**
 * This class is responsible for the output of this program. It prints
 * files according to a given section of the command file, and warnings 
 * if the files could not be printed as requested.
 * @author orib
 */
public class FilePrinter {
	
	// Constant for indicating that no warning was found.
	private final static int NO_WARNING = 0;
	/**
	 * Prints filtered, ordered files of the sourcedir according to the given
	 *  section of the command file.
	 * @param section - the section of the command file with instructions
	 * 				for what will be printed.
	 * @param files - the files of the directory to be filtered/ordered.
	 */
	public static void printFiles(Section section, File[] files){
		// Get section details.
		Order sectionOrder = section.getOrder();
		Filter sectionFilter = section.getFilter();
		// The list of files that passed filtering.
		ArrayList<File> filteredFiles = new ArrayList<File>();
		for (File file : files){
			// It is a file that passed the filtering therefore can be printed
			if (file.isFile() && sectionFilter.isPass(file)){
				filteredFiles.add(file);
			}
		}
		
		// Sort the files using the order found in the section.
		Collections.sort(filteredFiles, sectionOrder);
		// Print the wanted files according to the given section.
		for (File file : filteredFiles){
			System.out.println(file.getName());
		}
	}
	
	/**
	 * Prints the warnings of the given section.
	 * @param section - section of the command file to print warnings of.
	 */
	public static void printWarnings(Section section){
		// The numbers of lines in the given section of the command file
		// which warnings occurred on. The numbers are counted from the 
		// beginning of the command file.
		ArrayList<Integer> warningLineNums = section.getWarnings();
		for (Integer warningNum : warningLineNums){
			// If there was a warning that the parser spotted, print it.
			if (warningNum != NO_WARNING){
				System.err.println("Warning in line " +warningNum);
			}
		}
		
				
	}
}
