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
		this.getProgram().setLocation(getLocation());
		if (this.getProgram().getTime() <= 0.2) {
			setTimeConsumed(true);
			this.getProgram().setSkip(true);
			return;
		}
		//this.getProgram().setSkip(false);
		this.getProgram().moveTime();
	}
	
	//Run is not allowed in function
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals){
		throw new IllegalArgumentException();
	}
	
	
}