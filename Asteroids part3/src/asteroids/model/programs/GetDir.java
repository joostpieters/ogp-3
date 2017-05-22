package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetDir extends Expression<Double> {

	//Constructor
	public GetDir(SourceLocation location) {
		super(location);
	}

	//calculate the direction
	@Override
	public Double calculate() {
		//TODO
		return getProgram().getShip().getDirection();
	}

	//calculate inside of function body
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return getProgram().getShip().getDirection();
	}

	//set the program for the expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}


}
