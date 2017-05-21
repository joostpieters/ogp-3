package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;


public class Print extends Statement{

	private Expression value;
	
	public Print(Expression value, SourceLocation location){
		super(location);
		this.value = value;
	}

	@Override
	public void run() {
		Object goodvalue = value.evaluate();
		if(goodvalue == null) System.out.print("null");
		else System.out.println(goodvalue.toString());
		getProgram().addResult(goodvalue);
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		value.setProgram(program);
		
	}
	
	

}
