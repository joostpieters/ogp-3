package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SquareRoot extends Expression<Double> {
	
	//Initialize variable
	Expression<Double> operand;

	//Constructor
	public SquareRoot(Expression<Double> expression, SourceLocation location) {
		super(location);
		this.setOperand(expression);
	}

	//getOperand
	public Expression<Double> getOperand(){
		return this.operand;
	}
	
	//setOperand
	public void setOperand(Expression<Double> op){
		this.operand = op;
	}
	
	//Calculate the square root of expression
	@Override
	public Double calculate() {
		return Math.sqrt(operand.calculate());
	}

	//Calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return Math.sqrt(operand.calculate(arguments, locals));
	}

	//Set program for expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		operand.setProgram(program);
	}
	


}
