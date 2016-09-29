package oop.ex6.main;
import java.io.IOException;

public class InputException extends IOException{
	private String warningMesage;
	
	public InputException(String warningMesage){
		this.warningMesage = warningMesage;
	}
	
	public String getWarningMesage(){
		return warningMesage;
	}

}
