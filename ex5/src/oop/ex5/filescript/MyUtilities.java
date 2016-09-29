package oop.ex5.filescript;

import java.io.File;

/**
 * Utility class to work with files.
 * @author OriB
 */
public class MyUtilities {
	
	private static final int BYTES_IN_KBYTE = 1024;
	private static final char FILE_TYPE_DELIMITER = '.';
	
	/**
	 * @param file - the file to get the size of.
	 * @return the size of the file (double).
	 */
	public static double getFileSize(File file){
		return (double) (file.length() / BYTES_IN_KBYTE);
	}
	
	/**
	 * @param file - the file to get the name of.
	 * @return the name of the file.
	 */
	public static String getFileName(File file){
		return file.getName();
	}
	/**
	 * @param file - the file to get the type of.
	 * @return the type of the file (String).
	 */
	public static String getFileType(File file){
		String fileName = getFileName(file);
		return fileName.substring(fileName.lastIndexOf(FILE_TYPE_DELIMITER)
				+ 1);
	}
}
