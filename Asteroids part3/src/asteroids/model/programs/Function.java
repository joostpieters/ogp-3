package asteroids.model.programs;

import asteroids.model.*;
import java.util.*;
import asteroids.part3.programs.SourceLocation;

public class Function {
	//Initialize variables
	private String name;
	private Statement body;
	private SourceLocation location;
	private Program program;
	private boolean hasBreak;

	
	//Constructor
	public Function(String name, Statement body, SourceLocation location) {
		setFunctionName(name);
		setFunctionBody(body);
		this.location = location;
	}
	
	//Retrieve the name of the function
	public String getFunctionName(){
		return this.name;
	}
		
	//Set the name of the function
	public void setFunctionName(String name){
		this.name = name;
	}
		
	//Retrieve the body of the function
	public Statement getFunctionBody(){
		return this.body;
	}
		
	//Set the body of the function
	public void setFunctionBody(Statement body){
		this.body = body;
	}
		
	//Break?
	public boolean breakDiscovered() {
		return this.hasBreak;
	}
	
	//Break discovered set
	private void setBreakDiscovered(boolean discovered) {
		this.hasBreak = discovered;
	}

	//Set the program for the function an its body
	public void setProgram(Program program){
		this.program = program;
		body.setProgram(program);
	}
		
	//Retrieve the program this function and its contents belongs to
	public Program getProgram(){
		return this.program;
	}

	//Calculate the function and it's contents
	public Object calculate(Object[] arguments){//
		//Init
		setBreakDiscovered(false);
		Set<Variable> locals = new HashSet<Variable>();
		try{
			//Look for break statements in the body
			Optional result = body.run(arguments, locals);
			if(body.breakDiscovered()){
				setBreakDiscovered(true);
				return null;
			}
			//If not, calculate the body
			else setBreakDiscovered(false);
			return result.get();
		} 
		
		catch(NoSuchElementException excep){
			throw new IllegalArgumentException();
		}
	}

	

}
