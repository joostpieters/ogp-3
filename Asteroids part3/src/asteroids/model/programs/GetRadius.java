package asteroids.model.programs;

import java.util.*;
import asteroids.model.CircularObject;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetRadius extends Expression<Double> {
	
	//Initialise variables
	private Expression<? extends CircularObject> expression;

	//Constructor
	public GetRadius(Expression<? extends CircularObject> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//Calculate the radius
	@Override
	public Double calculate() throws IllegalArgumentException {
		return expression.calculate().getRadius();
	}

	//Calculate inside of function with given arguments and locals
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return expression.calculate(arguments, locals).getRadius();
	}

	//set program for the expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	
}
