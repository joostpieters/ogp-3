package asteroids.model.programs;

import java.util.*;
import asteroids.model.CircularObject;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Gety extends Expression<Double> {
	
	//Initialize variables
	private Expression<? extends CircularObject> expression;
	
	
	//Constructor
	public Gety(Expression<? extends CircularObject> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//Calculate the yCoordinate
	@Override
	public Double calculate() throws IllegalArgumentException {
		return expression.calculate().getPositionArray()[1];
	}
	
	//Calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return expression.calculate(arguments, locals).getPositionArray()[1];
	}

	//Set Program for expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}

}
