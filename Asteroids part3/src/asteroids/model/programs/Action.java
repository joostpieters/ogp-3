package asteroids.model.programs;

import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;


public abstract class Action extends Statement {

	public boolean noTimeConsumed;
	
	
	public Action(SourceLocation location) {
		super(location);
	}
	
	public void setNoTimeConsumed(boolean yesno){
		this.noTimeConsumed = yesno;
	}
	
	public boolean noTimeConsumed(){
		return noTimeConsumed;
	}


}
