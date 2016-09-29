package oop.ex6.main;

import java.util.ArrayList;

public class Condition {
	private String lines;
	private ArrayList<Variable> globalVars;
	private ArrayList<Variable> localVars;
	
	
	public Condition(String lines, ArrayList<Variable> localVars,
			ArrayList<Variable> globalVars){
		this.lines = lines;
		this.globalVars = globalVars;
		this.localVars = localVars;
	}

	String getLines() {
		return lines;
	}

	void setLines(String lines) {
		this.lines = lines;
	}
	
	ArrayList<Variable> getGlobalVars() {
		return globalVars;
	}

	void setGlobalVars(ArrayList<Variable> globalVars) {
		this.globalVars = globalVars;
	}
	
	ArrayList<Variable> getLocalVars() {
		return localVars;
	}

	void setLocalVars(ArrayList<Variable> localVars) {
		this.localVars = localVars;
	}
	
	
}
