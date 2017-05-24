package asteroids.model.programs;

import java.util.*;

import asteroids.part3.programs.SourceLocation;

public class Fire extends Statement {

	private boolean TimeConsumed;

	//Constructor 
	public Fire(SourceLocation location) {
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
	
	
	//Run and fire a bullet from the ship the program runs on
	@Override
	public void run() {
		setTimeConsumed(false);
		getProgram().setLocation(getLocation());
		if (getProgram().getTime() < 0.2) {
			setTimeConsumed(true);
			return;
		}
		getProgram().getShip().fire();
		getProgram().moveTime();
	}
	
	//Run is not allowed in function
		@Override
		public Optional run(Object[] arguments, Set<Variable> locals){
			throw new IllegalArgumentException();
		}
	
	
}