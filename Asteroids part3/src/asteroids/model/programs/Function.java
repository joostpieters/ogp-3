package asteroids.model.programs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Function {
	private String name;
	private Statement body;
	private SourceLocation location;
	private Program program;
	private boolean breakDiscovered;

	
	//Constructor
	public Function(String name, Statement body, SourceLocation location) {
		this.name = name;
		this.body = body;
		this.location = location;
	}
	
	//Get function name
	public String getName() {
		return name;
	}
	
	//calculate the function
	public Object calculate(Object[] arguments){
		setBreakDiscovered(false);
		Set<Variable> locals = new HashSet<Variable>();
		try{
			//If there is a break nothing happens
			Optional result = body.run(arguments ,locals);
			if (body.breakDiscovered()) {
				setBreakDiscovered(true);
				return null;
			}
			//No break
			else setBreakDiscovered(false);
			return result.get();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	//Break?
	public boolean breakDiscovered() {
		return breakDiscovered;
	}
	
	//Break discovered set
	private void setBreakDiscovered(boolean discovered) {
		breakDiscovered = discovered;
	}

	//Set the program the function and its parts belong to.
	public void setProgram(Program program) {
		this.program = program;
		body.setProgram(program);
	}



	

}
