package oop.ex6.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Parser {
//	private final String BLOCK_OPENER = "{";
//	private final String BLOCK_CLOSER = "}";
	
	private BufferedReader fileReader;
	
	
	public Parser(String commandFile) throws FileNotFoundException {
		this.fileReader = new BufferedReader(new FileReader(commandFile));
	}
	
	public void parseOnFile() throws IOException, InputException{
		try{
			
			// Split the file into lines.
			ArrayList<String> lines = getFileCodeLines();
			
			// List of the methods in the file.
			ArrayList<Method> methodList = new ArrayList<Method>();
			
			// List of the global variables in the file.
			ArrayList<Variable> globalVarList = new ArrayList<Variable>();
			
			String currentLine;
			// Go over the file lines and check their validity:
			for (int i = 0 ; i < lines.size(); i++){
				currentLine = lines.get(i);
				
				// If this line is a declaration of a global variable:
				if (Semicolon.isSemicolon(currentLine)){//BANANA
					
					ArrayList<Variable> lineArray = VariableFactory.createVariables(currentLine, globalVarList, null);
					if (lineArray != null){
						globalVarList.addAll(lineArray);
					}					
				}
				
				
				// This line is a beginning of a method.
//				else if (Bracket.isBracketOpener(lines.get(i))){
//					int lineOfMethodEnd = findMethodEnd(lines, i);
//					Method method = new Method(lines.subList(i, lineOfMethodEnd));
//					i = lineOfMethodEnd + 1; ///Check bound!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@
//				}
//				else{
//					throw new InputException("Illegal file format");
//				}
				
					
			}
			
			
			for (Variable var : globalVarList){
				
				System.out.println(var.getName() + " name");
				System.out.println(var.getFinalState() + " final");
				System.out.println(var.getType() + " type");
				System.out.println(var.getInitializedState() + " initialized");
			}
			
		
//		}catch(IOException n){
//			
//		}
		}catch (InputException e) {
			throw new InputException("");
		}
		
		
	}
	
	public int findMethodEnd(ArrayList<String> fileLines, int openingIndex) throws InputException{
		int bracketCounter = 1;
		for (int i = openingIndex + 1; i < fileLines.size() ; i++){
			// Check if the next line is opening new bracket
			if (Bracket.isBracketOpener(fileLines.get(i))){
				bracketCounter++;
			}
			// Check if the next line is closing new bracket
			else if (Bracket.isBracketCloser(fileLines.get(i))){
				bracketCounter--;
			}
			// If all the brackets in the method are closed  
			if (bracketCounter == 0){
				//i is the index of the last line of the method
				return i; 
			}
		}
		//If the method does not have the right format
		throw new InputException("Bad Method Format");
		
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> getFileCodeLines() throws IOException{
		ArrayList<String> lines = new ArrayList<String>();
		//read the first line
		String line = fileReader.readLine();

		//while its not the end of file insert to the array all the lines in the Array
		while (line != null){
			// The line is a comment or it's an empty line. Ignore it.
			if(Comment.isComment(line) || EmptyLines.isEmptyLine(line)){
				line = fileReader.readLine();
			}
			else{
				lines.add(line);
				line = fileReader.readLine();
			}
		}
		return lines;
		
	}
	
	

}
