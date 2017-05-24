package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Statement implements ProgramLocation {

	//Initialize variables
	private SourceLocation location;
	private Program currentprogram;
	
	
	//Constructor using given location in the program.
	public Statement(SourceLocation location) {
		this.location = location;
	}

	//Get the location of the statement
	public SourceLocation getLocation() {
		return location;
	}
	
	//Set the program the statement belongs to
	public void setProgram(Program program){
		this.currentprogram = program;
	}
	
	//Get the program the statement belongs to
	public Program getProgram(){
		return currentprogram;
	}

	//Break discovered in code
	public boolean breakDiscovered() {
		return false;
	}

	//Used time when running
	public boolean NoTimeConsumed(){
		return false;
	}
	
	//Run the program
		public abstract void run();
		public abstract Optional run(Object[] arguments, Set<Variable> locals);
}
