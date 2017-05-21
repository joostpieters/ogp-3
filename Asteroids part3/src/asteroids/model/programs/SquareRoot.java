package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;


public class SquareRoot extends Expression<Double>{
	
	Expression<Double> exp;
	
	protected SquareRoot(Expression<Double> exp, SourceLocation location) {
		super(location);
		this.exp = exp;
	}

	@Override
	public Double evaluate(){
		return Math.sqrt(exp.evaluate());
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		exp.setProgram(program);
	}
	

}
