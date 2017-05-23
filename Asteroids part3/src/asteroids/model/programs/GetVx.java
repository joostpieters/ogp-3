package asteroids.model.programs;

import java.util.*;
import asteroids.model.CircularObject;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetVx extends Expression<Double> {
	
	//Initial variable
	private Expression<? extends CircularObject> expression;
	
	//Constructor for GetVx
	public GetVx(Expression<? extends CircularObject> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//calculate the xvelocity
	@Override
	public Double calculate() throws IllegalArgumentException {
		return expression.calculate().getVelocityArray()[0];
	}

	//calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return expression.calculate(arguments, locals).getVelocityArray()[0];
	}

	//Set program for the expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	
}
