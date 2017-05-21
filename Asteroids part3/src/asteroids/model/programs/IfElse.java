package asteroids.model.programs;


import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;


public class IfElse extends Statement{

	//? Boolean toevoegen?
	private Expression<Boolean> condition;
	private Statement bodyif;
	private Statement bodyelse;
	private boolean existif;
	private boolean existelse;
	private boolean noTimeConsumed;
	private boolean breakDiscovered;
	
	
	public IfElse(Expression<Boolean> condition, Statement bodyif, Statement bodyelse, SourceLocation location) {
		super(location);
		this.bodyif = bodyif;
		this.bodyelse = bodyelse;
		this.condition = condition;
	}

	
	public boolean NoTimeConsumed(){
		return noTimeConsumed;
	}
	
	public boolean breakDiscovered(){
		return breakDiscovered;
	}
	
	public void setbreakDiscovered(boolean discovered){
		this.breakDiscovered = discovered;
	}

	public void setNoTimeConsumed(boolean yesno){
		noTimeConsumed = yesno;
	}
	
	public void setProgram(Program program){
		super.setProgram(program);
		condition.setProgram(program);
		bodyif.setProgram(program);
		if(bodyelse != null) bodyelse.setProgram(program);
	}
	
	@Override
	public void run() {
		noTimeConsumed = false;
		breakDiscovered = false;
		if(!existif && !existelse){
			if (condition.evaluate()) existif = true;
			else{
				if (bodyelse == null) return;
				existelse = true;
			}
		}
		if(existif){
			bodyif.run();
			if(bodyif.NoTimeConsumed()){
				setNoTimeConsumed(true);
				return;
			}
			else{
				if(bodyif.breakDiscovered()) setbreakDiscovered(true);
				else setbreakDiscovered(false);
				existif = false;
				}
			
		}
		if(existelse){
			bodyelse.run();
			if (bodyelse.NoTimeConsumed()){
				setNoTimeConsumed(true);
				return;
			}
			else{
				if(bodyelse.breakDiscovered()) setbreakDiscovered(true);
				else setbreakDiscovered(false);
				existelse = false;
			}
		}
		return;
		
	}
	
	
	
	
	
	
	
	
	

}
