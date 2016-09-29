package oop.ex6.main;

import java.util.ArrayList;

public class Method {
	
	private String name;
	private ArrayList<Variable> parameters;
	private ArrayList<Variable> globalVars;
	private ArrayList<Variable> localVars;
	private ArrayList<String> lines;
	
	public Method (String name, ArrayList<Variable> parameters, ArrayList<Variable> globalVars,
			ArrayList<Variable> localVars, ArrayList<String> lines){
		this.name = name;
		this.parameters = parameters;
		this.globalVars = globalVars;
		this.localVars = localVars;
		this.lines = lines;
	}
	
	ArrayList<Variable> getGlobalVars() {
		return globalVars;
	}

	void setGlobalVars(ArrayList<Variable> globalVars) {
		this.globalVars = globalVars;
	}

	ArrayList<String> getLines() {
		return lines;
	}

	void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	ArrayList<Variable> getParameters() {
		return parameters;
	}

	void setParameters(ArrayList<Variable> parameters) {
		this.parameters = parameters;
	}

	ArrayList<Variable> getLocalVars() {
		return localVars;
	}

	void setLocalVars(ArrayList<Variable> localVars) {
		this.localVars = localVars;
	}
	
}
