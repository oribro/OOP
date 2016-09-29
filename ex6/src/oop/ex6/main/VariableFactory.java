package oop.ex6.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableFactory {
	
	final static String typeRegex = "(String|int|double|char|boolean)";
	final static String nameRegex = "\\s*((_\\w+)|([a-zA-Z]\\w*))";

	
	final static String intRegex = "\\-?\\d+";
	final static String charRegex = "\'.\'";
	final static String doubleRegex = "\\-?[0-9]+(\\.[0-9]+)?";
	final static String stringRegex = "\".*\"";
	final static String posibleValueRegex = "((" + intRegex + ")|(\\" + charRegex + ")|(" + doubleRegex + 
			")|" + "(" + stringRegex + ")|true|false|("+ nameRegex + "))";
	
	final static String valregex = "(\\=\\s*((" + nameRegex + ")|(" + posibleValueRegex + ")))" ;
	final static String morVarInLine = "\\s*,\\s*" + nameRegex + "(\\s*=\\s*" + posibleValueRegex +")*";
	final static String finalRegex = "final\\s*";
	
	
	public static ArrayList<Variable> createVariables(String line, ArrayList<Variable> 
						globalVarList, ArrayList<Variable> localVarList) throws InputException{
		
		try{
			
			ArrayList<Variable> varInLine = new ArrayList<Variable>();
			// The list of lists of existing variables.
			ArrayList<ArrayList<Variable>> listOfArrays = new  ArrayList<ArrayList<Variable>>();
			// Initialize the list of lists with the lists.
			listOfArrays.add(varInLine);
			listOfArrays.add(globalVarList);
			
			Pattern p = Pattern.compile("\\s*("+ finalRegex + ")?\\s*" + typeRegex + "\\s*(" + 
											nameRegex + ")\\s*("+ valregex +"?)+" + "((" + 
											morVarInLine +")*)\\s*;\\s*");
			Matcher m = p.matcher(line);
			
			// If the line is not a declaration of variable
			if (!m.matches()){ 
				
				// If the variable exists and is not final
				Pattern pt = Pattern.compile("\\s*(" + nameRegex + ")\\s*("+ valregex +")\\s*;\\s*");
				Matcher mt = pt.matcher(line);
				if (mt.matches()){
					
					String name = mt.group(1);
					String value = mt.group(7);
					Variable preVar = searchInArray (listOfArrays , name);
					System.out.println(preVar.getName());
					checkValType (value, listOfArrays, preVar.getType());
					
					if (preVar.getFinalState() == true){
						
						throw new InputException("ReInitialization of Final Variable");
					}
					
					if (preVar.getInitializedState() == false){
						preVar.setToInitialized();
						
					}
					return null;
				}
					
					// If the general structure of the declaration is illegal
				throw new InputException("Illegal Value Declaration");
			}
			
			
			
			//First Var In Line:
			
			
			
			boolean isFinal;
			boolean isInitialized;
			String type = m.group(2);
			String name = m.group(3);
			String value = m.group(9);
			String moreVarInLine = m.group(25);
			
//			System.out.println(type);
//			System.out.println(name);
//			System.out.println(value);
//			System.out.println(moreVarInLine);
//			

			
			// Check for duplicate local variable names
			if (localVarList != null){
				checkNameDuplicate(localVarList, name);
				listOfArrays.add(localVarList);
			// Check for duplicate global variable names.
			}else{
				checkNameDuplicate(globalVarList, name);
			}

				
			// If the variable is final.
			if (m.group(1) == null){ 
				isFinal = false;
			}
			else{
				isFinal = true;
			}
			
			// Initialization check.
			if (value == null){ 
				isInitialized = false;
			}
			else{ 
				// If value is valid
				checkValType (value,  listOfArrays, type);
				isInitialized = true;
			}
			
			// If there is invalid final declaration. 
			if (isFinal == true && isInitialized == false){
				throw new InputException("Final variable is not initialized");
			}

			// If the var is valid create it and insert to the array of var in this line.
			Variable firstVar = new Variable(name, type, isFinal, isInitialized);
			varInLine.add(firstVar);
			
			
			// More Var In Line:
			
			//If we have more var declaration in this line
			if (moreVarInLine != null && !moreVarInLine.isEmpty()){
				
				String[] moreVarArray;
				String[] varDetails;
				
				// Split the line by the ',' to find more variables.
				moreVarArray = moreVarInLine.split("\\s*,\\s*");

				// For every variable create it if it's legal.
				for(int i = 1; i < moreVarArray.length; i++){
					
					// Split the name and the value if exists.
					varDetails = moreVarArray[i].split("\\s*=\\s*");
					
					// Create the value.	
					name = varDetails[0];
					isInitialized = false;
					
					if (varDetails.length > 1){  // a value exists.
						
						// Update the array of this line
						listOfArrays.add(0, varInLine);

						// Check if the value is valid
						checkValType (varDetails[1], listOfArrays, firstVar.getType());
	
						isInitialized = true;
					}
					//Create the new var and insert to the list of var in line
					Variable newVar = new Variable(name, firstVar.getType(), firstVar.getFinalState(), isInitialized);
					varInLine.add(newVar);
				}
			}
			return varInLine;
		}catch(InputException e){
			throw new InputException("");
		}
	}
	

	public static void checkValType (String value, ArrayList<ArrayList<Variable>> listOfArrays, String firstVarType) throws InputException{

		String type = null;
		
		
		if (value.matches(stringRegex)){ // String
			type = "String";
		}
		else if (value.matches(charRegex)){ //char
			type = "char";
		}
		else if (value.equals("true") || value.equals("false")){ //Boolean
			type = "boolean";
		}
		else if (value.matches(intRegex)){ //int
			type = "int";
		}
		else if (value.matches(doubleRegex)){ //double
			type ="double";
		}
		else{// Variable
			type = searchInArray(listOfArrays , value).getType();	
		}

		if ((firstVarType.equals("double")) && (type.equals("int"))){
			type = "double";
		}
		if ((firstVarType.equals("boolean")) && (type.equals("int") ||type.equals("double"))){
			type = "boolean";
		}
		
		else if (!type.equals(firstVarType)){
			throw new InputException("Invalid Var Initialization");
		}	
	}

	
	
	public static Variable searchInArray (ArrayList<ArrayList<Variable>> listOfArrays , String varName) throws InputException{
		// First check if the value is a variable in that was initialized previously in this line.
		// Check if its a local variable
		// Check if its a global variable:
		for (ArrayList<Variable> list: listOfArrays){
			for (Variable var: list){
				if (var.getName().equals(varName)){
					if (var.getInitializedState() == false){
						System.out.println("BANANA");
						throw new InputException("Invalid Var Initialization");
					}
					return var;
				}
			}		
		}
		throw new InputException("Invalid Var Initialization");
	}
	
	public static void checkNameDuplicate(ArrayList<Variable> varArray, String name) throws InputException{
		// Check for duplicate in array variables.
		for (Variable var : varArray){
			if (name.equals(var.getName())){
				throw new InputException("Duplicate variables");
			}
		}	
	}


}
