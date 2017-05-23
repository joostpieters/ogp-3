package asteroids.model.programs;

import java.util.*;
import asteroids.model.CircularObject;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetVy extends Expression<Double> {
	
	//Initialise variables
	private Expression<? extends CircularObject> expression;
	
	//Constructor for GetVy
	public GetVy(Expression<? extends CircularObject> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//Calcuate the yvelocity
	@Override
	public Double calculate() throws IllegalArgumentException {
		return expression.calculate().getVelocityArray()[1];
	}

	//Calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return expression.calculate(arguments, locals).getVelocityArray()[1];
	}

	//Set the program for the expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	

}
