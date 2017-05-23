package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Addition extends Expression<Double> {
	
	
	private Expression<? extends Double> expression1;
	private Expression<? extends Double> expression2;

	//Constructor for addition
	public Addition(Expression<? extends Double> expression1, Expression<? extends Double> expression2,
			SourceLocation location) {
		super(location);
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	//Set the program for every part of the addition expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression1.setProgram(program);
		expression2.setProgram(program);
	}
	
	//Calculate the whole addition expression
	@Override
	public Double calculate() {
		return expression1.calculate() + expression2.calculate();
	}

	//Calculate the whole addition expression in a function with given arguments and local variables.s
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return expression1.calculate(arguments, locals) + expression2.calculate(arguments, locals);
	}

	
		

}
