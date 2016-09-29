package oop.ex6.main;

public class Variable {
	 
	private String name;
	private String type;
	private boolean isFinal;
	private boolean isInitialized;
	
	// Constructor:
	
	public Variable(String name, String type, boolean isFinal, boolean isInitialized){
		this.name = name;
		this.type = type;
		this.isFinal = isFinal;
		this.isInitialized = isInitialized;		
	}
	
	// Getters:
	
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
	
	public boolean getFinalState(){
		return this.isFinal;
	}
	
	public boolean getInitializedState(){
		return this.isInitialized;
	}
	public void setToInitialized(){
		this.isInitialized = true;
	}
}
