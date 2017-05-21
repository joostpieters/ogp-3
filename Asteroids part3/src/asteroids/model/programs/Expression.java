package asteroids.model.programs;

import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;



public abstract class Expression<S> {
	
	private SourceLocation location;
	private Program program;
	public abstract S evaluate() throws IllegalArgumentException;
	
	//Protected?
	protected Expression(SourceLocation location){
		this.location = location;
		
	}
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return program;
	}

}
