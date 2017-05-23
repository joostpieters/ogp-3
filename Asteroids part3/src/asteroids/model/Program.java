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
		this.main = main;
		this.allFunctions = functions;
		main.setProgram(this);
		remainingTime = 0;
	}
	
	//Run the program for a given amount of time.
	public List<Object> run(double dt) throws IllegalArgumentException{
		remainingTime = remainingTime + dt;
		main.run();
		if(!main.NoTimeConsumed()){
			if(main.breakDiscovered()) throw new IllegalArgumentException("Break not supported");
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
	
	//Get the remainingTime a program can run.
	public double getTime(){
		return remainingTime;
	}
	
	//Subtract from the time if a given part of the program needs time to run.
	public void moveTime(){
		remainingTime = remainingTime - 0.2;
	}
	
	//Get the results of the program.
	public List<Object> getResults() {
		return results;
	}
	
	//Add result
	public void addResult(Object result) {
		results.add(result);
	}
	
	//Add Variable to the program
	public void addVariable(Variable variable){
		allVariables.add(variable);
	}
	
	//Get a set of variables of the program.
	public Set<Variable> getVariables(){
		return new HashSet<Variable>(allVariables);
	}
	
	//Get all the functions in a program
	public Function getFunction(String name) throws NoSuchElementException {
		return this.allFunctions.stream().filter(func -> func.getName().equals(name)).reduce((first, second) -> second).get();
	}
	
	//Get variable
	public Variable getVariable(String variableName) throws NoSuchElementException {
		return getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst().get();
	}
}
