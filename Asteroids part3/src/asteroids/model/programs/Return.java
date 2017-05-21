package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;

public class Return extends Statement{

	private Expression value;
	
	
	public Return(Expression value, SourceLocation location){
		super(location);
		this.value = value;
	}

	@Override
	public void run() {
		throw new IllegalArgumentException();
	}
	
	public void setProgram(Program program){
		super.setProgram(program);
		value.setProgram(program);
	}
	
	
	
}

