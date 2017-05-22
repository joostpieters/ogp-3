package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.CircularObject;
public abstract class CircObj extends Expression<CircularObject> {

	public CircObj(SourceLocation location){
		super(location);
	}
	
	@Override
	public void setProgram(Program program) {
		 super.setProgram(program);
	}
	
	
	public CircularObject calculate(Object[] arguments, Set<Variable> locals){
		return calculate();
	}

}
