package asteroids.model.programs;

import java.util.*;

import asteroids.part3.programs.SourceLocation;

public class ThrustOn extends Statement {

	private boolean NoTimeConsumed;

	//Constructor 
	public ThrustOn(SourceLocation location) {
		super(location);
	}

	//Set Time 
	protected void setNoTimeConsumed(boolean time) {
		this.NoTimeConsumed =  time;
		
	}
	
	//get Time
	public boolean noTimeConsumed(){
		return NoTimeConsumed;
	}
	
	
	//Run
	@Override
	public void run() {
		setNoTimeConsumed(false);
		getProgram().setLocation(getLocation());
		if (getProgram().getTime() < 0.2) {
			setNoTimeConsumed(true);
			return;
		}
		getProgram().getShip().thrustOn();
		getProgram().moveTime();
	}
	
	//Run is not allowed in function
		@Override
		public Optional run(Object[] arguments, Set<Variable> locals){
			throw new IllegalArgumentException();
		}
	
	
}