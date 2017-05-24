package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Equals extends Expression<Boolean> {
	
	//Initial variables
	private Expression<Double> rightExpression;
	private Expression<Double> leftExpression;

	//Equals constructor
	public Equals(Expression expression1, Expression expression2,
			SourceLocation location) {
		super(location);
		setRightOperand(expression1);
		setLeftOperand(expression2);
	}

	//SetLeftOperand
	public void setLeftOperand(Expression<Double> left){
		this.leftExpression = left;
	}
	
	//SetRightOperand
	public void setRightOperand(Expression<Double> right){
		this.rightExpression = right;
	}
	
	//GetRightOperand
	public Expression<Double> getRightOperand(){
		return this.rightExpression;
	}
	
	//GetLeftOperand
	public Expression<Double> getLeftOperand(){
		return this.leftExpression;
	}
	
	//Set the program
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getRightOperand().setProgram(program);
		getLeftOperand().setProgram(program);
	}

	
	//Calculate and see if both expressions are the same
	@Override
	public Boolean calculate() {
		Object calculated1 = getRightOperand().calculate();
		Object calculated2 = getLeftOperand().calculate();
		return calculated1.equals(calculated2);
	}

	//Calculate inside of a function
	@Override
	public Boolean calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		Object calculated1 = getRightOperand().calculate(arguments, locals);
		Object calculated2 = getLeftOperand().calculate(arguments, locals);
		return calculated1.equals(calculated2);
	}

	


}
