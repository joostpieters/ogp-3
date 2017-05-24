package asteroids.model.programs;

import java.util.*;
import asteroids.model.CircularObject;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetVy extends Expression<Double> {
	
	//Initialize variables
	private Expression<CircularObject> circObj;

	
	//Constructor for GetVx
	public GetVy(Expression<CircularObject> expression, SourceLocation location) {
		super(location);
		setCircObj(expression);
	}

	//set circObj
	public void setCircObj(Expression<CircularObject> obj){
		this.circObj = obj;
	}
	
	//get circObj
	public Expression<CircularObject> getCircObj(){
		return this.circObj;
	}
	
	//calculate the yvelocity
	@Override
	public Double calculate() throws IllegalArgumentException {
		return getCircObj().calculate().getVelocityArray()[1];
	}

	//calculate inside of function
	@Override
	public Double calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		return  getCircObj().calculate(arguments, locals).getVelocityArray()[1];
	}

	//Set program for the expression
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		 getCircObj().setProgram(program);
	}
	
}
