package asteroids.model;

import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;

public class Program {
	
	
	
	private Ship ship;
	private SourceLocation location = new SourceLocation(0,0);
	private Statement main;
	private Set<Variable> allVariables = new HashSet<Variable>();
	private List<Object> results = new ArrayList<Object>();
	private double remainingTime;
	private List<Function> allFunctions;
	
	//Constructor for program with main and if any, a number of functions.
	public Program(List<Function> functions, Statement main){
		this.setMain(main);
		this.setAllFunctions(functions);
		main.setProgram(this);
		for(Function function: functions) function.setProgram(this);
		remainingTime = 0;
	}
	
	//Run the program for a given amount of time.
	public List<Object> run(double dt) throws IllegalArgumentException{
		remainingTime = remainingTime + dt;
		main.run();
		if(!main.NoTimeConsumed()){
			if(main.breakDiscovered()) throw new IllegalArgumentException();
			location = new SourceLocation(0,0);
			List<Object> resultthrow = results;
			results = null;
			return resultthrow;
		}
		return null;
	}
	
	//Set the ship the program is needs to run on.
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
	//Get the ship the program runs on.
	public Ship getShip(){
		return ship;
	}

	//Get a location in the program using provided SourceLocation class.
	public SourceLocation getLocation(){
		return location;
	}
	
	//Set a location in the program.
	public void setLocation(SourceLocation location){
		this.location = location; 
	}
	
	//Set main statement
	public void setMain(Statement main){
		this.main = main; 
	}
	
	//get main statement
	public Statement getMain(){
		return this.main; 
	}
		
	//Get the remainingTime a program can run.
	public double getTime(){
		return remainingTime;
	}
	
	//Subtract from the time if a given part of the program needs time to run.
	public void moveTime(){
		remainingTime = remainingTime - 0.2;
	}
	
	//Get a list of all the functions
	public List<Function> getAllFunctions(){
		return this.allFunctions;
	}
	
	//Set the list of all functions
	public void setAllFunctions(List<Function> func){
		this.allFunctions = func;
	}
	
	//Get a specific function from the list of functions, providing the name.
	public Function getFunction(String name) throws NoSuchElementException {
		return this.allFunctions.stream().filter(func -> func.getFunctionName().equals(name)).reduce((first, second) -> second).get();
	}
	
	//Add result
	public void addResult(Object result) {
		results.add(result);
	}
	
	//Get the results of the program.
	public List<Object> getResults() {
		return results;
	}
	
	//Add Variable to the program
	public void addVariable(Variable variable){
		allVariables.add(variable);
	}
	
	//Remove a variable from the list of variables
	public void removeVariable(Variable variable){
		this.getVariables().remove(variable);
	}
	
	//Get a specific variable from the list of variables, providing the name.
	public Variable getVariable(String name) throws NoSuchElementException {
		return getVariables().stream().filter(var -> var.getName().equals(name)).findFirst().get();
	}
	
	//Get a set of variables of the program.
	public Set<Variable> getVariables(){
		return new HashSet<Variable>(allVariables);
	}
	
	//Set a set of all variables of the program
	public void setVariables(Set <Variable> vars){
		this.allVariables = vars;
	}
	
	

}
