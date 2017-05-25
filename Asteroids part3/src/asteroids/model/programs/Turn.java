package asteroids.model.programs;

import java.util.*;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Turn extends Statement {

	private boolean NoTimeConsumed;
	private Expression angle;
	
	//Constructor 
	public Turn(Expression angle, SourceLocation location) {
		super(location);
		this.angle = angle;
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
		getProgram().getShip().turn((Double)angle.calculate());
		getProgram().moveTime();
	}
	
	//Run is not allowed in function
		@Override
		public Optional run(Object[] arguments, Set<Variable> locals){
			throw new IllegalArgumentException();
		}
	
		
		@Override
		public void setProgram(Program program){
			super.setProgram(program);
			angle.setProgram(program);
		}
	
}