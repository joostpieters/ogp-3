package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Equals extends Expression<Boolean> {
	
	//Initial variables
	private Expression expression1;
	private Expression expression2;

	//Equals constructor
	public Equals(Expression expression1, Expression expression2,
			SourceLocation location) {
		super(location);
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	//Calculate and see if both expressions are the same
	@Override
	public Boolean calculate() {
		Object calculated1 = expression1.calculate();
		Object calculated2 = expression2.calculate();
		return calculated1.equals(calculated2);
	}

	//Calculate inside of a function
	@Override
	public Boolean calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		Object calculated1 = expression1.calculate(arguments, locals);
		Object calculated2 = expression2.calculate(arguments, locals);
		return calculated1.equals(calculated2);
	}

	//Set the program
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression1.setProgram(program);
		expression2.setProgram(program);
	}



}
