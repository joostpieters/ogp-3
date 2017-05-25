package asteroids.model.programs;

import java.util.*;
import asteroids.part3.programs.SourceLocation;

public class Skip extends Statement {

	private boolean TimeConsumed;

	//Constructor 
	public Skip(SourceLocation location) {
		super(location);
	}

	//Set Time 
	protected void setTimeConsumed(boolean time) {
		this.TimeConsumed =  time;
		
	}
	
	//get Time
	public boolean TimeConsumed(){
		return TimeConsumed;
	}
	
	
	//Run
	@Override
	public void run() {
		setTimeConsumed(false);
		getProgram().setLocation(getLocation());
		if (getProgram().getTime() < 0.2) {
			setTimeConsumed(true);
			return;
		}
		getProgram().moveTime();
	}
	
	//Run is not allowed in function
		@Override
		public Optional run(Object[] arguments, Set<Variable> locals){
			throw new IllegalArgumentException();
		}
	
	
}