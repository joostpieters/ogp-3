package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Break extends Statement {

	//Constructor for break
	public Break(SourceLocation location) {
		super(location);
	}
	
	//Is there a break discovered?
		public boolean breakDiscovered(){
			return true;
		}

	//Running will return nothing because of the break
	@Override
	public void run() {
		return;
	}
	
	//Running inside of function
		@Override
		public Optional run(Object[] arguments, Set<Variable> locals) {
			return Optional.empty();
		}
	
	//Set the program the break belongs to
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}
	
	

	

}
