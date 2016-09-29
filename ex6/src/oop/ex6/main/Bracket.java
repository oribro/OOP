package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bracket {
	public static boolean isBracketOpener(String line){          //////CHANGE COMPILE TO ONLY ONES PER PATTERN
															///// EFFICIENCY!!!!!!!!!!!!!
		Pattern p = Pattern.compile("\\w*\\s*\\{\\s*");
		Matcher m = p.matcher(line);
		return m.matches();
	}
	
	public static boolean isBracketCloser(String line){          //////CHANGE COMPILE TO ONLY ONES PER PATTERN
																///// EFFICIENCY!!!!!!!!!!!!!
		Pattern p = Pattern.compile("\\s*}\\s*");
		Matcher m = p.matcher(line);
		return m.matches();
	}
	
}
