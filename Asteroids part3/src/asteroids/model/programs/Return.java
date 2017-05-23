package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Return extends Statement {
	
	//Initialize variables
	private Expression value;

	//Constructor
	public Return(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}

	//Run
	@Override
	public void run() {
		throw new IllegalArgumentException();	
	}
	
	//Run inside of function
		@Override
		public Optional run(Object[] arguments, Set<Variable> locals) {
			return Optional.of(value.calculate(arguments, locals));
		}
	
	//Set program for value
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}

	

}
