package asteroids.model.programs;

import java.util.*;

import asteroids.part3.programs.SourceLocation;

public class ThrustOff extends Statement {

	private boolean TimeConsumed;

	//Constructor 
	public ThrustOff(SourceLocation location) {
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
		this.setTimeConsumed(false);
		this.getProgram().setLocation(getLocation());
		if (this.getProgram().getTime() < 0.2) {
			this.setTimeConsumed(true);
			return;
			
		}
		this.getProgram().getShip().thrustOff();
		this.getProgram().moveTime();
	}
	
	//Run is not allowed in function
		@Override
		public Optional run(Object[] arguments, Set<Variable> locals){
			throw new IllegalArgumentException();
		}
	
	
}