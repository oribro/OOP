package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Semicolon {
	
	public static boolean isSemicolon(String line){
				
		String isEndsWithSemiColom = ";\\s*$";
		Pattern p = Pattern.compile(isEndsWithSemiColom);
		Matcher m = p.matcher(line);
		return m.find();
		}
}
