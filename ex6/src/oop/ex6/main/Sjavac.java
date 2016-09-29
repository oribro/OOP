package oop.ex6.main;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author lilaca01
 *
 */
public class Sjavac {
	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws  IOException{
		try{
			if (args.length != 1){ //Check if the num of arguments is legal
				throw new InputException("Illegal number of arguments");
			}
			Parser parse = new Parser(args[0]);
			parse.parseOnFile();
			System.out.println(0);
		}catch (InputException error){
			System.out.println(1);
//			System.err.println(error.getWarningMesage());
		}
		catch (IOException error){
			System.out.println(2);
		}
	}
	
	
	

}
