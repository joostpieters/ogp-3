package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Comparison extends Expression<Boolean> {
	
	private Expression<? extends Double> expression1;
	private Expression<? extends Double> expression2;

	//Constructor for Comparison
	public Comparison(Expression<? extends Double> expression1, Expression<? extends Double> expression2,
			SourceLocation location) {
		super(location);
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	//Calculate the comparison
	@Override
	public Boolean calculate() {
		return expression1.calculate() < expression2.calculate();
	}

	//Calculate the comparison inside of a function
	@Override
	public Boolean calculate(Object[] arguments, Set<Variable> locals) {
		return expression1.calculate(arguments, locals) < expression2.calculate(arguments, locals);
	}

	//Set program for every part of the comparison expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression1.setProgram(program);
		expression2.setProgram(program);
	}




}
