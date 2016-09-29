package oop.ex6.main;

import java.util.ArrayList;
import java.util.regex.*;


public class regexTests {
	public static void main(String[] args){
		String typeRegex = "(String|int|double|char|boolean)";
		String nameRegex = "\\s*((_\\w+)|([a-zA-Z]\\w*))";

		
		String intRegex = "\\d+";
		String charRegex = "\'.\'";
		String doubleRegex = "^[0-9]+(\\.[0-9]+)?$";
		String stringRegex = "\".*\"";
		String posibleValueRegex = "((" + intRegex + ")|(\\" + charRegex + ")|(" + doubleRegex + ")|" +
				"(" + stringRegex + ")|true|false|("+ nameRegex + "))";
		
		String valregex = "(\\=\\s*((" + nameRegex + ")|(" + posibleValueRegex + ")))" ;
		String morVarInLine = "\\s*,\\s*" + nameRegex + "((\\s*=\\s*" + posibleValueRegex +")?)";
		
		String finalRegex = "final\\s*";
		String isEndsWithSemiColom = ";\\s*$";

		
		Pattern p = Pattern.compile(posibleValueRegex);
		Matcher m = p.matcher("1");
		
		
		
		
//		Pattern p = Pattern.compile();
//		Matcher m = p.matcher(", _j = 3 		;	   ;   ");
//		System.out.println(m.matches());//true
//		System.out.println(m.group(3)); //final
		ArrayList<String> s = new ArrayList<>();
		for (String e : s){
			System.out.println(e);
		}
	

	}

}
