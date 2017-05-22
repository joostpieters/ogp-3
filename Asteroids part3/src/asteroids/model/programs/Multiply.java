package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Multiply extends Expression<Double> {
	
	//Initialize variables
	private Expression<? extends Double> expression1;
	private Expression<? extends Double> expression2;

	
	//Constructor for multiply
	public Multiply(Expression<? extends Double> expression1, Expression<? extends Double> expression2,	SourceLocation location) {
		super(location);
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	//Calculate the mulitplication of both expressions
	@Override
	public Double calculate() {
		return expression1.calculate() * expression2.calculate();
	}

	//Calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return expression1.calculate(arguments, locals) * expression2.calculate(arguments, locals);
	}

	//Set program for all expressions
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression1.setProgram(program);
		expression2.setProgram(program);
	}

}
