package oop.ex5.filescript;

import java.io.IOException;
import java.util.ArrayList;

import java.io.File;


/**
 * The manager for the program: it will call the parser to parse the command
 *  file, and will call the printer to iterate over the different sections,
 *   printing warnings and filtered ordered files.
 * @author orib
 */
public class MyFileScript {
	// Constants for the program arguments.
	private final static int SOURCE_DIR_INDEX = 0;
	private final static int COMMAND_FILE_INDEX = 1;
	private final static int VALID_NUM_OF_ARGS = 2;
	
	
	/**
	 * The main method of the program.
	 * @param args - the first argument is sourcedir - the directory that
	 *  the program works on.
	 *              the second argument is commandfile - a file with commands
	 *              to perform on the files in the directory. 
	 * @throws IOException if there was an error while accessing the command
	 * file.
	 * @throws MyException if there was a warning while parsing the command
	 * file.
	 */
	public static void main (String args[]) throws IOException, MyException{
		
		try{
			if (args.length != VALID_NUM_OF_ARGS){
				throw new BadArgumentsError();
			}
			// The path of the directory we work with.
			String sourceDirPath = args[SOURCE_DIR_INDEX];
			// The path of the command file.
			String commandFilePath = args[COMMAND_FILE_INDEX];
			// Parse the command file for sections.
			Parser parser = new Parser(commandFilePath);
			ArrayList<Section> sections = parser.createSections();
			// Get the files in the directory.
			File file = new File(sourceDirPath);
			File[] filesInDir = file.listFiles();
			// Traverse the different sections of the command file.
			for (Section currentSection : sections){
				// Print section warnings
				FilePrinter.printWarnings(currentSection);
				// Print the wanted files of the section.
				FilePrinter.printFiles(currentSection, filesInDir);
			}
		}
		// There was a type 2 error - print "ERROR" and exit.
		catch (MyError|IOException error){
			System.err.println(MyError.ERROR_MESSAGE);
			return;
		}
	
	}
	
}
