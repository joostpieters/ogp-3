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
	
	
	public Program(List<Function> functions, Statement main){
		this.main = main;
		this.allFunctions = functions;	
		remainingTime = 0;
		main.setProgram(this);
		//System.out.println(main.toString());
	}
	
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
	
	
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
	public Ship getShip(){
		return ship;
	}

	public SourceLocation getLocation(){
		return location;
	}
	
	public void setLocation(SourceLocation location){
		this.location = location; 
	}
	
	public double getTime(){
		return remainingTime;
	}
	
	public void moveTime(){
		remainingTime = remainingTime - 0.2;
	}
	public List<Object> getResults() {
		return results;
	}
	
	public void addResult(Object result) {
		results.add(result);
	}
	
	public void addVariable(Variable variable){
		allVariables.add(variable);
	}
	
	public Set<Variable> getVariables(){
		return new HashSet<Variable>(allVariables);
	}
	
	
}
