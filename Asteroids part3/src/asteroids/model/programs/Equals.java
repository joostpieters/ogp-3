package asteroids.model.programs;



import java.util.*;
import asteroids.model.CircularObject;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;

public class Equals extends Expression<Boolean>{
	
	private Expression exp1;
	private Expression exp2;	
	
	public Equals(Expression exp1, Expression exp2, SourceLocation location) {
		super(location);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}


	@Override
	public Boolean evaluate() throws IllegalArgumentException{
		Object exp1good = exp1.evaluate();
		Object exp2good = exp2.evaluate();
		return exp1good.equals(exp2good);
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		exp1.setProgram(program);
		exp2.setProgram(program);
	}
	

}
