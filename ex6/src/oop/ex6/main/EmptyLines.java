package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmptyLines {
	public static boolean isEmptyLine(String line){          //////CHANGE COMPILE TO ONLY ONES PER PATTERN
															///// EFFICIENCY!!!!!!!!!!!!!
		Pattern p = Pattern.compile("\\s*");
		Matcher m = p.matcher(line);
		return m.matches();
	}
	
	

}
