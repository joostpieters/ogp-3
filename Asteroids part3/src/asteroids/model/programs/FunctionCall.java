package asteroids.model.programs;

import java.util.*;
import asteroids.part3.programs.SourceLocation;

public class FunctionCall extends Expression<Object> {
	private String name;
	private List<Expression> arguments;
	private boolean breakDiscovered;

	
	//Constructor
	public FunctionCall(String name,
			List<Expression> arguments, SourceLocation location) {
		super(location);
		this.name = name;
		this.arguments = arguments;
	}

	//break discovered
	public boolean breakDiscovered() {
		return breakDiscovered;
	}

	//set break discovered
	public void setBreakDiscovered(boolean discovered) {
		this.breakDiscovered = discovered;
	}
	
	//Calculate the function call
	@Override
	public Object calculate() {
		//get all the arguments and the name of the function
		Function function =  getProgram().getFunction(name);
		Object[] calculatedarguments = arguments.stream().map(arg -> arg.calculate()).toArray();
		Object result = function.calculate(calculatedarguments);
		if (function.breakDiscovered()) setBreakDiscovered(true);
		else setBreakDiscovered(false);
		return result;
	}

	//Calculate inside function
	@Override
	public Object calculate(Object[] arguments, Set<Variable> locals) throws IllegalArgumentException {
		setBreakDiscovered(false);
		Function function =  getProgram().getFunction(name);
		Object[] calculatedarguments = this.arguments.stream().map(arg -> arg.calculate(arguments, locals)).toArray();
		Object result = function.calculate(calculatedarguments);
		if (function.breakDiscovered()) {
			setBreakDiscovered(true);
		}
		else setBreakDiscovered(false);
		return result;
	}
	



}
