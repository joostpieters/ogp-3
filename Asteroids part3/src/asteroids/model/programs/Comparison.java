package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Comparison extends Expression<Boolean> {
	
	//Initialize variables
	private Expression<Double> rightExpression;
	private Expression<Double> leftExpression;

	//Constructor for Comparison
	public Comparison(Expression<Double> expression1, Expression<Double> expression2,
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
	
	//Calculate the comparison
	@Override
	public Boolean calculate() {
		return getRightOperand().calculate() < getLeftOperand().calculate();
	}

	//Calculate the comparison inside of a function
	@Override
	public Boolean calculate(Object[] arguments, Set<Variable> locals) {
		return getRightOperand().calculate(arguments, locals) < getLeftOperand().calculate(arguments, locals);
	}

	//Set program for every part of the comparison expression.
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getRightOperand().setProgram(program);
		getLeftOperand().setProgram(program);
	}




}
