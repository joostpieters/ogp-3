package asteroids.model.programs;


import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReadVariable extends Expression<Object> {

	//Initialize variables
	private String name;
	
	
	//Constructor
	public ReadVariable(String name, SourceLocation location){
		super(location);
		setName(name);
	}
	
	//getName
	public String getName(){
		return this.name;
	}
	
	//setName
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}

	@Override
	public Object calculate(){
		return getProgram().getVariable(name).getValue();
	}
	
	public Object calculate(Object[] arguments, Set<Variable> locals){
		Optional<Variable> localVariable = locals.stream().filter(variable -> variable.getName().equals(name)).findFirst();
		if(localVariable.isPresent()) return localVariable.get().getValue();
		else return getProgram().getVariable(name).getValue();
	}


}
