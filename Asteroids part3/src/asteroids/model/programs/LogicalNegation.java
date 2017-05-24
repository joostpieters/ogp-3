package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;


public class LogicalNegation extends Expression<Boolean> {
	
	//Initialize variables
	private Expression<Boolean> operand;

	//Constructor
	public LogicalNegation(Expression<Boolean> expression, SourceLocation location) {
		super(location);
		setOperand(expression);
	}
	
	//setOperand
	public void setOperand(Expression<Boolean> operand){
		this.operand = operand;
	}
	
	//getOperand
	public Expression<Boolean> getOperand(){
		return this.operand;
	}

	//Calculate  logical negation of  expression
	@Override
	public Boolean calculate() {
		return !operand.calculate();
	}

	//Calculate inside of function
	@Override
	public Boolean calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return !operand.calculate(arguments, locals);
	}

	//Set program for expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		operand.setProgram(program);
	}
	
}
