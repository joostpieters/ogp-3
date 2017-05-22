package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SignChange extends Expression<Double> {
	
	//Initialize variable
	private Expression<? extends Double> expression;

	//Constructor for SignChange
	public SignChange(Expression<? extends Double> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//Calculate negative sign
	@Override
	public Double calculate() {
		return -expression.calculate();
	}

	//Calculate in function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return -expression.calculate(arguments, locals);
	}

	//Set program for expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	

}
