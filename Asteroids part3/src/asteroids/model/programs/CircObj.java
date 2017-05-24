package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.CircularObject;
public abstract class CircObj extends Expression<CircularObject> {
	
	private Program program;

	//Constructor for circular Object expression
	public CircObj(SourceLocation location){
		super(location);
	}
	

	//Set the program for the expression
	@Override
	public void setProgram(Program program) {
		 super.setProgram(program);
	}
	
	//Calculate the Object
	public CircularObject calculate(Object[] arguments, Set<Variable> locals){
		return calculate();
	}

}
