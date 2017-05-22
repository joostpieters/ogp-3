package asteroids.model.programs;

import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Expression<S> {
	private SourceLocation location;
	private Program currentprogram;
	public abstract S calculate() throws IllegalArgumentException;
	public abstract S calculate(Object[] actualArgs, Set<Variable> localVariables) throws IllegalArgumentException;
	
	protected Expression(SourceLocation location){
		this.location = location;
	}

	public void setProgram(Program program){
		this.currentprogram = program;
	}
	
	public Program getProgram(){
		return currentprogram;
	}

}
