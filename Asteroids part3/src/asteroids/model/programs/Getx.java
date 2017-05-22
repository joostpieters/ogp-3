package asteroids.model.programs;

import java.util.*;
import asteroids.model.CircularObject;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Getx extends Expression<Double> {
	
	//Initialisation of variables
	private Expression<? extends CircularObject> expression;
	
	//Constructor vor Getx
	public Getx(Expression<? extends CircularObject> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//Calculate the xCoordintate
	@Override
	public Double calculate() throws IllegalArgumentException {
		return expression.calculate().getPositionArray()[0];
	}

	//Calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return expression.calculate(arguments, locals).getPositionArray()[0];
	}
	
	//Set program for expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}


}
