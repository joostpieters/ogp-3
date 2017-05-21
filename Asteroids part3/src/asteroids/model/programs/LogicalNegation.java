package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;

public class LogicalNegation extends Expression<Boolean>{
	
	
	private Expression<? extends Boolean> exp;
	
	protected LogicalNegation(Expression<? extends Boolean> exp, SourceLocation location) {
		super(location);
		this.exp = exp;
	}

	@Override
	public Boolean evaluate(){
		return !exp.evaluate();
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		exp.setProgram(program);
	}

}
