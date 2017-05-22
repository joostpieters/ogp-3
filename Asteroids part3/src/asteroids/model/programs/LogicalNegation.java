package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;


public class LogicalNegation extends Expression<Boolean> {
	
	//Initialize variables
	private Expression<? extends Boolean> expression;

	
	//Constructor
	public LogicalNegation(Expression<? extends Boolean> expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}

	//Calculate  logical negation of  expression
	@Override
	public Boolean calculate() {
		return !expression.calculate();
	}

	//Calculate inside of function
	@Override
	public Boolean calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return !expression.calculate(arguments, locals);
	}

	//Set program for expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	
}
