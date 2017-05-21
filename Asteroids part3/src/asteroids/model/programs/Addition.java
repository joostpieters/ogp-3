package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;

public class Addition extends Expression<Double> {

	private Expression<? extends Double> exp1;
	private Expression<? extends Double> exp2;
	
	
	public Addition(Expression<? extends Double> exp1, Expression<? extends Double> exp2, SourceLocation location) {
		super(location);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public Double evaluate() throws IllegalArgumentException {
		return exp1.evaluate() + exp2.evaluate();
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		exp1.setProgram(program);
		exp2.setProgram(program);
	}
	
	
	

}
