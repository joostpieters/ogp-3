package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SignChange extends Expression<Double> {
	
	//Initialize variable
	private Expression<Double> operand;

	//Constructor for SignChange
	public SignChange(Expression<Double> expression, SourceLocation location) {
		super(location);
		this.setOperand(expression);
	}

	//setOperand
	public void setOperand(Expression<Double> op){
		this.operand = op;
	}
	
	//getOperand
	public Expression<Double> getOperand(){
		return this.operand;
	}
	
	//Calculate negative sign
	@Override
	public Double calculate() {
		return -this.getOperand().calculate();
	}

	//Calculate in function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return -this.getOperand().calculate(arguments, locals);
	}

	//Set program for expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		this.getOperand().setProgram(program);
	}
	

}
