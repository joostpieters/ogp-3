package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Multiply extends Expression<Double> {
	
	
	private Expression<Double> rightExpression;
	private Expression<Double> leftExpression;

	//Constructor
	public Multiply(Expression<Double> expression1, Expression<Double> expression2, SourceLocation location){
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
		
	//Set the program for every part of the addition expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		getRightOperand().setProgram(program);
		getLeftOperand().setProgram(program);
	}
	
	//Calculate the addition expression
	@Override 
	public Double calculate(){
		return getLeftOperand().calculate() * getRightOperand().calculate();
	}
	
	//Calculate the addition expression inside of a function
	@Override 
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException{
		return getLeftOperand().calculate() * getRightOperand().calculate();
	}
	
		

}
