package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;


public class SignChange extends Expression<Double>{
	
	private Expression<? extends Double> exp;
	
	public SignChange(Expression<? extends Double> exp, SourceLocation location){
		super(location);
		this.exp = exp;
	}

	@Override
	public Double evaluate() throws IllegalArgumentException {
		return -exp.evaluate();
	}

	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		exp.setProgram(program);
	}
	
}

