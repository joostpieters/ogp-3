package asteroids.model.programs;

import java.util.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.model.programs.*;
import asteroids.model.Program;

public class While extends Statement {

	private Expression<Boolean> cond;
	private Statement body;
	private boolean bodyexist;
	private boolean noTimeConsumed;
	
	public While(Expression<Boolean> condition, Statement body, SourceLocation location) {
		super(location);
		this.cond = condition;
		this.body = body;
	}

	public Expression getCondition() {
		return this.cond;
	}
	
	public void setCondition(Expression condition) {
		this.cond = condition;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	public void setBody(Statement body) {
		this.body = body;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		cond.setProgram(program);
		body.setProgram(program);
	}

	public boolean noTimeConsumed(){
		return noTimeConsumed;
	}
	
	@Override
	public void run() {
		noTimeConsumed = false;
		if(!bodyexist){
			if(cond.evaluate()) bodyexist = true;
			else return;
		}
		body.run();
		if (body.NoTimeConsumed()){
			noTimeConsumed = true;
			return;
		}
		while(cond.evaluate() && !body.breakDiscovered()){
			getProgram().setLocation(this.getLocation());
			body.run();
			if (body.NoTimeConsumed()){
				noTimeConsumed = true;
				return;
			}
		}
		bodyexist = false;
		
	}
	
	
}
