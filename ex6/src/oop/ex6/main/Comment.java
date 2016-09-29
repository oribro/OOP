package oop.ex6.main;
import java.util.regex.*;

public class Comment {
	public static boolean isComment(String line){
		Pattern p = Pattern.compile(" *//");
		Matcher m = p.matcher(line);
		return m.lookingAt();
	}

}
