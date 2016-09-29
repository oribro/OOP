import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.regex.*;


public class tests {
	public static void main(String[] args) throws IOException{
		
//		Pattern p = Pattern.compile("s*\\(char|int|double|String|boolean)s+\\=");
//		Matcher m = p.matcher("cgxdfxgxfdgff		;");
//		System.out.println(m.matches());
		
		
		
		Pattern p = Pattern.compile("\\s*void\\s*([a-zA-Z]\\w*)(");
		Matcher m = p.matcher("   void foo()");
		System.out.println(m.matches());
		
//		
		
		
		
//		Reader tempFileReader;
//		try {
//			tempFileReader = new FileReader("/cs/stud/orib/workspace/ex6/a.txt");
//			BufferedReader r = new BufferedReader(tempFileReader); 
//			System.out.println(r.readLine());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
			 	 	 
		
	}
}
