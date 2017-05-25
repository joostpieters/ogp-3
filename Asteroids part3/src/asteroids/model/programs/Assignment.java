package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Assignment<S> extends Statement {
	private Expression<S> value;
	private String name;
	private boolean breakDiscovered;

	public Assignment(String name, Expression<S> value, SourceLocation location) {
		super(location);
		this.setName(name);
		this.setValue(value);
	}

	//Get name of the variable
	public String getName(){
		return this.name;
	}
	
	//SetName of the variable
	public void setName(String name){
		this.name = name;
	}
	
	//getValue
	public Expression<S> getValue(){
		return this.value;
	}
	
	//SetValue
	public void setValue(Expression<S> val){
		this.value = val;
	}
	
	//Start assignment
	@Override
	public void run() {
		setBreakDiscovered(false);
		//Look up for functions, if name of the variable already exists as a function, nothing will happen.
		try{
			getProgram().getFunction(name);
			throw new IllegalArgumentException("Already declared as function");
			
		} catch (NoSuchElementException excep){}
		
		//Find variable and assign
		Optional<Variable> variableToAssignTo = getProgram().getVariables().stream().filter(variable -> variable.getName().equals(name)).findFirst();
		if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.calculate());
		//If variable doesn't exist yet, make a new one.
		else getProgram().addVariable(new Variable<S>(name, value.calculate()));
		if (value instanceof FunctionCall && ((FunctionCall)value).breakDiscovered()) setBreakDiscovered(true);
	}
	
	//Assign in a function
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals){
		Optional<Variable> variableToAssignTo = locals.stream().filter(variable -> variable.getName().equals(name)).findFirst();
		if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.calculate(arguments, locals));
		else locals.add(new Variable<S>(name, value.calculate(arguments, locals)));
		if (value instanceof FunctionCall && ((FunctionCall)value).breakDiscovered()) setBreakDiscovered(true);
		return Optional.empty();
	}
	
	//Set break discovered
	private void setBreakDiscovered(boolean disc) {
		this.breakDiscovered = disc;
	}
	
	//get breaks
	public boolean breakDiscovered(){
		return breakDiscovered;
	}

	//Set program for every part of the assignement statement
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}
	


}
