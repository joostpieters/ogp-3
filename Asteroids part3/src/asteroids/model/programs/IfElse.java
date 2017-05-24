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
	private boolean TimeConsumed;
	private boolean breakDiscovered;

	
	//Constructor
	public IfElse(Expression<? extends Boolean> condition, Statement bodyif, Statement bodyelse, SourceLocation location) {
		super(location);
		setIfBody(bodyif);
		setElseBody(bodyelse);
		this.condition = condition;
	}
	
	
	//Get if body
	public Statement getIfBody(){
		return this.bodyif;
	}
	
	//set if body
	public void setIfBody(Statement body){
		this.bodyif = body;
	}
	
	//Get else body
	public Statement getElseBody(){
		return this.bodyelse;
	}
	
	//Set else body
	public void setElseBody(Statement body){
		this.bodyelse = body;
	}
	
	//get TimeConsumed
	public boolean TimeConsumed(){
		return TimeConsumed;
	}
	
	//Get breakDiscovered
	public boolean breakDiscovered(){
		return breakDiscovered;
	}
		
	//setBreakDiscovered
	public void setbreakDiscovered(boolean discovered){
		this.breakDiscovered = discovered;
	}

	//setTimeConsumed
	public void setTimeConsumed(boolean yesno){
		TimeConsumed = yesno;
	}
	
	//Run the if/else statement
	@Override
	public void run() {
		//Check for if and else statements
		TimeConsumed = false;
		breakDiscovered = false;
		if(!existif && !existelse){
			if (condition.calculate()) existif = true;
			else{
				if (bodyelse == null) return;
				existelse = true;
			}
		}
		//evaluate bodyif
		if(existif){
			bodyif.run();
			if(bodyif.NoTimeConsumed()){
				setTimeConsumed(true);
				return;
			}
			else{
				if(bodyif.breakDiscovered()) setbreakDiscovered(true);
				else setbreakDiscovered(false);
				existif = false;
				}
			
		}
		//evaluate bodyelse
		if(existelse){
			bodyelse.run();
			if (bodyelse.NoTimeConsumed()){
				setTimeConsumed(true);
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
		TimeConsumed = false;
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
