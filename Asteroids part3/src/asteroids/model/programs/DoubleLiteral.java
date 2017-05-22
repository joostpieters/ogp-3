package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class DoubleLiteral extends Expression<Double> {
	
	private double literal;

	//Constructor for double literal
	public DoubleLiteral(double literal, SourceLocation location) {
		super(location);
		this.literal = literal;
	}

	//Calculate the expression, i.e return the double
	@Override
	public Double calculate() {
		return literal;
	}
	
	//Calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals){
		return literal;
	}

	//Set the program the literal belongs to.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}
	
	
}
