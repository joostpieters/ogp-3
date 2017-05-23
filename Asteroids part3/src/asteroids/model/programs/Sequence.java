package asteroids.model.programs;

import java.util.*;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Sequence extends Statement {

	//Initialize variables
	private List<Statement> statements;
	private boolean breakDiscovered;
	private boolean NoTimeConsumed;

	//Constructor for Sequence
	public Sequence(List<Statement> statements, SourceLocation location) {
		super(location);
		this.statements = statements;
	}
	
	//NoTimeConsumed
	@Override
	public boolean NoTimeConsumed(){
		return NoTimeConsumed;
	}

	//breakDiscovered
	@Override
	public boolean breakDiscovered(){
		return breakDiscovered;
	}

	//Run Sequence
	@Override
	public void run() {
		NoTimeConsumed = false;
		breakDiscovered = false;
		SourceLocation location = getProgram().getLocation();
		for(int i = 0; i < statements.size(); i++) {
			Statement statement = statements.get(i);
			SourceLocation nextStatementLocation = (i == statements.size()-1) ? null : statements.get(i+1).getLocation();
			if(i == statements.size()-1 || nextStatementLocation.getLine() > location.getLine()||(nextStatementLocation.getLine()==location.getLine()&&nextStatementLocation.getColumn()>location.getColumn())){
				statement.run();
				if(statement.NoTimeConsumed()){
					NoTimeConsumed = true;
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
			Statement statement = statements.get(i);
			Optional result = statement.run(arguments, locals);
			if (result.isPresent()) {
				if (statement.breakDiscovered()) breakDiscovered = true;
				return result;
			}
			if (statement.breakDiscovered()) {
				breakDiscovered = true;
				return Optional.empty();
			}
		}
		return Optional.empty();
	}

	//Set program for each statement
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		for(Statement statement : statements) statement.setProgram(program);
	}

	
	
}
