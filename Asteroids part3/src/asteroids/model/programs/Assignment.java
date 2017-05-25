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
		this.setValue(value);
		this.setName(name);
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
		//Situation 1: Look up for functions, if name of the variable already exists as a function, nothing will happen.
		try{
			getProgram().getFunction(this.name);
			throw new IllegalArgumentException("Already declared as function");
		} catch (NoSuchElementException excep){
			//If not like that, go further
		}
		
		//Situation 2: Find the first variable with that name and assign
		Set<Variable> programVariables = getProgram().getVariables();
		Optional<Variable> assign = programVariables.stream().filter(variable -> variable.getName().equals(name)).findFirst();
		if(assign.isPresent()){
			assign.get().setValue(value.calculate());
		}
		//Situation 3: If variable doesn't exist yet, make a new one with the provided name and value
		else getProgram().addVariable(new Variable<S>(name, value.calculate()));
		//Set break if needed
		if (value instanceof FunctionCall && ((FunctionCall)value).breakDiscovered()) setBreakDiscovered(true);
	}
	
	//Assign in a function
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals){
		//No need to check for situation 1. Look to the local variables to see if it already exist.
		Optional<Variable> assign = locals.stream().filter(variable -> variable.getName().equals(name)).findFirst();
		if(assign.isPresent()){
			assign.get().setValue(value.calculate(arguments, locals));
		}
		//If not introduce a new local variable with the name and provided value
		else locals.add(new Variable<S>(name, value.calculate(arguments, locals)));
		//Set break if needed
		if (value instanceof FunctionCall && ((FunctionCall)value).breakDiscovered()) setBreakDiscovered(true);
		//Nothing to return
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
