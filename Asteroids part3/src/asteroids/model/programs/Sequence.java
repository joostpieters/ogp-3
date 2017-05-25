package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Sequence extends Statement {

	//Initialize variables
	private List<Statement> statements;
	private boolean breakDiscovered;
	private boolean TimeConsumed;

	//Constructor for Sequence
	public Sequence(List<Statement> statements, SourceLocation location) {
		super(location);
		this.setStatements(statements);
	}
	
	//NoTimeConsumed
	@Override
	public boolean NoTimeConsumed(){
		return TimeConsumed;
	}

	//breakDiscovered
	@Override
	public boolean breakDiscovered(){
		return breakDiscovered;
	}

	//setStatements
	public void setStatements(List<Statement> stat){
		this.statements = stat;
	}
	
	//getStatements
	public List<Statement> getStatements(){
		return this.statements;
	}
	
	//Set program for each statement
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		for(Statement stat : statements) stat.setProgram(program);
	}
	
	
	//Run Sequence
	@Override
	public void run() {
		//Set variables
		TimeConsumed = false;
		breakDiscovered = false;
		//Get the current location
		SourceLocation currentlocation = getProgram().getLocation();
		//for each statement
		for(int i = 0; i < statements.size(); i++) {
			Statement statement = statements.get(i);
			SourceLocation nextLocation = null;
			//Set the next statement if it exists
			if (i == statements.size()-1){
				nextLocation = null;
			}
			else nextLocation = statements.get(i+1).getLocation();
			//Run each statement if valid
			if(i == statements.size()-1 || nextLocation.getLine() > currentlocation.getLine()||
					(nextLocation.getLine() == currentlocation.getLine() && nextLocation.getColumn() > currentlocation.getColumn())){
				statement.run();
				//Check for breaks and time used to run
				if(statement.NoTimeConsumed()){
					TimeConsumed = true;
					return;
				}
				if(statement.breakDiscovered()) {
					breakDiscovered = true;
					return;
				}
			}
		}
	}

	//Run inside function
	@Override
	public Optional run(Object[] arguments, Set<Variable> locals) {
		breakDiscovered = false;
		for(int i = 0; i < statements.size(); i++) {
			Statement current = statements.get(i);
			Optional result = current.run(arguments, locals);
			if (result.isPresent()) {
				if (current.breakDiscovered()) breakDiscovered = true;
				return result;
			}
			if (current.breakDiscovered()) {
				breakDiscovered = true;
				return Optional.empty();
			}
		}
		return Optional.empty();
	}


	
}
