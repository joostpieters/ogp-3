package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class IfElse extends Statement {
	
	//Initialize variables
	private Expression<? extends Boolean> condition;
	private Statement bodyif;
	private Statement bodyelse;
	private boolean existif;
	private boolean existelse;
	private boolean noTimeConsumed;
	private boolean breakDiscovered;

	
	//Constructor
	public IfElse(Expression<? extends Boolean> condition, Statement bodyif, Statement bodyelse, SourceLocation location) {
		super(location);
		this.bodyif = bodyif;
		this.bodyelse = bodyelse;
		this.condition = condition;
	}
	
	
	//get NotimeConsumed
	public boolean NoTimeConsumed(){
		return noTimeConsumed;
	}
	
	//Get breakDiscovered
	public boolean breakDiscovered(){
		return breakDiscovered;
	}
		
	//setBreakDiscovered
	public void setbreakDiscovered(boolean discovered){
		this.breakDiscovered = discovered;
	}

	//setNoTimeConsumed
	public void setNoTimeConsumed(boolean yesno){
		noTimeConsumed = yesno;
	}
	
	

	//Run the if/else statement
	@Override
	public void run() {
		noTimeConsumed = false;
		breakDiscovered = false;
		if(!existif && !existelse){
			if (condition.calculate()) existif = true;
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

	//Run inside of function with given arguments and variables
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals) {
		setbreakDiscovered(false);
		noTimeConsumed = false;
		if (condition.calculate(arguments, locals)) existif = true;
		else {
			if (bodyelse == null) return Optional.empty();
			existelse = true;
		}
		if(existif) {
			Optional result = bodyif.run(arguments, locals);
			if(bodyif.breakDiscovered()){ 
				setbreakDiscovered(true);
				}
			else setbreakDiscovered(false);
			return result;
		}
		if(existelse) {
			Optional result = bodyelse.run(arguments, locals);
			if(bodyelse.breakDiscovered()){ 
				setbreakDiscovered(true);
				}
			else setbreakDiscovered(false);
			return result;
		}
		return Optional.empty();
	}

	//Set the program for every part of the if/else statement
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		condition.setProgram(program);
		bodyif.setProgram(program);
		if(bodyelse != null) bodyelse.setProgram(program);
	}
	
	

}
