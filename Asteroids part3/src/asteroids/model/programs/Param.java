package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Param extends Expression<Object> {

	//Initialize variables
	private String param;
	private Function function;

	//Constructor for param
	public Param(String param, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.param = param;
	}
	
	//Not allowed inside things that are not functions
	@Override
	public Object calculate() {
		throw new IllegalArgumentException();
	}

	//Calculate in function
	@Override
	public Object calculate(Object[] arguments, Set<Variable> locals) throws IndexOutOfBoundsException {
		int argIndex = Integer.parseInt(param.substring(1, param.length()));
		return arguments[argIndex-1];
	}

	//Set program
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}

}
