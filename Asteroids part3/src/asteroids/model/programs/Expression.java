package asteroids.model.programs;

import java.util.Set;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Expression<S> implements ProgramLocation{
	
	//Initialize variables
	private SourceLocation location;
	private Program currentprogram;
	
	//Constructor
	protected Expression(SourceLocation location){
		this.location = location;
	}
	
	//Calculate the expression in normal code or in a function.
	public abstract S calculate() throws IllegalArgumentException;
	public abstract S calculate(Object[] actualArgs, Set<Variable> localVariables) throws IllegalArgumentException;
	
	
	//setProgram
	public void setProgram(Program program){
		this.currentprogram = program;
	}
	
	//getProgram
	public Program getProgram(){
		return currentprogram;
	}

}
