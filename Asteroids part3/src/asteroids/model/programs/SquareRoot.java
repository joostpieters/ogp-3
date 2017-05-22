package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SquareRoot extends Expression<Double> {
	
	//Initialize variable
	Expression<Double> expression;

	//Constructor
	public SquareRoot(Expression<Double> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//Calculate the square root of expression
	@Override
	public Double calculate() {
		return Math.sqrt(expression.calculate());
	}

	//Calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return Math.sqrt(expression.calculate(arguments, locals));
	}

	//Set program for expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	


}
