package asteroids.model.programs;



import java.util.*;
import asteroids.model.CircularObject;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;

public class GetVy extends Expression<Double>{
	
	private Expression<? extends CircularObject> exp;
	
	
	public GetVy(Expression<? extends CircularObject> exp,SourceLocation location) {
		super(location);
		this.exp = exp;
	}


	@Override
	public Double evaluate() throws IllegalArgumentException{
		return exp.evaluate().getVelocityArray()[1];
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		exp.setProgram(program);
	}
	

}
