package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Print extends Statement {
	
	//Initialize variables
	private Expression value;

	
	//Constructor
	public Print(Expression value, SourceLocation location) {
		super(location);
		this.value = value;
	}
	
	//Run the print 
	@Override
	public void run() {
		Object eval = value.calculate();
		if (eval == null) System.out.println("null");
		else System.out.println(eval.toString());
		getProgram().addResult(eval);
	}
	
	//No print inside of function
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals) {
		throw new IllegalArgumentException();
	}

	//Set program for value
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}

	
}